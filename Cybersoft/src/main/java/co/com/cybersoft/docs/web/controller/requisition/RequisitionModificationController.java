package co.com.cybersoft.docs.web.controller.requisition;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import co.com.cybersoft.docs.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionModificationEvent;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;


/**
 * Controller class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/requisition/modifyRequisition/{id}")
public class RequisitionModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(RequisitionModificationController.class);
	
	@Autowired
	private RequisitionPersistenceService requisitionService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/requisition/modifyRequisition";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyRequisition(@PathVariable("id") String id, @Valid @ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, HttpServletRequest request) throws Exception {
		
		requisitionInfo.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		requisitionInfo.setDateOfModification(new Date());
		
		request.getSession().setAttribute("requisitionInfo", requisitionInfo);
		requisitionService.modifyRequisition(new RequisitionModificationEvent(requisitionInfo));
		return "redirect:/docs/requisition/searchRequisition";
	}

	@ModelAttribute("requisitionInfo")
	private RequisitionInfo getRequisitionInfo(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the requisition "+id);
		
		RequisitionInfo requisitionInfo = new RequisitionInfo();
			
		RequisitionEvent requestRequisition = requisitionService.requestRequisitionDetails(new RequestRequisitionEvent(id));
		
		
		BeanUtils.copyProperties(requestRequisition.getRequisition(), requisitionInfo);
		request.getSession().setAttribute("requisitionInfo", requisitionInfo);
		
		return requisitionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			RequisitionInfo requisitionInfo=(RequisitionInfo) req.getSession().getAttribute("requisitionInfo");
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, requisitionInfo);
			modelAndView.setViewName("/configuration/requisition/modifyRequisition");
		}
		else{
			modelAndView.addObject("requisitionInfo", req.getSession().getAttribute("requisitionInfo"));
			modelAndView.addObject("requisitionModificationException",true);
			modelAndView.setViewName("/configuration/requisition/modifyRequisition");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, RequisitionInfo requisitionInfo){
		List<ObjectError> errors = exception.getAllErrors();
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (fieldError.getCodes()[0].startsWith(CyberUtils.lenghtValidation)
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