package co.com.cybersoft.maintenance.tables.web.controller.typeCauseClose;

import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.CreateTypeCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseInfo;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseCloseDetailsEvent;



/**
 * Controller for typeCauseClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeCauseClose/createTypeCauseClose/{from}")
public class TypeCauseCloseCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(TypeCauseCloseCreationController.class);
	
	@Autowired
	private TypeCauseCloseService typeCauseCloseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeCauseClose/createTypeCauseClose";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createTypeCauseClose(@Valid @ModelAttribute("typeCauseCloseInfo") TypeCauseCloseInfo typeCauseCloseInfo, Model model, HttpServletRequest request) throws Exception{
		typeCauseCloseInfo.setCreated(false);
		TypeCauseCloseDetails typeCauseCloseDetails = createTypeCauseCloseDetails(typeCauseCloseInfo);
		typeCauseCloseDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		typeCauseCloseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeCauseCloseDetails.setDateOfCreation(new Date());
		typeCauseCloseDetails.setDateOfModification(new Date());
		//typeCauseCloseDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("typeCauseCloseInfo", typeCauseCloseInfo);
		typeCauseCloseService.createTypeCauseClose(new CreateTypeCauseCloseEvent(typeCauseCloseDetails));
		String calledFrom = typeCauseCloseInfo.getCalledFrom();
		typeCauseCloseInfo=new TypeCauseCloseInfo();
		typeCauseCloseInfo.setCreated(true);
		typeCauseCloseInfo.setCalledFrom(calledFrom);
		
		typeCauseCloseInfo.setActive(true);

		
		model.addAttribute("typeCauseCloseInfo", typeCauseCloseInfo);
		return "/maintenance/typeCauseClose/createTypeCauseClose";
	}
	
	private TypeCauseCloseDetails createTypeCauseCloseDetails(TypeCauseCloseInfo typeCauseCloseInfo){
		TypeCauseCloseDetails typeCauseCloseDetails = new TypeCauseCloseDetails();
		BeanUtils.copyProperties(typeCauseCloseInfo, typeCauseCloseDetails);
		return typeCauseCloseDetails;
	}
	
	@ModelAttribute("typeCauseCloseInfo")
	private TypeCauseCloseInfo getTypeCauseCloseInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		TypeCauseCloseInfo typeCauseCloseInfo = new TypeCauseCloseInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestTypeCauseCloseDetailsEvent event = new RequestTypeCauseCloseDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			TypeCauseCloseDetailsEvent responseEvent = typeCauseCloseService.requestTypeCauseCloseDetails(event);
			if (responseEvent.getTypeCauseCloseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getTypeCauseCloseDetails(), typeCauseCloseInfo);
		}
		
		
		typeCauseCloseInfo.setId(null);
		typeCauseCloseInfo.setActive(true);

		
		typeCauseCloseInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("typeCauseCloseInfo", typeCauseCloseInfo);
		
		return typeCauseCloseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TypeCauseCloseInfo typeCauseCloseInfo=(TypeCauseCloseInfo) req.getSession().getAttribute("typeCauseCloseInfo");
			modelAndView.addObject("typeCauseCloseInfo", typeCauseCloseInfo);
			modelAndView.addObject("typeCauseCloseValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, typeCauseCloseInfo);
			modelAndView.setViewName("/maintenance/typeCauseClose/createTypeCauseClose");
		}
		else{
			modelAndView.addObject("typeCauseCloseInfo", req.getSession().getAttribute("typeCauseCloseInfo"));
			modelAndView.addObject("typeCauseCloseCreateException",true);
			modelAndView.setViewName("/maintenance/typeCauseClose/createTypeCauseClose");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TypeCauseCloseInfo typeCauseCloseInfo){
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