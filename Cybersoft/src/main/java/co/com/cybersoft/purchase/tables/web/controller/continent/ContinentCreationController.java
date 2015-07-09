package co.com.cybersoft.purchase.tables.web.controller.continent;

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
import org.springframework.transaction.annotation.Transactional;
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

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.events.continent.CreateContinentEvent;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentInfo;
import co.com.cybersoft.purchase.tables.events.continent.ContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentDetailsEvent;



/**
 * Controller for continent
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/continent/createContinent/{from}")
public class ContinentCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ContinentCreationController.class);
	
	@Autowired
	private ContinentService continentService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String continentCreation() throws Exception {
		return "/purchase/continent/createContinent";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createContinent(@Valid @ModelAttribute("continentInfo") ContinentInfo continentInfo, Model model, HttpServletRequest request) throws Exception{
		continentInfo.setCreated(false);
		ContinentDetails continentDetails = createContinentDetails(continentInfo);
		continentDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		continentDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		continentDetails.setDateOfCreation(new Date());
		continentDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("continentInfo", continentInfo);
		continentService.createContinent(new CreateContinentEvent(continentDetails));
		String calledFrom = continentInfo.getCalledFrom();
		continentInfo=new ContinentInfo();
		continentInfo.setCreated(true);
		continentInfo.setCalledFrom(calledFrom);
		
		continentInfo.setActive(true);

		
		model.addAttribute("continentInfo", continentInfo);
		return "/purchase/continent/createContinent";
	}
	
	private ContinentDetails createContinentDetails(ContinentInfo continentInfo){
		ContinentDetails continentDetails = new ContinentDetails();
		BeanUtils.copyProperties(continentInfo, continentDetails);
		return continentDetails;
	}
	
	@ModelAttribute("continentInfo")
	private ContinentInfo getContinentInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ContinentInfo continentInfo = new ContinentInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestContinentDetailsEvent event = new RequestContinentDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ContinentDetailsEvent responseEvent = continentService.requestContinentDetails(event);
			if (responseEvent.getContinentDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getContinentDetails(), continentInfo);
		}
		
		
		continentInfo.setId(null);
		continentInfo.setActive(true);

		
		continentInfo.setCalledFrom(calledFrom);
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
			modelAndView.setViewName("/purchase/continent/createContinent");
		}
		else{
			modelAndView.addObject("continentInfo", req.getSession().getAttribute("continentInfo"));
			modelAndView.addObject("continentCreateException",true);
			modelAndView.setViewName("/purchase/continent/createContinent");
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