package com.degloba.persistence.nosql.google.bigtable.tools;


import static com.google.cloud.bigtable.admin.v2.models.GCRules.GCRULES;

import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminClient;
import com.google.cloud.bigtable.admin.v2.BigtableTableAdminSettings;
import com.google.cloud.bigtable.admin.v2.models.ColumnFamily;
import com.google.cloud.bigtable.admin.v2.models.CreateTableRequest;
import com.google.cloud.bigtable.admin.v2.models.GCRules.DurationRule;
import com.google.cloud.bigtable.admin.v2.models.GCRules.IntersectionRule;
import com.google.cloud.bigtable.admin.v2.models.GCRules.UnionRule;
import com.google.cloud.bigtable.admin.v2.models.GCRules.VersionRule;
import com.google.cloud.bigtable.admin.v2.models.ModifyColumnFamiliesRequest;
import com.google.cloud.bigtable.admin.v2.models.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * 	<li>Cloud Bigtable emmagatzema les dades en taules escalables massivament, cadascuna de les quals és un mapa de clau/valor ordenat.
 *  
 * 	<li>La taula es compon de files, cadascuna de les quals normalment descriu una sola entitat i columnes, que contenen valors individuals per a cada fila.
 *  
 * 	<li>Cada fila està indexada per una clau de fila única i les columnes que estan relacionades entre si normalment s'agrupen en una família de columnes.
 *  
 * 	<li>Cada columna s’identifica mitjançant una combinació de la família de columnes i un qualificador de columna, 
 * que és un nom únic dins de la família de columnes.
 * </ul>
 * 
 * <p>Utilitza BigtableTableAdminClient per crear, configurar i suprimir una taula Bigtable Cloud
 *
 * <ul>
 *   <li>crea una table
 *   <li>llista totes les taules
 *   <li>recupera la metadata d'una taula
 *   <li>crea DurationRule
 *   <li>crea VersionRule
 *   <li>crea UnionRule
 *   <li>crea IntersectionRule
 *   <li>crea nested rule
 *   <li>llista column families
 *   <li>modifica column family rule
 *   <li>printa modified column family
 *   <li>esborra una column family
 *   <li>esborra una table
 * </ul>
 */
public class TableAdmin {

