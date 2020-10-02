package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.HashMap;
import java.util.Map;

import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.degloba.ecommerce.enviaments.domain.persistence.nosql.mongo.Enviament;
import com.degloba.ecommerce.enviaments.domain.persistence.nosql.mongo.IEnviamentReactiveRepository;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;
import com.degloba.ecommerce.enviaments.facade.dtos.EstatEnviament;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/////public interface IEnviamentReactiveRepository extends ReactiveMongoRepository<Enviament, String> {


@Repository
public class EnviamentRepository2 implements IEnviamentReactiveRepository {
    static Map<Long,Enviament> enviamentData;

    static
    {
    	
		  enviamentData = new HashMap<>(); 
		  enviamentData.put((long) 1,new Enviament("1", "1", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 2,new Enviament("1", "2", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 3,new Enviament("1", "3", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 4,new Enviament("1", "4", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 5,new Enviament("1", "5", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 6,new Enviament("1", "6", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 7,new Enviament("1", "7", "EstatEnviament.SENT")); 
		  enviamentData.put((long) 8,new Enviament("1", "8", "EstatEnviament.SENT")); 
    }
    

      
	@Override
	public <S extends Enviament> Mono<S> insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Flux<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Flux<S> insert(Publisher<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Flux<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Flux<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Enviament> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Mono<S> save(S entity) {
    	Enviament existingEnviament=enviamentData.get(entity.getEnviamentId());
        if(existingEnviament!=null)
        {
        	//////existingEnviament.setName(enviamentDto.getName());
        }
        return (Mono<S>) Mono.just(existingEnviament);
	}

	@Override
	public <S extends Enviament> Flux<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Flux<S> saveAll(Publisher<S> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Enviament> findById(String id) {
		return Mono.just(enviamentData.get(id));
	}

	@Override
	public Mono<Enviament> findById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Enviament> findAll() {
		return Flux.fromIterable(enviamentData.values());
	}

	@Override
	public Flux<Enviament> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Enviament> findAllById(Publisher<String> idStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Enviament entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Enviament> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Enviament> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Mono<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Mono<Long> count(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Enviament> Mono<Boolean> exists(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
}
