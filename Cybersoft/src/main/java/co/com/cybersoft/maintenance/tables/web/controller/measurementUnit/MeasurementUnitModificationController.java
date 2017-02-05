package co.com.cybersoft.maintenance.tables.web.controller.measurementUnit;

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
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/measurementUnit/modifyMeasurementUnit/{id}")
public class MeasurementUnitModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(MeasurementUnitModificationController.class);
	
	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/measurementUnit/modifyMeasurementUnit";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyMeasurementUnit(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("measurementUnitInfo") MeasurementUnitInfo measurementUnitInfo, HttpServletRequest request) throws Exception {
		
		MeasurementUnitDetails measurementUnitDetails = createMeasurementUnitDetails(measurementUnitInfo);
		measurementUnitDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitDetails.setDateOfModification(new Date());
		//measurementUnitDetails.set_companyId(((MeasurementUnitDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("measurementUnitInfo", measurementUnitInfo);
		measurementUnitService.modifyMeasurementUnit(new MeasurementUnitModificationEvent(measurementUnitDetails));
		return "redirect:/maintenance/measurementUnit/searchMeasurementUnit";
	}
	
	private MeasurementUnitDetails createMeasurementUnitDetails(MeasurementUnitInfo measurementUnitInfo){
		MeasurementUnitDetails measurementUnitDetails = new MeasurementUnitDetails();
		BeanUtils.copyProperties(measurementUnitInfo, measurementUnitDetails);
		return measurementUnitDetails;
	}

	@ModelAttribute("measurementUnitInfo")
	private MeasurementUnitInfo getMeasurementUnitInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the measurementUnit "+id);
		
		MeasurementUnitInfo measurementUnitInfo = new MeasurementUnitInfo();
			
		MeasurementUnitDetailsEvent requestMeasurementUnitDetails = measurementUnitService.requestMeasurementUnitDetails(new RequestMeasurementUnitDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		measurementUnitInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestMeasurementUnitDetails.getMeasurementUnitDetails(), measurementUnitInfo);
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
			modelAndView.setViewName("/maintenance/measurementUnit/modifyMeasurementUnit");
		}
		else{
			modelAndView.addObject("measurementUnitInfo", req.getSession().getAttribute("measurementUnitInfo"));
			modelAndView.addObject("measurementUnitModificationException",true);
			modelAndView.setViewName("/maintenance/measurementUnit/modifyMeasurementUnit");
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