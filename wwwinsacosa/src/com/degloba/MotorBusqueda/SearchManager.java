package com.degloba.MotorBusqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;

import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.util.Version;


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

public class SearchManager {
	
    private String searchWord;
    
    private IndexManager indexManager;
    
    private Analyzer analyzer;
    
    String _indexDir;
    
    public SearchManager(String string, String searchWord){
        this.searchWord   =  searchWord;
        this.indexManager =  new IndexManager();
        
        //this.analyzer     =  new StandardAnalyzer();
        this.analyzer     =  new AnalizerPersonalitzat();
        
        _indexDir = string;
    }
    
    /**
     * do search
     */
    public List search(){
        List searchResult = new ArrayList();
  
    	
        IndexSearcher indexSearcher = null;
        
        
     // 2. query
        String querystr = args.length > 0 ? args[0] : "lucene";
        
        // the "title" arg specifies the default field to use
         // when no field is explicitly specified in the query.
         Query q = new QueryParser(Version.LUCENE_35, "title", analyzer).parse(querystr);
     
        
      // 3. search
         
        
        int hitsPerPage = 10;
        IndexSearcher searcher = new IndexSearcher(index);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
    
        
        
        

        try{
            indexSearcher = new IndexSearcher(_indexDir);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        QueryParser queryParser = new QueryParser(null, "content",analyzer);
        Query query = null;
        try {
            query = queryParser.parse(searchWord);
        } catch (ParseException e) {
          e.printStackTrace();
        }
        
        
     // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
          int docId = hits[i].doc;
          Document d = searcher.doc(docId);
          System.out.println((i + 1) + ". " + d.get("title"));
        }
    
     // searcher can only be closed when there
        // is no need to access the documents any more. 
       searcher.close();
    
       
       
        
        if(null != query && null != indexSearcher){			
            try {
                hits = indexSearcher.search(query, null);
                for(int i = 0; i < hits.length; i ++){
                    SearchResultBean resultBean = new SearchResultBean();
                    
                    // SON ELS CAMPS PELS QUALS CERQUEM
                    resultBean.setPath(hits.doc(i).get("url").toString());
                    resultBean.setkExpedient(hits.doc(i).get("expedient"));
                    resultBean.setNumAny(hits.doc(i).get("numAny"));
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

