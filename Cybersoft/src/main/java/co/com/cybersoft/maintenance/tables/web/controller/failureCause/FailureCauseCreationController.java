package co.com.cybersoft.maintenance.tables.web.controller.failureCause;

import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.maintenance.tables.events.failureCause.CreateFailureCauseEvent;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseInfo;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCauseDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for failureCause
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCause/createFailureCause/{from}")
public class FailureCauseCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(FailureCauseCreationController.class);
	
	@Autowired
	private FailureCauseService failureCauseService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/failureCause/createFailureCause";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createFailureCause(@Valid @ModelAttribute("failureCauseInfo") FailureCauseInfo failureCauseInfo, Model model, HttpServletRequest request) throws Exception{
		failureCauseInfo.setCreated(false);
		FailureCauseDetails failureCauseDetails = createFailureCauseDetails(failureCauseInfo);
		failureCauseDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseDetails.setDateOfCreation(new Date());
		failureCauseDetails.setDateOfModification(new Date());
		//failureCauseDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("failureCauseInfo", failureCauseInfo);
		failureCauseService.createFailureCause(new CreateFailureCauseEvent(failureCauseDetails));
		String calledFrom = failureCauseInfo.getCalledFrom();
		failureCauseInfo=new FailureCauseInfo();
		failureCauseInfo.setCreated(true);
		failureCauseInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		failureCauseInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		failureCauseInfo.setActive(true);

		
		model.addAttribute("failureCauseInfo", failureCauseInfo);
		return "/maintenance/failureCause/createFailureCause";
	}
	
	private FailureCauseDetails createFailureCauseDetails(FailureCauseInfo failureCauseInfo){
		FailureCauseDetails failureCauseDetails = new FailureCauseDetails();
		BeanUtils.copyProperties(failureCauseInfo, failureCauseDetails);
		return failureCauseDetails;
	}
	
	@ModelAttribute("failureCauseInfo")
	private FailureCauseInfo getFailureCauseInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		FailureCauseInfo failureCauseInfo = new FailureCauseInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		failureCauseInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestFailureCauseDetailsEvent event = new RequestFailureCauseDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			FailureCauseDetailsEvent responseEvent = failureCauseService.requestFailureCauseDetails(event);
			if (responseEvent.getFailureCauseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getFailureCauseDetails(), failureCauseInfo);
		}
		
		
		failureCauseInfo.setId(null);
		failureCauseInfo.setActive(true);

		
		failureCauseInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("failureCauseInfo", failureCauseInfo);
		
		return failureCauseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			FailureCauseInfo failureCauseInfo=(FailureCauseInfo) req.getSession().getAttribute("failureCauseInfo");
			modelAndView.addObject("failureCauseInfo", failureCauseInfo);
			modelAndView.addObject("failureCauseValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, failureCauseInfo);
			modelAndView.setViewName("/maintenance/failureCause/createFailureCause");
		}
		else{
			modelAndView.addObject("failureCauseInfo", req.getSession().getAttribute("failureCauseInfo"));
			modelAndView.addObject("failureCauseCreateException",true);
			modelAndView.setViewName("/maintenance/failureCause/createFailureCause");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, FailureCauseInfo failureCauseInfo){
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