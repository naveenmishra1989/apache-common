package com.naveen.apachecommon.service;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import static com.naveen.apachecommon.service.CSVWriterService.SAMPLE_CSV_FILE;

@Aspect
@Component
public class Myemployeeaspect {

    @AfterReturning(value= "execution(* com.naveen.apachecommon.controller.CSVController.generateReport(..))",returning= "result")
    public void afterReturningAdvice(JoinPoint joinPoint, ResponseEntity result) throws IOException {
        System.out.println("Inside afterReturning() method...." + " Inserted after= " + joinPoint.getSignature().getName() + " method");

    }
}