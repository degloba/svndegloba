package com.degloba.MotorBusqueda;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


/**
 * Title: Gestor Indexos amb Lucene
 * Description: Gestor Indexos amb Lucene
 * Copyright:    Copyright (c) 2009
 * Company: Summa
 * @author Pere Santasusana
 * @version 1.0
 */

public class IndexManager {


	Directory _indexDir;

	
    /**
     * create index
     */
	public IndexWriter createIndex(String dataDir, Directory indexDir) throws IOException{
    	
	    // 0. Specify the analyzer for tokenizing text.
	     //    The same analyzer should be used for indexing and searching			
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		
		// 1. create the index
		Directory index = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
		IndexWriter indexWriter = new IndexWriter(index, config);
	    
        return indexWriter;

    }

    
    /**
     * Add one document to the Lucene index
     */
    public void addDocument(java.io.InputStream is , String pathDocument , Integer kExpedient, String numAny, IndexWriter indexWriter) throws Exception {

    	
    	Reader content = new java.io.InputStreamReader(is, "ISO-8859-1");      //afegit PERE per copiar els nous documents al READER !!!!!!!!!!!!!!!

        Document document = new Document();
        document.add(new Field("content",content));
        document.add(new Field("url",pathDocument ,Field.Store.YES,Field.Index.ANALYZED));
        document.add(new Field("expedient",kExpedient.toString() ,Field.Store.YES,Field.Index.NO));
        document.add(new Field("numAny",numAny ,Field.Store.YES,Field.Index.NO));
       
        try {
              indexWriter.addDocument(document);
              indexWriter.commit();
             ////////////// indexWriter.optimize();
        
        } catch (IOException e) {
              e.printStackTrace();
        } finally {
        	////////////////indexWriter.close();
    	}
    }


}
