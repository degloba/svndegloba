package com.degloba.cqrs.querys;

import lombok.Value;

@Value
public class PersonByIdQuery {

    private String personId;

}