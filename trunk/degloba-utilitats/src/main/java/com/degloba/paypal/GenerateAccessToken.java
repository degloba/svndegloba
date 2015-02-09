package com.degloba.paypal;

import com.paypal.core.ConfigManager;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;


public class GenerateAccessToken {
	
	public static String getAccessToken(String cliId, String cliSecret) throws PayPalRESTException {		
		// ###AccessToken		
		// Retrieve the access token from		
		// OAuthTokenCredential by passing in		
		// ClientID and ClientSecret		
		String clientID = ConfigManager.getInstance().getValue(cliId);		
		String clientSecret = ConfigManager.getInstance().getValue(cliSecret);		
		return new OAuthTokenCredential(clientID, clientSecret).getAccessToken();	
		}
	
}
