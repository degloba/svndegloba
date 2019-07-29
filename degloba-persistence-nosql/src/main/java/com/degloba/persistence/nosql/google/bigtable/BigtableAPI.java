package com.degloba.persistence.nosql.google.bigtable;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.api.gax.rpc.NotFoundException;
import com.google.api.gax.rpc.ServerStream;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminClient;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminSettings;
import com.google.cloud.bigtable.admin.v2.models.CreateTableRequest;
import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.BigtableDataSettings;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.RowCell;
import com.google.cloud.bigtable.data.v2.models.RowMutation;

/**
 * Aplicació que connecta a Cloud Bigtable utilitzant l'API nativa HBase.
 * Realitza algunes operacions bàsiques.
 */
public class BigtableAPI {
	
	  private static final String COLUMN_FAMILY = "cf1";
	  private static final String COLUMN_QUALIFIER = "greeting";
	  private static final String ROW_KEY_PREFIX = "rowKey";
	  private final String tableId;
	  private final BigtableDataClient dataClient;
	  private final BigtableTableAdminClient adminClient;

  // Refer to table metadata names by byte array in the HBase API
  private static final byte[] TABLE_NAME = Bytes.toBytes("Hello-Bigtable");
  private static final byte[] COLUMN_FAMILY_NAME = Bytes.toBytes("cf1");
  private static final byte[] COLUMN_NAME = Bytes.toBytes("greeting");
  
  final static Log LOG = LogFactory.getLog(BigtableAPI.class);

  // Write some friendly greetings to Cloud Bigtable
  private static final String[] GREETINGS = {
    "Hello World!", "Hello Cloud Bigtable!", "Hello HBase!"
  };

  /**
   * Create a table -- first time only.
   * @param connection to Bigtable
   * @return the status
   */
  public Boolean createTable(Connection connection) {
    try {
      // L’API d’administració ens permet crear, gestionar i eliminar taules
      Admin admin = connection.getAdmin();

      // Crea una taula amb una sola família de columnes
      HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
      descriptor.addFamily(new HColumnDescriptor(COLUMN_FAMILY_NAME));

      admin.createTable(descriptor);
      // [END creating_a_table]
    } catch (IOException e) {
      return false;
    }
    return true;
  }
  
  /**
   * 
   * @param tableName
   * @param conf
   * @param columnFamilies
   * @throws IOException
   */
  public static void createTable(TableName tableName, Configuration conf,
      List<String> columnFamilies) throws IOException {
	  
    LOG.info("Creating Table " + tableName);
    
    Connection connection = BigtableConnect.connectWithConfiguration();  //ConnectionFactory.createConnection(conf);
    Admin admin = null;
    try {
      admin = connection.getAdmin();
      if (tableExists(tableName, admin)) {
        LOG.info("Table " + tableName + " already exists");
      } else {
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
        for (String columnFamily : columnFamilies) {
          tableDescriptor.addFamily(new HColumnDescriptor(columnFamily));
        }

        // NOTE: Anviltop createTable is synchronous while HBASE creation is not.
        admin.createTable(tableDescriptor);
      }
    } catch (Exception e) {
      LOG.error("Could not create table " + tableName, e);
    } finally {
      try {
        admin.close();
      } catch (Exception e) {
        LOG.error("Could not close the admin", e);
      }
      connection.close();
    }
  }

  private static boolean tableExists(TableName tableName, Admin admin)  {
    try {
      return admin.tableExists(tableName);
    } catch (Exception e) {
      LOG.error("Could not figure out if table " + tableName + " exists.", e);
      return false;
    } finally {
    }
  }

