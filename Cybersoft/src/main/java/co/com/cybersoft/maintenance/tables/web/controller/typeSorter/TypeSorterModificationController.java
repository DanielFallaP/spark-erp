package co.com.cybersoft.maintenance.tables.web.controller.typeSorter;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterInfo;

/**
 * Controller class for TypeSorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeSorter/modifyTypeSorter/{id}")
public class TypeSorterModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeSorterModificationController.class);
	
	@Autowired
	private TypeSorterService typeSorterService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeSorter/modifyTypeSorter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyTypeSorter(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("typeSorterInfo") TypeSorterInfo typeSorterInfo, HttpServletRequest request) throws Exception {
		
		TypeSorterDetails typeSorterDetails = createTypeSorterDetails(typeSorterInfo);
		typeSorterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeSorterDetails.setDateOfModification(new Date());
		//typeSorterDetails.set_companyId(((TypeSorterDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("typeSorterInfo", typeSorterInfo);
		typeSorterService.modifyTypeSorter(new TypeSorterModificationEvent(typeSorterDetails));
		return "redirect:/maintenance/typeSorter/searchTypeSorter";
	}
	
	private TypeSorterDetails createTypeSorterDetails(TypeSorterInfo typeSorterInfo){
		TypeSorterDetails typeSorterDetails = new TypeSorterDetails();
		BeanUtils.copyProperties(typeSorterInfo, typeSorterDetails);
		return typeSorterDetails;
	}

	@ModelAttribute("typeSorterInfo")
	private TypeSorterInfo getTypeSorterInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the typeSorter "+id);
		
		TypeSorterInfo typeSorterInfo = new TypeSorterInfo();
			
		TypeSorterDetailsEvent requestTypeSorterDetails = typeSorterService.requestTypeSorterDetails(new RequestTypeSorterDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestTypeSorterDetails.getTypeSorterDetails(), typeSorterInfo);
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
			modelAndView.setViewName("/maintenance/typeSorter/modifyTypeSorter");
		}
		else{
			modelAndView.addObject("typeSorterInfo", req.getSession().getAttribute("typeSorterInfo"));
			modelAndView.addObject("typeSorterModificationException",true);
			modelAndView.setViewName("/maintenance/typeSorter/modifyTypeSorter");
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