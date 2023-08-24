package com.naveen.apachecommon;

import com.naveen.apachecommon.config.DummyData;
import com.naveen.apachecommon.entity.Person;
import com.naveen.apachecommon.repo.PersonRepository;
import com.naveen.apachecommon.service.JobManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootApplication
@EnableAspectJAutoProxy
public class ApacheCommonApplication implements CommandLineRunner{

	@Autowired
	private JobManager jobManager;

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApacheCommonApplication.class, args);
	}

	@PostConstruct
	public void run() throws IOException {
		List<Person> personList  = DummyData.getPeople();

		long personCount = personRepository.count();

		if (personCount>=50)
			log.info("Person record more than 50 no longer deletion records : "+personCount +" >= 50");
		else {
			personRepository.deleteAll();
			List<Person> personList1  = personRepository.saveAll(personList);
			if(!personList1.isEmpty()){
				log.info("List of people table is created and records are : "+personRepository.count());
			}else {
				log.info("List of people table is not created");
			}
		}



	}

	@Override
	public void run(String... args) throws Exception {
		//jobManager.startJob();
	}
}
