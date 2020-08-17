package com.dit.himachal;

import com.dit.himachal.modals.SaarthiObject;
import com.dit.himachal.modals.VahanObject;
import com.dit.himachal.security.CryptographyAES;
import com.dit.himachal.utilities.Constants;
import com.dit.himachal.utilities.NetworkUtils;
import com.dit.himachal.utilities.Utilities;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

public class HTTP {
    private static final Logger logger = LoggerFactory.getLogger(HTTP.class);

    CryptographyAES AES = new CryptographyAES();

    /**
     * Post Data
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

    /**
     * Get DATA
     */
    public SaarthiObject getSarthiData(String drivingLicence) throws IOException {
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        SaarthiObject object = null;
        byte[]   us = Base64.encodeBase64(Constants.usesr.getBytes());
        byte[]   ps = Base64.encodeBase64(Constants.password.getBytes());
        byte[]   dl = Base64.encodeBase64(drivingLicence.getBytes());

        conn_ = NetworkUtils.getSarthiInputStreamConnection(Constants.SaarthiURLL+ new String(dl)+"/"+
                new String(us)+"/"+
                new String(ps)+"");

        if (conn_.getResponseCode() != 200) {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader((conn_.getErrorStream())));
            String output;
            System.out.println("Output from Server error ....  \n");

                while ((output = br.readLine()) != null) {
                    sb.append(output + "\n");
                }
                br.close();
                System.out.println("Error==  "+ sb.toString());
                //Parse Json Here
           object = Utilities.parseJson(sb.toString());
                return object;

        }
        BufferedReader br = new BufferedReader(new
                InputStreamReader((conn_.getInputStream())));
        String output = null;
        System.out.println("Output from Server ....  \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
           // sb.append(output + "\n");

        }
        br.close();
        object = Utilities.parseJson(output);
        return object;

    }

    public SaarthiObject GetData(String drivingLicence) {
        HttpURLConnection conn_ = null;
        BufferedReader reader = null;
        SaarthiObject object = null;
        byte[]   us = Base64.encodeBase64(Constants.usesr.getBytes());
        byte[]   ps = Base64.encodeBase64(Constants.password.getBytes());
        byte[]   dl = Base64.encodeBase64(drivingLicence.getBytes());



        try {
            conn_ = NetworkUtils.getSarthiInputStreamConnection(Constants.SaarthiURLL+ new String(dl)+"/"+
                    new String(us)+"/"+
                    new String(ps)+"");

            if (conn_.getResponseCode() != 200) {
                reader = new BufferedReader(new InputStreamReader(conn_.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                conn_.disconnect();
                object = Utilities.parseJson(sb.toString());
                return object;
            } else {


                reader = new BufferedReader(new InputStreamReader(conn_.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                conn_.disconnect();
                object = Utilities.parseJson(sb.toString());
                return object;
            }

        } catch (Exception e) {
            e.printStackTrace();
            object = null;
            return object;
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    object = null;
                    return object;
                }
            }
        }
    }


}
