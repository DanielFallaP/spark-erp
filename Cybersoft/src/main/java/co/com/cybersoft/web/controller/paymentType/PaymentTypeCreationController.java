package co.com.cybersoft.web.controller.paymentType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.domain.PaymentTypeDetails;
import co.com.cybersoft.core.services.paymentType.PaymentTypeService;
import co.com.cybersoft.events.paymentType.CreatePaymentTypeEvent;
import co.com.cybersoft.web.domain.paymentType.PaymentTypeInfo;

/**
 * Controller for paymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/paymentType/createPaymentType/{from}")
public class PaymentTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentTypeCreationController.class);
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String paymentTypeCreation(){
		return "/configuration/paymentType/createPaymentType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createPaymentType(@Valid @ModelAttribute("paymentTypeInfo") PaymentTypeInfo paymentTypeInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of an paymentType!!!");
		paymentTypeInfo.setCreated(false);
		PaymentTypeDetails paymentTypeDetails = createPaymentTypeDetails(paymentTypeInfo);
		paymentTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		request.getSession().setAttribute("paymentTypeInfo", paymentTypeInfo);
		paymentTypeService.createPaymentType(new CreatePaymentTypeEvent(paymentTypeDetails));
		String calledFrom = paymentTypeInfo.getCalledFrom();
		paymentTypeInfo=new PaymentTypeInfo();
		paymentTypeInfo.setCreated(true);
		paymentTypeInfo.setCalledFrom(calledFrom);
		model.addAttribute("paymentTypeInfo", paymentTypeInfo);
		return "/configuration/paymentType/createPaymentType";
	}
	
	private PaymentTypeDetails createPaymentTypeDetails(PaymentTypeInfo paymentTypeInfo){
		PaymentTypeDetails paymentTypeDetails = new PaymentTypeDetails();
		LOG.debug(paymentTypeInfo.getCode());
		BeanUtils.copyProperties(paymentTypeInfo, paymentTypeDetails);
		return paymentTypeDetails;
	}
	
	@ModelAttribute("paymentTypeInfo")
	private PaymentTypeInfo getPaymentTypeInfo(@PathVariable("from") String calledFrom){
		PaymentTypeInfo paymentTypeInfo = new PaymentTypeInfo();
		paymentTypeInfo.setCalledFrom(calledFrom);
		return paymentTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("paymentTypeInfo", req.getSession().getAttribute("paymentTypeInfo"));
		modelAndView.addObject("paymentTypeCreateException",true);
		modelAndView.setViewName("/configuration/paymentType/createPaymentType");
		exception.printStackTrace();
		return modelAndView;
	}
	
}