package com.degloba.cqrs.querys;

import lombok.Value;

@Value
public class PersonByIdQueryResult {

    private String personId;

    private String addressId;

    private String fullName;

}
