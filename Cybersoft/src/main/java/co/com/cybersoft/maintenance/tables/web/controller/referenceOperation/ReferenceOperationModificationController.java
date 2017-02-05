package co.com.cybersoft.maintenance.tables.web.controller.referenceOperation;

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
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;
import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationService;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationModificationEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationInfo;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationService;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;


/**
 * Controller class for ReferenceOperation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/referenceOperation/modifyReferenceOperation/{id}")
public class ReferenceOperationModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ReferenceOperationModificationController.class);
	
	@Autowired
	private ReferenceOperationService referenceOperationService;
	
	@Autowired
		private ReferenceService referenceService;

	@Autowired
		private OperationService operationService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/referenceOperation/modifyReferenceOperation";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyReferenceOperation(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("referenceOperationInfo") ReferenceOperationInfo referenceOperationInfo, HttpServletRequest request) throws Exception {
		
		ReferenceOperationDetails referenceOperationDetails = createReferenceOperationDetails(referenceOperationInfo);
		referenceOperationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		referenceOperationDetails.setDateOfModification(new Date());
		//referenceOperationDetails.set_companyId(((ReferenceOperationDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("referenceOperationInfo", referenceOperationInfo);
		referenceOperationService.modifyReferenceOperation(new ReferenceOperationModificationEvent(referenceOperationDetails));
		return "redirect:/maintenance/referenceOperation/searchReferenceOperation";
	}
	
	private ReferenceOperationDetails createReferenceOperationDetails(ReferenceOperationInfo referenceOperationInfo){
		ReferenceOperationDetails referenceOperationDetails = new ReferenceOperationDetails();
		BeanUtils.copyProperties(referenceOperationInfo, referenceOperationDetails);
		return referenceOperationDetails;
	}

	@ModelAttribute("referenceOperationInfo")
	private ReferenceOperationInfo getReferenceOperationInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the referenceOperation "+id);
		
		ReferenceOperationInfo referenceOperationInfo = new ReferenceOperationInfo();
			
		ReferenceOperationDetailsEvent requestReferenceOperationDetails = referenceOperationService.requestReferenceOperationDetails(new RequestReferenceOperationDetailsEvent(id));
		
		ReferencePageEvent allReferenceEvent = referenceService.requestAllByNameReference();
		referenceOperationInfo.setReferenceList(allReferenceEvent.getReferenceList());
		OperationPageEvent allOperationEvent = operationService.requestAllByNameOperation();
		referenceOperationInfo.setOperationList(allOperationEvent.getOperationList());

		
		BeanUtils.copyProperties(requestReferenceOperationDetails.getReferenceOperationDetails(), referenceOperationInfo);
		request.getSession().setAttribute("referenceOperationInfo", referenceOperationInfo);
		
		return referenceOperationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ReferenceOperationInfo referenceOperationInfo=(ReferenceOperationInfo) req.getSession().getAttribute("referenceOperationInfo");
			modelAndView.addObject("referenceOperationInfo", referenceOperationInfo);
			modelAndView.addObject("referenceOperationValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, referenceOperationInfo);
			modelAndView.setViewName("/maintenance/referenceOperation/modifyReferenceOperation");
		}
		else{
			modelAndView.addObject("referenceOperationInfo", req.getSession().getAttribute("referenceOperationInfo"));
			modelAndView.addObject("referenceOperationModificationException",true);
			modelAndView.setViewName("/maintenance/referenceOperation/modifyReferenceOperation");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ReferenceOperationInfo referenceOperationInfo){
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