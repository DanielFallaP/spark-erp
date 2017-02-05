package co.com.cybersoft.maintenance.tables.web.controller.otherConcepts;

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
import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;
import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsService;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsModificationEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;


/**
 * Controller class for OtherConcepts
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/otherConcepts/modifyOtherConcepts/{id}")
public class OtherConceptsModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(OtherConceptsModificationController.class);
	
	@Autowired
	private OtherConceptsService otherConceptsService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private MeasurementUnitService measurementUnitService;

	@Autowired
		private TypeWorkService typeWorkService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/otherConcepts/modifyOtherConcepts";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyOtherConcepts(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("otherConceptsInfo") OtherConceptsInfo otherConceptsInfo, HttpServletRequest request) throws Exception {
		
		OtherConceptsDetails otherConceptsDetails = createOtherConceptsDetails(otherConceptsInfo);
		otherConceptsDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		otherConceptsDetails.setDateOfModification(new Date());
		//otherConceptsDetails.set_companyId(((OtherConceptsDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("otherConceptsInfo", otherConceptsInfo);
		otherConceptsService.modifyOtherConcepts(new OtherConceptsModificationEvent(otherConceptsDetails));
		return "redirect:/maintenance/otherConcepts/searchOtherConcepts";
	}
	
	private OtherConceptsDetails createOtherConceptsDetails(OtherConceptsInfo otherConceptsInfo){
		OtherConceptsDetails otherConceptsDetails = new OtherConceptsDetails();
		BeanUtils.copyProperties(otherConceptsInfo, otherConceptsDetails);
		return otherConceptsDetails;
	}

	@ModelAttribute("otherConceptsInfo")
	private OtherConceptsInfo getOtherConceptsInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the otherConcepts "+id);
		
		OtherConceptsInfo otherConceptsInfo = new OtherConceptsInfo();
			
		OtherConceptsDetailsEvent requestOtherConceptsDetails = otherConceptsService.requestOtherConceptsDetails(new RequestOtherConceptsDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		otherConceptsInfo.setCompanyList(allCompanyEvent.getCompanyList());
		MeasurementUnitPageEvent allUnitMeasureEvent = measurementUnitService.requestAllByNameUnit();
		otherConceptsInfo.setUnitMeasureList(allUnitMeasureEvent.getMeasurementUnitList());
		TypeWorkPageEvent allTypeWorkEvent = typeWorkService.requestAllByTypeWork();
		otherConceptsInfo.setTypeWorkList(allTypeWorkEvent.getTypeWorkList());

		
		BeanUtils.copyProperties(requestOtherConceptsDetails.getOtherConceptsDetails(), otherConceptsInfo);
		request.getSession().setAttribute("otherConceptsInfo", otherConceptsInfo);
		
		return otherConceptsInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			OtherConceptsInfo otherConceptsInfo=(OtherConceptsInfo) req.getSession().getAttribute("otherConceptsInfo");
			modelAndView.addObject("otherConceptsInfo", otherConceptsInfo);
			modelAndView.addObject("otherConceptsValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, otherConceptsInfo);
			modelAndView.setViewName("/maintenance/otherConcepts/modifyOtherConcepts");
		}
		else{
			modelAndView.addObject("otherConceptsInfo", req.getSession().getAttribute("otherConceptsInfo"));
			modelAndView.addObject("otherConceptsModificationException",true);
			modelAndView.setViewName("/maintenance/otherConcepts/modifyOtherConcepts");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, OtherConceptsInfo otherConceptsInfo){
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