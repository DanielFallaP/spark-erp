package co.com.cybersoft.maintenance.tables.web.controller.measurementUnit;

import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitInfo;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for measurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/measurementUnit/createMeasurementUnit/{from}")
public class MeasurementUnitCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitCreationController.class);
	
	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/measurementUnit/createMeasurementUnit";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createMeasurementUnit(@Valid @ModelAttribute("measurementUnitInfo") MeasurementUnitInfo measurementUnitInfo, Model model, HttpServletRequest request) throws Exception{
		measurementUnitInfo.setCreated(false);
		MeasurementUnitDetails measurementUnitDetails = createMeasurementUnitDetails(measurementUnitInfo);
		measurementUnitDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitDetails.setDateOfCreation(new Date());
		measurementUnitDetails.setDateOfModification(new Date());
		//measurementUnitDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("measurementUnitInfo", measurementUnitInfo);
		measurementUnitService.createMeasurementUnit(new CreateMeasurementUnitEvent(measurementUnitDetails));
		String calledFrom = measurementUnitInfo.getCalledFrom();
		measurementUnitInfo=new MeasurementUnitInfo();
		measurementUnitInfo.setCreated(true);
		measurementUnitInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		measurementUnitInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		measurementUnitInfo.setActive(true);

		
		model.addAttribute("measurementUnitInfo", measurementUnitInfo);
		return "/maintenance/measurementUnit/createMeasurementUnit";
	}
	
	private MeasurementUnitDetails createMeasurementUnitDetails(MeasurementUnitInfo measurementUnitInfo){
		MeasurementUnitDetails measurementUnitDetails = new MeasurementUnitDetails();
		BeanUtils.copyProperties(measurementUnitInfo, measurementUnitDetails);
		return measurementUnitDetails;
	}
	
	@ModelAttribute("measurementUnitInfo")
	private MeasurementUnitInfo getMeasurementUnitInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		MeasurementUnitInfo measurementUnitInfo = new MeasurementUnitInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		measurementUnitInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestMeasurementUnitDetailsEvent event = new RequestMeasurementUnitDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			MeasurementUnitDetailsEvent responseEvent = measurementUnitService.requestMeasurementUnitDetails(event);
			if (responseEvent.getMeasurementUnitDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getMeasurementUnitDetails(), measurementUnitInfo);
		}
		
		
		measurementUnitInfo.setId(null);
		measurementUnitInfo.setActive(true);

		
		measurementUnitInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("measurementUnitInfo", measurementUnitInfo);
		
		return measurementUnitInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			MeasurementUnitInfo measurementUnitInfo=(MeasurementUnitInfo) req.getSession().getAttribute("measurementUnitInfo");
			modelAndView.addObject("measurementUnitInfo", measurementUnitInfo);
			modelAndView.addObject("measurementUnitValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, measurementUnitInfo);
			modelAndView.setViewName("/maintenance/measurementUnit/createMeasurementUnit");
		}
		else{
			modelAndView.addObject("measurementUnitInfo", req.getSession().getAttribute("measurementUnitInfo"));
			modelAndView.addObject("measurementUnitCreateException",true);
			modelAndView.setViewName("/maintenance/measurementUnit/createMeasurementUnit");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, MeasurementUnitInfo measurementUnitInfo){
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