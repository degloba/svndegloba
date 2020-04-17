package com.degloba.billing;

import com.paypal.api.payments.CreditCard;

/**
 * Interfície : Processa un càrrec a la targeta de crèdit
 * 
 * @author degloba
 *
 */
public interface ICreditCardProcessor {

	ChargeResult charge(CreditCard creditCard, Object quantitat);

}
