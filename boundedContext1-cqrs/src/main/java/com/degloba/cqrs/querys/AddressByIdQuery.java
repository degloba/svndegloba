package com.degloba.cqrs.querys;

import lombok.Value;

@Value
public class AddressByIdQuery {

    private final String addressId;

}