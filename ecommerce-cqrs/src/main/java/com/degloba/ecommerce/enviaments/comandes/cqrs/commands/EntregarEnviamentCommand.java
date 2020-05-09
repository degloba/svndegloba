package com.degloba.ecommerce.enviaments.comandes.cqrs.commands;

import java.io.Serializable;

import com.degloba.cqrs.command.annotations.ICommand;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Value;


/**
 * @category
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@ICommand
@Value
public class EntregarEnviamentCommand implements Serializable {

    private final AggregateId enviamentId;

    public EntregarEnviamentCommand(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }
}
