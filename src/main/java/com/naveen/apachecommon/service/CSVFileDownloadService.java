package com.naveen.apachecommon.service;

import com.naveen.apachecommon.entity.Person;
import com.naveen.apachecommon.repo.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@Service
public class CSVFileDownloadService {
    private static boolean flag = true;

    @Autowired
    private PersonRepository repository;
    public boolean csvConvert(HttpServletResponse response,String csvFileName) {

        response.setContentType("text/csv");
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName+".csv");
        response.setHeader(headerKey, headerValue);
        List<Person> personList = repository.findAll();
        try {
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                    CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"id", "firstName", "lastName", "email", "gender", "age"};

            csvWriter.writeHeader(header);

            for (Person person : personList) {
                csvWriter.write(person, header);
            }
        } catch (IOException e) {
           log.error("Exception occurred");
            flag = false;
        }
        return flag;
    }
}
