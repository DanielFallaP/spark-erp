package co.com.cybersoft.web.controller.paymentType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.core.domain.PaymentTypeDetails;
import co.com.cybersoft.core.services.paymentType.PaymentTypeService;
import co.com.cybersoft.events.paymentType.PaymentTypeDetailsEvent;
import co.com.cybersoft.events.paymentType.PaymentTypeModificationEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypeDetailsEvent;
import co.com.cybersoft.web.domain.paymentType.PaymentTypeInfo;


/**
 * Event class for PaymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/paymentType/modifyPaymentType/{code}")
public class PaymentTypeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(PaymentTypeModificationController.class);
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/paymentType/modifyPaymentType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyPaymentType(@ModelAttribute("paymentTypeInfo") PaymentTypeInfo paymentTypeInfo) throws Exception {
		LOG.debug("Modification of paymentType "+paymentTypeInfo.getCode());
		PaymentTypeDetails paymentTypeDetails = createPaymentTypeDetails(paymentTypeInfo);
		paymentTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		paymentTypeService.modifyPaymentType(new PaymentTypeModificationEvent(paymentTypeDetails));
		return "redirect:/configuration/paymentType/searchPaymentType";
	}
	
	private PaymentTypeDetails createPaymentTypeDetails(PaymentTypeInfo paymentTypeInfo){
		PaymentTypeDetails paymentTypeDetails = new PaymentTypeDetails();
		LOG.debug(paymentTypeInfo.getCode());
		BeanUtils.copyProperties(paymentTypeInfo, paymentTypeDetails);
		return paymentTypeDetails;
	}

	@ModelAttribute("paymentTypeInfo")
	private PaymentTypeInfo getPaymentTypeInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the paymentType "+code);
		PaymentTypeDetailsEvent requestPaymentTypeDetails = paymentTypeService.requestPaymentTypeDetails(new RequestPaymentTypeDetailsEvent(code));
		PaymentTypeInfo paymentTypeInfo = new PaymentTypeInfo();
		BeanUtils.copyProperties(requestPaymentTypeDetails.getPaymentTypeDetails(), paymentTypeInfo);
		return paymentTypeInfo;
	}
}