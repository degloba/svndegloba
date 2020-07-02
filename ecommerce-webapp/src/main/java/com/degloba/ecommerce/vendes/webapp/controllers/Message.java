package com.degloba.ecommerce.vendes.webapp.controllers;

/**
 * The message bean that will be used in the echo request and response.
 */
public class Message {

  private String message;
  
  private String id;
  private String title;
  private Boolean complete;
  
  
  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

/*public Boolean getComplete() {
	return complete;
}

public void setComplete(Boolean complete) {
	this.complete = complete;
}
  */
  
}
