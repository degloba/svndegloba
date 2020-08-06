package com.degloba.ecommerce.enviaments.facade.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

/**
 * 
 * @author degloba
 *
 * @category Data Transfer Object (DTO) d'una entitat {@link Enviament}
 */
@SuppressWarnings("serial")
@Value
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class EnviamentDto implements Serializable {

    private int enviamentId;
    private int comandaId;
    private EstatEnviament estatEnviament;

}
