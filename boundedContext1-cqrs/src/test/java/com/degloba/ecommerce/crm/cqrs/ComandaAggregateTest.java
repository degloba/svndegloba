package com.degloba.ecommerce.crm.cqrs;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import com.degloba.ecommerce.crm.cqrs.commands.ComandaAggregate;
import com.degloba.ecommerce.crm.cqrs.commands.EnviarComandaCommand;
import com.degloba.ecommerce.crm.cqrs.commands.FerComandaCommand;
import com.degloba.ecommerce.crm.cqrs.events.ComandaConfirmadaEvent;
import com.degloba.ecommerce.crm.cqrs.events.ComandaEnviadaEvent;
import com.degloba.ecommerce.vendes.cqrs.events.ComandaFetaEvent;

import junit.framework.TestCase;

public class ComandaAggregateTest extends TestCase {

	private FixtureConfiguration<ComandaAggregate> fixture;
	 
	@Before
	public void setUp() {
	    fixture = new AggregateTestFixture<>(ComandaAggregate.class);
	    
	}
	
	/**
	 * @category Quan l’agregat gestiona la {@link Command} {@link FerComandaCommand}, 
	 * hauria de produir un event {@link ComandaFetaEvent}
	 */
	@Test
	public void test1() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.givenNoPriorActivity()
		  .when(new FerComandaCommand(orderId, product))
		  .expectEvents(new ComandaFetaEvent(orderId, product));
	}
	
	/**
	 * @category A continuació, podem provar la lògica de presa de decisions de només poder enviar una comanda si s'ha confirmat. 
	 * Per això tenim dos escenaris: un on esperem una excepció
	 */
	@Test
	public void test2() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.given(new ComandaFetaEvent(orderId, product))
		  .when(new EnviarComandaCommand(orderId))
		  .expectException(IllegalStateException.class);
	}
	
	/**
	 * @category  i on esperem un OrderShippedEvent.
	 * 
	 */
	@Test
	public void test3() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.given(new ComandaFetaEvent(orderId, product), new ComandaConfirmadaEvent(orderId))
		  .when(new EnviarComandaCommand(orderId))
		  .expectEvents(new ComandaEnviadaEvent(orderId));
	}
	

}


