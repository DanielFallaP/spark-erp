package co.com.cybersoft.core.services.paymentType;

import co.com.cybersoft.events.paymentType.CreatePaymentTypeEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.PaymentTypePageEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeModificationEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface PaymentTypeService {
	PaymentTypeDetailsEvent createPaymentType(CreatePaymentTypeEvent event ) throws Exception;
	
	PaymentTypePageEvent requestPaymentTypePage(RequestPaymentTypePageEvent event) throws Exception;

	PaymentTypeDetailsEvent requestPaymentTypeDetails(RequestPaymentTypeDetailsEvent event ) throws Exception;

	PaymentTypeDetailsEvent modifyPaymentType(PaymentTypeModificationEvent event) throws Exception;
}