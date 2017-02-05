package co.com.cybersoft.maintenance.tables.web.controller.typeCauseClose;

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
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseInfo;

/**
 * Controller class for TypeCauseClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeCauseClose/modifyTypeCauseClose/{id}")
public class TypeCauseCloseModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeCauseCloseModificationController.class);
	
	@Autowired
	private TypeCauseCloseService typeCauseCloseService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/typeCauseClose/modifyTypeCauseClose";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyTypeCauseClose(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("typeCauseCloseInfo") TypeCauseCloseInfo typeCauseCloseInfo, HttpServletRequest request) throws Exception {
		
		TypeCauseCloseDetails typeCauseCloseDetails = createTypeCauseCloseDetails(typeCauseCloseInfo);
		typeCauseCloseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		typeCauseCloseDetails.setDateOfModification(new Date());
		//typeCauseCloseDetails.set_companyId(((TypeCauseCloseDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("typeCauseCloseInfo", typeCauseCloseInfo);
		typeCauseCloseService.modifyTypeCauseClose(new TypeCauseCloseModificationEvent(typeCauseCloseDetails));
		return "redirect:/maintenance/typeCauseClose/searchTypeCauseClose";
	}
	
	private TypeCauseCloseDetails createTypeCauseCloseDetails(TypeCauseCloseInfo typeCauseCloseInfo){
		TypeCauseCloseDetails typeCauseCloseDetails = new TypeCauseCloseDetails();
		BeanUtils.copyProperties(typeCauseCloseInfo, typeCauseCloseDetails);
		return typeCauseCloseDetails;
	}

	@ModelAttribute("typeCauseCloseInfo")
	private TypeCauseCloseInfo getTypeCauseCloseInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the typeCauseClose "+id);
		
		TypeCauseCloseInfo typeCauseCloseInfo = new TypeCauseCloseInfo();
			
		TypeCauseCloseDetailsEvent requestTypeCauseCloseDetails = typeCauseCloseService.requestTypeCauseCloseDetails(new RequestTypeCauseCloseDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestTypeCauseCloseDetails.getTypeCauseCloseDetails(), typeCauseCloseInfo);
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
			modelAndView.setViewName("/maintenance/typeCauseClose/modifyTypeCauseClose");
		}
		else{
			modelAndView.addObject("typeCauseCloseInfo", req.getSession().getAttribute("typeCauseCloseInfo"));
			modelAndView.addObject("typeCauseCloseModificationException",true);
			modelAndView.setViewName("/maintenance/typeCauseClose/modifyTypeCauseClose");
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