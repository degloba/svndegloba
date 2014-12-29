package com.degloba.boundedContext.casino.domain;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;


public class CasinoClasseService implements ICasinoClasseService {

	ICasinoClasseRepository _repositori;
	

	public CasinoClasseService() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CasinoClasseService(ICasinoClasseRepository _repositori) {
		super();
		this._repositori = _repositori;
	}
	
	@Override
	public IEntityService<BaseAggregateRoot> createService() {
		// 
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cconfigurationContext.xml");
		
	
		//ApplicationContext ctx =   new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		//MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		
		ICasinoClasseRepository repositori = (ICasinoClasseRepository) applicationContext.getBean("casinoRepository");
		
		IRepository<BaseAggregateRoot> rep = repositori.CreateRepository();
		return new EntityCasinoService<BaseAggregateRoot>(rep);
	}

	
	public ICasinoClasseRepository get_repositori() {
		return _repositori;
	}
	
}
