package com.guice;

import java.util.Date;

public interface PaymentFactory {
	  public Payment create(Date startDate);
	}