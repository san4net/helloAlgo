package com.guice;

import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class RealPayment implements Payment {
	
	@Inject
	public RealPayment(CreditService creditService) {
		
	}
	
	@Override
	public String depositer() {
		return null;
	}

}
