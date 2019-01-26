package com.degloba.gcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import java.security.GeneralSecurityException;

import com.google.api.services.storage.Storage;

import com.google.api.client.http.InputStreamContent;

import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.Objects;
import com.google.api.services.storage.model.StorageObject;


public class StorageUtils {
	  
	  private final static Logger logger = Logger.getLogger(StorageFactory.class.getName());
	  
	  /** Global instance of the JSON factory. */
	  //private static final String TEST_FILENAME = "json-test.txt";

	  // [START list_bucket]
	  /**
	   * Fetch a list of the objects within the given bucket.
	   *
	   * @param bucketName the name of the bucket to list.
	   * @return a list of the contents of the specified bucket.
	   */
	  public static List<StorageObject> listBucket(String bucketName)
	      throws IOException, GeneralSecurityException {
	    Storage client = StorageFactory.getService();
	    Storage.Objects.List listRequest = client.objects().list(bucketName);

	    List<StorageObject> results = new ArrayList<StorageObject>();
	    Objects objects;

	    // Iterate through each page of results, and add them to our results list.
	    do {
	      objects = listRequest.execute();
	      // Add the items in this page of results to the list we'll return.
	      results.addAll(objects.getItems());

	      // Get the next page, in the next iteration of this loop.
	      listRequest.setPageToken(objects.getNextPageToken());
	    } while (null != objects.getNextPageToken());

	    return results;
	  }
	  // [END list_bucket]

	  // [START get_bucket]
	  /**
	   * Fetches the metadata for the given bucket.
	   *
	   * @param bucketName the name of the bucket to get metadata about.
	   * @return a Bucket containing the bucket's metadata.
	   */
	  public static Bucket getBucket(String bucketName) throws IOException, GeneralSecurityException {
	    Storage client = StorageFactory.getService();

	    Storage.Buckets.Get bucketRequest = client.buckets().get(bucketName);
	    // Fetch the full set of the bucket's properties (e.g. include the ACLs in the response)
	    bucketRequest.setProjection("full");
	    return bucketRequest.execute();
	  }
	  // [END get_bucket]

	  // [START upload_stream]
	  /**
	   * Uploads data to an object in a bucket.
	   *
	   * @param name the name of the destination object.
	   * @param contentType the MIME type of the data.
	   * @param file the file to upload.
	   * @param bucketName the name of the bucket to create the object in.
	   */
	  public static void uploadFile(
	      String name, String contentType, File file, String bucketName)
	  
	      throws IOException, GeneralSecurityException {
	    InputStreamContent contentStream = new InputStreamContent(
	        contentType, new FileInputStream(file));
	    // Setting the length improves upload performance
	    contentStream.setLength(file.length());
	    StorageObject objectMetadata = new StorageObject()
	        // Set the destination object name
	        .setName(name)
	        // Set the access control list to publicly read-only
	        .setAcl(Arrays.asList(
	            new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

	    // Do the insert
	    Storage client = StorageFactory.getService();
	    Storage.Objects.Insert insertRequest = client.objects().insert(
	        bucketName, objectMetadata, contentStream);

	    insertRequest.execute();
	  }
	  // [END upload_stream]
	  
	  // [START upload_stream]
	  /**
	   * Uploads data to an object in a bucket.
	   *
	   * @param name the name of the destination object.
	   * @param contentType the MIME type of the data.
	   * @param file the file to upload.
	   * @param bucketName the name of the bucket to create the object in.
	   */
	  public static void uploadFile(
	      String name, String contentType, InputStream inputStream,Long size, String bucketName)
	      throws IOException, GeneralSecurityException {
	    InputStreamContent contentStream = new InputStreamContent(
	        contentType, inputStream);
	    // Setting the length improves upload performance
	    contentStream.setLength(size);
	    StorageObject objectMetadata = new StorageObject()
	        // Set the destination object name
	        .setName(name)
	        // Set the access control list to publicly read-only
	        .setAcl(Arrays.asList(
	            new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

	    // Do the insert
	    Storage client = StorageFactory.getService();
	    Storage.Objects.Insert insertRequest = client.objects().insert(
	        bucketName, objectMetadata, contentStream);

	    insertRequest.execute();
	  }
	  // [END upload_stream]

	  // [START delete_object]
	  /**
	   * Deletes an object in a bucket.
	   *
	   * @param path the path to the object to delete.
	   * @param bucketName the bucket the object is contained in.
	   */
	  public static void deleteObject(String path, String bucketName)
	      throws IOException, GeneralSecurityException {
	    Storage client = StorageFactory.getService();
	    client.objects().delete(bucketName, path).execute();
	  }
	  // [END delete_object]
	 
	}
