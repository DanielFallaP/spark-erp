package co.com.cybersoft.maintenance.tables.web.controller.typeMaintenance;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceService;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for TypeMaintenance
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeMaintenance/modifyTypeMaintenance/{id}")
public class TypeMaintenanceModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeMaintenanceModificationController.class);
	
	@Autowired
	private TypeMaintenanceService typeMaintenanceService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeMaintenance/modifyTypeMaintenance";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyTypeMaintenance(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("typeMaintenanceInfo") TypeMaintenanceInfo typeMaintenanceInfo, HttpServletRequest request) throws Exception {
		
		TypeMaintenanceDetails typeMaintenanceDetails = createTypeMaintenanceDetails(typeMaintenanceInfo);
		typeMaintenanceDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeMaintenanceDetails.setDateOfModification(new Date());
		//typeMaintenanceDetails.set_companyId(((TypeMaintenanceDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("typeMaintenanceInfo", typeMaintenanceInfo);
		typeMaintenanceService.modifyTypeMaintenance(new TypeMaintenanceModificationEvent(typeMaintenanceDetails));
		return "redirect:/maintenance/typeMaintenance/searchTypeMaintenance";
	}
	
	private TypeMaintenanceDetails createTypeMaintenanceDetails(TypeMaintenanceInfo typeMaintenanceInfo){
		TypeMaintenanceDetails typeMaintenanceDetails = new TypeMaintenanceDetails();
		BeanUtils.copyProperties(typeMaintenanceInfo, typeMaintenanceDetails);
		return typeMaintenanceDetails;
	}

	@ModelAttribute("typeMaintenanceInfo")
	private TypeMaintenanceInfo getTypeMaintenanceInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the typeMaintenance "+id);
		
		TypeMaintenanceInfo typeMaintenanceInfo = new TypeMaintenanceInfo();
			
		TypeMaintenanceDetailsEvent requestTypeMaintenanceDetails = typeMaintenanceService.requestTypeMaintenanceDetails(new RequestTypeMaintenanceDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		typeMaintenanceInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestTypeMaintenanceDetails.getTypeMaintenanceDetails(), typeMaintenanceInfo);
		request.getSession().setAttribute("typeMaintenanceInfo", typeMaintenanceInfo);
		
		return typeMaintenanceInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TypeMaintenanceInfo typeMaintenanceInfo=(TypeMaintenanceInfo) req.getSession().getAttribute("typeMaintenanceInfo");
			modelAndView.addObject("typeMaintenanceInfo", typeMaintenanceInfo);
			modelAndView.addObject("typeMaintenanceValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, typeMaintenanceInfo);
			modelAndView.setViewName("/maintenance/typeMaintenance/modifyTypeMaintenance");
		}
		else{
			modelAndView.addObject("typeMaintenanceInfo", req.getSession().getAttribute("typeMaintenanceInfo"));
			modelAndView.addObject("typeMaintenanceModificationException",true);
			modelAndView.setViewName("/maintenance/typeMaintenance/modifyTypeMaintenance");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TypeMaintenanceInfo typeMaintenanceInfo){
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