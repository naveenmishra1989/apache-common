package com.naveen.apachecommon;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFile {
    public static void main(String[] args) throws IOException {


        System.out.println(DateTimeFormatter.ofPattern("MMM").parse("Jan").get(ChronoField.MONTH_OF_YEAR));
    }
}

