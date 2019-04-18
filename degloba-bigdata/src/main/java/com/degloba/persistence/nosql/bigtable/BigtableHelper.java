package com.degloba.persistence.nosql.bigtable;

import com.google.cloud.bigtable.hbase.BigtableConfiguration;
import java.io.IOException;

import org.apache.hadoop.hbase.client.Connection;

public class BigtableHelper {

  private static String PROJECT_ID;
  private static String INSTANCE_ID;

  // The initial connection to Cloud Bigtable is an expensive operation -- We cache this Connection
  // to speed things up.  For this sample, keeping them here is a good idea, for
  // your application, you may wish to keep this somewhere else.
  private static Connection connection = null; // The authenticated connection
  
  /** Connect will establish the connection to Cloud Bigtable. */
  public static void connect() throws IOException {

    if (PROJECT_ID == null || INSTANCE_ID == null) {
      
      //  sc.log("environment variables BIGTABLE_PROJECT, and BIGTABLE_INSTANCE need to be defined.");
      
      return;
    }

    connection = BigtableConfiguration.connect(PROJECT_ID, INSTANCE_ID);
  }

  /**
   * Get the shared connection to Cloud Bigtable.
   * @return the connection
   */
  public static Connection getConnection() {
    if (connection == null) {
      try {
        connect();
      } catch (IOException e) {
        
        //  sc.log("connect ", e);
        
      }
    }
    if (connection == null) {
      
      //  sc.log("BigtableHelper-No Connection");
      
    }
    return connection;
  }

  
}
