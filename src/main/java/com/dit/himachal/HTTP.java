package com.dit.himachal;

import com.dit.himachal.modals.VahanObject;
import com.dit.himachal.security.CryptographyAES;
import com.dit.himachal.utilities.Constants;
import com.dit.himachal.utilities.NetworkUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

public class HTTP {
    private static final Logger logger = LoggerFactory.getLogger(HTTP.class);

    CryptographyAES AES = new CryptographyAES();

    /**
     * GET Data
     */
    public VahanObject postData(VahanObject object) {
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        VahanObject returnObject  = new VahanObject();;
        try {

            conn_ = NetworkUtils.getInputStreamConnection(object.getUrl()+object.getFunction_name());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("clientId", Constants.clientId);
            if(object.getFunction_name().contains("getDetails")){
                jsonObject.addProperty("regNo", object.getParameters_to_send());  //DL4SBG8823
            }else if(object.getFunction_name().contains("chassis")){
                jsonObject.addProperty("chasisNo", object.getParameters_to_send());
            }else{
                jsonObject.addProperty("engineNo", object.getParameters_to_send());
            }

            System.out.println("JSON TO SEND==== " + jsonObject.toString());



            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            try {
                int HttpResult = conn_.getResponseCode();
                if (HttpResult != HttpURLConnection.HTTP_OK) {
                    sb = NetworkUtils.getErrorStream(conn_);
                      returnObject.setResponse(sb.toString());
                      returnObject.setSuccessFail("FALIURE");
                      returnObject.setFunction_name(object.getFunction_name());
                      returnObject.setParameters_to_send(object.getParameters_to_send());
                    return returnObject;

                } else {
                    sb = NetworkUtils.getInputStream(conn_);
                    returnObject.setResponse(AES.decryptFile(sb.toString(),Constants.securityKeyAES));
                  //  returnObject.setResponse(sb.toString());
                    returnObject.setSuccessFail("SUCCESS");
                    returnObject.setFunction_name(object.getFunction_name());
                    returnObject.setParameters_to_send(object.getParameters_to_send());
                    return returnObject;

                }
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                returnObject.setResponse(e.getLocalizedMessage());
                returnObject.setSuccessFail("FALIURE");
                returnObject.setFunction_name(object.getFunction_name());
                returnObject.setParameters_to_send(object.getParameters_to_send());
                return returnObject;
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (conn_ != null)
                conn_.disconnect();
        }
        return returnObject;
    }

}
