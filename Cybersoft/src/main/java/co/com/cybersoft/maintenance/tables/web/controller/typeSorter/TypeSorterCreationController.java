package co.com.cybersoft.maintenance.tables.web.controller.typeSorter;

import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.maintenance.tables.events.typeSorter.CreateTypeSorterEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterInfo;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterDetailsEvent;



/**
 * Controller for typeSorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeSorter/createTypeSorter/{from}")
public class TypeSorterCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(TypeSorterCreationController.class);
	
	@Autowired
	private TypeSorterService typeSorterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeSorter/createTypeSorter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createTypeSorter(@Valid @ModelAttribute("typeSorterInfo") TypeSorterInfo typeSorterInfo, Model model, HttpServletRequest request) throws Exception{
		typeSorterInfo.setCreated(false);
		TypeSorterDetails typeSorterDetails = createTypeSorterDetails(typeSorterInfo);
		typeSorterDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		typeSorterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeSorterDetails.setDateOfCreation(new Date());
		typeSorterDetails.setDateOfModification(new Date());
		//typeSorterDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("typeSorterInfo", typeSorterInfo);
		typeSorterService.createTypeSorter(new CreateTypeSorterEvent(typeSorterDetails));
		String calledFrom = typeSorterInfo.getCalledFrom();
		typeSorterInfo=new TypeSorterInfo();
		typeSorterInfo.setCreated(true);
		typeSorterInfo.setCalledFrom(calledFrom);
		
		typeSorterInfo.setActive(true);

		
		model.addAttribute("typeSorterInfo", typeSorterInfo);
		return "/maintenance/typeSorter/createTypeSorter";
	}
	
	private TypeSorterDetails createTypeSorterDetails(TypeSorterInfo typeSorterInfo){
		TypeSorterDetails typeSorterDetails = new TypeSorterDetails();
		BeanUtils.copyProperties(typeSorterInfo, typeSorterDetails);
		return typeSorterDetails;
	}
	
	@ModelAttribute("typeSorterInfo")
	private TypeSorterInfo getTypeSorterInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		TypeSorterInfo typeSorterInfo = new TypeSorterInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestTypeSorterDetailsEvent event = new RequestTypeSorterDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			TypeSorterDetailsEvent responseEvent = typeSorterService.requestTypeSorterDetails(event);
			if (responseEvent.getTypeSorterDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getTypeSorterDetails(), typeSorterInfo);
		}
		
		
		typeSorterInfo.setId(null);
		typeSorterInfo.setActive(true);

		
		typeSorterInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("typeSorterInfo", typeSorterInfo);
		
		return typeSorterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TypeSorterInfo typeSorterInfo=(TypeSorterInfo) req.getSession().getAttribute("typeSorterInfo");
			modelAndView.addObject("typeSorterInfo", typeSorterInfo);
			modelAndView.addObject("typeSorterValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, typeSorterInfo);
			modelAndView.setViewName("/maintenance/typeSorter/createTypeSorter");
		}
		else{
			modelAndView.addObject("typeSorterInfo", req.getSession().getAttribute("typeSorterInfo"));
			modelAndView.addObject("typeSorterCreateException",true);
			modelAndView.setViewName("/maintenance/typeSorter/createTypeSorter");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TypeSorterInfo typeSorterInfo){
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