package com.example.weapon_microservice.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCriteria {
    private String field;
    private Operator operator;
    private Object value;

    enum Operator {
        EQ, GTE, LTE, REGEX, IN
    }
}
