package com.naveen.apachecommon.repo;
import com.naveen.apachecommon.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
   Optional<List<Person>> findAllByAge(Integer age);
}
