package co.com.cybersoft.purchase.tables.web.controller.exchangeRate;

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
import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;
import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.purchase.tables.events.exchangeRate.CreateExchangeRateEvent;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateInfo;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRateDetailsEvent;


import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;


/**
 * Controller for exchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/exchangeRate/createExchangeRate/{from}")
public class ExchangeRateCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateCreationController.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
		private CurrencyService currencyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String exchangeRateCreation() throws Exception {
		return "/purchase/exchangeRate/createExchangeRate";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createExchangeRate(@Valid @ModelAttribute("exchangeRateInfo") ExchangeRateInfo exchangeRateInfo, Model model, HttpServletRequest request) throws Exception{
		exchangeRateInfo.setCreated(false);
		ExchangeRateDetails exchangeRateDetails = createExchangeRateDetails(exchangeRateInfo);
		exchangeRateDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		exchangeRateDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		exchangeRateDetails.setDateOfCreation(new Date());
		exchangeRateDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("exchangeRateInfo", exchangeRateInfo);
		exchangeRateService.createExchangeRate(new CreateExchangeRateEvent(exchangeRateDetails));
		String calledFrom = exchangeRateInfo.getCalledFrom();
		exchangeRateInfo=new ExchangeRateInfo();
		exchangeRateInfo.setCreated(true);
		exchangeRateInfo.setCalledFrom(calledFrom);
		EmbeddedField[] _embeddedFields=new EmbeddedField[1];
		EmbeddedField _embeddedField=new EmbeddedField("Currency", null);
		_embeddedFields[0]=_embeddedField;
		CurrencyPageEvent allLocalCurrencyEvent = currencyService.requestAllByCode(_embeddedFields);
		exchangeRateInfo.setLocalCurrencyList(allLocalCurrencyEvent.getCurrencyList());CurrencyPageEvent allForeignCurrencyEvent = currencyService.requestAllByCode();
		exchangeRateInfo.setForeignCurrencyList(allForeignCurrencyEvent.getCurrencyList());
		
		exchangeRateInfo.setDate(new Date());
		exchangeRateInfo.setActive(true);

		
		model.addAttribute("exchangeRateInfo", exchangeRateInfo);
		return "/purchase/exchangeRate/createExchangeRate";
	}
	
	private ExchangeRateDetails createExchangeRateDetails(ExchangeRateInfo exchangeRateInfo){
		ExchangeRateDetails exchangeRateDetails = new ExchangeRateDetails();
		BeanUtils.copyProperties(exchangeRateInfo, exchangeRateDetails);
		return exchangeRateDetails;
	}
	
	@ModelAttribute("exchangeRateInfo")
	private ExchangeRateInfo getExchangeRateInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ExchangeRateInfo exchangeRateInfo = new ExchangeRateInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
	
		EmbeddedField[] _embeddedFields=new EmbeddedField[1];
		EmbeddedField _embeddedField=new EmbeddedField("Currency", null);
		_embeddedFields[0]=_embeddedField;
		CurrencyPageEvent allLocalCurrencyEvent = currencyService.requestAllByCode(_embeddedFields);
		exchangeRateInfo.setLocalCurrencyList(allLocalCurrencyEvent.getCurrencyList());CurrencyPageEvent allForeignCurrencyEvent = currencyService.requestAllByCode();
		exchangeRateInfo.setForeignCurrencyList(allForeignCurrencyEvent.getCurrencyList());
		
		
		if (value!=null){
			RequestExchangeRateDetailsEvent event = new RequestExchangeRateDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ExchangeRateDetailsEvent responseEvent = exchangeRateService.requestExchangeRateDetails(event);
			if (responseEvent.getExchangeRateDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getExchangeRateDetails(), exchangeRateInfo);
		}
		
		
		exchangeRateInfo.setId(null);
		exchangeRateInfo.setDate(new Date());
		exchangeRateInfo.setActive(true);

		
		exchangeRateInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("exchangeRateInfo", exchangeRateInfo);
		
		return exchangeRateInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ExchangeRateInfo exchangeRateInfo=(ExchangeRateInfo) req.getSession().getAttribute("exchangeRateInfo");
			modelAndView.addObject("exchangeRateInfo", exchangeRateInfo);
			modelAndView.addObject("exchangeRateValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, exchangeRateInfo);
			modelAndView.setViewName("/purchase/exchangeRate/createExchangeRate");
		}
		else{
			modelAndView.addObject("exchangeRateInfo", req.getSession().getAttribute("exchangeRateInfo"));
			modelAndView.addObject("exchangeRateCreateException",true);
			modelAndView.setViewName("/purchase/exchangeRate/createExchangeRate");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ExchangeRateInfo exchangeRateInfo){
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