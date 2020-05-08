package com.degloba.ecommerce.vendes.infrastructure.persistence.nosql.mongo;

import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class VendesReactiveRepository implements ReactiveMongoRepository<Compra, Long> {

	@Override
	public Flux<Compra> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Compra arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Compra> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Compra> arg0) {
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
	public Flux<Compra> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Compra> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Compra> findAllById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Compra> findById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Compra> findById(Publisher<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Mono<S> save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> saveAll(Publisher<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Mono<Long> count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Mono<Boolean> exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Mono<S> findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Mono<S> insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> insert(Publisher<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Compra> Flux<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	