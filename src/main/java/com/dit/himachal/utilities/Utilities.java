package com.dit.himachal.utilities;


import com.dit.himachal.modals.SaarthiObject;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilities {

    public static Date convertStringToDate(String date) throws ParseException {
        String sDate1 = date;
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
        System.out.println(sDate1 + "\t" + date1);

        return date1;
    }

    public static final String createOtpMessage(String OTP) {

        return Constants.otp_Message + OTP;
    }


    public static final String getPhotoUrl(String imageName) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageName)
                .toUriString();
        return fileDownloadUri;
    }

    public static String base64Encode(String token) {
        byte[] encodedBytes = Base64Utils.encode(token.getBytes());
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }


    public static String base64Decode(String token) {
        byte[] decodedBytes = Base64Utils.decode(token.getBytes());
        return new String(decodedBytes, Charset.forName("UTF-8"));
    }

    public static boolean ifEmptyField(String str) {
        if (str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) && !"null".equals(str.trim())
                && !str.isEmpty() && !"0".equalsIgnoreCase(str.trim()))
            return true;

        return false;
    }

    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    public static boolean positiveNegitive(Integer number) {
        if (number > 0) {
            return true;
        } else if (number < 0) {
            return false;
        } else {
            return false;
        }
    }

    public static SaarthiObject parseJson(String data){
        SaarthiObject object = null;
        if(data!=null){
            object = new SaarthiObject();
            JsonObject o = new JsonParser().parse(data).getAsJsonObject();
            System.out.println(o.toString());

            if (o.getAsJsonObject().get("name") instanceof JsonNull) {
                object.setDlLicName("");
            } else {
                object.setDlLicName(o.getAsJsonObject().get("name").getAsString());
            }

            if (o.getAsJsonObject().get("dlLicnum") instanceof JsonNull) {
                object.setDlLicNum("");
            } else {
                object.setDlLicNum(o.getAsJsonObject().get("dlLicnum").getAsString());
            }

            if (o.getAsJsonObject().get("status") instanceof JsonNull) {
                object.setDlLicStatus("");
            } else {
                object.setDlLicStatus(o.getAsJsonObject().get("status").getAsString());
            }

            if (o.getAsJsonObject().get("dlNonTransValdTill") instanceof JsonNull) {
                object.setDlNonTransValidTill("");
            } else {
                object.setDlNonTransValidTill(o.getAsJsonObject().get("dlNonTransValdTill").getAsString());
            }

            if (o.getAsJsonObject().get("errorcode") instanceof JsonNull) {
                object.setErrorCode(-1);
            } else {
                object.setErrorCode(o.getAsJsonObject().get("errorcode").getAsInt());
            }

            if (o.getAsJsonObject().get("errormsg") instanceof JsonNull) {
                object.setErrorMessage("");
            } else {
                object.setErrorMessage(o.getAsJsonObject().get("errormsg").getAsString());
            }

            if (o.getAsJsonObject().get("issuing_Authority") instanceof JsonNull) {
                object.setIssuing_authority("");
            } else {
                object.setIssuing_authority(o.getAsJsonObject().get("issuing_Authority").getAsString());
            }

            if (o.getAsJsonObject().get("dlTransValdTill") instanceof JsonNull) {
                object.setDlTransValidTill("");
            } else {
                object.setDlTransValidTill(o.getAsJsonObject().get("dlTransValdTill").getAsString());
            }


        }


        return object;
    }




}
