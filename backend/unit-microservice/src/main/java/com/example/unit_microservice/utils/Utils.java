package com.example.unit_microservice.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utils {
    public static String referenceGenerator(String code){
                return code + "-" +
                        LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "-" +
                        RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    public static String generateId(String category){
        return category + "-" + UUID.randomUUID();
    }

    public static boolean validateString(String str){
        return str != null && !str.isEmpty();
    }

    public static boolean validateInt(Integer integer){
        return integer != null && integer >= 0;
    }

    public static boolean validateDouble(Double num){
        return num != null && num >= 0;
    }
}
