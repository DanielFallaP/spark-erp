package co.com.cybersoft.core.services.paymentType;

import java.util.Date;

import co.com.cybersoft.events.paymentType.CreatePaymentTypeEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.PaymentTypePageEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeModificationEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypePageEvent;
import co.com.cybersoft.persistence.services.paymentType.PaymentTypePersistenceService;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PaymentTypeServiceImpl implements PaymentTypeService{

	private final PaymentTypePersistenceService paymentTypePersistenceService;
	
	public PaymentTypeServiceImpl(final PaymentTypePersistenceService paymentTypePersistenceService){
		this.paymentTypePersistenceService=paymentTypePersistenceService;
	}
	
	/**
	 */
	@Override
	public PaymentTypeDetailsEvent createPaymentType(CreatePaymentTypeEvent event ) throws Exception{
		event.getPaymentTypeDetails().setDateOfModification(new Date());
		return paymentTypePersistenceService.createPaymentType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public PaymentTypePageEvent requestPaymentTypePage(RequestPaymentTypePageEvent event) throws Exception{
		return paymentTypePersistenceService.requestPaymentTypePage(event);
	}

	@Override
	public PaymentTypeDetailsEvent requestPaymentTypeDetails(RequestPaymentTypeDetailsEvent event ) throws Exception{
		return paymentTypePersistenceService.requestPaymentTypeDetails(event);
	}

	@Override
	public PaymentTypeDetailsEvent modifyPaymentType(PaymentTypeModificationEvent event) throws Exception {
		event.getPaymentTypeDetails().setDateOfModification(new Date());
		return paymentTypePersistenceService.modifyPaymentType(event);
	}

}