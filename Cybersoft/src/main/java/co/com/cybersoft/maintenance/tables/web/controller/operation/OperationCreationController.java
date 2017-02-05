package co.com.cybersoft.maintenance.tables.web.controller.operation;

import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationService;
import co.com.cybersoft.maintenance.tables.events.operation.CreateOperationEvent;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationInfo;
import co.com.cybersoft.maintenance.tables.events.operation.OperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for operation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/operation/createOperation/{from}")
public class OperationCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(OperationCreationController.class);
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/operation/createOperation";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createOperation(@Valid @ModelAttribute("operationInfo") OperationInfo operationInfo, Model model, HttpServletRequest request) throws Exception{
		operationInfo.setCreated(false);
		OperationDetails operationDetails = createOperationDetails(operationInfo);
		operationDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		operationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		operationDetails.setDateOfCreation(new Date());
		operationDetails.setDateOfModification(new Date());
		//operationDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("operationInfo", operationInfo);
		operationService.createOperation(new CreateOperationEvent(operationDetails));
		String calledFrom = operationInfo.getCalledFrom();
		operationInfo=new OperationInfo();
		operationInfo.setCreated(true);
		operationInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		operationInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		operationInfo.setActive(true);

		
		model.addAttribute("operationInfo", operationInfo);
		return "/maintenance/operation/createOperation";
	}
	
	private OperationDetails createOperationDetails(OperationInfo operationInfo){
		OperationDetails operationDetails = new OperationDetails();
		BeanUtils.copyProperties(operationInfo, operationDetails);
		return operationDetails;
	}
	
	@ModelAttribute("operationInfo")
	private OperationInfo getOperationInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		OperationInfo operationInfo = new OperationInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		operationInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestOperationDetailsEvent event = new RequestOperationDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			OperationDetailsEvent responseEvent = operationService.requestOperationDetails(event);
			if (responseEvent.getOperationDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getOperationDetails(), operationInfo);
		}
		
		
		operationInfo.setId(null);
		operationInfo.setActive(true);

		
		operationInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("operationInfo", operationInfo);
		
		return operationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			OperationInfo operationInfo=(OperationInfo) req.getSession().getAttribute("operationInfo");
			modelAndView.addObject("operationInfo", operationInfo);
			modelAndView.addObject("operationValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, operationInfo);
			modelAndView.setViewName("/maintenance/operation/createOperation");
		}
		else{
			modelAndView.addObject("operationInfo", req.getSession().getAttribute("operationInfo"));
			modelAndView.addObject("operationCreateException",true);
			modelAndView.setViewName("/maintenance/operation/createOperation");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, OperationInfo operationInfo){
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