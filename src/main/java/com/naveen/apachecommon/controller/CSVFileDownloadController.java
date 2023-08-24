package com.naveen.apachecommon.controller;

import com.naveen.apachecommon.entity.Person;
import com.naveen.apachecommon.repo.PersonRepository;
import com.naveen.apachecommon.service.CSVFileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CSVFileDownloadController {

    @Autowired
    private CSVFileDownloadService service;

   // localhost:9000//downloadCSV/{csvFileName}
    @RequestMapping(value = "/downloadCSV/{csvFileName}")
    public ResponseEntity<String> downloadCSV(HttpServletResponse response, @PathVariable String csvFileName) throws IOException {

        boolean csvConvert = service.csvConvert(response, csvFileName);
        if (csvConvert)
             return new ResponseEntity("Success", HttpStatus.OK);

        return new ResponseEntity("Failure", HttpStatus.NOT_FOUND);

    }
}