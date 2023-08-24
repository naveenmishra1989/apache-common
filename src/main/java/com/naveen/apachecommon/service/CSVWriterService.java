package com.naveen.apachecommon.service;

import com.naveen.apachecommon.entity.Person;
import com.naveen.apachecommon.repo.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
@Service
public class CSVWriterService {

    @Autowired
    private PersonRepository repository;

    public static final String SAMPLE_CSV_FILE = "./output/person.csv";

    public File writeCSVFile() throws IOException {

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "FirstName", "LastName", "Email", "Gender", "Age"));
        ) {
            List<Person> personList = repository.findAll();
            if (!personList.isEmpty()) {
                for (Person person : personList) {
                    csvPrinter.printRecord(person.getId(), person.getFirstName(), person.getLastName(),person.getEmail(), person.getGender(),person.getAge());
                }
            } else {
                throw new RuntimeException("Person List has empty record...");

            }

           log.info("CSV file has been created .....");
            csvPrinter.flush();
        }
        File file = new File(SAMPLE_CSV_FILE);
        return file;
    }

}
