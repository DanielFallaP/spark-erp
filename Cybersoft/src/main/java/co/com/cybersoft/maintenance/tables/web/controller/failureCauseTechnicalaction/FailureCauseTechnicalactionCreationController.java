package co.com.cybersoft.maintenance.tables.web.controller.failureCauseTechnicalaction;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;
import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionService;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.CreateFailureCauseTechnicalactionEvent;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionInfo;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;


/**
 * Controller for failureCauseTechnicalaction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCauseTechnicalaction/createFailureCauseTechnicalaction/{from}")
public class FailureCauseTechnicalactionCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(FailureCauseTechnicalactionCreationController.class);
	
	@Autowired
	private FailureCauseTechnicalactionService failureCauseTechnicalactionService;
	
	@Autowired
		private FailureCauseService failureCauseService;

	@Autowired
		private TechnicalActionService technicalActionService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/failureCauseTechnicalaction/createFailureCauseTechnicalaction";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createFailureCauseTechnicalaction(@Valid @ModelAttribute("failureCauseTechnicalactionInfo") FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo, Model model, HttpServletRequest request) throws Exception{
		failureCauseTechnicalactionInfo.setCreated(false);
		FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails = createFailureCauseTechnicalactionDetails(failureCauseTechnicalactionInfo);
		failureCauseTechnicalactionDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseTechnicalactionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseTechnicalactionDetails.setDateOfCreation(new Date());
		failureCauseTechnicalactionDetails.setDateOfModification(new Date());
		//failureCauseTechnicalactionDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
		failureCauseTechnicalactionService.createFailureCauseTechnicalaction(new CreateFailureCauseTechnicalactionEvent(failureCauseTechnicalactionDetails));
		String calledFrom = failureCauseTechnicalactionInfo.getCalledFrom();
		failureCauseTechnicalactionInfo=new FailureCauseTechnicalactionInfo();
		failureCauseTechnicalactionInfo.setCreated(true);
		failureCauseTechnicalactionInfo.setCalledFrom(calledFrom);
		FailureCausePageEvent allFailureCauseEvent = failureCauseService.requestAllByNameFailureCause();
		failureCauseTechnicalactionInfo.setFailureCauseList(allFailureCauseEvent.getFailureCauseList());
		TechnicalActionPageEvent allTechnicalActionEvent = technicalActionService.requestAllByTechnicalActionName();
		failureCauseTechnicalactionInfo.setTechnicalActionList(allTechnicalActionEvent.getTechnicalActionList());

		
		failureCauseTechnicalactionInfo.setActive(true);

		
		model.addAttribute("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
		return "/maintenance/failureCauseTechnicalaction/createFailureCauseTechnicalaction";
	}
	
	private FailureCauseTechnicalactionDetails createFailureCauseTechnicalactionDetails(FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo){
		FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails = new FailureCauseTechnicalactionDetails();
		BeanUtils.copyProperties(failureCauseTechnicalactionInfo, failureCauseTechnicalactionDetails);
		return failureCauseTechnicalactionDetails;
	}
	
	@ModelAttribute("failureCauseTechnicalactionInfo")
	private FailureCauseTechnicalactionInfo getFailureCauseTechnicalactionInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo = new FailureCauseTechnicalactionInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		FailureCausePageEvent allFailureCauseEvent = failureCauseService.requestAllByNameFailureCause();
		failureCauseTechnicalactionInfo.setFailureCauseList(allFailureCauseEvent.getFailureCauseList());
		TechnicalActionPageEvent allTechnicalActionEvent = technicalActionService.requestAllByTechnicalActionName();
		failureCauseTechnicalactionInfo.setTechnicalActionList(allTechnicalActionEvent.getTechnicalActionList());

		
		
		if (value!=null){
			RequestFailureCauseTechnicalactionDetailsEvent event = new RequestFailureCauseTechnicalactionDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			FailureCauseTechnicalactionDetailsEvent responseEvent = failureCauseTechnicalactionService.requestFailureCauseTechnicalactionDetails(event);
			if (responseEvent.getFailureCauseTechnicalactionDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getFailureCauseTechnicalactionDetails(), failureCauseTechnicalactionInfo);
		}
		
		
		failureCauseTechnicalactionInfo.setId(null);
		failureCauseTechnicalactionInfo.setActive(true);

		
		failureCauseTechnicalactionInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
		
		return failureCauseTechnicalactionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo=(FailureCauseTechnicalactionInfo) req.getSession().getAttribute("failureCauseTechnicalactionInfo");
			modelAndView.addObject("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
			modelAndView.addObject("failureCauseTechnicalactionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, failureCauseTechnicalactionInfo);
			modelAndView.setViewName("/maintenance/failureCauseTechnicalaction/createFailureCauseTechnicalaction");
		}
		else{
			modelAndView.addObject("failureCauseTechnicalactionInfo", req.getSession().getAttribute("failureCauseTechnicalactionInfo"));
			modelAndView.addObject("failureCauseTechnicalactionCreateException",true);
			modelAndView.setViewName("/maintenance/failureCauseTechnicalaction/createFailureCauseTechnicalaction");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo){
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