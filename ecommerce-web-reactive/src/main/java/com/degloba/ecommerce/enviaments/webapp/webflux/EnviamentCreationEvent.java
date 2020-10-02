package com.degloba.ecommerce.enviaments.webapp.webflux;

import lombok.Data;

@Data
public class EnviamentCreationEvent {
    private String enviamentId;
    private String creationTime;
    
    public EnviamentCreationEvent(String enviamentId, String creationTime) {
        super();
        this.enviamentId = enviamentId;
        this.creationTime = creationTime;
    }
}
