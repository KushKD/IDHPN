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
        Document document = new Document(new Rectangle(400f, 190f), 1f, 1f, 1f, 1f);
       // Document document = new Document(PageSize.A9.rectangle(400f, 190f),0,0,0,0);
        document.addTitle(vehicleOwnerEntries.getIdCardNumber());


        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font boldFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);


            PdfPCell cell;


            // Create a new Table
            PdfPTable childTable1 = new PdfPTable(2);
            float[] columnWidthsnested = {50f, 80f};
            childTable1.setWidths(columnWidthsnested);
            childTable1.addCell("Reg No:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getIdCardNumber(), boldFont2));

            childTable1.addCell("Name:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerName(), boldFont2));
            childTable1.addCell("Vehicle Number:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerVehicleNumber(), boldFont2));
            childTable1.addCell("Driving Licence Number:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerDrivingLicence(), boldFont2));
            childTable1.addCell("Engine Number:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerEngineNumber(), boldFont2));
            childTable1.addCell("Chassis Number:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerChassisNumber(), boldFont2));
            childTable1.addCell("Mobile Number:");
            childTable1.addCell(new Phrase(String.valueOf(vehicleOwnerEntries.getVehicleOwnerMobileNumber()), boldFont2));
            childTable1.addCell("Valid From:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getIsValidFrom(), boldFont2));
            childTable1.addCell("Valid Upto:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getIsValidUpto(), boldFont2));

            childTable1.addCell("Vehicle Driver Address:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleDriverAddress(), boldFont2));
            childTable1.addCell("Vehicle Owner on:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerAddress(), boldFont2));
            childTable1.addCell("Aadhaar Number:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getVehicleOwnerAadhaarNumber(), boldFont2));
            childTable1.addCell("Card Created on:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getCardCreation(), boldFont2));
            childTable1.addCell("Barrier Name:");
            childTable1.addCell(new Phrase(vehicleOwnerEntries.getBarriermaster().getBarrierName(), boldFont2));


            cell = new PdfPCell(childTable1);
            cell.setColspan(3);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("vehicle_number", vehicleOwnerEntries.getVehicleOwnerVehicleNumber());
            jsonObject.addProperty("mobile_number", vehicleOwnerEntries.getVehicleOwnerMobileNumber());
            jsonObject.addProperty("id_card_number", vehicleOwnerEntries.getIdCardNumber());

            //postJson
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(jsonObject.toString(), 50, 50, null);
            Image codeQrImage = barcodeQRCode.getImage();
            //codeQrImage.scalePercent(10, 10);


            Image image = Image.getInstance(new URL(Utilities.getPhotoUrl(vehicleOwnerEntries.getVehicleOwnerImageName())));
            image.scaleAbsolute(10,10);


            PdfPTable childTable2 = new PdfPTable(1);

//            float[] columnWidthsnested2 = {5f};
//
//            childTable2.setWidths(columnWidthsnested2);
            childTable2.addCell(image);
            childTable2.addCell(codeQrImage);
            childTable2.getDefaultCell().setBorder(0);


            cell = new PdfPCell(childTable2);

            cell.setColspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Shimla District Police", boldFont));
            cell.setColspan(4);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase("ID Card for Apple Season 2020 (only)", boldFont2));
            cell.setColspan(4);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("If found Please handover to Shimla Police"));
            cell.setColspan(4);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            PdfWriter.getInstance(document, out);
            document.open();

            document.add(table);

            document.close();

        } catch (DocumentException | MalformedURLException ex) {  // | MalformedURLException ex

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
