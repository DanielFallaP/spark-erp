package co.com.cybersoft.maintenance.tables.web.controller.typeConceptKardex;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexService;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexInfo;

/**
 * Controller class for TypeConceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeConceptKardex/modifyTypeConceptKardex/{id}")
public class TypeConceptKardexModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeConceptKardexModificationController.class);
	
	@Autowired
	private TypeConceptKardexService typeConceptKardexService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeConceptKardex/modifyTypeConceptKardex";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyTypeConceptKardex(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("typeConceptKardexInfo") TypeConceptKardexInfo typeConceptKardexInfo, HttpServletRequest request) throws Exception {
		
		TypeConceptKardexDetails typeConceptKardexDetails = createTypeConceptKardexDetails(typeConceptKardexInfo);
		typeConceptKardexDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeConceptKardexDetails.setDateOfModification(new Date());
		//typeConceptKardexDetails.set_companyId(((TypeConceptKardexDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("typeConceptKardexInfo", typeConceptKardexInfo);
		typeConceptKardexService.modifyTypeConceptKardex(new TypeConceptKardexModificationEvent(typeConceptKardexDetails));
		return "redirect:/maintenance/typeConceptKardex/searchTypeConceptKardex";
	}
	
	private TypeConceptKardexDetails createTypeConceptKardexDetails(TypeConceptKardexInfo typeConceptKardexInfo){
		TypeConceptKardexDetails typeConceptKardexDetails = new TypeConceptKardexDetails();
		BeanUtils.copyProperties(typeConceptKardexInfo, typeConceptKardexDetails);
		return typeConceptKardexDetails;
	}

	@ModelAttribute("typeConceptKardexInfo")
	private TypeConceptKardexInfo getTypeConceptKardexInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the typeConceptKardex "+id);
		
		TypeConceptKardexInfo typeConceptKardexInfo = new TypeConceptKardexInfo();
			
		TypeConceptKardexDetailsEvent requestTypeConceptKardexDetails = typeConceptKardexService.requestTypeConceptKardexDetails(new RequestTypeConceptKardexDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestTypeConceptKardexDetails.getTypeConceptKardexDetails(), typeConceptKardexInfo);
		request.getSession().setAttribute("typeConceptKardexInfo", typeConceptKardexInfo);
		
		return typeConceptKardexInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			TypeConceptKardexInfo typeConceptKardexInfo=(TypeConceptKardexInfo) req.getSession().getAttribute("typeConceptKardexInfo");
			modelAndView.addObject("typeConceptKardexInfo", typeConceptKardexInfo);
			modelAndView.addObject("typeConceptKardexValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, typeConceptKardexInfo);
			modelAndView.setViewName("/maintenance/typeConceptKardex/modifyTypeConceptKardex");
		}
		else{
			modelAndView.addObject("typeConceptKardexInfo", req.getSession().getAttribute("typeConceptKardexInfo"));
			modelAndView.addObject("typeConceptKardexModificationException",true);
			modelAndView.setViewName("/maintenance/typeConceptKardex/modifyTypeConceptKardex");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, TypeConceptKardexInfo typeConceptKardexInfo){
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