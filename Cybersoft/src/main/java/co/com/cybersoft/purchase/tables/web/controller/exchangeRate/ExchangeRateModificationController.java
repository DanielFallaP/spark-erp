package co.com.cybersoft.purchase.tables.web.controller.exchangeRate;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.transaction.annotation.Transactional;


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
import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;
import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateModificationEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateInfo;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;


/**
 * Controller class for ExchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/exchangeRate/modifyExchangeRate/{id}")
public class ExchangeRateModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ExchangeRateModificationController.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
		private CurrencyService currencyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/purchase/exchangeRate/modifyExchangeRate";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyExchangeRate(@PathVariable("id") Long id, @Valid @ModelAttribute("exchangeRateInfo") ExchangeRateInfo exchangeRateInfo, HttpServletRequest request) throws Exception {
		
		ExchangeRateDetails exchangeRateDetails = createExchangeRateDetails(exchangeRateInfo);
		exchangeRateDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		exchangeRateDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("exchangeRateInfo", exchangeRateInfo);
		exchangeRateService.modifyExchangeRate(new ExchangeRateModificationEvent(exchangeRateDetails));
		return "redirect:/purchase/exchangeRate/searchExchangeRate";
	}
	
	private ExchangeRateDetails createExchangeRateDetails(ExchangeRateInfo exchangeRateInfo){
		ExchangeRateDetails exchangeRateDetails = new ExchangeRateDetails();
		BeanUtils.copyProperties(exchangeRateInfo, exchangeRateDetails);
		return exchangeRateDetails;
	}

	@ModelAttribute("exchangeRateInfo")
	private ExchangeRateInfo getExchangeRateInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the exchangeRate "+id);
		
		ExchangeRateInfo exchangeRateInfo = new ExchangeRateInfo();
			
		ExchangeRateDetailsEvent requestExchangeRateDetails = exchangeRateService.requestExchangeRateDetails(new RequestExchangeRateDetailsEvent(id));
		
		EmbeddedField[] _additionalFieldsLocalCurrency=new EmbeddedField[1];
		EmbeddedField _additionalFieldLocalCurrencyCurrency=new EmbeddedField("Currency", null);
		_additionalFieldsLocalCurrency[0]=_additionalFieldLocalCurrencyCurrency;
		EmbeddedField[] _additionalFieldsForeignCurrency=new EmbeddedField[1];
		EmbeddedField _additionalFieldForeignCurrencyCurrency=new EmbeddedField("Currency", null);
		_additionalFieldsForeignCurrency[0]=_additionalFieldForeignCurrencyCurrency;

		CurrencyPageEvent allLocalCurrencyEvent = currencyService.requestAllByCode(_additionalFieldsLocalCurrency);
		exchangeRateInfo.setLocalCurrencyList(allLocalCurrencyEvent.getCurrencyList());
		CurrencyPageEvent allForeignCurrencyEvent = currencyService.requestAllByCode(_additionalFieldsForeignCurrency);
		exchangeRateInfo.setForeignCurrencyList(allForeignCurrencyEvent.getCurrencyList());

		
		BeanUtils.copyProperties(requestExchangeRateDetails.getExchangeRateDetails(), exchangeRateInfo);
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
			modelAndView.setViewName("/purchase/exchangeRate/modifyExchangeRate");
		}
		else{
			modelAndView.addObject("exchangeRateInfo", req.getSession().getAttribute("exchangeRateInfo"));
			modelAndView.addObject("exchangeRateModificationException",true);
			modelAndView.setViewName("/purchase/exchangeRate/modifyExchangeRate");
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