package co.com.cybersoft.maintenance.tables.web.controller.effectFail;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailService;
import co.com.cybersoft.maintenance.tables.events.effectFail.CreateEffectFailEvent;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailInfo;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for effectFail
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/effectFail/createEffectFail/{from}")
public class EffectFailCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(EffectFailCreationController.class);
	
	@Autowired
	private EffectFailService effectFailService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/effectFail/createEffectFail";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createEffectFail(@Valid @ModelAttribute("effectFailInfo") EffectFailInfo effectFailInfo, Model model, HttpServletRequest request) throws Exception{
		effectFailInfo.setCreated(false);
		EffectFailDetails effectFailDetails = createEffectFailDetails(effectFailInfo);
		effectFailDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		effectFailDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		effectFailDetails.setDateOfCreation(new Date());
		effectFailDetails.setDateOfModification(new Date());
		//effectFailDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("effectFailInfo", effectFailInfo);
		effectFailService.createEffectFail(new CreateEffectFailEvent(effectFailDetails));
		String calledFrom = effectFailInfo.getCalledFrom();
		effectFailInfo=new EffectFailInfo();
		effectFailInfo.setCreated(true);
		effectFailInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		effectFailInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		effectFailInfo.setActive(true);

		
		model.addAttribute("effectFailInfo", effectFailInfo);
		return "/maintenance/effectFail/createEffectFail";
	}
	
	private EffectFailDetails createEffectFailDetails(EffectFailInfo effectFailInfo){
		EffectFailDetails effectFailDetails = new EffectFailDetails();
		BeanUtils.copyProperties(effectFailInfo, effectFailDetails);
		return effectFailDetails;
	}
	
	@ModelAttribute("effectFailInfo")
	private EffectFailInfo getEffectFailInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		EffectFailInfo effectFailInfo = new EffectFailInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		effectFailInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestEffectFailDetailsEvent event = new RequestEffectFailDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			EffectFailDetailsEvent responseEvent = effectFailService.requestEffectFailDetails(event);
			if (responseEvent.getEffectFailDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getEffectFailDetails(), effectFailInfo);
		}
		
		
		effectFailInfo.setId(null);
		effectFailInfo.setActive(true);

		
		effectFailInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("effectFailInfo", effectFailInfo);
		
		return effectFailInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			EffectFailInfo effectFailInfo=(EffectFailInfo) req.getSession().getAttribute("effectFailInfo");
			modelAndView.addObject("effectFailInfo", effectFailInfo);
			modelAndView.addObject("effectFailValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, effectFailInfo);
			modelAndView.setViewName("/maintenance/effectFail/createEffectFail");
		}
		else{
			modelAndView.addObject("effectFailInfo", req.getSession().getAttribute("effectFailInfo"));
			modelAndView.addObject("effectFailCreateException",true);
			modelAndView.setViewName("/maintenance/effectFail/createEffectFail");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, EffectFailInfo effectFailInfo){
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