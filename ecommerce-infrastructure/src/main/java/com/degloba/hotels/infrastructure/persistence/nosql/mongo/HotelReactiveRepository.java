package com.degloba.hotels.infrastructure.persistence.nosql.mongo;

import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.hotels.Hotel;
import com.degloba.hotels.domain.persistence.nosql.mongo.IHotelReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class HotelReactiveRepository implements IHotelReactiveRepository {

	@Override
	public Flux<Hotel> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Hotel arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Hotel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Hotel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Hotel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Hotel> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Hotel> findAllById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Hotel> findById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Hotel> findById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Mono<S> save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> saveAll(Publisher<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Mono<Long> count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Mono<Boolean> exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Mono<S> findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Mono<S> insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> insert(Publisher<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Hotel> Flux<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	