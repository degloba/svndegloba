package com.degloba.eventsourcing.persona;

import lombok.Value;

/**
 * 
 * @author degloba
 *
 * @category Event
 */
@Value
public class PersonaCreadaEvent {

    private final String personaId;

    private final String nomComplet;

}
