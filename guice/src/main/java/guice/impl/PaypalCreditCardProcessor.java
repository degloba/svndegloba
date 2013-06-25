package guice.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.sdk.exceptions.PayPalException;


import com.degloba.billing.ChargeResult;
import com.degloba.billing.CreditCardProcessor;
import com.degloba.billing.GenerateAccessToken;

public class PaypalCreditCardProcessor implements CreditCardProcessor{

	

	/**
	 * Bean de formulario para que el usuario se valide.
	 **/
	public class TargetaCreditForm
	{
	  	private String numero = null;
	  	private String tipus = null;
	  	private String CVV2 = null;
	  	private int mesExp;
	  	private int anyExp;
	  	private String pais = null;
	  	private String provincia = null;
	  	private String ciutat = null;
	  	private String adreca = null;
	  	private String codi_postal = null;
	  	private String nom = null;
	  	private String cognoms = null; 
	  	private String telefon= null;
	  	private String email = null;
	  	  	
	  	
		  	
	  	
		//CallerServices caller;  // FA REFERENCIA AL PERFIL DEL VENEDOR DE 
		// PRODUCTES/SERVEIS (EN AQUEST CAS INSACO QUE VEN 
		// EL SERVEI DE PUBLICITAT D'INMOBLES)
		// QUE TE UN COMPTE PAYPAL ASSOCIAT I A TRAVES
		// DEL QUAL REBRA ELS PAGAMENTS
	  
		/**
	   	 * Es crida per part del controlador quan un client entra la informaci? de la tarjeta
	   	 * de credit o debit per pagar
	   	 * 
	   	 **/
	  	public String pagamentTargeta()
	  		throws Exception
		{
		     
		     // creem el perfil del compte PayPal d'INSACO
		     crearPerfilVenedor();
		     // realitzem la transaccio (Abonament al compte PayPal d'INSACO)
		     DoDirectPayment();
		    	
	    	return "Success";

	  	}
	  	
	  	
	  	
	  	
	    
