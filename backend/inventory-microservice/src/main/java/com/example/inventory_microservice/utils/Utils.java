package com.example.inventory_microservice.utils;

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
}
