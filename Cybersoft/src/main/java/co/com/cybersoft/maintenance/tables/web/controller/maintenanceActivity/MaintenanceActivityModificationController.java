package co.com.cybersoft.maintenance.tables.web.controller.maintenanceActivity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityService;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for MaintenanceActivity
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceActivity/modifyMaintenanceActivity/{id}")
public class MaintenanceActivityModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(MaintenanceActivityModificationController.class);
	
	@Autowired
	private MaintenanceActivityService maintenanceActivityService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/maintenanceActivity/modifyMaintenanceActivity";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyMaintenanceActivity(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("maintenanceActivityInfo") MaintenanceActivityInfo maintenanceActivityInfo, HttpServletRequest request) throws Exception {
		
		MaintenanceActivityDetails maintenanceActivityDetails = createMaintenanceActivityDetails(maintenanceActivityInfo);
		maintenanceActivityDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceActivityDetails.setDateOfModification(new Date());
		//maintenanceActivityDetails.set_companyId(((MaintenanceActivityDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("maintenanceActivityInfo", maintenanceActivityInfo);
		maintenanceActivityService.modifyMaintenanceActivity(new MaintenanceActivityModificationEvent(maintenanceActivityDetails));
		return "redirect:/maintenance/maintenanceActivity/searchMaintenanceActivity";
	}
	
	private MaintenanceActivityDetails createMaintenanceActivityDetails(MaintenanceActivityInfo maintenanceActivityInfo){
		MaintenanceActivityDetails maintenanceActivityDetails = new MaintenanceActivityDetails();
		BeanUtils.copyProperties(maintenanceActivityInfo, maintenanceActivityDetails);
		return maintenanceActivityDetails;
	}

	@ModelAttribute("maintenanceActivityInfo")
	private MaintenanceActivityInfo getMaintenanceActivityInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the maintenanceActivity "+id);
		
		MaintenanceActivityInfo maintenanceActivityInfo = new MaintenanceActivityInfo();
			
		MaintenanceActivityDetailsEvent requestMaintenanceActivityDetails = maintenanceActivityService.requestMaintenanceActivityDetails(new RequestMaintenanceActivityDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceActivityInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestMaintenanceActivityDetails.getMaintenanceActivityDetails(), maintenanceActivityInfo);
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
			modelAndView.setViewName("/maintenance/maintenanceActivity/modifyMaintenanceActivity");
		}
		else{
			modelAndView.addObject("maintenanceActivityInfo", req.getSession().getAttribute("maintenanceActivityInfo"));
			modelAndView.addObject("maintenanceActivityModificationException",true);
			modelAndView.setViewName("/maintenance/maintenanceActivity/modifyMaintenanceActivity");
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