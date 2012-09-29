package com.degloba.MotorBusqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;

import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.BooleanClause.Occur;


/**
 * This class is used to search the 
 * Lucene index and return search results
 */
/**
 * Title: 
 * Description: 
 * Copyright:    Copyright (c) 2009
 * Company: Summa
 * @author Pere Santasusana
 * @version 1.0
 */

public class SearchManagerAvanzada {
	
    private List cadenasACercar;
    
    private IndexManager indexManager;
    
    private Analyzer analyzer;
    
    public SearchManagerAvanzada(List cadenasACercar){
        this.cadenasACercar   =  cadenasACercar;
        this.indexManager =  new IndexManager();
        this.analyzer     =  new StandardAnalyzer(null);
    }
    
    /**
     * do search
     * @throws IOException 
     * @throws CorruptIndexException 
     * @throws ParseException 
     */
    public List search(List condicions) throws CorruptIndexException, IOException, ParseException{
        List searchResult = new ArrayList();
      /*  if(false == indexManager.ifIndexExist()){
        try {
            if(false == indexManager.createIndex()){
                return searchResult;
            }
        } catch (IOException e) {
          e.printStackTrace();
          return searchResult;
        }
        }*/
    	
        IndexReader indexReader = IndexReader.open(indexManager.getIndexDir());

        IndexSearcher indexSearcher = null;

        try{
            indexSearcher = new IndexSearcher(indexManager.getIndexDir());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        
        Query query = null;
        
        ArrayList<Term> terms = new ArrayList<Term>();  // TERMES  
        
        // 1.- Termes a Buscar dins el camp "content"
        
        
        for (int i = 0 ; i < cadenasACercar.size(); i++)
        {
        	terms.add(new Term("content",(String)cadenasACercar.get(i)));
        }
        
        
        // CAS 1 : Boolean
        //Occur occur = new Occur(searchWord);
        BooleanQuery booleanQuery = new BooleanQuery(); 
        
        QueryParser queryParser = new QueryParser(null, "content", analyzer);
        query = queryParser.parse("java AND net NOT dot");
        BooleanClause boolClause = new BooleanClause(query, null);
        
        for (int i = 0 ; i < cadenasACercar.size(); i++)
        {
            booleanQuery.add(new TermQuery(terms.get(i)), boolClause.getOccur());
        }

       // searchResult = search2(booleanQuery);
        
        
        

        
        // CAS 2 : Fuzzy
        query = new FuzzyQuery(new Term("content", "999"));
        searchResult = search2(query);
        
        /*
        
        System.out.println("ALL SEARCHABLE TERMS");
		LinkedList termsWithPrefix = new LinkedList();
		TermEnum te = indexReader.terms();
		do {
		  if (te.term() != null) {
		    if (te.term().text() != null) {
		      System.out.println("!" + te.term().text() + "!");
		    }
		  }
		} while (te.next());


		
		
		System.out.println("SEARCHABLE TERMS FROM H to Z");
		termsWithPrefix = new LinkedList();
		te = indexReader.terms(new Term("content", "h*"));
		do {
			System.out.println(te.term().text());			
		} while (te.next());

		
		
		System.out.println("SEARCHABLE TERMS STARTING WITH D");
		termsWithPrefix = new LinkedList();
		String pattern = "d*";
		te = indexReader.terms(new Term("content", pattern));
		while (te.term().text().matches(pattern)) {
			System.out.println(te.term().text());			
			termsWithPrefix.add(te.term());
			if (te.next() == false) 
				break;
		}
		 */
		
		PhraseQuery pq = new  PhraseQuery();
	    for (int i = 0 ; i < cadenasACercar.size(); i++)
	        {	
			pq.add(new Term("content", (String)cadenasACercar.get(i)));
	        }

		//searchResult = search2(pq);
	    
	    
	    
	
		/*
		System.out.println("\n****** PrefixQuery Example ******");
		System.out.println("DOCUMENTS STARTING WITH D");
		query = new PrefixQuery(new Term("content", "d"));
		searchResult = search2(query);
		
		System.out.println("\n****** RangeQuery Example ******");
		System.out.println("DOCUMENTS FROM AAA to FFF, not inclusive");

		Term t1 = new Term("content", "aaa");
		Term t2 = new Term("content", "fff");
		query = new RangeQuery(t1, t2, false);
		searchResult = search2(query);
		
		System.out.println("\n***** TermQuery Example *****");
		System.out.println("DOCUMENTS THAT CONTAIN AAA");
		query = new TermQuery(new Term("content", "aaa"));
		searchResult = search2(query);

		System.out.println("\n***** WildcardQuery Example *****");
		System.out.println("DOCUMENTS STARTING WITH D");
		query = new WildcardQuery(new Term("content", "d*"));
		searchResult = search2(query);

		System.out.println("");
		System.out.println("DOCUMENTS ENDING WITH E");
		query = new WildcardQuery(new Term("content", "*e"));
		searchResult = search2(query);

		System.out.println("DOCUMENTS THAT CONTAIN Z");
		query = new WildcardQuery(new Term("content", "*z*"));
		searchResult = search2(query);
		
		*/

       
        return searchResult;
    }
    
    
    
    
    public List search2(Query query) throws CorruptIndexException, IOException{
        List searchResult = new ArrayList();
        
        IndexSearcher indexSearcher = null;
        QueryParser queryParser = null;
        indexSearcher = new IndexSearcher(indexManager.getIndexDir());
        
        TopDocs hits = null;
        //query = queryParser.parse(searchWord);
		/////////////////////////////query = queryParser.parse(queryString);
		   try {
			hits = indexSearcher.search(query,null,1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(null != query && null != indexSearcher){			
            try {
              
                for(int i = 0; i < hits.totalHits; i ++){
                    SearchResultBean resultBean = new SearchResultBean();
                    resultBean.setPath(hits.scoreDocs[i]..get("url").toString());
                    resultBean.setkExpedient(hits.doc(i).get("expedient"));
                    resultBean.setTitle(hits.doc(i).get("title"));
                    searchResult.add(resultBean);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return searchResult;
    }
    
    
    
}

