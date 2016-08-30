package co.com.cybersoft.purchase.tables.web.controller.continent;

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
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.events.continent.ContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentModificationEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentInfo;

/**
 * Controller class for Continent
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/continent/modifyContinent/{id}")
public class ContinentModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ContinentModificationController.class);
	
	@Autowired
	private ContinentService continentService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/purchase/continent/modifyContinent";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyContinent(@PathVariable("id") Long id, @Valid @ModelAttribute("continentInfo") ContinentInfo continentInfo, HttpServletRequest request) throws Exception {
		
		ContinentDetails continentDetails = createContinentDetails(continentInfo);
		continentDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		continentDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("continentInfo", continentInfo);
		continentService.modifyContinent(new ContinentModificationEvent(continentDetails));
		return "redirect:/purchase/continent/searchContinent";
	}
	
	private ContinentDetails createContinentDetails(ContinentInfo continentInfo){
		ContinentDetails continentDetails = new ContinentDetails();
		BeanUtils.copyProperties(continentInfo, continentDetails);
		return continentDetails;
	}

	@ModelAttribute("continentInfo")
	private ContinentInfo getContinentInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the continent "+id);
		
		ContinentInfo continentInfo = new ContinentInfo();
			
		ContinentDetailsEvent requestContinentDetails = continentService.requestContinentDetails(new RequestContinentDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestContinentDetails.getContinentDetails(), continentInfo);
		request.getSession().setAttribute("continentInfo", continentInfo);
		
		return continentInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ContinentInfo continentInfo=(ContinentInfo) req.getSession().getAttribute("continentInfo");
			modelAndView.addObject("continentInfo", continentInfo);
			modelAndView.addObject("continentValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, continentInfo);
			modelAndView.setViewName("/purchase/continent/modifyContinent");
		}
		else{
			modelAndView.addObject("continentInfo", req.getSession().getAttribute("continentInfo"));
			modelAndView.addObject("continentModificationException",true);
			modelAndView.setViewName("/purchase/continent/modifyContinent");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ContinentInfo continentInfo){
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