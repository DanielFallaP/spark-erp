package co.com.cybersoft.purchase.tables.web.controller.country;

import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;

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
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.events.country.CreateCountryEvent;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryInfo;
import co.com.cybersoft.purchase.tables.events.country.CountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryDetailsEvent;


import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;


/**
 * Controller for country
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/country/createCountry/{from}")
public class CountryCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CountryCreationController.class);
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
		private RegionService regionService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/country/createCountry";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCountry(@Valid @ModelAttribute("countryInfo") CountryInfo countryInfo, Model model, HttpServletRequest request) throws Exception{
		countryInfo.setCreated(false);
		CountryDetails countryDetails = createCountryDetails(countryInfo);
		countryDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		countryDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		countryDetails.setDateOfCreation(new Date());
		countryDetails.setDateOfModification(new Date());
		//countryDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("countryInfo", countryInfo);
		countryService.createCountry(new CreateCountryEvent(countryDetails));
		String calledFrom = countryInfo.getCalledFrom();
		countryInfo=new CountryInfo();
		countryInfo.setCreated(true);
		countryInfo.setCalledFrom(calledFrom);
		RegionPageEvent allRegionEvent = regionService.requestAllByRegion();
		countryInfo.setRegionList(allRegionEvent.getRegionList());

		
		countryInfo.setActive(true);

		
		model.addAttribute("countryInfo", countryInfo);
		return "/purchase/country/createCountry";
	}
	
	private CountryDetails createCountryDetails(CountryInfo countryInfo){
		CountryDetails countryDetails = new CountryDetails();
		BeanUtils.copyProperties(countryInfo, countryDetails);
		return countryDetails;
	}
	
	@ModelAttribute("countryInfo")
	private CountryInfo getCountryInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CountryInfo countryInfo = new CountryInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		RegionPageEvent allRegionEvent = regionService.requestAllByRegion();
		countryInfo.setRegionList(allRegionEvent.getRegionList());

		
		
		if (value!=null){
			RequestCountryDetailsEvent event = new RequestCountryDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CountryDetailsEvent responseEvent = countryService.requestCountryDetails(event);
			if (responseEvent.getCountryDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCountryDetails(), countryInfo);
		}
		
		
		countryInfo.setId(null);
		countryInfo.setActive(true);

		
		countryInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("countryInfo", countryInfo);
		
		return countryInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CountryInfo countryInfo=(CountryInfo) req.getSession().getAttribute("countryInfo");
			modelAndView.addObject("countryInfo", countryInfo);
			modelAndView.addObject("countryValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, countryInfo);
			modelAndView.setViewName("/purchase/country/createCountry");
		}
		else{
			modelAndView.addObject("countryInfo", req.getSession().getAttribute("countryInfo"));
			modelAndView.addObject("countryCreateException",true);
			modelAndView.setViewName("/purchase/country/createCountry");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CountryInfo countryInfo){
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