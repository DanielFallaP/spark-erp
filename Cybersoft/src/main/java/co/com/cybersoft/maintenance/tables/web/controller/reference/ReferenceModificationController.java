package co.com.cybersoft.maintenance.tables.web.controller.reference;

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
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.reference.ReferenceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferenceDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for Reference
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/reference/modifyReference/{id}")
public class ReferenceModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ReferenceModificationController.class);
	
	@Autowired
	private ReferenceService referenceService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/reference/modifyReference";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyReference(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("referenceInfo") ReferenceInfo referenceInfo, HttpServletRequest request) throws Exception {
		
		ReferenceDetails referenceDetails = createReferenceDetails(referenceInfo);
		referenceDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceDetails.setDateOfModification(new Date());
		//referenceDetails.set_companyId(((ReferenceDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("referenceInfo", referenceInfo);
		referenceService.modifyReference(new ReferenceModificationEvent(referenceDetails));
		return "redirect:/maintenance/reference/searchReference";
	}
	
	private ReferenceDetails createReferenceDetails(ReferenceInfo referenceInfo){
		ReferenceDetails referenceDetails = new ReferenceDetails();
		BeanUtils.copyProperties(referenceInfo, referenceDetails);
		return referenceDetails;
	}

	@ModelAttribute("referenceInfo")
	private ReferenceInfo getReferenceInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the reference "+id);
		
		ReferenceInfo referenceInfo = new ReferenceInfo();
			
		ReferenceDetailsEvent requestReferenceDetails = referenceService.requestReferenceDetails(new RequestReferenceDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		referenceInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestReferenceDetails.getReferenceDetails(), referenceInfo);
		request.getSession().setAttribute("referenceInfo", referenceInfo);
		
		return referenceInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ReferenceInfo referenceInfo=(ReferenceInfo) req.getSession().getAttribute("referenceInfo");
			modelAndView.addObject("referenceInfo", referenceInfo);
			modelAndView.addObject("referenceValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, referenceInfo);
			modelAndView.setViewName("/maintenance/reference/modifyReference");
		}
		else{
			modelAndView.addObject("referenceInfo", req.getSession().getAttribute("referenceInfo"));
			modelAndView.addObject("referenceModificationException",true);
			modelAndView.setViewName("/maintenance/reference/modifyReference");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ReferenceInfo referenceInfo){
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