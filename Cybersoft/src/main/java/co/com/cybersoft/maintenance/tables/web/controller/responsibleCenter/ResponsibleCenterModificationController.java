package co.com.cybersoft.maintenance.tables.web.controller.responsibleCenter;

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
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterService;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for ResponsibleCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/responsibleCenter/modifyResponsibleCenter/{id}")
public class ResponsibleCenterModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ResponsibleCenterModificationController.class);
	
	@Autowired
	private ResponsibleCenterService responsibleCenterService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/responsibleCenter/modifyResponsibleCenter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyResponsibleCenter(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("responsibleCenterInfo") ResponsibleCenterInfo responsibleCenterInfo, HttpServletRequest request) throws Exception {
		
		ResponsibleCenterDetails responsibleCenterDetails = createResponsibleCenterDetails(responsibleCenterInfo);
		responsibleCenterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		responsibleCenterDetails.setDateOfModification(new Date());
		//responsibleCenterDetails.set_companyId(((ResponsibleCenterDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("responsibleCenterInfo", responsibleCenterInfo);
		responsibleCenterService.modifyResponsibleCenter(new ResponsibleCenterModificationEvent(responsibleCenterDetails));
		return "redirect:/maintenance/responsibleCenter/searchResponsibleCenter";
	}
	
	private ResponsibleCenterDetails createResponsibleCenterDetails(ResponsibleCenterInfo responsibleCenterInfo){
		ResponsibleCenterDetails responsibleCenterDetails = new ResponsibleCenterDetails();
		BeanUtils.copyProperties(responsibleCenterInfo, responsibleCenterDetails);
		return responsibleCenterDetails;
	}

	@ModelAttribute("responsibleCenterInfo")
	private ResponsibleCenterInfo getResponsibleCenterInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the responsibleCenter "+id);
		
		ResponsibleCenterInfo responsibleCenterInfo = new ResponsibleCenterInfo();
			
		ResponsibleCenterDetailsEvent requestResponsibleCenterDetails = responsibleCenterService.requestResponsibleCenterDetails(new RequestResponsibleCenterDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		responsibleCenterInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestResponsibleCenterDetails.getResponsibleCenterDetails(), responsibleCenterInfo);
		request.getSession().setAttribute("responsibleCenterInfo", responsibleCenterInfo);
		
		return responsibleCenterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ResponsibleCenterInfo responsibleCenterInfo=(ResponsibleCenterInfo) req.getSession().getAttribute("responsibleCenterInfo");
			modelAndView.addObject("responsibleCenterInfo", responsibleCenterInfo);
			modelAndView.addObject("responsibleCenterValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, responsibleCenterInfo);
			modelAndView.setViewName("/maintenance/responsibleCenter/modifyResponsibleCenter");
		}
		else{
			modelAndView.addObject("responsibleCenterInfo", req.getSession().getAttribute("responsibleCenterInfo"));
			modelAndView.addObject("responsibleCenterModificationException",true);
			modelAndView.setViewName("/maintenance/responsibleCenter/modifyResponsibleCenter");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ResponsibleCenterInfo responsibleCenterInfo){
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