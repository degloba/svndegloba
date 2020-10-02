package com.degloba.ecommerce.trader.webapp.reactive.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.trader.facade.dtos.QuoteBf;
import com.degloba.ecommerce.trader.facade.dtos.QuotePdf;
import com.degloba.ecommerce.trader.webapp.reactive.reports.ReportGenerator;
import com.degloba.ecommerce.trader.webapp.reactive.utils.MongoUtils;
import com.degloba.ecommerce.trader.webapp.reactive.utils.WebUtils;
import com.degloba.trader.enviaments.webapp.data.PrepareData;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bitfinex")
public class BitfinexController {
	private static final String URLBF = "https://api.bitfinex.com";

	@Autowired
	private ReactiveMongoOperations operations;
	
	@Autowired 
	private ReportGenerator reportGenerator;
	

	@GetMapping("/{currpair}/orderbook")
	public Mono<String> getOrderbook(@PathVariable String currpair, HttpServletRequest request) {		
		WebClient wc = WebUtils.buildWebClient(URLBF);
		return wc.get().uri("/v1/book/" + currpair + "/").accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(res -> res.bodyToMono(String.class));
	}

	@GetMapping("/{pair}/current")
	public Mono<QuoteBf> currentQuote(@PathVariable String pair) {
		Query query = MongoUtils.buildCurrentQuery(Optional.of(pair));
		return this.operations.findOne(query, QuoteBf.class);
	}

	@GetMapping("/{pair}/{timeFrame}")
	public Flux<QuoteBf> tfQuotes(@PathVariable String timeFrame, @PathVariable String pair) {		
		if (MongoUtils.TimeFrame.TODAY.getValue().equals(timeFrame)) {
			Query query = MongoUtils.buildTodayQuery(Optional.of(pair));
			return this.operations.find(query, QuoteBf.class).filter(q -> filterEvenMinutes(q));
		} else if (MongoUtils.TimeFrame.SEVENDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build7DayQuery(Optional.of(pair));
			return this.operations.find(query, QuoteBf.class, PrepareData.BF_HOUR_COL);
		} else if (MongoUtils.TimeFrame.THIRTYDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build30DayQuery(Optional.of(pair));
			return this.operations.find(query, QuoteBf.class, PrepareData.BF_DAY_COL);
		} else if (MongoUtils.TimeFrame.NINTYDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build90DayQuery(Optional.of(pair));
			return this.operations.find(query, QuoteBf.class, PrepareData.BF_DAY_COL);
		}

		return Flux.empty();
	}
	
	@GetMapping(path="/{pair}/{timeFrame}/pdf", produces=MediaType.APPLICATION_PDF_VALUE)
	public Mono<byte[]> pdfReport(@PathVariable String timeFrame, @PathVariable String pair) {
		if (MongoUtils.TimeFrame.TODAY.getValue().equals(timeFrame)) {
			Query query = MongoUtils.buildTodayQuery(Optional.of(pair));
			return this.reportGenerator.generateReport(this.operations.find(query, QuoteBf.class).filter(this::filter10Minutes).map(this::convert));
		} else if (MongoUtils.TimeFrame.SEVENDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build7DayQuery(Optional.of(pair));
			return this.reportGenerator.generateReport(this.operations.find(query, QuoteBf.class, PrepareData.BF_HOUR_COL).map(this::convert));
		} else if (MongoUtils.TimeFrame.THIRTYDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build30DayQuery(Optional.of(pair));
			return this.reportGenerator.generateReport(this.operations.find(query, QuoteBf.class, PrepareData.BF_DAY_COL).map(this::convert));
		} else if (MongoUtils.TimeFrame.NINTYDAYS.getValue().equals(timeFrame)) {
			Query query = MongoUtils.build90DayQuery(Optional.of(pair));
			return this.reportGenerator.generateReport(this.operations.find(query, QuoteBf.class, PrepareData.BF_DAY_COL).map(this::convert));
		}
		
		return Mono.empty();
	}

	private QuotePdf convert(QuoteBf quote) {
		QuotePdf quotePdf = new QuotePdf(quote.getLast_price(), quote.getPair(), quote.getVolume(), quote.getCreatedAt(), quote.getBid(), quote.getAsk());		
		return quotePdf;
	}
	
	private boolean filterEvenMinutes(QuoteBf quote) {
		return MongoUtils.filterEvenMinutes(quote.getCreatedAt());
	}
	
	private boolean filter10Minutes(QuoteBf quote) {
		return MongoUtils.filter10Minutes(quote.getCreatedAt());
	}	
}
