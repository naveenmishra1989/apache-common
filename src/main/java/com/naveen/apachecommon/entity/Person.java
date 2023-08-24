package com.naveen.apachecommon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;
   String firstName;
   String lastName;
   String email;
   String gender;
   Integer age;


}