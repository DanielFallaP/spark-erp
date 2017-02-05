package co.com.cybersoft.maintenance.tables.web.controller.effectFailTechnicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionService;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.CreateEffectFailTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionInfo;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailService;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;


/**
 * Controller for effectFailTechnicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/effectFailTechnicalAction/createEffectFailTechnicalAction/{from}")
public class EffectFailTechnicalActionCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(EffectFailTechnicalActionCreationController.class);
	
	@Autowired
	private EffectFailTechnicalActionService effectFailTechnicalActionService;
	
	@Autowired
		private EffectFailService effectFailService;

	@Autowired
		private TechnicalActionService technicalActionService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/effectFailTechnicalAction/createEffectFailTechnicalAction";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createEffectFailTechnicalAction(@Valid @ModelAttribute("effectFailTechnicalActionInfo") EffectFailTechnicalActionInfo effectFailTechnicalActionInfo, Model model, HttpServletRequest request) throws Exception{
		effectFailTechnicalActionInfo.setCreated(false);
		EffectFailTechnicalActionDetails effectFailTechnicalActionDetails = createEffectFailTechnicalActionDetails(effectFailTechnicalActionInfo);
		effectFailTechnicalActionDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		effectFailTechnicalActionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		effectFailTechnicalActionDetails.setDateOfCreation(new Date());
		effectFailTechnicalActionDetails.setDateOfModification(new Date());
		//effectFailTechnicalActionDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("effectFailTechnicalActionInfo", effectFailTechnicalActionInfo);
		effectFailTechnicalActionService.createEffectFailTechnicalAction(new CreateEffectFailTechnicalActionEvent(effectFailTechnicalActionDetails));
		String calledFrom = effectFailTechnicalActionInfo.getCalledFrom();
		effectFailTechnicalActionInfo=new EffectFailTechnicalActionInfo();
		effectFailTechnicalActionInfo.setCreated(true);
		effectFailTechnicalActionInfo.setCalledFrom(calledFrom);
		EffectFailPageEvent allEffectFailEvent = effectFailService.requestAllByEffectFailName();
		effectFailTechnicalActionInfo.setEffectFailList(allEffectFailEvent.getEffectFailList());
		TechnicalActionPageEvent allTechnicalActionEvent = technicalActionService.requestAllByTechnicalActionName();
		effectFailTechnicalActionInfo.setTechnicalActionList(allTechnicalActionEvent.getTechnicalActionList());

		
		effectFailTechnicalActionInfo.setActive(true);

		
		model.addAttribute("effectFailTechnicalActionInfo", effectFailTechnicalActionInfo);
		return "/maintenance/effectFailTechnicalAction/createEffectFailTechnicalAction";
	}
	
	private EffectFailTechnicalActionDetails createEffectFailTechnicalActionDetails(EffectFailTechnicalActionInfo effectFailTechnicalActionInfo){
		EffectFailTechnicalActionDetails effectFailTechnicalActionDetails = new EffectFailTechnicalActionDetails();
		BeanUtils.copyProperties(effectFailTechnicalActionInfo, effectFailTechnicalActionDetails);
		return effectFailTechnicalActionDetails;
	}
	
	@ModelAttribute("effectFailTechnicalActionInfo")
	private EffectFailTechnicalActionInfo getEffectFailTechnicalActionInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		EffectFailTechnicalActionInfo effectFailTechnicalActionInfo = new EffectFailTechnicalActionInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		EffectFailPageEvent allEffectFailEvent = effectFailService.requestAllByEffectFailName();
		effectFailTechnicalActionInfo.setEffectFailList(allEffectFailEvent.getEffectFailList());
		TechnicalActionPageEvent allTechnicalActionEvent = technicalActionService.requestAllByTechnicalActionName();
		effectFailTechnicalActionInfo.setTechnicalActionList(allTechnicalActionEvent.getTechnicalActionList());

		
		
		if (value!=null){
			RequestEffectFailTechnicalActionDetailsEvent event = new RequestEffectFailTechnicalActionDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			EffectFailTechnicalActionDetailsEvent responseEvent = effectFailTechnicalActionService.requestEffectFailTechnicalActionDetails(event);
			if (responseEvent.getEffectFailTechnicalActionDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getEffectFailTechnicalActionDetails(), effectFailTechnicalActionInfo);
		}
		
		
		effectFailTechnicalActionInfo.setId(null);
		effectFailTechnicalActionInfo.setActive(true);

		
		effectFailTechnicalActionInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("effectFailTechnicalActionInfo", effectFailTechnicalActionInfo);
		
		return effectFailTechnicalActionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			EffectFailTechnicalActionInfo effectFailTechnicalActionInfo=(EffectFailTechnicalActionInfo) req.getSession().getAttribute("effectFailTechnicalActionInfo");
			modelAndView.addObject("effectFailTechnicalActionInfo", effectFailTechnicalActionInfo);
			modelAndView.addObject("effectFailTechnicalActionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, effectFailTechnicalActionInfo);
			modelAndView.setViewName("/maintenance/effectFailTechnicalAction/createEffectFailTechnicalAction");
		}
		else{
			modelAndView.addObject("effectFailTechnicalActionInfo", req.getSession().getAttribute("effectFailTechnicalActionInfo"));
			modelAndView.addObject("effectFailTechnicalActionCreateException",true);
			modelAndView.setViewName("/maintenance/effectFailTechnicalAction/createEffectFailTechnicalAction");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, EffectFailTechnicalActionInfo effectFailTechnicalActionInfo){
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