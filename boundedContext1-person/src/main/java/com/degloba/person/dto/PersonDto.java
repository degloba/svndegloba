package com.degloba.person.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class PersonDto {

    @NotNull
    private final String fullName;

}
