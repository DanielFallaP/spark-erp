package co.com.cybersoft.maintenance.tables.web.controller.reference;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.maintenance.tables.events.reference.CreateReferenceEvent;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceInfo;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferenceDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for reference
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/reference/createReference/{from}")
public class ReferenceCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ReferenceCreationController.class);
	
	@Autowired
	private ReferenceService referenceService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/reference/createReference";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createReference(@Valid @ModelAttribute("referenceInfo") ReferenceInfo referenceInfo, Model model, HttpServletRequest request) throws Exception{
		referenceInfo.setCreated(false);
		ReferenceDetails referenceDetails = createReferenceDetails(referenceInfo);
		referenceDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceDetails.setDateOfCreation(new Date());
		referenceDetails.setDateOfModification(new Date());
		//referenceDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("referenceInfo", referenceInfo);
		referenceService.createReference(new CreateReferenceEvent(referenceDetails));
		String calledFrom = referenceInfo.getCalledFrom();
		referenceInfo=new ReferenceInfo();
		referenceInfo.setCreated(true);
		referenceInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		referenceInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		referenceInfo.setActive(true);

		
		model.addAttribute("referenceInfo", referenceInfo);
		return "/maintenance/reference/createReference";
	}
	
	private ReferenceDetails createReferenceDetails(ReferenceInfo referenceInfo){
		ReferenceDetails referenceDetails = new ReferenceDetails();
		BeanUtils.copyProperties(referenceInfo, referenceDetails);
		return referenceDetails;
	}
	
	@ModelAttribute("referenceInfo")
	private ReferenceInfo getReferenceInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ReferenceInfo referenceInfo = new ReferenceInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		referenceInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestReferenceDetailsEvent event = new RequestReferenceDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ReferenceDetailsEvent responseEvent = referenceService.requestReferenceDetails(event);
			if (responseEvent.getReferenceDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getReferenceDetails(), referenceInfo);
		}
		
		
		referenceInfo.setId(null);
		referenceInfo.setActive(true);

		
		referenceInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("referenceInfo", referenceInfo);
		
		return referenceInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ReferenceInfo referenceInfo=(ReferenceInfo) req.getSession().getAttribute("referenceInfo");
			modelAndView.addObject("referenceInfo", referenceInfo);
			modelAndView.addObject("referenceValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, referenceInfo);
			modelAndView.setViewName("/maintenance/reference/createReference");
		}
		else{
			modelAndView.addObject("referenceInfo", req.getSession().getAttribute("referenceInfo"));
			modelAndView.addObject("referenceCreateException",true);
			modelAndView.setViewName("/maintenance/reference/createReference");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ReferenceInfo referenceInfo){
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