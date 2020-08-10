package com.dit.himachal.externalservices;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DrivingLicence {

    public static void main(String args[]) throws IOException {


        String dlnumber = "AP26 19860001659";
        byte[]   dl = Base64.encodeBase64(dlnumber.getBytes());

        String usesr = " HP_OnlineSewa";
        byte[]   us = Base64.encodeBase64(usesr.getBytes());
        String password ="02a1faeb78fcca5f13243d13094350e4";
        byte[]   ps = Base64.encodeBase64(password.getBytes());

        URL url = new
                URL("http://164.100.94.236:8080/sarathiReport/rsServices/sarathi/service/getLicenseData/"+
                new String(dl)+"/"+
                new String(us)+"/"+
                new String(ps)+"");
        System.out.println(url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new
                InputStreamReader((conn.getInputStream())));
        String output; System.out.println("Output from Server ....  \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

    }







}
