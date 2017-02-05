package co.com.cybersoft.maintenance.tables.web.controller.technicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.maintenance.tables.events.technicalAction.CreateTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionInfo;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for technicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/technicalAction/createTechnicalAction/{from}")
public class TechnicalActionCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(TechnicalActionCreationController.class);
	
	@Autowired
	private TechnicalActionService technicalActionService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/technicalAction/createTechnicalAction";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createTechnicalAction(@Valid @ModelAttribute("technicalActionInfo") TechnicalActionInfo technicalActionInfo, Model model, HttpServletRequest request) throws Exception{
		technicalActionInfo.setCreated(false);
		TechnicalActionDetails technicalActionDetails = createTechnicalActionDetails(technicalActionInfo);
		technicalActionDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		technicalActionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		technicalActionDetails.setDateOfCreation(new Date());
		technicalActionDetails.setDateOfModification(new Date());
		//technicalActionDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("technicalActionInfo", technicalActionInfo);
		technicalActionService.createTechnicalAction(new CreateTechnicalActionEvent(technicalActionDetails));
		String calledFrom = technicalActionInfo.getCalledFrom();
		technicalActionInfo=new TechnicalActionInfo();
		technicalActionInfo.setCreated(true);
		technicalActionInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		technicalActionInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		technicalActionInfo.setActive(true);

		
		model.addAttribute("technicalActionInfo", technicalActionInfo);
		return "/maintenance/technicalAction/createTechnicalAction";
	}
	
	private TechnicalActionDetails createTechnicalActionDetails(TechnicalActionInfo technicalActionInfo){
		TechnicalActionDetails technicalActionDetails = new TechnicalActionDetails();
		BeanUtils.copyProperties(technicalActionInfo, technicalActionDetails);
		return technicalActionDetails;
	}
	
	@ModelAttribute("technicalActionInfo")
	private TechnicalActionInfo getTechnicalActionInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		TechnicalActionInfo technicalActionInfo = new TechnicalActionInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		technicalActionInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestTechnicalActionDetailsEvent event = new RequestTechnicalActionDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			TechnicalActionDetailsEvent responseEvent = technicalActionService.requestTechnicalActionDetails(event);
			if (responseEvent.getTechnicalActionDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getTechnicalActionDetails(), technicalActionInfo);
		}
		
		
		technicalActionInfo.setId(null);
		technicalActionInfo.setActive(true);

		
		technicalActionInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("technicalActionInfo", technicalActionInfo);
		
		return technicalActionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TechnicalActionInfo technicalActionInfo=(TechnicalActionInfo) req.getSession().getAttribute("technicalActionInfo");
			modelAndView.addObject("technicalActionInfo", technicalActionInfo);
			modelAndView.addObject("technicalActionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, technicalActionInfo);
			modelAndView.setViewName("/maintenance/technicalAction/createTechnicalAction");
		}
		else{
			modelAndView.addObject("technicalActionInfo", req.getSession().getAttribute("technicalActionInfo"));
			modelAndView.addObject("technicalActionCreateException",true);
			modelAndView.setViewName("/maintenance/technicalAction/createTechnicalAction");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TechnicalActionInfo technicalActionInfo){
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