package com.degloba.boundedContext.application.api.handlers;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.degloba.SpringMongoConfig;
import com.degloba.boundedContext.application.api.commands.AddModalpanelCommand;
import com.degloba.boundedContext.domain.modules.client.Empresa;
import com.degloba.boundedContext.domain.modules.client.IEmpresaRepository;
import com.degloba.boundedContext.domain.modules.modalpanel.IModalpanelRepository;
import com.degloba.boundedContext.domain.modules.modalpanel.Modalpanel;
import com.degloba.system.application.SystemContext;



// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;


@CommandHandlerAnnotation
public class AddModalpanelCommandHandler implements ICommandHandler<AddModalpanelCommand>{ 

	/*@Inject
	private ReservationRepository reservationRepository;
*/
	@Inject
	private IModalpanelRepository<Long> modalpanelRepository;
	
/*	@Inject
	private IPersonRepository<Long> personRepository;*/

	/*@Inject
	private SuggestionService suggestionService;
*/
/*	@Inject
	private IClientRepository<Long> clientRepository;

	@Inject
	private SystemContext systemContext;*/

	@Override
	public Void handle(AddModalpanelCommand command) {
		//Reservation reservation = reservationRepository.load(command.getOrderId());

		//Modalpanel modalPanel = modalpanelRepository.load(command.getModalpanelId());

		/*if (! modalPanel.isAvailabe()){
			Client client = loadClient();	
			modalPanel = suggestionService.suggestEquivalent(modalPanel, client);
		}*/

	/*	reservation.add(modalPanel, command.getQuantity());

		reservationRepository.save(reservation);*/
		
		Modalpanel modalpanel = new Modalpanel();
		//modalpanel.setAggregateId(command.getModalpanelId());
		modalpanel.setActiu(true);
		modalpanel.setDescripcio("hola");
		modalpanel.setTitol("titol hora");
		modalpanel.changeStatus(Modalpanel.ModalpanelStatus.VIP);
			
		this.modalpanelRepository.save(modalpanel);
		
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

/*	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}*/
}
