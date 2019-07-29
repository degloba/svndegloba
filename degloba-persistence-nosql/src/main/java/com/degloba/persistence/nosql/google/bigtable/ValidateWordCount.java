package com.degloba.persistence.nosql.google.bigtable;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * https://github.com/GoogleCloudPlatform/cloud-bigtable-examples/edit/master/java/dataproc-wordcount/src/main/java/com/example/bigtable/sample/ValidateWordCount.java
 * Cloud Bigtable suporta les API de l'HBase 1.0 i posteriors.
 * 
 * @author pere
 *
 */
public class ValidateWordCount {

  @SuppressWarnings("unused")
  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length < 2) {
      System.err.println("Usage: java -cp <this jar>:<hbase classpath> "
          + ValidateWordCount.class.getName() + " <table-name> <expected count>");
      System.exit(2);
    }

    TableName tableName = TableName.valueOf(otherArgs[0]);
    int expectedCount = Integer.parseInt(otherArgs[1]);

    Scan scan = new Scan();
    scan.addFamily(Bytes.toBytes("cf"));
    scan.setStartRow(Bytes.toBytes(""));
    int count = 0;

    try (Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(tableName);
        ResultScanner rs = table.getScanner(scan)) {
      for (Result result : rs) {
        count++;
      }
    }

    System.out.println("Count: " + count + ".  Expected: " + expectedCount);
    System.exit(count == expectedCount ? 0 : 1);
  }
}
