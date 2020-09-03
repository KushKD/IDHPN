package com.dit.himachal.utilities;

import com.dit.himachal.apicontroller.API;
import com.dit.himachal.entities.VehicleOwnerEntries;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.awt.print.PageFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    //mvn package
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);


    public static ByteArrayInputStream generateIdCard(VehicleOwnerEntries data) throws JsonProcessingException {
        VehicleOwnerEntries vehicleOwnerEntries = null;
        ObjectMapper objectMapper = new ObjectMapper();

        vehicleOwnerEntries = data;
        String postJson = objectMapper.writeValueAsString(vehicleOwnerEntries);
        Document document = new Document(PageSize.A4, 1, 1, 1, 1);
        document.addTitle(vehicleOwnerEntries.getIdCardNumber());


        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font boldFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

        try {

            PdfPTable parent = new PdfPTable(2);
            float[] columnWidthsnestedparent = {50f, 50f};
            parent.setWidths(columnWidthsnestedparent);
            parent.setWidthPercentage(100);


            // Create a Simple table
            PdfPTable one = new PdfPTable(2);
            float[] columnWidthsnested = {70f, 30f};
            one.setWidths(columnWidthsnested);
            one.getDefaultCell().setBorder(0);

            PdfPCell cell = new PdfPCell(new Phrase("Shimla District Police", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);


            cell = new PdfPCell(new Phrase("ID Card for Apple Season 2020 (only)", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);

            cell = new PdfPCell(new Phrase(vehicleOwnerEntries.getIdCardNumber(), boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);



            // Create a new Table
            PdfPTable childTable1 = new PdfPTable(2);
            float[] y = {40f, 60f};
            childTable1.setWidths(y);
           // childTable1.addCell(new Phrase("Reg No:", boldFont2));
           // childTable1.addCell(new Phrase(vehicleOwnerEntries.getIdCardNumber(), boldFont2));


            childTable1.addCell(new Phrase("Name:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerName(), boldFont2));
            childTable1.addCell(new Phrase("Vehicle Number:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerVehicleNumber(), boldFont2));
            childTable1.addCell(new Phrase("Driving Licence Number:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerDrivingLicence(), boldFont2));
            childTable1.addCell(new Phrase("Engine Number:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerEngineNumber(), boldFont2));
            childTable1.addCell(new Phrase("Chassis Number:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerChassisNumber(), boldFont2));
            childTable1.addCell(new Phrase("Mobile Number:", boldFont2));
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getVehicleOwnerMobileNumber()), boldFont2));
            childTable1.addCell(new Phrase("Valid From:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getIsValidFrom(), boldFont2));
            childTable1.addCell(new Phrase("Valid Upto:", boldFont2));
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getIsValidUpto(), boldFont2));


            Image image = Image.getInstance(new URL(Utilities.getPhotoUrl(vehicleOwnerEntries.getVehicleOwnerImageName())));
            PdfPTable childTable2 = new PdfPTable(1);
            childTable2.addCell(image);

            one.addCell(childTable1);
            one.addCell(childTable2);

            //One Ends

            //Two
            // Create a Simple table
            PdfPTable two = new PdfPTable(2);
            float[] columnWidthsnestedtwo = {70f, 30f};
            two.setWidths(columnWidthsnestedtwo);
            two.getDefaultCell().setBorder(0);


            // Create a new Table
            PdfPTable childTable1two = new PdfPTable(2);
            float[] x = {40f, 60f};
            childTable1two.setWidths(x);
            childTable1two.addCell(new Phrase("Vehicle Driver Address:",boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getVehicleDriverAddress(), boldFont2));
            childTable1two.addCell(new Phrase("Vehicle Owner Address:",boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerAddress(), boldFont2));
            childTable1two.addCell(new Phrase("Aadhaar Number:",boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerAadhaarNumber(), boldFont2));
            childTable1two.addCell(new Phrase("Card Created on:",boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getCardCreation(), boldFont2));
            childTable1two.addCell(new Phrase("Barrier Name:",boldFont2));
            childTable1two.addCell(new Phrase(vehicleOwnerEntries.getBarriermaster().getBarrierName(), boldFont2));

            JsonObject jsonObjecttwo = new JsonObject();
            jsonObjecttwo.addProperty("vehicle_number", vehicleOwnerEntries.getVehicleOwnerVehicleNumber());
            jsonObjecttwo.addProperty("mobile_number", vehicleOwnerEntries.getVehicleOwnerMobileNumber());
            jsonObjecttwo.addProperty("id_card_number", vehicleOwnerEntries.getIdCardNumber());

            //postJson
            BarcodeQRCode barcodeQRCodetwo = new BarcodeQRCode(jsonObjecttwo.toString(), 50, 50, null);
            Image codeQrImagetwo = barcodeQRCodetwo.getImage();
            PdfPTable childTable2two = new PdfPTable(1);
            childTable2two.addCell(codeQrImagetwo);

            two.addCell(childTable1two);
            two.addCell(childTable2two);
            //Two Ends

            cell = new PdfPCell(new Phrase("If found Please handover to Shimla Police",boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            two.addCell(cell);

            parent.addCell(one);
            parent.addCell(two);


            PdfWriter.getInstance(document, out);
            document.open();

            document.add(parent);

            document.close();

        } catch (DocumentException | MalformedURLException ex) {  // | MalformedURLException ex

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
