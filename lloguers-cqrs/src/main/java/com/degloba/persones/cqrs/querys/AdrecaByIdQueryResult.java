package com.degloba.persones.cqrs.querys;

import lombok.Value;

@Value
public class AdrecaByIdQueryResult {
	
    private final String adrecaId;
    private final String personaId;
    private final String carrerINumero;
    private final String zipCode;

}
