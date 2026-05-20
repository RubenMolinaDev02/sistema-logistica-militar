package com.example.weapon_microservice.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuerySecurityValidator {

    private final Set<String> allowedFields;
    private final Map<String, Set<String>> fieldOperators;
    private final Map<String, Class<?>> fieldTypes;

    public QuerySecurityValidator(
            Set<String> allowedFields,
            Map<String, Set<String>> fieldOperators,
            Map<String, Class<?>> fieldTypes
    ) {
        this.allowedFields = allowedFields;
        this.fieldOperators = fieldOperators;
        this.fieldTypes = fieldTypes;
    }

    public void validate(FilterCriteria filter) {

        validateField(filter);
        validateOperator(filter);
        validateType(filter);
    }

    private void validateField(FilterCriteria filter) {
        if (!allowedFields.contains(filter.getField())) {
            throw new IllegalArgumentException("Field not allowed: " + filter.getField());
        }
    }

    private void validateOperator(FilterCriteria filter) {
        Set<String> allowedOps = fieldOperators.get(filter.getField());

        if (allowedOps == null || !allowedOps.contains(filter.getOperator().name())) {
            throw new IllegalArgumentException(
                    "Operator not allowed for field: " + filter.getField()
            );
        }
    }

    private void validateType(FilterCriteria filter) {

        Object value = filter.getValue();
        if (value == null) return;

        Class<?> expected = fieldTypes.get(filter.getField());
        if (expected == null) return;

        // Number validation
        if (expected == Number.class && !(value instanceof Number)) {
            throw new IllegalArgumentException("Expected numeric value for " + filter.getField());
        }

        // String validation
        if (expected == String.class && !(value instanceof String)) {
            throw new IllegalArgumentException("Expected string value for " + filter.getField());
        }

        // IN must be list
        if (filter.getOperator() == FilterCriteria.Operator.IN
                && !(value instanceof List<?>)) {
            throw new IllegalArgumentException("IN operator requires a list");
        }
    }
}