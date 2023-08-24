package com.naveen.apachecommon.service;

import com.naveen.apachecommon.entity.Person;
import com.naveen.apachecommon.repo.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Log4j2
@Service
public class ExcelWriter {

    private static final String SAMPLE_XLSX_FILE = "./output/person.xlsx";
    private static final String[] columns = {"ID", "FirstName", "LastName", "Email", "Gender", "Age"};
    @Autowired
    private PersonRepository repository;

    public void xlsFileCreation() throws IOException,
            InvalidFormatException {
        List<Person> personList = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Person");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 28);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Other rows and cells with Person data
            int rowNum = 1;

            for (Person person : personList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(person.getId());
                row.createCell(1).setCellValue(person.getFirstName());
                row.createCell(2).setCellValue(person.getLastName());
                row.createCell(3).setCellValue(person.getEmail());
                row.createCell(4).setCellValue(person.getGender());
                row.createCell(5).setCellValue(person.getAge());
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            // Write the output to a file
            try {
                FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE);
                workbook.write(fileOut);
                log.info("File is created successfully ...");
            } catch (Exception e) {
                log.error("Exception in file creation...");
            }
        }


    }

}