	  	public void crearPerfilVenedor() throws PayPalException {
	    	
	    	System.out.println("CREA PERFIL VENEDOR PAYPAL : INSACO");
	    	//caller = new CallerServices();
	    	
	    	/*
	    	 WARNING: Do not embed plaintext credentials in your application code.
	    	 Doing so is insecure and against best practices.
	    	 Your API credentials must be handled securely. Please consider 
	    	 encrypting them for use in any production environment, and ensure
	    	 that only authorized individuals may view or modify them.
	    	 */
	    	
	    	
	    	// CONNEXIO/LOGIN VENEDOR/PROVEIDOR DE SERVEIS/PRODUCTES
	    	// EXEMPLE : EBAY
	    	// EXEMPLE : DEGLOBA/INSACOSA
	    	//APIProfile profile = ProfileFactory.createSSLAPIProfile();
	    	
	    	// USUARI DEL VENEDOR (EN AQUEST CAS INSACO QUE VEN LA POSSIBILITAT DE 
	    	// PUBLICAR INMOBLES) 
	    	// CAL QUE EL COMPTE PAYPAL SIGUI PREMIER/BUSINESS
	    	/*profile.setAPIUsername("sdk-seller_api1.sdk.com");  // FIXE PER UN COMPTE PAYPAL  
	    	profile.setAPIPassword("12345678");  // FIXE PER UN COMPTE PAYPAL
*/	    	
	    	// CAL POSAR TOT EL PATH !!!!!!!
	    	// CUIDADO !!!!!!!!! A L'HORA DE POSAR-HO EN EL HOSTING
	    	
	    	/*profile.setCertificateFile("/usr/local/eclipse/workspace/insacosa/WEB-INF/classes/paypal/Cert/sdk-seller.p12");
	    	//profile.setCertificateFile("./sdk-seller.p12");
	    	profile.setPrivateKeyPassword("password");
	    	profile.setEnvironment("sandbox");
	    	caller.setAPIProfile(profile);*/
	  	
	    }  	
	  	
	  	
	  	
	  	
	    /**
	     * @throws PayPalException
	     * @throws PayPalRESTException 
	     */
	    public void DoDirectPayment() throws PayPalException, PayPalRESTException {
	    	
	    	System.out.println("ABONAMENT QUANTITAT A INSACO");    	
	       	System.out.println("\n########## Do Direct Payment ##########\n");
	        	
	        	/*DoDirectPaymentRequestType request = new DoDirectPaymentRequestType();
	    		DoDirectPaymentRequestDetailsType details = new DoDirectPaymentRequestDetailsType();*/

	    		
	    		// TARJETA DE CREDIT DEL COMPRADOR/CLIENT DEL SERVEI
	    		// QUE HAURA ENTRAT EN ALGUN FORMULARI	
	    		// AQUEST ES EL FORMULARI QUE HAUREM DE DISSENYAR PERQUE
	    		// L'ENTRI EL CLIENT DE LA WEB (EX: INSACOSA)
	    		// EXEMPLE : TARJETA DE CREDIT/DEBIT D'UN CLIENT DE INSACOSA
	    		
	    		// IMPORTANT : EN AQUEST EXEMPLE JAVA ESTEM OBLIGATS A QUE INSACOSA.COM
	    		// SUPORTI HTTPS PERQUE S'ENTRA EL NUMERO DE COMPTE MITJAN?ANT UN FORMULARI
	    		// DE LA WEB D'INSACO (tarjeta_credit.jsp)
	    		
	    		// EN E? CAS DEL JSP pagament_Paypal.jsp NO ES NECESSARI QUE INSACOSA.COM
	    		// SUPORTI HTTPS PERQUE TOTA LA TRANSACCIO ES FA A TRAVES DE LA WEB DE
	    		// PAYPAL QUE ES CONNECTA AMB HTTPS
	    		
	    		
//	     **************************************		
//	     FORMULARI QUE CALDRA DEFINIR EN LA WEB
//	     **************************************		
	    		
	   /* 		PayerInfoType cardOwner = new PayerInfoType();  // INFO DEL CLIENT/COMPRADOR
	    		cardOwner.setPayerCountry(CountryCodeType.ES);
	    		
	    		AddressType address = new AddressType();
	    		address.setPostalCode(codi_postal);  // CODI POSTAL DEL CLIENT (CAMP FORMULARI)
	    		address.setStateOrProvince(provincia);  //PROVINCIA DEL CLIENT (CAMP FORMULARI)
	    		address.setStreet1(adreca);  //CARRER CLIENT (CAMP FORMULARI)
	    		address.setCountryName(pais);  // PAIS CLIENT (CAMP FORMULARI)
	    		address.setCountry(CountryCodeType.ES);
	    		address.setCityName(ciutat);  // CIUTAT CLIENT (CAMP FORMULARI)
	    		cardOwner.setAddress(address);
	    			
	    		PersonNameType payerName = new PersonNameType();
	    		payerName.setFirstName(nom);
	    		payerName.setLastName(cognoms);
	    		cardOwner.setPayerName(payerName);*/
	       	
	       	
	     // ###AccessToken		
	       	// Retrieve the access token from		
	       	// OAuthTokenCredential by passing in		
	       	// ClientID and ClientSecret		
	       	APIContext apiContext = null;		
	       	String accessToken = null;
	       	
	       	try {			
	       		accessToken = GenerateAccessToken.getAccessToken("sdk-seller_api1.sdk.com","12345678");	
	       		
	       	// ### Api Context			
	       		// Pass in a `ApiContext` object to authenticate			
	       		// the call and to send a unique request id			
	       		// (that ensures idempotency). The SDK generates			
	       		// a request id if you do not pass one explicitly.			
	       		apiContext = new APIContext(accessToken);			
	       		// Use this variant if you want to pass in a request id			
	       		// that is meaningful in your application, ideally			
	       		// a order id.			
	       		/*			 
	       		 * String requestId = Long.toString(System.nanoTime(); APIContext			 
	       		 * apiContext = new APIContext(accessToken, requestId ));			 
	       		 */		
	       		} catch (PayPalRESTException e) {			
	       			e.printStackTrace();	
	       		}
	       	
	    
	

	    		
	    		Address billingAddress = new Address();
	    		billingAddress.setLine1(adreca);
	    		billingAddress.setCity(ciutat);
	    		billingAddress.setCountryCode("ES");
	    		billingAddress.setPostalCode(codi_postal);
	    		billingAddress.setState(provincia);

	    		CreditCard creditCard = new CreditCard();
	    		creditCard.setNumber(numero);
	    		creditCard.setType("visa");
	    		creditCard.setExpireMonth(mesExp);
	    		creditCard.setExpireYear(anyExp);
	    		creditCard.setCvv2(CVV2);
	    		creditCard.setFirstName(nom);
	    		creditCard.setLastName(cognoms);
	    		creditCard.setBillingAddress(billingAddress);

	    		
	    	/*	CreditCardDetailsType creditCard = new CreditCardDetailsType();
	    		creditCard.setCreditCardNumber(numero);  // TARJETA VISA ACCEPTADA
	    		creditCard.setCreditCardType(CreditCardTypeType.Visa); // VISA
	    		creditCard.setCVV2(CVV2);
	    		creditCard.setExpMonth(mesExp);
	    		creditCard.setExpYear(anyExp);*/
	    		

	    		creditCard.setBillingAddress(billingAddress);
	    		//creditCard.setCardOwner(cardOwner);
	    		//details.setCreditCard(creditCard);
	    		
	    		//S'ESPECIFICA LA IP PERQUE PAYPAL DETECTI POSSIBLES FRAUS
	    		/*details.setIPAddress("12.36.5.78");
	    		details.setMerchantSessionId("456977");
	    		details.setPaymentAction(PaymentActionCodeType.Sale);  // TIPUS DE COMPRA

	    		PaymentDetailsType payment = new PaymentDetailsType();
	    		
	    		BasicAmountType orderTotal = new BasicAmountType();
	    		orderTotal.setCurrencyID(CurrencyCodeType.EUR);  // EURO
	    		orderTotal.set_value("18.00");				// QUANTITAT
	    		payment.setOrderTotal(orderTotal);*/
	    		
	    		
	    		
	    		Details amountDetails = new Details();
	    		amountDetails.setSubtotal("7.41");
	    		amountDetails.setTax("0.03");
	    		amountDetails.setShipping("0.03");

	    		Amount amount = new Amount();
	    		amount.setTotal("7.47");
	    		amount.setCurrency("USD");
	    		amount.setDetails(amountDetails);

	    		// The Payment creation API requires a list of			
	    		// Transaction; add the created `Transaction`			
	    		// to a List
	    		Transaction transaction = new Transaction();
	    		transaction.setAmount(amount);
	    		transaction.setDescription("This is the payment transaction description.");

	    		List<Transaction> transactions = new ArrayList<Transaction>();
	    		transactions.add(transaction);

	    		FundingInstrument fundingInstrument = new FundingInstrument();
	    		fundingInstrument.setCreditCard(creditCard);

	    		List<FundingInstrument> fundingInstruments = new ArrayList<FundingInstrument>();
	    		fundingInstruments.add(fundingInstrument);

	    		// ###Payer			
	    		// A resource representing a Payer that funds a payment			
	    		// Payment Method			
	    		// as 'paypal'
	    		Payer payer = new Payer();
	    		payer.setFundingInstruments(fundingInstruments);
	    		payer.setPaymentMethod("credit_card");

	    		// ###Payment			
	    		// A Payment Resource; create one using			
	    		// the above types and intent as 'sale'
	    		Payment payment = new Payment();
	    		payment.setIntent("sale");
	    		payment.setPayer(payer);
	    		payment.setTransactions(transactions);

	    		try
	    		{
	    			Payment createdPayment = payment.create(accessToken);
		    		
		    		Iterator<Links> links = createdPayment.getLinks().iterator();				
		    		while (links.hasNext()) {					
		    			Links link = links.next();					
		    			if (link.getRel().equalsIgnoreCase("approval_url")) {						
		    			//req.setAttribute("redirectURL", link.getHref());					
		    			}	
		    		}
	    		}
	    		catch (PayPalRESTException e) {				
	    			//req.setAttribute("error", e.getMessage());
	    		}
	    		
	    					
	    		

//	    		 **************************************		
//	    		 FI FORMULARI QUE CALDRA DEFINIR EN LA WEB
//	    		 **************************************
	    		
	    		/*details.setPaymentDetails(payment);
	    		request.setDoDirectPaymentRequestDetails(details);*/
	    		
	    		
	    		// NOTA : el caller conte la informaci? a qui s'ha d'abonar un import
	    		// En aquest cas seria la Web que ven productes/serveis i per tant INSACO
	    		// que ha de tenir un compte PAYPAL associat a ell 
	    		//DoDirectPaymentResponseType response = (DoDirectPaymentResponseType) caller.call("DoDirectPayment", request);
	   /*     	
	        	System.out.println("Operation Ack: " + response.getAck());
	     	  	System.out.println("---------- Results ----------");
	     	  	System.out.println("\nTransaction ID: " + response.getTransactionID());
	     	  	System.out.println("CVV2: " + response.getCVV2Code());
	     	  	System.out.println("AVS: " + response.getAVSCode());
	     	  	System.out.println("Quantitat Total: " + response.getAmount().getCurrencyID() 
	    			+ " " + response.getAmount().get_value());*/
	    			
	    		
	        }
	        	
	  	
	  	
	  	
	  	public String getCodi_postal() {
			return codi_postal;
		}


