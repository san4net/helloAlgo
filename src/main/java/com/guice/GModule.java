package com.guice;

import java.util.Date;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class GModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(CreditService.class).to(CreditImpl.class);
		bind(Payment.class).to(RealPayment.class);
		install(new FactoryModuleBuilder()
	    .implement(Payment.class, RealPayment.class)
	    .build(PaymentFactory.class));

	}
  public static void main(String[] args) {
	Injector in = Guice.createInjector(new GModule());
	RealPaymentFactory fac = in.getInstance(RealPaymentFactory.class);
	
	fac.create(new Date());
}	
  
}
