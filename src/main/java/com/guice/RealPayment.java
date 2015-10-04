package com.guice;

import com.google.inject.Inject;

public class RealPayment implements Payment {
	
	@Inject
	public RealPayment(CreditService creditService) {
		
	}
	
	@Override
	public String depositer() {
		return null;
	}

}