		public void setCodi_postal(String codi_postal) {
			this.codi_postal = codi_postal;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getTelefon() {
			return (this.telefon);
		}


		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}


		public String getAdreca() {
			return adreca;
		}


		public void setAdreca(String adreca) {
			this.adreca = adreca;
		}


		public int getAnyExp() {
			return anyExp;
		}


		public void setAnyExp(int anyExp) {
			this.anyExp = anyExp;
		}


		public String getCiutat() {
			return ciutat;
		}


		public void setCiutat(String ciutat) {
			this.ciutat = ciutat;
		}


		public String getCognoms() {
			return cognoms;
		}


		public void setCognoms(String cognoms) {
			this.cognoms = cognoms;
		}

		public String getCVV2() {
			return (this.CVV2);
		}


		public void setCVV2(String cvv2) {
			this.CVV2 = cvv2;
		}


		public int getMesExp() {
			return (this.mesExp);
		}


		public void setMesExp(int mesExp) {
			this.mesExp = mesExp;
		}


		public String getNom() {
			return (this.nom);
		}



		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getNumero() {
			return numero;
		}



		public void setNumero(String numero) {
			this.numero = numero;
		}



		public String getPais() {
			return pais;
		}


		public void setPais(String pais) {
			this.pais = pais;
		}


		public String getProvincia() {
			return provincia;
		}

		
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}


		public String getTipus() {
			return tipus;
		}



		public void setTipus(String tipus) {
			this.tipus = tipus;
		}


	 	
	  	
	  	
	  	
	}

	public ChargeResult charge(CreditCard creditCard, Object amount) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
