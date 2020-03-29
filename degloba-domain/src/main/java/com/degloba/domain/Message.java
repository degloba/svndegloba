package com.degloba.domain;

import lombok.Data;

/** 
 * @category Un missatge (POJO) on podem guardar qualsevol tipus d'informació 
 */
@Data
public class Message {
	
  private String messageId;
  private String publishTime;
  private String data;

  public Message(String messageId) {
    this.messageId = messageId;
  }

}
