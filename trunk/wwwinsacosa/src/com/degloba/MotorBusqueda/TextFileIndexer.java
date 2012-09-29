package com.degloba.MotorBusqueda;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;

/**
 * This terminal application creates an Apache Lucene index in a folder and adds files into this index
 * based on the input of the user.
 */
public class TextFileIndexer {

  private IndexWriter writer;

  public static void main(String[] args) throws IOException {
    System.out.println("Enter the path where the index will be created: ");

    BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
    String s = br.readLine();

    TextFileIndexer indexer = null;
    try {
      indexer = new TextFileIndexer(s);
    } catch (Exception ex) {
      System.out.println("Cannot create index..." + ex.getMessage());
      System.exit(-1);
    }

    //===================================================
    //read input from user until he enters q for quit
    //===================================================
    while (!s.equalsIgnoreCase("q")) {
      try {
        System.out.println("Enter the file or folder name to add into the index (q=quit):");
        System.out.println("[Acceptable file types: .xml, .html, .html, .txt]");
        s = br.readLine();
        if (s.equalsIgnoreCase("q")) {
          break;
        }

        //try to add file into the index
        indexer.indexFileOrDirectory(s);
      } catch (Exception e) {
        System.out.println("Error indexing " + s + " : " + e.getMessage());
      }
    }

    //===================================================
    //after adding, we always have to call the
    //closeIndex, otherwise the index is not created    
    //===================================================
    indexer.closeIndex();
  }

  /**
   * Constructor
   * @param indexDir the name of the folder in which the index should be created
   * @throws java.io.IOException
   */
  TextFileIndexer(String indexDir) throws IOException {
    // the boolean true parameter means to create a new index everytime, 
    // potentially overwriting any existing files there.
    FSDirectory dir = FSDirectory.open(new File(indexDir));

    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);

    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, analyzer);

    writer = new IndexWriter(dir, config);
  }

  /**
   * Indexes a file or directory
   * @param fileName the name of a text file or a folder we wish to add to the index
   * @throws java.io.IOException
   */
  public void indexFileOrDirectory(String fileName) throws IOException {
    //===================================================
    //gets the list of files in a folder (if user has submitted
    //the name of a folder) or gets a single file name (is user
    //has submitted only the file name) 
    //===================================================
    ArrayList<File> queue = listFiles(new File(fileName));
    
    int originalNumDocs = writer.numDocs();
    for (File f : queue) {
      FileReader fr = null;
      try {
        Document doc = new Document();

        //===================================================
        // add contents of file
        //===================================================
        fr = new FileReader(f);
        doc.add(new Field("contents", fr));

        //===================================================
        //adding second field which contains the path of the file
        //===================================================
        doc.add(new Field("path", fileName,
                Field.Store.YES,
                Field.Index.NOT_ANALYZED));

        writer.addDocument(doc);
        System.out.println("Added: " + f);
      } catch (Exception e) {
        System.out.println("Could not add: " + f);
      } finally {
        fr.close();
      }
    }
    
    int newNumDocs = writer.numDocs();
    System.out.println("");
    System.out.println("************************");
    System.out.println((newNumDocs - originalNumDocs) + " documents added.");
    System.out.println("************************");

    queue.clear();
  }

  private ArrayList<File> listFiles(File file) {
    ArrayList<File> queue = new ArrayList<File>();

    if (!file.exists()) {
      System.out.println(file + " does not exist.");
    }
    if (file.isDirectory()) {
      for (File f : file.listFiles()) {
        listFiles(f);
      }
    } else {
      String filename = file.getName().toLowerCase();
      //===================================================
      // Only index text files
      //===================================================
      if (filename.endsWith(".htm") || filename.endsWith(".html") || 
              filename.endsWith(".xml") || filename.endsWith(".txt")) {
        queue.add(file);
      } else {
        System.out.println("Skipped " + filename);
      }
    }
    return queue;
  }

  /**
   * Close the index.
   * @throws java.io.IOException
   */
  public void closeIndex() throws IOException {
    writer.optimize();
    writer.close();
  }
}

