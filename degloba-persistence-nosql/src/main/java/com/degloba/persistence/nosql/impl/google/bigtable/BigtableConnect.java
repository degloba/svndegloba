package com.degloba.persistence.nosql.google.bigtable;

import com.google.cloud.bigtable.hbase.BigtableConfiguration;
import com.google.cloud.bigtable.hbase.BigtableOptionsFactory;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class BigtableConnect {

  public static String projectId;
  public static String instanceId;
  public static String appProfileId = "default";
  public static Connection connection = null;

  public static void main(String... args) {
    projectId = args[0];  // my-gcp-project-id
    instanceId = args[1]; // my-bigtable-instance-id

    if (args.length > 2) {
      appProfileId = args[2];    // my-bigtable-app-profile-id or default if not provided.
    }
  }

  // [START bigtable_connect]
  public static void connect() throws IOException {
    connection = BigtableConfiguration.connect(projectId, instanceId);
    
    System.out.println("--- Connection established with Bigtable Instance ---");
  }
  // [END bigtable_connect]

  // [START bigtable_connect_app_profile]
  public static void connectWithAppProfile() throws IOException {
    connection = BigtableConfiguration.connect(projectId, instanceId, appProfileId);
  }
  // [END bigtable_connect_app_profile]

  // [START bigtable_connect_with_configuration]
  public static Connection connectWithConfiguration() throws IOException {
	// Definiu la configuració de HBase amb el ID de projecte, instància ID i appProfileID opcional
	// de resources/hbase_site.xml
    Configuration config = HBaseConfiguration.create();
    connection = ConnectionFactory.createConnection(config);
    return connection;
  }
  // [END bigtable_connect_with_configuration]
}
