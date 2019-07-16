package com.degloba.billing;


/**
 * Interfície : Servei de Facturació
 */
public interface IBillingService {

	/**   
	 * Intenta cobrar la comanda a la targeta de crèdit. 
	 * Tant siguin transaccions amb èxit com amb error, es registraran.   
	 *    
	 * @return a receipt of the transaction. If the charge was successful, the   
	 *      receipt will be successful. Otherwise, the receipt will contain a   
	 *      decline note describing why the charge failed.   
	 */  
	//Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
