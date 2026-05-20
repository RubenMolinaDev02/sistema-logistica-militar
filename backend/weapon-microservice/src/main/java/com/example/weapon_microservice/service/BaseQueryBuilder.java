package com.example.weapon_microservice.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Set;

public abstract class BaseQueryBuilder<T> {

    protected abstract Set<String> getAllowedFields();

    protected Criteria buildCriteria(FilterCriteria filter) {

        return switch (filter.getOperator()) {

            case EQ ->
                    Criteria.where(filter.getField()).is(filter.getValue());

            case REGEX ->
                    Criteria.where(filter.getField())
                            .regex(filter.getValue().toString(), "i");

            case GTE ->
                    Criteria.where(filter.getField()).gte(filter.getValue());

            case LTE ->
                    Criteria.where(filter.getField()).lte(filter.getValue());

            case IN ->
                    Criteria.where(filter.getField()).in((List<?>) filter.getValue());
        };
    }

    protected void applySorting(Query query, String sortBy, String direction) {

        if (sortBy == null || !getAllowedFields().contains(sortBy)) return;

        Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        query.with(Sort.by(dir, sortBy));
    }
}