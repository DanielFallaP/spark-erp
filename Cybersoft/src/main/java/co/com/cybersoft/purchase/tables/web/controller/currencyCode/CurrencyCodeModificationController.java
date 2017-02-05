package co.com.cybersoft.purchase.tables.web.controller.currencyCode;

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
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeModificationEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeInfo;
import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;


/**
 * Controller class for CurrencyCode
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currencyCode/modifyCurrencyCode/{id}")
public class CurrencyCodeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CurrencyCodeModificationController.class);
	
	@Autowired
	private CurrencyCodeService currencyCodeService;
	
	@Autowired
		private CountryService countryService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/currencyCode/modifyCurrencyCode";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCurrencyCode(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("currencyCodeInfo") CurrencyCodeInfo currencyCodeInfo, HttpServletRequest request) throws Exception {
		
		CurrencyCodeDetails currencyCodeDetails = createCurrencyCodeDetails(currencyCodeInfo);
		currencyCodeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyCodeDetails.setDateOfModification(new Date());
		//currencyCodeDetails.set_companyId(((CurrencyCodeDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("currencyCodeInfo", currencyCodeInfo);
		currencyCodeService.modifyCurrencyCode(new CurrencyCodeModificationEvent(currencyCodeDetails));
		return "redirect:/purchase/currencyCode/searchCurrencyCode";
	}
	
	private CurrencyCodeDetails createCurrencyCodeDetails(CurrencyCodeInfo currencyCodeInfo){
		CurrencyCodeDetails currencyCodeDetails = new CurrencyCodeDetails();
		BeanUtils.copyProperties(currencyCodeInfo, currencyCodeDetails);
		return currencyCodeDetails;
	}

	@ModelAttribute("currencyCodeInfo")
	private CurrencyCodeInfo getCurrencyCodeInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the currencyCode "+id);
		
		CurrencyCodeInfo currencyCodeInfo = new CurrencyCodeInfo();
			
		CurrencyCodeDetailsEvent requestCurrencyCodeDetails = currencyCodeService.requestCurrencyCodeDetails(new RequestCurrencyCodeDetailsEvent(id));
		
		CountryPageEvent allCountryEvent = countryService.requestAllByCountry();
		currencyCodeInfo.setCountryList(allCountryEvent.getCountryList());

		
		BeanUtils.copyProperties(requestCurrencyCodeDetails.getCurrencyCodeDetails(), currencyCodeInfo);
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
			modelAndView.setViewName("/purchase/currencyCode/modifyCurrencyCode");
		}
		else{
			modelAndView.addObject("currencyCodeInfo", req.getSession().getAttribute("currencyCodeInfo"));
			modelAndView.addObject("currencyCodeModificationException",true);
			modelAndView.setViewName("/purchase/currencyCode/modifyCurrencyCode");
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