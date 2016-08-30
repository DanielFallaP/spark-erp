package co.com.cybersoft.purchase.tables.web.controller.currencyCode;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

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
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.events.currencyCode.CreateCurrencyCodeEvent;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeInfo;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodeDetailsEvent;


import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;


/**
 * Controller for currencyCode
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currencyCode/createCurrencyCode/{from}")
public class CurrencyCodeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CurrencyCodeCreationController.class);
	
	@Autowired
	private CurrencyCodeService currencyCodeService;
	
	@Autowired
		private CountryService countryService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String currencyCodeCreation() throws Exception {
		return "/purchase/currencyCode/createCurrencyCode";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCurrencyCode(@Valid @ModelAttribute("currencyCodeInfo") CurrencyCodeInfo currencyCodeInfo, Model model, HttpServletRequest request) throws Exception{
		currencyCodeInfo.setCreated(false);
		CurrencyCodeDetails currencyCodeDetails = createCurrencyCodeDetails(currencyCodeInfo);
		currencyCodeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyCodeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyCodeDetails.setDateOfCreation(new Date());
		currencyCodeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("currencyCodeInfo", currencyCodeInfo);
		currencyCodeService.createCurrencyCode(new CreateCurrencyCodeEvent(currencyCodeDetails));
		String calledFrom = currencyCodeInfo.getCalledFrom();
		currencyCodeInfo=new CurrencyCodeInfo();
		currencyCodeInfo.setCreated(true);
		currencyCodeInfo.setCalledFrom(calledFrom);
		CountryPageEvent allCountryEvent = countryService.requestAllByCountry();
		currencyCodeInfo.setCountryList(allCountryEvent.getCountryList());

		
		currencyCodeInfo.setActive(true);

		
		model.addAttribute("currencyCodeInfo", currencyCodeInfo);
		return "/purchase/currencyCode/createCurrencyCode";
	}
	
	private CurrencyCodeDetails createCurrencyCodeDetails(CurrencyCodeInfo currencyCodeInfo){
		CurrencyCodeDetails currencyCodeDetails = new CurrencyCodeDetails();
		BeanUtils.copyProperties(currencyCodeInfo, currencyCodeDetails);
		return currencyCodeDetails;
	}
	
	@ModelAttribute("currencyCodeInfo")
	private CurrencyCodeInfo getCurrencyCodeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CurrencyCodeInfo currencyCodeInfo = new CurrencyCodeInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CountryPageEvent allCountryEvent = countryService.requestAllByCountry();
		currencyCodeInfo.setCountryList(allCountryEvent.getCountryList());

		
		
		if (value!=null){
			RequestCurrencyCodeDetailsEvent event = new RequestCurrencyCodeDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CurrencyCodeDetailsEvent responseEvent = currencyCodeService.requestCurrencyCodeDetails(event);
			if (responseEvent.getCurrencyCodeDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCurrencyCodeDetails(), currencyCodeInfo);
		}
		
		
		currencyCodeInfo.setId(null);
		currencyCodeInfo.setActive(true);

		
		currencyCodeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("currencyCodeInfo", currencyCodeInfo);
		
		return currencyCodeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CurrencyCodeInfo currencyCodeInfo=(CurrencyCodeInfo) req.getSession().getAttribute("currencyCodeInfo");
			modelAndView.addObject("currencyCodeInfo", currencyCodeInfo);
			modelAndView.addObject("currencyCodeValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, currencyCodeInfo);
			modelAndView.setViewName("/purchase/currencyCode/createCurrencyCode");
		}
		else{
			modelAndView.addObject("currencyCodeInfo", req.getSession().getAttribute("currencyCodeInfo"));
			modelAndView.addObject("currencyCodeCreateException",true);
			modelAndView.setViewName("/purchase/currencyCode/createCurrencyCode");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CurrencyCodeInfo currencyCodeInfo){
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