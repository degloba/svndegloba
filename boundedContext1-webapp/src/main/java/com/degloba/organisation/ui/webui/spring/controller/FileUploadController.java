package com.degloba.organisation.ui.webui.spring.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;


import com.degloba.StorageFactory;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

@Component
public class FileUploadController {
	
	private UploadedFile uploadedFile;
	
	 /**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
	  private static final int BUFFER_SIZE = 2 * 1024 * 1024;
	  
	  private final static Logger logger = Logger.getLogger(FileUploadController.class.getName());
	  
	  
	  private final static GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
    	      .initialRetryDelayMillis(10)
    	      .retryMaxAttempts(10)
    	      .totalRetryPeriodMillis(15000)
    	      .build());
    

    public void handleFileUpload(FileUploadEvent event) 
    			throws IOException, InvalidParameterException {
    	
    	
    	String file = event.getFile().getFileName();
    	    	
    	   InputStreamContent contentStream = new InputStreamContent("image/png", event.getFile().getInputstream());
    		    // Setting the length improves upload performance
    	   
    		    contentStream.setLength(event.getFile().getSize());
    		    
    		    StorageObject objectMetadata = new StorageObject()
    		        // Set the destination object name
    		        .setName(file)
    		        // Set the access control list to publicly read-only
    		        .setAcl(Arrays.asList(
    		            new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

    		    // Do the insert
    		    Storage client;
				try {
					client = StorageFactory.getService();
					
					Storage.Objects.Insert insertRequest = client.objects().insert(
	    		    		"wwwdegloba-1350.appspot.com", objectMetadata, contentStream);
					
	    		    insertRequest.execute();
	    		    
	    		    logger.fine("**************POSTERIOR FileUploadController:client " + client);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
    		    
    		  }
    	
    
    public void handleFileUpload2(FileUploadEvent event) 
			throws IOException, InvalidParameterException {
    	
    	UploadedFile uploadedFile = event.getFile();
  	    		
    	
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        byte[] b = event.getFile().getContents();
        
        //Writing to Cloud Storage
        //GcsFileOptions instance = GcsFileOptions.getDefaultInstance();
        GcsFileOptions instance = new GcsFileOptions.Builder().mimeType("image/png").build();
        
        GcsFilename fileName = new GcsFilename("wwwdegloba.appspot.com", "pere.png");    //   getFileName(req);
        
        GcsOutputChannel outputChannel;
        try {
        	
        	gcsService.createOrReplace(fileName, instance,ByteBuffer.wrap(b));
        	
            //copy(event.getFile().getContents(), Channels.newOutputStream(outputChannel));
            
        } catch (IOException ex)
        {
        	            
        }
       
    }
    
    
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    
/*    public void submit() {    
        // Get information you from the uploaded file
        System.out.println("Uploaded file name : " + uploadedFile.getFileName());
    }*/
    
    
    /*private GcsFilename getFileName(HttpServletRequest req) {
        String[] splits = req.getRequestURI().split("/", 4);
        if (!splits[0].equals("") || !splits[1].equals("gcs")) {
          throw new IllegalArgumentException("The URL is not formed as expected. " +
              "Expecting /gcs/<bucket>/<object>");
        }
        return new GcsFilename(splits[2], splits[3]);
      }

      *//**
       * Transfer the data from the inputStream to the outputStream. Then close both streams.
       */
      private void copy(byte[] input, OutputStream output) throws IOException {
        //try {
    	  
          byte[] buffer = new byte[BUFFER_SIZE];
          int bytesRead = input.length;   //input.read(buffer);
          
          output.write(bytesRead);
          
         /* while (bytesRead != -1) {
            output.write(buffer, 0, bytesRead);
            bytesRead = input.read(buffer);
          }
        } finally {
          input.close();
          output.close();
        }  */
      }

}
