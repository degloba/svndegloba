package com.degloba.persones.cqrs.querys;

import lombok.Value;

@Value
public class PersonByIdQueryResult {

    private String personId;

    private String addressId;

    private String fullName;

}
