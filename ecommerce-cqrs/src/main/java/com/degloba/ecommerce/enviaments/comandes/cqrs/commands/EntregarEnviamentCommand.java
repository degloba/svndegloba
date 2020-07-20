package com.degloba.ecommerce.enviaments.comandes.cqrs.commands;

import java.io.Serializable;

import com.degloba.cqrs.commands.annotations.ICommandAnnotation;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.AllArgsConstructor;
import lombok.Value;


/**
 * @category
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@ICommandAnnotation
@Value
@AllArgsConstructor
public class EntregarEnviamentCommand implements Serializable {

    private final AggregateId enviamentId;

}
