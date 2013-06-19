package com.degloba.billing;

import com.paypal.api.payments.CreditCard;


public interface CreditCardProcessor {

	ChargeResult charge(CreditCard creditCard, Object amount);

}
