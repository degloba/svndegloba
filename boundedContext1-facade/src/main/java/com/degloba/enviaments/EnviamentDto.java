package com.degloba.ecommerce.enviaments.cqrs.readmodel.dtos;

import java.io.Serializable;

import com.degloba.ecommerce.enviaments.domain.EstatEnviament;

/**
 * 
 * @author degloba
 *
 * @category Data Transfer Object (DTO) d'una entitat {@link Enviament}
 */
@SuppressWarnings("serial")
public class EnviamentDto implements Serializable {

    private long enviamentId;
    private long comandaId;
    private EstatEnviament status;

    public EnviamentDto(long enviamentId, long comandaId, EstatEnviament status) {
        this.enviamentId = enviamentId;
        this.comandaId = comandaId;
        this.status = status;
    }

    public long getEnviamentId() {
        return enviamentId;
    }

    public long getComandaId() {
        return comandaId;
    }

    public EstatEnviament getStatus() {
        return status;
    }
}
