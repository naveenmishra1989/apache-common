package com.naveen.apachecommon.service;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Log4j2
@Service
public class JobManager {

    private static final String SAMPLE_CSV_FILE = "./output/person.csv";
    private static final String SAMPLE_XLSX_FILE = "./output/person.xlsx";

    File file = new File(SAMPLE_CSV_FILE);
    File file1 = new File(SAMPLE_XLSX_FILE);

    @Autowired
    private CSVWriterService csvWriterService;

    @Autowired
    private CSVReaderService csvReaderService;

    @Autowired
    private ExcelWriter excelWriter;

    public void startJob() {
        try {
            if (file.exists()) {
                boolean delete = file.delete();
                log.info("Existing csv lie deleted..." + delete);

            }

            if (file1.exists()) {
                boolean delete = file1.delete();
                log.info("Existing xlsx lie deleted..." + delete);

            }
            csvWriterService.writeCSVFile();
            csvReaderService.readCSVFile();
            excelWriter.xlsFileCreation();
            log.info("Job task has been completed...");
        } catch (IOException | InvalidFormatException e) {

            e.printStackTrace();
        }
    }
}
