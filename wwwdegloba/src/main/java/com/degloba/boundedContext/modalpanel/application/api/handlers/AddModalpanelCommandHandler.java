package com.degloba.boundedContext.modalpanel.application.api.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.modalpanel.application.api.commands.AddModalpanelCommand;
import com.degloba.boundedContext.modalpanel.domain.IModalpanelRepository;
import com.degloba.boundedContext.modalpanel.domain.Modalpanel;


// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@CommandHandlerAnnotation
public class AddModalpanelCommandHandler implements ICommandHandler<AddModalpanelCommand>{ 

	@Inject
	private IModalpanelRepository<AggregateId> modalpanelRepository;
	

	@Override
	public Void handle(AddModalpanelCommand command) {

		Modalpanel modalpanel = new Modalpanel();
		modalpanel.setActiu(true);
		modalpanel.setDescripcio("hola");
		modalpanel.setTitol("titol hora");
		modalpanel.changeStatus(Modalpanel.ModalpanelStatus.VIP);
			
		this.modalpanelRepository.persist(modalpanel);
		
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
