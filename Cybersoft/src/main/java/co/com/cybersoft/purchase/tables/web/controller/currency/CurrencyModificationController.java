package co.com.cybersoft.purchase.tables.web.controller.currency;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.ui.Model;
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
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyModificationEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyInfo;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;


/**
 * Controller class for Currency
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currency/modifyCurrency/{id}")
public class CurrencyModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CurrencyModificationController.class);
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
		private CurrencyCodeService currencyCodeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));

		return "/purchase/currency/modifyCurrency";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCurrency(Model model,@PathVariable("id") Long id, @Valid @ModelAttribute("currencyInfo") CurrencyInfo currencyInfo, HttpServletRequest request) throws Exception {
		
		CurrencyDetails currencyDetails = createCurrencyDetails(currencyInfo);
		currencyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyDetails.setDateOfModification(new Date());
		currencyDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));

		request.getSession().setAttribute("currencyInfo", currencyInfo);
		currencyService.modifyCurrency(new CurrencyModificationEvent(currencyDetails));
		return "redirect:/purchase/currency/searchCurrency";
	}
	
	private CurrencyDetails createCurrencyDetails(CurrencyInfo currencyInfo){
		CurrencyDetails currencyDetails = new CurrencyDetails();
		BeanUtils.copyProperties(currencyInfo, currencyDetails);
		return currencyDetails;
	}

	@ModelAttribute("currencyInfo")
	private CurrencyInfo getCurrencyInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the currency "+id);
		
		CurrencyInfo currencyInfo = new CurrencyInfo();
			
		CurrencyDetailsEvent requestCurrencyDetails = currencyService.requestCurrencyDetails(new RequestCurrencyDetailsEvent(id));
		
		CurrencyCodePageEvent allCodeEvent = currencyCodeService.requestAllByCurrency();
		currencyInfo.setCodeList(allCodeEvent.getCurrencyCodeList());

		
		BeanUtils.copyProperties(requestCurrencyDetails.getCurrencyDetails(), currencyInfo);
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
			modelAndView.setViewName("/purchase/currency/modifyCurrency");
		}
		else{
			modelAndView.addObject("currencyInfo", req.getSession().getAttribute("currencyInfo"));
			modelAndView.addObject("currencyModificationException",true);
			modelAndView.setViewName("/purchase/currency/modifyCurrency");
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