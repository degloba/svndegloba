package com.degloba.persones.facade.dtos;

import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author degloba
 *
 * @category DTO persona
 */
@Value
public class PersonaDto {

    @NotNull
    private final String nomComplert;

}
