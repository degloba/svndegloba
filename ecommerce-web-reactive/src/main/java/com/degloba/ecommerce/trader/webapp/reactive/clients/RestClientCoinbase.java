package com.degloba.ecommerce.trader.webapp.reactive.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.trader.facade.dtos.QuoteCb;
import com.degloba.ecommerce.trader.facade.dtos.WrapperCb;
import com.fasterxml.jackson.databind.ObjectMapper;


import reactor.core.publisher.Mono;

public class RestClientCoinbase {
	private static final String URL = "https://api.coinbase.com/v2";
	
	public static void main(String[] args) {
//		RestClientCoinbase client = new RestClientCoinbase();
//		client.testIt();
		WebClient wc = WebClient.create(URL);		
		QuoteCb quoteCb = wc.get().uri("/exchange-rates?currency=BTC")
                .accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .flatMap(response ->	response.bodyToMono(WrapperCb.class))
                .flatMap(resp -> Mono.just(resp.getData()))
                .flatMap(resp2 -> Mono.just(resp2.getRates()))
                .block();		
		System.out.println(quoteCb.toString());
		
	}		
	
	   private void testIt(){

		      String https_url = "https://api.coinbase.com/v2/exchange-rates?currency=BTC";
		      URL url;
		      try {

			     url = new URL(https_url);
			     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();			     

			     //dump all the content
			     print_content(con);

		      } catch (MalformedURLException e) {
			     e.printStackTrace();
		      } catch (IOException e) {
			     e.printStackTrace();
		      }

		   }
	   
	   private void print_content(HttpsURLConnection con){
		   ObjectMapper mapper = new ObjectMapper();
		   if(con!=null){

			try {

			   System.out.println("****** Content of the URL ********");
			   BufferedReader br =
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));

			   String input;

			   while ((input = br.readLine()) != null){
			      if(!input.matches("Content")) {
			    	  WrapperCb w = mapper.readValue(input, WrapperCb.class);
			    	  System.out.println(w.getData().getRates().getUsd());
			      }
				   System.out.println(input);
			   }
			   br.close();

			} catch (IOException e) {
			   e.printStackTrace();
			}

		       }

		   }
}