package co.com.cybersoft.purchase.tables.web.controller.currency;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import org.springframework.ui.Model;

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
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CreateCurrencyEvent;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyInfo;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyDetailsEvent;


import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;


/**
 * Controller for currency
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currency/createCurrency/{from}")
public class CurrencyCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CurrencyCreationController.class);
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
		private CurrencyCodeService currencyCodeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/currency/createCurrency";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCurrency(@Valid @ModelAttribute("currencyInfo") CurrencyInfo currencyInfo, Model model, HttpServletRequest request) throws Exception{
		currencyInfo.setCreated(false);
		CurrencyDetails currencyDetails = createCurrencyDetails(currencyInfo);
		currencyDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyDetails.setDateOfCreation(new Date());
		currencyDetails.setDateOfModification(new Date());
		//currencyDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("currencyInfo", currencyInfo);
		currencyService.createCurrency(new CreateCurrencyEvent(currencyDetails));
		String calledFrom = currencyInfo.getCalledFrom();
		currencyInfo=new CurrencyInfo();
		currencyInfo.setCreated(true);
		currencyInfo.setCalledFrom(calledFrom);
		CurrencyCodePageEvent allCodeEvent = currencyCodeService.requestAllByCurrency();
		currencyInfo.setCodeList(allCodeEvent.getCurrencyCodeList());

		
		currencyInfo.setActive(true);

		
		model.addAttribute("currencyInfo", currencyInfo);
		return "/purchase/currency/createCurrency";
	}
	
	private CurrencyDetails createCurrencyDetails(CurrencyInfo currencyInfo){
		CurrencyDetails currencyDetails = new CurrencyDetails();
		BeanUtils.copyProperties(currencyInfo, currencyDetails);
		return currencyDetails;
	}
	
	@ModelAttribute("currencyInfo")
	private CurrencyInfo getCurrencyInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CurrencyInfo currencyInfo = new CurrencyInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CurrencyCodePageEvent allCodeEvent = currencyCodeService.requestAllByCurrency();
		currencyInfo.setCodeList(allCodeEvent.getCurrencyCodeList());

		
		
		if (value!=null){
			RequestCurrencyDetailsEvent event = new RequestCurrencyDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CurrencyDetailsEvent responseEvent = currencyService.requestCurrencyDetails(event);
			if (responseEvent.getCurrencyDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCurrencyDetails(), currencyInfo);
		}
		
		
		currencyInfo.setId(null);
		currencyInfo.setActive(true);

		
		currencyInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("currencyInfo", currencyInfo);
		
		return currencyInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CurrencyInfo currencyInfo=(CurrencyInfo) req.getSession().getAttribute("currencyInfo");
			modelAndView.addObject("currencyInfo", currencyInfo);
			modelAndView.addObject("currencyValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, currencyInfo);
			modelAndView.setViewName("/purchase/currency/createCurrency");
		}
		else{
			modelAndView.addObject("currencyInfo", req.getSession().getAttribute("currencyInfo"));
			modelAndView.addObject("currencyCreateException",true);
			modelAndView.setViewName("/purchase/currency/createCurrency");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CurrencyInfo currencyInfo){
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