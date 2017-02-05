package co.com.cybersoft.maintenance.tables.web.controller.maintenanceWork;

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
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkService;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for MaintenanceWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceWork/modifyMaintenanceWork/{id}")
public class MaintenanceWorkModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(MaintenanceWorkModificationController.class);
	
	@Autowired
	private MaintenanceWorkService maintenanceWorkService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/maintenanceWork/modifyMaintenanceWork";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyMaintenanceWork(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("maintenanceWorkInfo") MaintenanceWorkInfo maintenanceWorkInfo, HttpServletRequest request) throws Exception {
		
		MaintenanceWorkDetails maintenanceWorkDetails = createMaintenanceWorkDetails(maintenanceWorkInfo);
		maintenanceWorkDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		maintenanceWorkDetails.setDateOfModification(new Date());
		//maintenanceWorkDetails.set_companyId(((MaintenanceWorkDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("maintenanceWorkInfo", maintenanceWorkInfo);
		maintenanceWorkService.modifyMaintenanceWork(new MaintenanceWorkModificationEvent(maintenanceWorkDetails));
		return "redirect:/maintenance/maintenanceWork/searchMaintenanceWork";
	}
	
	private MaintenanceWorkDetails createMaintenanceWorkDetails(MaintenanceWorkInfo maintenanceWorkInfo){
		MaintenanceWorkDetails maintenanceWorkDetails = new MaintenanceWorkDetails();
		BeanUtils.copyProperties(maintenanceWorkInfo, maintenanceWorkDetails);
		return maintenanceWorkDetails;
	}

	@ModelAttribute("maintenanceWorkInfo")
	private MaintenanceWorkInfo getMaintenanceWorkInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the maintenanceWork "+id);
		
		MaintenanceWorkInfo maintenanceWorkInfo = new MaintenanceWorkInfo();
			
		MaintenanceWorkDetailsEvent requestMaintenanceWorkDetails = maintenanceWorkService.requestMaintenanceWorkDetails(new RequestMaintenanceWorkDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		maintenanceWorkInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestMaintenanceWorkDetails.getMaintenanceWorkDetails(), maintenanceWorkInfo);
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
			modelAndView.setViewName("/maintenance/maintenanceWork/modifyMaintenanceWork");
		}
		else{
			modelAndView.addObject("maintenanceWorkInfo", req.getSession().getAttribute("maintenanceWorkInfo"));
			modelAndView.addObject("maintenanceWorkModificationException",true);
			modelAndView.setViewName("/maintenance/maintenanceWork/modifyMaintenanceWork");
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