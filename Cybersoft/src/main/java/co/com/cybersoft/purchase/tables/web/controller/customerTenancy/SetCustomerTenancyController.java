package co.com.cybersoft.purchase.tables.web.controller.customerTenancy;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.CustomerTenancyDetails;
import co.com.cybersoft.purchase.tables.core.services.customerTenancy.CustomerTenancyService;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.CustomerTenancyModificationEvent;
import co.com.cybersoft.purchase.tables.events.customerTenancy.RequestCustomerTenancyDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.customerTenancy.CustomerTenancyInfo;

import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;


/**
 * Controller class for CustomerTenancy
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/customerTenancy/setCustomerTenancy")
public class SetCustomerTenancyController {
	private static final Logger LOG=LoggerFactory.getLogger(SetCustomerTenancyController.class);
	
	@Autowired
	private CustomerTenancyService customerTenancyService;
	
	@Autowired
		private CurrencyService currencyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/purchase/customerTenancy/setCustomerTenancy";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyCustomerTenancy(@Valid @ModelAttribute("customerTenancyInfo") CustomerTenancyInfo customerTenancyInfo, HttpServletRequest request) throws Exception {
		CustomerTenancyDetails customerTenancyDetails = createCustomerTenancyDetails(customerTenancyInfo);
		customerTenancyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		customerTenancyDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("customerTenancyInfo", customerTenancyInfo);
		customerTenancyService.modifyCustomerTenancy(new CustomerTenancyModificationEvent(customerTenancyDetails));
		return "redirect:/settings";
	}
	
	private CustomerTenancyDetails createCustomerTenancyDetails(CustomerTenancyInfo customerTenancyInfo){
		CustomerTenancyDetails customerTenancyDetails = new CustomerTenancyDetails();
		BeanUtils.copyProperties(customerTenancyInfo, customerTenancyDetails);
		return customerTenancyDetails;
	}

	@ModelAttribute("customerTenancyInfo")
	private CustomerTenancyInfo getCustomerTenancyInfo(HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the customerTenancy");
		CustomerTenancyInfo customerTenancyInfo = new CustomerTenancyInfo();
		
		CustomerTenancyDetailsEvent requestCustomerTenancyDetails = customerTenancyService.requestOnlyRecord();
		CurrencyPageEvent allLocalCurrencyEvent = currencyService.requestAllByCode();
		customerTenancyInfo.setLocalCurrencyList(allLocalCurrencyEvent.getCurrencyList());CurrencyPageEvent allForeignCurrencyEvent = currencyService.requestAllByCode();
		customerTenancyInfo.setForeignCurrencyList(allForeignCurrencyEvent.getCurrencyList());
		request.getSession().setAttribute("customerTenancyInfo", customerTenancyInfo);
		
		BeanUtils.copyProperties(requestCustomerTenancyDetails.getCustomerTenancyDetails(), customerTenancyInfo);
		return customerTenancyInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CustomerTenancyInfo customerTenancyInfo=(CustomerTenancyInfo) req.getSession().getAttribute("customerTenancyInfo");
			modelAndView.addObject("customerTenancyInfo", customerTenancyInfo);
			modelAndView.addObject("customerTenancyValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, customerTenancyInfo);
			modelAndView.setViewName("/purchase/customerTenancy/setCustomerTenancy");
		}
		else{
			modelAndView.addObject("customerTenancyInfo", req.getSession().getAttribute("customerTenancyInfo"));
			modelAndView.addObject("customerTenancyModificationException",true);
			modelAndView.setViewName("/purchase/customerTenancy/setCustomerTenancy");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CustomerTenancyInfo customerTenancyInfo){
		List<ObjectError> errors = exception.getAllErrors();
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (fieldError.getCodes()[0].startsWith(CyberUtils.lengthValidation)
							||fieldError.getCodes()[0].startsWith(CyberUtils.rangeValidation)){
						
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"outOfRangeErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.notEmptyValidation)  
							|| fieldError.getCodes()[0].startsWith(CyberUtils.notNullValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"requiredFieldErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.typeValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"fieldTypeErrorValidationMessage");
						
					}
					else{
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"genericErrorValidationMessage");
						
					}
				}
			}
		}
		return model;
	}
}