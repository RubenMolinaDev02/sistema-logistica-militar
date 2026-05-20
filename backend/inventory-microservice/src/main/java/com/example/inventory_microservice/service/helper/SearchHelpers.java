package com.example.inventory_microservice.service.helper;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class SearchHelpers {
    public static void addIfPresent(
            Query query,
            String field,
            Object value
    ) {

        if (value != null) {
            query.addCriteria(
                    Criteria.where(field).is(value)
            );
        }
    }
}
