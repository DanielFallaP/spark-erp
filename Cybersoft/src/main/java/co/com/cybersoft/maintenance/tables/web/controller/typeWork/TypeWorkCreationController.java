package co.com.cybersoft.maintenance.tables.web.controller.typeWork;

import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.maintenance.tables.events.typeWork.CreateTypeWorkEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkInfo;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkDetailsEvent;



/**
 * Controller for typeWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeWork/createTypeWork/{from}")
public class TypeWorkCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(TypeWorkCreationController.class);
	
	@Autowired
	private TypeWorkService typeWorkService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeWork/createTypeWork";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createTypeWork(@Valid @ModelAttribute("typeWorkInfo") TypeWorkInfo typeWorkInfo, Model model, HttpServletRequest request) throws Exception{
		typeWorkInfo.setCreated(false);
		TypeWorkDetails typeWorkDetails = createTypeWorkDetails(typeWorkInfo);
		typeWorkDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		typeWorkDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeWorkDetails.setDateOfCreation(new Date());
		typeWorkDetails.setDateOfModification(new Date());
		//typeWorkDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("typeWorkInfo", typeWorkInfo);
		typeWorkService.createTypeWork(new CreateTypeWorkEvent(typeWorkDetails));
		String calledFrom = typeWorkInfo.getCalledFrom();
		typeWorkInfo=new TypeWorkInfo();
		typeWorkInfo.setCreated(true);
		typeWorkInfo.setCalledFrom(calledFrom);
		
		typeWorkInfo.setActive(true);

		
		model.addAttribute("typeWorkInfo", typeWorkInfo);
		return "/maintenance/typeWork/createTypeWork";
	}
	
	private TypeWorkDetails createTypeWorkDetails(TypeWorkInfo typeWorkInfo){
		TypeWorkDetails typeWorkDetails = new TypeWorkDetails();
		BeanUtils.copyProperties(typeWorkInfo, typeWorkDetails);
		return typeWorkDetails;
	}
	
	@ModelAttribute("typeWorkInfo")
	private TypeWorkInfo getTypeWorkInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		TypeWorkInfo typeWorkInfo = new TypeWorkInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestTypeWorkDetailsEvent event = new RequestTypeWorkDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			TypeWorkDetailsEvent responseEvent = typeWorkService.requestTypeWorkDetails(event);
			if (responseEvent.getTypeWorkDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getTypeWorkDetails(), typeWorkInfo);
		}
		
		
		typeWorkInfo.setId(null);
		typeWorkInfo.setActive(true);

		
		typeWorkInfo.setCalledFrom(calledFrom);
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
			modelAndView.setViewName("/maintenance/typeWork/createTypeWork");
		}
		else{
			modelAndView.addObject("typeWorkInfo", req.getSession().getAttribute("typeWorkInfo"));
			modelAndView.addObject("typeWorkCreateException",true);
			modelAndView.setViewName("/maintenance/typeWork/createTypeWork");
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