package com.degloba.ecommerce.enviaments.cqrs.writemodel.commands;

import java.io.Serializable;

import com.degloba.cqrs.command.annotations.ICommand;
import com.degloba.persistence.rdbms.jpa.AggregateId;

import lombok.Value;


/**
 * @category fer l'entrega d'un enviament
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@ICommand
@Value
public class EntregaEnviamentCommand implements Serializable {

    private final AggregateId enviamentId;

    public EntregaEnviamentCommand(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

}
