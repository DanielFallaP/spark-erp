package co.com.cybersoft.purchase.tables.web.controller.country;

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
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.events.country.CountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryModificationEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryInfo;
import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;


/**
 * Controller class for Country
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/country/modifyCountry/{id}")
public class CountryModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CountryModificationController.class);
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
		private RegionService regionService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/country/modifyCountry";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCountry(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("countryInfo") CountryInfo countryInfo, HttpServletRequest request) throws Exception {
		
		CountryDetails countryDetails = createCountryDetails(countryInfo);
		countryDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		countryDetails.setDateOfModification(new Date());
		//countryDetails.set_companyId(((CountryDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("countryInfo", countryInfo);
		countryService.modifyCountry(new CountryModificationEvent(countryDetails));
		return "redirect:/purchase/country/searchCountry";
	}
	
	private CountryDetails createCountryDetails(CountryInfo countryInfo){
		CountryDetails countryDetails = new CountryDetails();
		BeanUtils.copyProperties(countryInfo, countryDetails);
		return countryDetails;
	}

	@ModelAttribute("countryInfo")
	private CountryInfo getCountryInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the country "+id);
		
		CountryInfo countryInfo = new CountryInfo();
			
		CountryDetailsEvent requestCountryDetails = countryService.requestCountryDetails(new RequestCountryDetailsEvent(id));
		
		RegionPageEvent allRegionEvent = regionService.requestAllByRegion();
		countryInfo.setRegionList(allRegionEvent.getRegionList());

		
		BeanUtils.copyProperties(requestCountryDetails.getCountryDetails(), countryInfo);
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
			modelAndView.setViewName("/purchase/country/modifyCountry");
		}
		else{
			modelAndView.addObject("countryInfo", req.getSession().getAttribute("countryInfo"));
			modelAndView.addObject("countryModificationException",true);
			modelAndView.setViewName("/purchase/country/modifyCountry");
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