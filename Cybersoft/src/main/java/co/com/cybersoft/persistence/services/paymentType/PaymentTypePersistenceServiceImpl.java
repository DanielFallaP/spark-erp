package co.com.cybersoft.persistence.services.paymentType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.PaymentTypeDetails;
import co.com.cybersoft.events.paymentType.CreatePaymentTypeEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.PaymentTypePageEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeModificationEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypePageEvent;
import co.com.cybersoft.persistence.domain.PaymentType;
import co.com.cybersoft.persistence.repository.PaymentTypeRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PaymentTypePersistenceServiceImpl implements PaymentTypePersistenceService{

	private final PaymentTypeRepository paymentTypeRepository;
	
	public PaymentTypePersistenceServiceImpl(final PaymentTypeRepository paymentTypeRepository) {
		this.paymentTypeRepository=paymentTypeRepository;
	}
	
	@Override
	public PaymentTypeDetailsEvent createPaymentType(CreatePaymentTypeEvent event) throws Exception{
		PaymentType paymentType = PaymentType.fromPaymentTypeDetails(event.getPaymentTypeDetails());
		paymentType = paymentTypeRepository.save(paymentType);
		return new PaymentTypeDetailsEvent(paymentType.toPaymentTypeDetails());
	}

	@Override
	public PaymentTypePageEvent requestPaymentTypePage(RequestPaymentTypePageEvent event) throws Exception {
		Page<PaymentType> paymentTypes = paymentTypeRepository.findAll(event.getPageable());
		return new PaymentTypePageEvent(paymentTypes);
	}

	@Override
	public PaymentTypeDetailsEvent requestPaymentTypeDetails(RequestPaymentTypeDetailsEvent event) throws Exception {
		PaymentType paymentType = paymentTypeRepository.findByCode(event.getId());
		PaymentTypeDetails paymentTypeDetails = paymentType.toPaymentTypeDetails();
		return new PaymentTypeDetailsEvent(paymentTypeDetails);
	}

	@Override
	public PaymentTypeDetailsEvent modifyPaymentType(PaymentTypeModificationEvent event) throws Exception {
		PaymentType paymentType = PaymentType.fromPaymentTypeDetails(event.getPaymentTypeDetails());
		paymentType = paymentTypeRepository.save(paymentType);
		return new PaymentTypeDetailsEvent(paymentType.toPaymentTypeDetails());
	}

}