package co.com.cybersoft.maintenance.tables.web.controller.state;

import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;
import co.com.cybersoft.maintenance.tables.core.services.state.StateService;
import co.com.cybersoft.maintenance.tables.events.state.CreateStateEvent;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateInfo;
import co.com.cybersoft.maintenance.tables.events.state.StateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStateDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for state
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/state/createState/{from}")
public class StateCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(StateCreationController.class);
	
	@Autowired
	private StateService stateService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/state/createState";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createState(@Valid @ModelAttribute("stateInfo") StateInfo stateInfo, Model model, HttpServletRequest request) throws Exception{
		stateInfo.setCreated(false);
		StateDetails stateDetails = createStateDetails(stateInfo);
		stateDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		stateDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		stateDetails.setDateOfCreation(new Date());
		stateDetails.setDateOfModification(new Date());
		//stateDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("stateInfo", stateInfo);
		stateService.createState(new CreateStateEvent(stateDetails));
		String calledFrom = stateInfo.getCalledFrom();
		stateInfo=new StateInfo();
		stateInfo.setCreated(true);
		stateInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		stateInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		stateInfo.setActive(true);

		
		model.addAttribute("stateInfo", stateInfo);
		return "/maintenance/state/createState";
	}
	
	private StateDetails createStateDetails(StateInfo stateInfo){
		StateDetails stateDetails = new StateDetails();
		BeanUtils.copyProperties(stateInfo, stateDetails);
		return stateDetails;
	}
	
	@ModelAttribute("stateInfo")
	private StateInfo getStateInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		StateInfo stateInfo = new StateInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		stateInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestStateDetailsEvent event = new RequestStateDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			StateDetailsEvent responseEvent = stateService.requestStateDetails(event);
			if (responseEvent.getStateDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getStateDetails(), stateInfo);
		}
		
		
		stateInfo.setId(null);
		stateInfo.setActive(true);

		
		stateInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("stateInfo", stateInfo);
		
		return stateInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			StateInfo stateInfo=(StateInfo) req.getSession().getAttribute("stateInfo");
			modelAndView.addObject("stateInfo", stateInfo);
			modelAndView.addObject("stateValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, stateInfo);
			modelAndView.setViewName("/maintenance/state/createState");
		}
		else{
			modelAndView.addObject("stateInfo", req.getSession().getAttribute("stateInfo"));
			modelAndView.addObject("stateCreateException",true);
			modelAndView.setViewName("/maintenance/state/createState");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, StateInfo stateInfo){
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