  /** Connects to Cloud Bigtable, runs some basic operations and prints the results. */
  public void Add() {
    
    // Create the Bigtable connection, use try-with-resources to make sure it gets closed
    Connection connection = BigtableConnect.connection;
        
    try (Table table = connection.getTable(TableName.valueOf(TABLE_NAME))) {

      // Retrieve the table we just created so we can do some reads and writes
      // Write some rows to the table
    
      for (int i = 0; i < GREETINGS.length; i++) {
        // Cada fila té una clau de fila única.
        //
        // Note: This example uses sequential numeric IDs for simplicity, but
        // this can result in poor performance in a production application.
        // Since rows are stored in sorted order by key, sequential keys can
        // result in poor distribution of operations across nodes.
        //
        // For more information about how to design a Bigtable schema for the
        // best performance, see the documentation:
        //
        //     https://cloud.google.com/bigtable/docs/schema-design
        String rowKey = "greeting" + i;

        // Put a single row into the table. We could also pass a list of Puts to write a batch.
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME, Bytes.toBytes(GREETINGS[i]));
        table.put(put);
      }
      } catch (IOException e) {
    	
      }
    
  }
  public void GetValor(Table table) {
	  try
	  {
		// Get the first greeting by row key
	      String rowKey = "greeting0";
	      Result getResult = table.get(new Get(Bytes.toBytes(rowKey)));
	      String greeting = Bytes.toString(getResult.getValue(COLUMN_FAMILY_NAME, COLUMN_NAME));
	      
		  } catch (IOException e) {
  
		  }
  
  }
  
  public byte[] GetAll(Table table) throws IOException {
	  byte[] valueBytes = null;
	  
	// Now scan across all rows.
      Scan scan = new Scan();
    
      ResultScanner scanner = table.getScanner(scan);
      for (Result row : scanner) {
        valueBytes = row.getValue(COLUMN_FAMILY_NAME, COLUMN_NAME);
      }
      return valueBytes;
  }
  
  
  public static void main(String[] args) throws Exception {

	    if (args.length != 2) {
	      System.out.println("Missing required project id or instance id");
	      return;
	    }
	    String projectId = args[0];
	    String instanceId = args[1];

	    BigtableAPI helloWorld = new BigtableAPI(projectId, instanceId, "test-table");
	    helloWorld.run();
	  }

	  public BigtableAPI(String projectId, String instanceId, String tableId) throws IOException {
	    this.tableId = tableId;

	    // [START connecting_to_bigtable]
	    // Creates the settings to configure a bigtable data client.
	    BigtableDataSettings settings =
	        BigtableDataSettings.newBuilder().setProjectId(projectId).setInstanceId(instanceId).build();

	    // Creates a bigtable data client.
	    dataClient = BigtableDataClient.create(settings);

	    // Creates the settings to configure a bigtable table admin client.
	    BigtableTableAdminSettings adminSettings =
	        BigtableTableAdminSettings.newBuilder()
	            .setProjectId(projectId)
	            .setInstanceId(instanceId)
	            .build();

	    // Creates a bigtable table admin client.
	    adminClient = BigtableTableAdminClient.create(adminSettings);
	    // [END connecting_to_bigtable]
	  }

	  public void run() throws Exception {
	    createTable();
	    writeToTable();
	    readSingleRow();
	    readTable();
	    deleteTable();
	    dataClient.close();
	    adminClient.close();
	  }

	  /** Demonstrates how to create a table. */
	  public void createTable() {
	    // [START creating_a_table]
	    // Checks if table exists, creates table if does not exist.
	    if (!adminClient.exists(tableId)) {
	      System.out.println("Creating table: " + tableId);
	      CreateTableRequest createTableRequest =
	          CreateTableRequest.of(tableId).addFamily(COLUMN_FAMILY);
	      adminClient.createTable(createTableRequest);
	      System.out.printf("Table %s created successfully%n", tableId);
	    }
	    // [END creating_a_table]
	  }

	  /** Demonstrates how to write some rows to a table. */
	  public void writeToTable() {
	    // [START writing_rows]
	    try {
	      System.out.println("\nWriting some greetings to the table");
	      String[] greetings = {"Hello World!", "Hello Bigtable!", "Hello Java!"};
	      for (int i = 0; i < greetings.length; i++) {
	        RowMutation rowMutation =
	            RowMutation.create(tableId, ROW_KEY_PREFIX + i)
	                .setCell(COLUMN_FAMILY, COLUMN_QUALIFIER, greetings[i]);
	        dataClient.mutateRow(rowMutation);
	        System.out.println(greetings[i]);
	      }
	    } catch (NotFoundException e) {
	      System.err.println("Failed to write to non-existent table: " + e.getMessage());
	    }
	    // [END writing_rows]
	  }

	  /** Demonstrates how to read a single row from a table. */
	  public void readSingleRow() {
	    // [START reading_a_row]
	    try {
	      System.out.println("\nReading a single row by row key");
	      Row row = dataClient.readRow(tableId, ROW_KEY_PREFIX + 0);
	      System.out.println("Row: " + row.getKey().toStringUtf8());
	      for (RowCell cell : row.getCells()) {
	        System.out.printf(
	            "Family: %s    Qualifier: %s    Value: %s%n",
	            cell.getFamily(), cell.getQualifier().toStringUtf8(), cell.getValue().toStringUtf8());
	      }
	    } catch (NotFoundException e) {
	      System.err.println("Failed to read from a non-existent table: " + e.getMessage());
	    }
	    // [END reading_a_row]
	  }

	  /** Demonstrates how to read an entire table. */
	  public void readTable() {
	    // [START scanning_all_rows]
	    try {
	      System.out.println("\nReading the entire table");
	      Query query = Query.create(tableId);
	      ServerStream<Row> rowStream = dataClient.readRows(query);
	      for (Row r : rowStream) {
	        System.out.println("Row Key: " + r.getKey().toStringUtf8());
	        for (RowCell cell : r.getCells()) {
	          System.out.printf(
	              "Family: %s    Qualifier: %s    Value: %s%n",
	              cell.getFamily(), cell.getQualifier().toStringUtf8(), cell.getValue().toStringUtf8());
	        }
	      }
	    } catch (NotFoundException e) {
	      System.err.println("Failed to read a non-existent table: " + e.getMessage());
	    }
	    // [END scanning_all_rows]
	  }

	  /** Demonstrates how to delete a table. */
	  public void deleteTable() {
	    // [START deleting_a_table]
	    System.out.println("\nDeleting table: " + tableId);
	    try {
	      adminClient.deleteTable(tableId);
	      System.out.printf("Table %s deleted successfully%n", tableId);
	    } catch (NotFoundException e) {
	      System.err.println("Failed to delete a non-existent table: " + e.getMessage());
	    }
	    // [END deleting_a_table]
	  }
}
