package com.dit.himachal.utilities;

import com.dit.himachal.entities.VehicleOwnerEntries;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFileExporter {
    public static ByteArrayInputStream contactListToExcelFile(List<VehicleOwnerEntries> customers) {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Report_ID_Card");

            // Create a new font and alter it.
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short)14);
            font.setFontName("Ariel");
            font.setBold(true);

            Font fontCell = workbook.createFont();
            fontCell.setFontHeightInPoints((short)12);
            fontCell.setFontName("Ariel");
            fontCell.setBold(true);


            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerCellStyle.setBorderBottom(BorderStyle.DOTTED);
            headerCellStyle.setBorderLeft(BorderStyle.DOTTED);
            headerCellStyle.setBorderRight(BorderStyle.DOTTED);
            headerCellStyle.setBorderTop(BorderStyle.DOTTED);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setFont(font);
            ;
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header

            Cell cell = row.createCell(0);
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setBorderBottom(BorderStyle.DOTTED);
            style.setBorderLeft(BorderStyle.DOTTED);
            style.setBorderRight(BorderStyle.DOTTED);
            style.setBorderTop(BorderStyle.DOTTED);
            cell.setCellValue("S.No");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);


            cell = row.createCell(1);
            cell.setCellValue("ID Card Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(2);
            cell.setCellValue("District Name");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(3);
            cell.setCellValue("Barrier Name");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(4);
            cell.setCellValue("Vehicle Type");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(5);
            cell.setCellValue("Owner Type");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(6);
            cell.setCellValue("Name");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(7);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(8);
            cell.setCellValue("VehicleOwner Address");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(9);
            cell.setCellValue("Mobile Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(10);
            cell.setCellValue("Aadhaar Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(11);
            cell.setCellValue("Vehicle Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(12);
            cell.setCellValue("Engine Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(13);
            cell.setCellValue("Chassis Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(14);
            cell.setCellValue("Driving Licence Number");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(15);
            cell.setCellValue("Card Created On ");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);


            // Creating data rows for each customer
            for(int i = 0; i < customers.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(i+1);
                dataRow.createCell(1).setCellValue(customers.get(i).getIdCardNumber());
                dataRow.createCell(2).setCellValue(customers.get(i).getDistrictMaster().getDistrictName());
                dataRow.createCell(3).setCellValue(customers.get(i).getBarriermaster().getBarrierName());
                dataRow.createCell(4).setCellValue(customers.get(i).getVehicleType().getVehicleName());
                dataRow.createCell(5).setCellValue(customers.get(i).getVehicleUser().getVehicleOwnerTypeName());
                dataRow.createCell(6).setCellValue(customers.get(i).getVehicleOwnerName());
                dataRow.createCell(7).setCellValue(customers.get(i).getVehicleDriverAddress());
                dataRow.createCell(8).setCellValue(customers.get(i).getVehicleOwnerAddress());
                dataRow.createCell(9).setCellValue(customers.get(i).getVehicleOwnerMobileNumber());
                dataRow.createCell(10).setCellValue(customers.get(i).getVehicleOwnerAadhaarNumber());
                dataRow.createCell(11).setCellValue(customers.get(i).getVehicleOwnerVehicleNumber());
                dataRow.createCell(12).setCellValue(customers.get(i).getVehicleOwnerEngineNumber());
                dataRow.createCell(13).setCellValue(customers.get(i).getVehicleOwnerChassisNumber());
                dataRow.createCell(14).setCellValue(customers.get(i).getVehicleOwnerDrivingLicence());
                dataRow.createCell(15).setCellValue(customers.get(i).getCardCreation());
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
