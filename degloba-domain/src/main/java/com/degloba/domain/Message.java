package com.degloba.domain;

/** 
 * @category Un missatge (POJO) on podem guardar qualsevol tipus d'informació 
 */
public class Message {
	
  private String messageId;
  private String publishTime;
  private String data;

  public Message(String messageId) {
    this.messageId = messageId;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
