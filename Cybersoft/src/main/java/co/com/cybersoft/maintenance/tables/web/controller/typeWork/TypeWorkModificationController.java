package co.com.cybersoft.maintenance.tables.web.controller.typeWork;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkInfo;

/**
 * Controller class for TypeWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeWork/modifyTypeWork/{id}")
public class TypeWorkModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeWorkModificationController.class);
	
	@Autowired
	private TypeWorkService typeWorkService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeWork/modifyTypeWork";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyTypeWork(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("typeWorkInfo") TypeWorkInfo typeWorkInfo, HttpServletRequest request) throws Exception {
		
		TypeWorkDetails typeWorkDetails = createTypeWorkDetails(typeWorkInfo);
		typeWorkDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeWorkDetails.setDateOfModification(new Date());
		//typeWorkDetails.set_companyId(((TypeWorkDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("typeWorkInfo", typeWorkInfo);
		typeWorkService.modifyTypeWork(new TypeWorkModificationEvent(typeWorkDetails));
		return "redirect:/maintenance/typeWork/searchTypeWork";
	}
	
	private TypeWorkDetails createTypeWorkDetails(TypeWorkInfo typeWorkInfo){
		TypeWorkDetails typeWorkDetails = new TypeWorkDetails();
		BeanUtils.copyProperties(typeWorkInfo, typeWorkDetails);
		return typeWorkDetails;
	}

	@ModelAttribute("typeWorkInfo")
	private TypeWorkInfo getTypeWorkInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the typeWork "+id);
		
		TypeWorkInfo typeWorkInfo = new TypeWorkInfo();
			
		TypeWorkDetailsEvent requestTypeWorkDetails = typeWorkService.requestTypeWorkDetails(new RequestTypeWorkDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestTypeWorkDetails.getTypeWorkDetails(), typeWorkInfo);
		request.getSession().setAttribute("typeWorkInfo", typeWorkInfo);
		
		return typeWorkInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TypeWorkInfo typeWorkInfo=(TypeWorkInfo) req.getSession().getAttribute("typeWorkInfo");
			modelAndView.addObject("typeWorkInfo", typeWorkInfo);
			modelAndView.addObject("typeWorkValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, typeWorkInfo);
			modelAndView.setViewName("/maintenance/typeWork/modifyTypeWork");
		}
		else{
			modelAndView.addObject("typeWorkInfo", req.getSession().getAttribute("typeWorkInfo"));
			modelAndView.addObject("typeWorkModificationException",true);
			modelAndView.setViewName("/maintenance/typeWork/modifyTypeWork");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TypeWorkInfo typeWorkInfo){
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