package com.degloba.persones.facade.dtos;

import lombok.Value;

import javax.validation.constraints.NotNull;


@Value
public class AdrecaDto {

    @NotNull
    private final String carrerINumero;

    @NotNull
    private final String zipCode;

}
