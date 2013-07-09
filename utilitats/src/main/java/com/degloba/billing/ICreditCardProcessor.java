package com.degloba.billing;

import com.paypal.api.payments.CreditCard;


public interface ICreditCardProcessor {

	ChargeResult charge(CreditCard creditCard, Object amount);

}
