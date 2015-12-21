package com.degloba.integration.spring.mongodb.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

// Spring Data
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

// Spring Integration
import org.springframework.integration.mongodb.metadata.MongoDbMetadataStore;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import com.degloba.integration.spring.mongodb.rules.MongoDbAvailableTests;

/**
 * @author Senthil Arumugam, Samiraj Panneer Selvam
 * @since 4.2
 *
 */
public class MongoDbMetadataStoreTests extends MongoDbAvailableTests {

	private final static String DEFAULT_COLLECTION_NAME = "metadataStore";

	private final String file1 = "/remotepath/filesTodownload/file-1.txt";

	private final String file1Id = "12345";

	private MongoDbMetadataStore store = null;

	@Before
	public void configure() throws Exception {
		final MongoDbFactory mongoDbFactory = this.prepareMongoFactory(DEFAULT_COLLECTION_NAME);
		this.store = new MongoDbMetadataStore(mongoDbFactory);
	}

	@MongoDbAvailable
	@Test
	public void testConfigureCustomCollection() throws Exception {
		final String collectionName = "testMetadataStore";
		final MongoDbFactory mongoDbFactory = this.prepareMongoFactory(collectionName);
		final MongoTemplate template = new MongoTemplate(mongoDbFactory);
		store = new MongoDbMetadataStore(template, collectionName);
		testBasics();
	}

	@MongoDbAvailable
	@Test
	public void testConfigureFactory() throws Exception {
		final MongoDbFactory mongoDbFactory = this.prepareMongoFactory(DEFAULT_COLLECTION_NAME);
		store = new MongoDbMetadataStore(mongoDbFactory);
		testBasics();
	}

	@MongoDbAvailable
	@Test
	public void testConfigureFactorCustomCollection() throws Exception {
		final String collectionName = "testMetadataStore";
		final MongoDbFactory mongoDbFactory = this.prepareMongoFactory(collectionName);
		store = new MongoDbMetadataStore(mongoDbFactory, collectionName);
		testBasics();
	}

	private void testBasics() {
		String fileID = store.get(file1);
		assertNull(fileID);

		store.put(file1, file1Id);

		fileID = store.get(file1);
		assertNotNull(fileID);
		assertEquals(file1Id, fileID);
	}

	@Test
	@MongoDbAvailable
	public void testGetFromStore() {
		testBasics();
	}

	@Test
	@MongoDbAvailable
	public void testPutIfAbsent() throws Exception {
		String fileID = store.get(file1);
		assertNull("Get First time, Key doesnt exists", fileID);

		fileID = store.putIfAbsent(file1, file1Id);
		assertNull("Insert First time, Key insertion successful", fileID);

		fileID = store.putIfAbsent(file1, "56789");
		assertNotNull("Key Already Exists - Insertion Failed, for different value", fileID);
		assertEquals("Retrieving the Old Value", file1Id, fileID);

		assertEquals("Retrieving the Old Value", file1Id, store.get(file1));

	}

	@Test
	@MongoDbAvailable
	public void testRemove() throws Exception {
		String fileID = store.remove(file1);
		assertNull(fileID);

		fileID = store.putIfAbsent(file1, file1Id);
		assertNull(fileID);

		fileID = store.remove(file1);
		assertNotNull(fileID);
		assertEquals(file1Id, fileID);

		fileID = store.get(file1);
		assertNull(fileID);
	}

	@Test
	@MongoDbAvailable
	public void testReplace() throws Exception {
		boolean removedValue = store.replace(file1, file1Id, "4567");
		assertFalse(removedValue);
		String fileID = store.get(file1);
		assertNull(fileID);

		fileID = store.putIfAbsent(file1, file1Id);
		assertNull(fileID);

		removedValue = store.replace(file1, file1Id, "4567");
		assertTrue(removedValue);

		fileID = store.get(file1);
		assertNotNull(fileID);
		assertEquals("4567", fileID);

	}

}
