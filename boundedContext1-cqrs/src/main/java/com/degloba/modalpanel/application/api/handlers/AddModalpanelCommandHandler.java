package com.degloba.modalpanel.application.api.handlers;

import javax.inject.Inject;

// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;

import com.degloba.casino.modalpanel.IModalpanelRepository;
import com.degloba.casino.modalpanel.Modalpanel;

import com.degloba.modalpanel.application.api.commands.AddModalpanelCommand;


@CommandHandlerAnnotation
public class AddModalpanelCommandHandler implements ICommandHandler<AddModalpanelCommand>{ 

	@Inject
	private IModalpanelRepository<Long> modalpanelRepository;
	

	@Override
	public Void handle(AddModalpanelCommand command) {

		Modalpanel modalpanel = new Modalpanel();
		modalpanel.setActiu(true);
		modalpanel.setDescripcio("hola");
		modalpanel.setTitol("titol hora");
		modalpanel.changeStatus(Modalpanel.ModalpanelStatus.VIP);
			
		this.modalpanelRepository.persist(modalpanel);
		
		
		Modalpanel p = this.modalpanelRepository.load(Modalpanel.class, 1);
		
				
/*		Person person = new Person();
		person.setFirstName("pere");
		person.setLastName("cots");
		person.setLevel(0);
		*/
		
		/*ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		mongoOperation.save(person);*/
		
		//////////this.personRepository.save(person);
		
		return null;

	}

}
