package com.naveen.apachecommon.controller;

import com.naveen.apachecommon.service.CSVWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.naveen.apachecommon.service.CSVWriterService.SAMPLE_CSV_FILE;

@RestController
public class CSVController {

    @Autowired
    private CSVWriterService csvWriterService;
     //localhost:9000//report/{reportName}
    @GetMapping(value = "/report/{reportName}", produces = "text/csv")
    public ResponseEntity generateReport(@PathVariable(value = "reportName") String reportName) {
        try {
            System.out.println("Inside the Controller ...");
            File file = csvWriterService.writeCSVFile();
            ResponseEntity<FileSystemResource> body = ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + reportName + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));
                     //Files.deleteIfExists(Paths.get(SAMPLE_CSV_FILE));
            return body;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to generate report: " + reportName, ex);
        }
    }

}
