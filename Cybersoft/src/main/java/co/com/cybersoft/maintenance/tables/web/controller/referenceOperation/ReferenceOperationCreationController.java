package co.com.cybersoft.maintenance.tables.web.controller.referenceOperation;

import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;
import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationService;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.CreateReferenceOperationEvent;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationInfo;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationService;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;


/**
 * Controller for referenceOperation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/referenceOperation/createReferenceOperation/{from}")
public class ReferenceOperationCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ReferenceOperationCreationController.class);
	
	@Autowired
	private ReferenceOperationService referenceOperationService;
	
	@Autowired
		private ReferenceService referenceService;

	@Autowired
		private OperationService operationService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/referenceOperation/createReferenceOperation";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createReferenceOperation(@Valid @ModelAttribute("referenceOperationInfo") ReferenceOperationInfo referenceOperationInfo, Model model, HttpServletRequest request) throws Exception{
		referenceOperationInfo.setCreated(false);
		ReferenceOperationDetails referenceOperationDetails = createReferenceOperationDetails(referenceOperationInfo);
		referenceOperationDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceOperationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceOperationDetails.setDateOfCreation(new Date());
		referenceOperationDetails.setDateOfModification(new Date());
		//referenceOperationDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("referenceOperationInfo", referenceOperationInfo);
		referenceOperationService.createReferenceOperation(new CreateReferenceOperationEvent(referenceOperationDetails));
		String calledFrom = referenceOperationInfo.getCalledFrom();
		referenceOperationInfo=new ReferenceOperationInfo();
		referenceOperationInfo.setCreated(true);
		referenceOperationInfo.setCalledFrom(calledFrom);
		ReferencePageEvent allReferenceEvent = referenceService.requestAllByNameReference();
		referenceOperationInfo.setReferenceList(allReferenceEvent.getReferenceList());
		OperationPageEvent allOperationEvent = operationService.requestAllByNameOperation();
		referenceOperationInfo.setOperationList(allOperationEvent.getOperationList());

		
		referenceOperationInfo.setActive(true);

		
		model.addAttribute("referenceOperationInfo", referenceOperationInfo);
		return "/maintenance/referenceOperation/createReferenceOperation";
	}
	
	private ReferenceOperationDetails createReferenceOperationDetails(ReferenceOperationInfo referenceOperationInfo){
		ReferenceOperationDetails referenceOperationDetails = new ReferenceOperationDetails();
		BeanUtils.copyProperties(referenceOperationInfo, referenceOperationDetails);
		return referenceOperationDetails;
	}
	
	@ModelAttribute("referenceOperationInfo")
	private ReferenceOperationInfo getReferenceOperationInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ReferenceOperationInfo referenceOperationInfo = new ReferenceOperationInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		ReferencePageEvent allReferenceEvent = referenceService.requestAllByNameReference();
		referenceOperationInfo.setReferenceList(allReferenceEvent.getReferenceList());
		OperationPageEvent allOperationEvent = operationService.requestAllByNameOperation();
		referenceOperationInfo.setOperationList(allOperationEvent.getOperationList());

		
		
		if (value!=null){
			RequestReferenceOperationDetailsEvent event = new RequestReferenceOperationDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ReferenceOperationDetailsEvent responseEvent = referenceOperationService.requestReferenceOperationDetails(event);
			if (responseEvent.getReferenceOperationDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getReferenceOperationDetails(), referenceOperationInfo);
		}
		
		
		referenceOperationInfo.setId(null);
		referenceOperationInfo.setActive(true);

		
		referenceOperationInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("referenceOperationInfo", referenceOperationInfo);
		
		return referenceOperationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ReferenceOperationInfo referenceOperationInfo=(ReferenceOperationInfo) req.getSession().getAttribute("referenceOperationInfo");
			modelAndView.addObject("referenceOperationInfo", referenceOperationInfo);
			modelAndView.addObject("referenceOperationValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, referenceOperationInfo);
			modelAndView.setViewName("/maintenance/referenceOperation/createReferenceOperation");
		}
		else{
			modelAndView.addObject("referenceOperationInfo", req.getSession().getAttribute("referenceOperationInfo"));
			modelAndView.addObject("referenceOperationCreateException",true);
			modelAndView.setViewName("/maintenance/referenceOperation/createReferenceOperation");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ReferenceOperationInfo referenceOperationInfo){
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