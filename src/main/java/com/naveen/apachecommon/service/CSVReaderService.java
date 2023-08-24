package com.naveen.apachecommon.service;

import com.naveen.apachecommon.entity.Person;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CSVReaderService {

    private static final String SAMPLE_CSV_FILE_PATH = "./output/person.csv";

    void readCSVFile() throws IOException {
        final List<Person> personList;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            personList = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                Person person = Person.builder()
                        .id(Integer.valueOf(csvRecord.get("Id")))
                        .firstName(csvRecord.get("FirstName"))
                        .lastName(csvRecord.get("LastName"))
                        .email(csvRecord.get("Email"))
                        .gender(csvRecord.get("Gender"))
                        .age(Integer.valueOf(csvRecord.get("Age")))
                        .build();

                personList.add(person);
            }
        }

        log.info(personList);

    }

}
