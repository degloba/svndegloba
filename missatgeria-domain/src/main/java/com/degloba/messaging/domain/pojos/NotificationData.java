package com.degloba.messaging.domain.pojos;

import lombok.Data;


/**
 * @category classe POJO que representa les dades d'una notificacio
 * 
 * @author degloba
 *
 */
@Data
public class NotificationData {

	private long id;
	private String nom;
	private String email;
	private String mobil;
	
}
