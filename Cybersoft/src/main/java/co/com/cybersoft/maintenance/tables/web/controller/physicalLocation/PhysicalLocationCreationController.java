package co.com.cybersoft.maintenance.tables.web.controller.physicalLocation;

import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationService;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.CreatePhysicalLocationEvent;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationInfo;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;


/**
 * Controller for physicalLocation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/physicalLocation/createPhysicalLocation/{from}")
public class PhysicalLocationCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(PhysicalLocationCreationController.class);
	
	@Autowired
	private PhysicalLocationService physicalLocationService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private MeasurementUnitService measurementUnitService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/physicalLocation/createPhysicalLocation";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createPhysicalLocation(@Valid @ModelAttribute("physicalLocationInfo") PhysicalLocationInfo physicalLocationInfo, Model model, HttpServletRequest request) throws Exception{
		physicalLocationInfo.setCreated(false);
		PhysicalLocationDetails physicalLocationDetails = createPhysicalLocationDetails(physicalLocationInfo);
		physicalLocationDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		physicalLocationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		physicalLocationDetails.setDateOfCreation(new Date());
		physicalLocationDetails.setDateOfModification(new Date());
		//physicalLocationDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("physicalLocationInfo", physicalLocationInfo);
		physicalLocationService.createPhysicalLocation(new CreatePhysicalLocationEvent(physicalLocationDetails));
		String calledFrom = physicalLocationInfo.getCalledFrom();
		physicalLocationInfo=new PhysicalLocationInfo();
		physicalLocationInfo.setCreated(true);
		physicalLocationInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		physicalLocationInfo.setCompanyList(allCompanyEvent.getCompanyList());
		MeasurementUnitPageEvent allMeasurementUnitEvent = measurementUnitService.requestAllByNameUnit();
		physicalLocationInfo.setMeasurementUnitList(allMeasurementUnitEvent.getMeasurementUnitList());
		MeasurementUnitPageEvent allCapacityPhysicalLocationEvent = measurementUnitService.requestAllByNameUnit();
		physicalLocationInfo.setCapacityPhysicalLocationList(allCapacityPhysicalLocationEvent.getMeasurementUnitList());

		
		physicalLocationInfo.setActive(true);

		
		model.addAttribute("physicalLocationInfo", physicalLocationInfo);
		return "/maintenance/physicalLocation/createPhysicalLocation";
	}
	
	private PhysicalLocationDetails createPhysicalLocationDetails(PhysicalLocationInfo physicalLocationInfo){
		PhysicalLocationDetails physicalLocationDetails = new PhysicalLocationDetails();
		BeanUtils.copyProperties(physicalLocationInfo, physicalLocationDetails);
		return physicalLocationDetails;
	}
	
	@ModelAttribute("physicalLocationInfo")
	private PhysicalLocationInfo getPhysicalLocationInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		PhysicalLocationInfo physicalLocationInfo = new PhysicalLocationInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		physicalLocationInfo.setCompanyList(allCompanyEvent.getCompanyList());
		MeasurementUnitPageEvent allMeasurementUnitEvent = measurementUnitService.requestAllByNameUnit();
		physicalLocationInfo.setMeasurementUnitList(allMeasurementUnitEvent.getMeasurementUnitList());
		MeasurementUnitPageEvent allCapacityPhysicalLocationEvent = measurementUnitService.requestAllByNameUnit();
		physicalLocationInfo.setCapacityPhysicalLocationList(allCapacityPhysicalLocationEvent.getMeasurementUnitList());

		
		
		if (value!=null){
			RequestPhysicalLocationDetailsEvent event = new RequestPhysicalLocationDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			PhysicalLocationDetailsEvent responseEvent = physicalLocationService.requestPhysicalLocationDetails(event);
			if (responseEvent.getPhysicalLocationDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getPhysicalLocationDetails(), physicalLocationInfo);
		}
		
		
		physicalLocationInfo.setId(null);
		physicalLocationInfo.setActive(true);

		
		physicalLocationInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("physicalLocationInfo", physicalLocationInfo);
		
		return physicalLocationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			PhysicalLocationInfo physicalLocationInfo=(PhysicalLocationInfo) req.getSession().getAttribute("physicalLocationInfo");
			modelAndView.addObject("physicalLocationInfo", physicalLocationInfo);
			modelAndView.addObject("physicalLocationValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, physicalLocationInfo);
			modelAndView.setViewName("/maintenance/physicalLocation/createPhysicalLocation");
		}
		else{
			modelAndView.addObject("physicalLocationInfo", req.getSession().getAttribute("physicalLocationInfo"));
			modelAndView.addObject("physicalLocationCreateException",true);
			modelAndView.setViewName("/maintenance/physicalLocation/createPhysicalLocation");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, PhysicalLocationInfo physicalLocationInfo){
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