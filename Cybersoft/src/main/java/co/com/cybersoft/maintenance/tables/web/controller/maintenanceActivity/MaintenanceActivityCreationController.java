package co.com.cybersoft.maintenance.tables.web.controller.maintenanceActivity;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityService;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.CreateMaintenanceActivityEvent;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityInfo;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for maintenanceActivity
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceActivity/createMaintenanceActivity/{from}")
public class MaintenanceActivityCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(MaintenanceActivityCreationController.class);
	
	@Autowired
	private MaintenanceActivityService maintenanceActivityService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/maintenanceActivity/createMaintenanceActivity";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createMaintenanceActivity(@Valid @ModelAttribute("maintenanceActivityInfo") MaintenanceActivityInfo maintenanceActivityInfo, Model model, HttpServletRequest request) throws Exception{
		maintenanceActivityInfo.setCreated(false);
		MaintenanceActivityDetails maintenanceActivityDetails = createMaintenanceActivityDetails(maintenanceActivityInfo);
		maintenanceActivityDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceActivityDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceActivityDetails.setDateOfCreation(new Date());
		maintenanceActivityDetails.setDateOfModification(new Date());
		//maintenanceActivityDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("maintenanceActivityInfo", maintenanceActivityInfo);
		maintenanceActivityService.createMaintenanceActivity(new CreateMaintenanceActivityEvent(maintenanceActivityDetails));
		String calledFrom = maintenanceActivityInfo.getCalledFrom();
		maintenanceActivityInfo=new MaintenanceActivityInfo();
		maintenanceActivityInfo.setCreated(true);
		maintenanceActivityInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceActivityInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		maintenanceActivityInfo.setActive(true);

		
		model.addAttribute("maintenanceActivityInfo", maintenanceActivityInfo);
		return "/maintenance/maintenanceActivity/createMaintenanceActivity";
	}
	
	private MaintenanceActivityDetails createMaintenanceActivityDetails(MaintenanceActivityInfo maintenanceActivityInfo){
		MaintenanceActivityDetails maintenanceActivityDetails = new MaintenanceActivityDetails();
		BeanUtils.copyProperties(maintenanceActivityInfo, maintenanceActivityDetails);
		return maintenanceActivityDetails;
	}
	
	@ModelAttribute("maintenanceActivityInfo")
	private MaintenanceActivityInfo getMaintenanceActivityInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		MaintenanceActivityInfo maintenanceActivityInfo = new MaintenanceActivityInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceActivityInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestMaintenanceActivityDetailsEvent event = new RequestMaintenanceActivityDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			MaintenanceActivityDetailsEvent responseEvent = maintenanceActivityService.requestMaintenanceActivityDetails(event);
			if (responseEvent.getMaintenanceActivityDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getMaintenanceActivityDetails(), maintenanceActivityInfo);
		}
		
		
		maintenanceActivityInfo.setId(null);
		maintenanceActivityInfo.setActive(true);

		
		maintenanceActivityInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("maintenanceActivityInfo", maintenanceActivityInfo);
		
		return maintenanceActivityInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			MaintenanceActivityInfo maintenanceActivityInfo=(MaintenanceActivityInfo) req.getSession().getAttribute("maintenanceActivityInfo");
			modelAndView.addObject("maintenanceActivityInfo", maintenanceActivityInfo);
			modelAndView.addObject("maintenanceActivityValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, maintenanceActivityInfo);
			modelAndView.setViewName("/maintenance/maintenanceActivity/createMaintenanceActivity");
		}
		else{
			modelAndView.addObject("maintenanceActivityInfo", req.getSession().getAttribute("maintenanceActivityInfo"));
			modelAndView.addObject("maintenanceActivityCreateException",true);
			modelAndView.setViewName("/maintenance/maintenanceActivity/createMaintenanceActivity");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, MaintenanceActivityInfo maintenanceActivityInfo){
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