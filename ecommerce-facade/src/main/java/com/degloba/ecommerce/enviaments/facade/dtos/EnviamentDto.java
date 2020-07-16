package com.degloba.ecommerce.enviaments.facade.dtos;

import java.io.Serializable;

import com.degloba.ecommerce.enviaments.domain.enums.EstatEnviament;

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

    private long enviamentId;
    private long comandaId;
    private EstatEnviament estatEnviament;

}
