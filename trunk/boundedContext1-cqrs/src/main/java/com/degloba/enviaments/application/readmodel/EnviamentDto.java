package com.degloba.enviaments.application.readmodel;

import java.io.Serializable;

import com.degloba.casino.enviaments.EnviamentStatus;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;


@SuppressWarnings("serial")
public class EnviamentDto implements Serializable {

    private AggregateId enviamentId;
    private AggregateId ordreId;
    private EnviamentStatus status;

    public EnviamentDto(AggregateId enviamentId, AggregateId ordreId, EnviamentStatus status) {
        this.enviamentId = enviamentId;
        this.ordreId = ordreId;
        this.status = status;
    }

    public AggregateId geEnviamentId() {
        return enviamentId;
    }

    public AggregateId getOrdreId() {
        return ordreId;
    }

    public EnviamentStatus getStatus() {
        return status;
    }
}
