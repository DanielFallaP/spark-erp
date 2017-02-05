package co.com.cybersoft.maintenance.tables.web.controller.maintenanceWork;

import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkService;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.CreateMaintenanceWorkEvent;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkInfo;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for maintenanceWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceWork/createMaintenanceWork/{from}")
public class MaintenanceWorkCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(MaintenanceWorkCreationController.class);
	
	@Autowired
	private MaintenanceWorkService maintenanceWorkService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/maintenanceWork/createMaintenanceWork";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createMaintenanceWork(@Valid @ModelAttribute("maintenanceWorkInfo") MaintenanceWorkInfo maintenanceWorkInfo, Model model, HttpServletRequest request) throws Exception{
		maintenanceWorkInfo.setCreated(false);
		MaintenanceWorkDetails maintenanceWorkDetails = createMaintenanceWorkDetails(maintenanceWorkInfo);
		maintenanceWorkDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceWorkDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceWorkDetails.setDateOfCreation(new Date());
		maintenanceWorkDetails.setDateOfModification(new Date());
		//maintenanceWorkDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("maintenanceWorkInfo", maintenanceWorkInfo);
		maintenanceWorkService.createMaintenanceWork(new CreateMaintenanceWorkEvent(maintenanceWorkDetails));
		String calledFrom = maintenanceWorkInfo.getCalledFrom();
		maintenanceWorkInfo=new MaintenanceWorkInfo();
		maintenanceWorkInfo.setCreated(true);
		maintenanceWorkInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceWorkInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		maintenanceWorkInfo.setActive(true);

		
		model.addAttribute("maintenanceWorkInfo", maintenanceWorkInfo);
		return "/maintenance/maintenanceWork/createMaintenanceWork";
	}
	
	private MaintenanceWorkDetails createMaintenanceWorkDetails(MaintenanceWorkInfo maintenanceWorkInfo){
		MaintenanceWorkDetails maintenanceWorkDetails = new MaintenanceWorkDetails();
		BeanUtils.copyProperties(maintenanceWorkInfo, maintenanceWorkDetails);
		return maintenanceWorkDetails;
	}
	
	@ModelAttribute("maintenanceWorkInfo")
	private MaintenanceWorkInfo getMaintenanceWorkInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		MaintenanceWorkInfo maintenanceWorkInfo = new MaintenanceWorkInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceWorkInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestMaintenanceWorkDetailsEvent event = new RequestMaintenanceWorkDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			MaintenanceWorkDetailsEvent responseEvent = maintenanceWorkService.requestMaintenanceWorkDetails(event);
			if (responseEvent.getMaintenanceWorkDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getMaintenanceWorkDetails(), maintenanceWorkInfo);
		}
		
		
		maintenanceWorkInfo.setId(null);
		maintenanceWorkInfo.setActive(true);

		
		maintenanceWorkInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("maintenanceWorkInfo", maintenanceWorkInfo);
		
		return maintenanceWorkInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			MaintenanceWorkInfo maintenanceWorkInfo=(MaintenanceWorkInfo) req.getSession().getAttribute("maintenanceWorkInfo");
			modelAndView.addObject("maintenanceWorkInfo", maintenanceWorkInfo);
			modelAndView.addObject("maintenanceWorkValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, maintenanceWorkInfo);
			modelAndView.setViewName("/maintenance/maintenanceWork/createMaintenanceWork");
		}
		else{
			modelAndView.addObject("maintenanceWorkInfo", req.getSession().getAttribute("maintenanceWorkInfo"));
			modelAndView.addObject("maintenanceWorkCreateException",true);
			modelAndView.setViewName("/maintenance/maintenanceWork/createMaintenanceWork");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, MaintenanceWorkInfo maintenanceWorkInfo){
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