  private static final String COLUMN_FAMILY_1 = "cf1";
  private static final String COLUMN_FAMILY_2 = "cf2";
  private static final String COLUMN_FAMILY_3 = "cf3";
  private static final String COLUMN_FAMILY_4 = "cf4";
  private static final String COLUMN_FAMILY_5 = "cf5";
  private final String tableId;
  private final BigtableTableAdminClient adminClient;

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.out.println("Missing required project id or instance id");
      return;
    }
    String projectId = args[0];
    String instanceId = args[1];

    TableAdmin tableAdmin = new TableAdmin(projectId, instanceId, "test-table");
    tableAdmin.run();
  }

  public TableAdmin(String projectId, String instanceId, String tableId) throws IOException {
    this.tableId = tableId;

    // [START connecting_to_bigtable]
    // Crea els paràmetres per configurar un client d'administrador de la taula BigTable.
    BigtableTableAdminSettings adminSettings =
        BigtableTableAdminSettings.newBuilder()
            .setProjectId(projectId)
            .setInstanceId(instanceId)
            .build();

    // Crea un gestor d'administració de la taula BigTable.
    adminClient = BigtableTableAdminClient.create(adminSettings);
    // [END connecting_to_bigtable]
  }

  public void run() {
    createTable();
    listAllTables();
    getTableMeta();
    addFamilyWithMaxAgeRule();
    addFamilyWithMaxVersionsRule();
    addFamilyWithUnionRule();
    addFamilyWithIntersectionRule();
    addFamilyWithNestedRule();
    listColumnFamilies();
    modifyColumnFamilyRule();
    printModifiedColumnFamily();
    deleteColumnFamily();
    deleteTable();
    adminClient.close();
  }

  /** crea una taula amb la configuració especificada. */
  public void createTable() {
    // [START bigtable_create_table]
    // Comprova si la taula existeix, crea taula si no existeix.
    if (!adminClient.exists(tableId)) {
      System.out.println("Table does not exist, creating table: " + tableId);
      CreateTableRequest createTableRequest = CreateTableRequest.of(tableId).addFamily("cf");
      Table table = adminClient.createTable(createTableRequest);
      System.out.printf("Table: %s created successfully%n", table.getId());
    }
    // [END bigtable_create_table]
  }

  /** llista totes les taules d’una instància. */
  public void listAllTables() {
    System.out.println("\nListing tables in current instance");
    // [START bigtable_list_tables]
    // Lists tables in the current instance.
    try {
      List<String> tableIds = adminClient.listTables();
      for (String tableId : tableIds) {
        System.out.println(tableId);
      }
    } catch (NotFoundException e) {
      System.err.println("Failed to list tables from a non-existent instance: " + e.getMessage());
    }
    // [END bigtable_list_tables]
  }

  /** obté les metadades d'una taula. */
  public void getTableMeta() {
    System.out.println("\nPrinting table metadata");
    // [START bigtable_get_table_metadata]
    // Gets table metadata, and applies a view to the table fields.
    try {
      Table table = adminClient.getTable(tableId);
      System.out.println("Table: " + table.getId());
      Collection<ColumnFamily> columnFamilies = table.getColumnFamilies();
      for (ColumnFamily columnFamily : columnFamilies) {
        System.out.printf(
            "Column family: %s%nGC Rule: %s%n",
            columnFamily.getId(), columnFamily.getGCRule().toString());
      }
    } catch (NotFoundException e) {
      System.err.println(
          "Failed to retrieve table metadata for a non-existent table: " + e.getMessage());
    }
    // [END bigtable_get_table_metadata]
  }

  /** crea una nova instància de la DurationRule. 
   * GCRule es una regla per determinar quines celles ha de suprimir durant la Garbage Collector. 
   */
  public void addFamilyWithMaxAgeRule() {
    System.out.printf("%nCreating column family %s with max age GC rule%n", COLUMN_FAMILY_1);
    // [START bigtable_create_family_gc_max_age]
    // Creates a column family with GC policy : maximum age
    // where age = current time minus cell timestamp

    // Defines the GC rule to retain data with max age of 5 days.
    DurationRule maxAgeRule = GCRULES.maxAge(5, TimeUnit.DAYS);

    // Crea una família de columnes amb una regla GC donada.
    try {
      // ModifyColumnFamiliesRequest can be used both for adding and modifying families, here it is
      // being used to add a family
      ModifyColumnFamiliesRequest columnFamiliesRequest =
          ModifyColumnFamiliesRequest.of(tableId).addFamily(COLUMN_FAMILY_1, maxAgeRule);
      adminClient.modifyFamilies(columnFamiliesRequest);
      System.out.println("Created column family: " + COLUMN_FAMILY_1);
    } catch (AlreadyExistsException e) {
      System.err.println(
          "Failed to create column family with rule, already exists: " + e.getMessage());
    }
    // [END bigtable_create_family_gc_max_age]
  }

  /** crea una nova instància de VersionRule. */
  public void addFamilyWithMaxVersionsRule() {
    System.out.printf("%nCreating column family %s with max versions GC rule%n", COLUMN_FAMILY_2);
    // [START bigtable_create_family_gc_max_versions]
    // Crea una família de columnes amb política GC: versions més recents N
    // where 1 = most recent version

    // Defineix la política de GC per conservar només les 2 versions més recents.
    VersionRule versionRule = GCRULES.maxVersions(2);

    // Creates column family with given GC rule.
    try {
      // ModifyColumnFamiliesRequest can be used both for adding and modifying families, here it is
      // being used to add a family
      ModifyColumnFamiliesRequest columnFamiliesRequest =
          ModifyColumnFamiliesRequest.of(tableId).addFamily(COLUMN_FAMILY_2, versionRule);
      adminClient.modifyFamilies(columnFamiliesRequest);
      System.out.println("Created column family: " + COLUMN_FAMILY_2);
    } catch (AlreadyExistsException e) {
      System.err.println(
          "Failed to create column family with rule, already exists: " + e.getMessage());
    }
    // [END bigtable_create_family_gc_max_versions]
  }

  /** crea una nova instància UnionRule. */
  public void addFamilyWithUnionRule() {
    System.out.printf("%nCreating column family %s with union GC rule%n", COLUMN_FAMILY_3);
    // [START bigtable_create_family_gc_union]
    // Crea una família de columnes amb política GC per retirar les dades que coincideixin almenys amb una condició.

    // Defineix una llista de regles GC per retirar cel·les amb més de 5 dies o de la versió més vella
    UnionRule unionRule =
        GCRULES.union().rule(GCRULES.maxAge(5, TimeUnit.DAYS)).rule(GCRULES.maxVersions(1));

    // Creates column family with given GC rule.
    try {
      // ModifyColumnFamiliesRequest can be used both for adding and modifying families, here it is
      // being used to add a family
      ModifyColumnFamiliesRequest columnFamiliesRequest =
          ModifyColumnFamiliesRequest.of(tableId).addFamily(COLUMN_FAMILY_3, unionRule);
      adminClient.modifyFamilies(columnFamiliesRequest);
      System.out.println("Created column family: " + COLUMN_FAMILY_3);
    } catch (AlreadyExistsException e) {
      System.err.println(
          "Failed to create column family with rule, already exists: " + e.getMessage());
    }
    // [END bigtable_create_family_gc_union]
  }

  /** crea una nova instància IntersectionRule. */
  public void addFamilyWithIntersectionRule() {
    System.out.printf("%nCreating column family %s with intersection GC rule%n", COLUMN_FAMILY_4);
    // [START bigtable_create_family_gc_intersection]
    //Crea una família de columnes amb política GC per retirar dades que coincideixin amb totes les condicions.

    // Defineix una regla GC per retirar cel·les de més de 5 dies i més antiga que les 2 versions més recents.
    DurationRule maxAgeRule = GCRULES.maxAge(5, TimeUnit.DAYS);
    VersionRule versionRule = GCRULES.maxVersions(2);
    IntersectionRule intersectionRule = GCRULES.intersection().rule(maxAgeRule).rule(versionRule);

    // Creates column family with given GC rule.
    try {
      // ModifyColumnFamiliesRequest can be used both for adding and modifying families, here it is
      // being used to add a family
      ModifyColumnFamiliesRequest columnFamiliesRequest =
          ModifyColumnFamiliesRequest.of(tableId).addFamily(COLUMN_FAMILY_4, intersectionRule);
      adminClient.modifyFamilies(columnFamiliesRequest);
      System.out.println("Created column family: " + COLUMN_FAMILY_4);
    } catch (AlreadyExistsException e) {
      System.err.println(
          "Failed to create column family with rule, already exists: " + e.getMessage());
    }
    // [END bigtable_create_family_gc_intersection]
  }

  /** crea una regla imbricada utilitzant el IntersectionRule and UnionRule. */
  public void addFamilyWithNestedRule() {
    System.out.printf("%nCreating column family %s with a nested GC rule%n", COLUMN_FAMILY_5);
    // [START bigtable_create_family_gc_nested]
    // Crea una regla GC anidada:
    // Retira les cel·les més antigues que les 10 versions recents
    // O
    // Retira les cel·les amb més d’un mes I més antigues que les 2 versions recents
    VersionRule versionRule1 = GCRULES.maxVersions(10);
    VersionRule versionRule2 = GCRULES.maxVersions(2);
    DurationRule maxAgeRule = GCRULES.maxAge(30, TimeUnit.DAYS);
    IntersectionRule intersectionRule = GCRULES.intersection().rule(maxAgeRule).rule(versionRule2);
    UnionRule unionRule = GCRULES.union().rule(intersectionRule).rule(versionRule1);

    // Creates column family with given GC rule.
    try {
      // ModifyColumnFamiliesRequest es pot utilitzar tant per afegir i modificar famílies, aquí
      // s’utilitza per afegir una família
      ModifyColumnFamiliesRequest columnFamiliesRequest =
          ModifyColumnFamiliesRequest.of(tableId).addFamily(COLUMN_FAMILY_5, unionRule);
      adminClient.modifyFamilies(columnFamiliesRequest);
      System.out.println("Created column family: " + COLUMN_FAMILY_5);
    } catch (AlreadyExistsException e) {
      System.err.println(
          "Failed to create column family with rule, already exists: " + e.getMessage());
    }
    // [END bigtable_create_family_gc_nested]
  }

  /** llista les famílies de columnes d'una taula. */
  public void listColumnFamilies() {
    System.out.println("\nPrinting ID and GC Rule for all column families");
    // [START bigtable_list_column_families]
    // Lists all families in the table with GC rules.
    try {
      Table table = adminClient.getTable(tableId);
      Collection<ColumnFamily> columnFamilies = table.getColumnFamilies();
      for (ColumnFamily columnFamily : columnFamilies) {
        System.out.printf(
            "Column family: %s%nGC Rule: %s%n",
            columnFamily.getId(), columnFamily.getGCRule().toString());
      }
    } catch (NotFoundException e) {
      System.err.println(
          "Failed to list column families from a non-existent table: " + e.getMessage());
    }
    // [END bigtable_list_column_families]
  }

  /** Demonstrates how to modify a column family's rule. */
  public void modifyColumnFamilyRule() {
    System.out.printf("%nUpdating column family %s GC rule%n", COLUMN_FAMILY_1);
    // [START bigtable_update_gc_rule]
    // Updates the column family metadata to update the GC rule.
    // Updates a column family GC rule.
    VersionRule versionRule = GCRULES.maxVersions(1);
    try {
      // ModifyColumnFamiliesRequest can be used both for adding and modifying families, here it is
      // being used to modify a family
      // Updates column family with given GC rule.
      ModifyColumnFamiliesRequest updateRequest =
          ModifyColumnFamiliesRequest.of(tableId).updateFamily(COLUMN_FAMILY_1, versionRule);
      adminClient.modifyFamilies(updateRequest);
      System.out.printf("Column family %s GC rule updated%n", COLUMN_FAMILY_1);
    } catch (NotFoundException e) {
      System.err.println("Failed to modify a non-existent column family: " + e.getMessage());
    }
    // [END bigtable_update_gc_rule]
  }

  /** Demonstrates how to print the modified column family. */
  public void printModifiedColumnFamily() {
    System.out.printf("%nPrint updated GC rule for column family %s%n", COLUMN_FAMILY_1);
    // [START bigtable_family_get_gc_rule]
    try {
      Table table = adminClient.getTable(tableId);
      Collection<ColumnFamily> columnFamilies = table.getColumnFamilies();
      for (ColumnFamily columnFamily : columnFamilies) {
        if (columnFamily.getId().equals(COLUMN_FAMILY_1)) {
          System.out.printf(
              "Column family: %s%nGC Rule: %s%n",
              columnFamily.getId(), columnFamily.getGCRule().toString());
        }
      }
    } catch (NotFoundException e) {
      System.err.println("Failed to print a non-existent column family: " + e.getMessage());
    }
    // [END bigtable_family_get_gc_rule]
  }

  /** Demonstrates how to delete a column family. */
  public void deleteColumnFamily() {
    System.out.println("\nDelete column family: " + COLUMN_FAMILY_2);
    // [START bigtable_delete_family]
    // Deletes a column family.
    try {
      ModifyColumnFamiliesRequest deleted =
          ModifyColumnFamiliesRequest.of(tableId).dropFamily(COLUMN_FAMILY_2);
      adminClient.modifyFamilies(deleted);
      System.out.printf("Column family %s deleted successfully%n", COLUMN_FAMILY_2);
    } catch (NotFoundException e) {
      System.err.println("Failed to delete a non-existent column family: " + e.getMessage());
    }
    // [END bigtable_delete_family]
  }

  /** Demonstrates how to delete a table. */
  public void deleteTable() {
    // [START bigtable_delete_table]
    // Deletes the entire table.
    System.out.println("\nDelete table: " + tableId);
    try {
      adminClient.deleteTable(tableId);
      System.out.printf("Table: %s deleted successfully%n", tableId);
    } catch (NotFoundException e) {
      System.err.println("Failed to delete a non-existent table: " + e.getMessage());
    }
    // [END bigtable_delete_table]
  }
}
