package com.guice;

import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RealPaymentFactory implements PaymentFactory {
	 private final Provider<CreditService> creditServiceProvider;

	@Inject
	public RealPaymentFactory(Provider<CreditService> creditServiceProvider) {
		this.creditServiceProvider = creditServiceProvider;
	}


	@Override
	public Payment create(Date startDate) {
		return new RealPayment(creditServiceProvider.get());
	}
	}