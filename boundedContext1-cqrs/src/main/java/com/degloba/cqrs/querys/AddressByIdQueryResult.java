package com.degloba.cqrs.querys;

import lombok.Value;

@Value
public class AddressByIdQueryResult {
    private final String addressId;

    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}