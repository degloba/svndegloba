package com.degloba.ecommerce.trader.webapp.reactive.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.degloba.ecommerce.trader.facade.dtos.QuoteCb;
import com.degloba.ecommerce.trader.facade.dtos.QuoteCbSmall;
import com.degloba.ecommerce.trader.webapp.reactive.utils.MongoUtils;
import com.degloba.trader.enviaments.webapp.data.PrepareData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coinbase")
public class CoinbaseController {
	
	@Autowired
	private ReactiveMongoOperations operations;

	@GetMapping("/today")
	public Flux<QuoteCbSmall> todayQuotesBc() {
		Query query = MongoUtils.buildTodayQuery(Optional.empty());
		return this.operations.find(query,QuoteCb.class)
				.filter(q -> filterEvenMinutes(q))
				.map(quote -> new QuoteCbSmall(quote.getCreatedAt(), quote.getUsd(), quote.getEur(), quote.getEth(), quote.getLtc()));
	}
	
	@GetMapping("/7days")
	public Flux<QuoteCbSmall> sevenDaysQuotesBc() {
		Query query = MongoUtils.build7DayQuery(Optional.empty());
		return this.operations.find(query,QuoteCb.class,PrepareData.CB_HOUR_COL)
				.filter(q -> filterEvenMinutes(q))
				.map(quote -> new QuoteCbSmall(quote.getCreatedAt(), quote.getUsd(), quote.getEur(), quote.getEth(), quote.getLtc()));
	}
	
	@GetMapping("/30days")
	public Flux<QuoteCbSmall> thirtyDaysQuotesBc() {
		Query query = MongoUtils.build30DayQuery(Optional.empty());
		return this.operations.find(query,QuoteCb.class,PrepareData.CB_DAY_COL)
				.filter(q -> filterEvenMinutes(q))
				.map(quote -> new QuoteCbSmall(quote.getCreatedAt(), quote.getUsd(), quote.getEur(), quote.getEth(), quote.getLtc()));
	}
	
	@GetMapping("/90days")
	public Flux<QuoteCbSmall> nintyDaysQuotesBc() {
		Query query = MongoUtils.build90DayQuery(Optional.empty());
		return this.operations.find(query,QuoteCb.class,PrepareData.CB_DAY_COL)
				.filter(q -> filterEvenMinutes(q))
				.map(quote -> new QuoteCbSmall(quote.getCreatedAt(), quote.getUsd(), quote.getEur(), quote.getEth(), quote.getLtc()));
	}
	
	@GetMapping("/current")
	public Mono<QuoteCb> currentQuoteBc() {
		Query query = MongoUtils.buildCurrentQuery(Optional.empty());
		return this.operations.findOne(query,QuoteCb.class);
	}
	
	private boolean filterEvenMinutes(QuoteCb quote) {
		return MongoUtils.filterEvenMinutes(quote.getCreatedAt());
	}
}
