package co.com.cybersoft.maintenance.tables.web.controller.causePending;

import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;
import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingService;
import co.com.cybersoft.maintenance.tables.events.causePending.CreateCausePendingEvent;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingInfo;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for causePending
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causePending/createCausePending/{from}")
public class CausePendingCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CausePendingCreationController.class);
	
	@Autowired
	private CausePendingService causePendingService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/causePending/createCausePending";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCausePending(@Valid @ModelAttribute("causePendingInfo") CausePendingInfo causePendingInfo, Model model, HttpServletRequest request) throws Exception{
		causePendingInfo.setCreated(false);
		CausePendingDetails causePendingDetails = createCausePendingDetails(causePendingInfo);
		causePendingDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		causePendingDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		causePendingDetails.setDateOfCreation(new Date());
		causePendingDetails.setDateOfModification(new Date());
		//causePendingDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("causePendingInfo", causePendingInfo);
		causePendingService.createCausePending(new CreateCausePendingEvent(causePendingDetails));
		String calledFrom = causePendingInfo.getCalledFrom();
		causePendingInfo=new CausePendingInfo();
		causePendingInfo.setCreated(true);
		causePendingInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causePendingInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		causePendingInfo.setActive(true);

		
		model.addAttribute("causePendingInfo", causePendingInfo);
		return "/maintenance/causePending/createCausePending";
	}
	
	private CausePendingDetails createCausePendingDetails(CausePendingInfo causePendingInfo){
		CausePendingDetails causePendingDetails = new CausePendingDetails();
		BeanUtils.copyProperties(causePendingInfo, causePendingDetails);
		return causePendingDetails;
	}
	
	@ModelAttribute("causePendingInfo")
	private CausePendingInfo getCausePendingInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CausePendingInfo causePendingInfo = new CausePendingInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causePendingInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestCausePendingDetailsEvent event = new RequestCausePendingDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CausePendingDetailsEvent responseEvent = causePendingService.requestCausePendingDetails(event);
			if (responseEvent.getCausePendingDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCausePendingDetails(), causePendingInfo);
		}
		
		
		causePendingInfo.setId(null);
		causePendingInfo.setActive(true);

		
		causePendingInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("causePendingInfo", causePendingInfo);
		
		return causePendingInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CausePendingInfo causePendingInfo=(CausePendingInfo) req.getSession().getAttribute("causePendingInfo");
			modelAndView.addObject("causePendingInfo", causePendingInfo);
			modelAndView.addObject("causePendingValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, causePendingInfo);
			modelAndView.setViewName("/maintenance/causePending/createCausePending");
		}
		else{
			modelAndView.addObject("causePendingInfo", req.getSession().getAttribute("causePendingInfo"));
			modelAndView.addObject("causePendingCreateException",true);
			modelAndView.setViewName("/maintenance/causePending/createCausePending");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CausePendingInfo causePendingInfo){
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