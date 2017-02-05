package co.com.cybersoft.maintenance.tables.web.controller.failureCauseTechnicalaction;

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
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;
import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionService;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionInfo;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;


/**
 * Controller class for FailureCauseTechnicalaction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCauseTechnicalaction/modifyFailureCauseTechnicalaction/{id}")
public class FailureCauseTechnicalactionModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(FailureCauseTechnicalactionModificationController.class);
	
	@Autowired
	private FailureCauseTechnicalactionService failureCauseTechnicalactionService;
	
	@Autowired
		private FailureCauseService failureCauseService;

	@Autowired
		private TechnicalActionService technicalActionService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/failureCauseTechnicalaction/modifyFailureCauseTechnicalaction";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyFailureCauseTechnicalaction(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("failureCauseTechnicalactionInfo") FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo, HttpServletRequest request) throws Exception {
		
		FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails = createFailureCauseTechnicalactionDetails(failureCauseTechnicalactionInfo);
		failureCauseTechnicalactionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseTechnicalactionDetails.setDateOfModification(new Date());
		//failureCauseTechnicalactionDetails.set_companyId(((FailureCauseTechnicalactionDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
		failureCauseTechnicalactionService.modifyFailureCauseTechnicalaction(new FailureCauseTechnicalactionModificationEvent(failureCauseTechnicalactionDetails));
		return "redirect:/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction";
	}
	
	private FailureCauseTechnicalactionDetails createFailureCauseTechnicalactionDetails(FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo){
		FailureCauseTechnicalactionDetails failureCauseTechnicalactionDetails = new FailureCauseTechnicalactionDetails();
		BeanUtils.copyProperties(failureCauseTechnicalactionInfo, failureCauseTechnicalactionDetails);
		return failureCauseTechnicalactionDetails;
	}

	@ModelAttribute("failureCauseTechnicalactionInfo")
	private FailureCauseTechnicalactionInfo getFailureCauseTechnicalactionInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the failureCauseTechnicalaction "+id);
		
		FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo = new FailureCauseTechnicalactionInfo();
			
		FailureCauseTechnicalactionDetailsEvent requestFailureCauseTechnicalactionDetails = failureCauseTechnicalactionService.requestFailureCauseTechnicalactionDetails(new RequestFailureCauseTechnicalactionDetailsEvent(id));
		
		FailureCausePageEvent allFailureCauseEvent = failureCauseService.requestAllByNameFailureCause();
		failureCauseTechnicalactionInfo.setFailureCauseList(allFailureCauseEvent.getFailureCauseList());
		TechnicalActionPageEvent allTechnicalActionEvent = technicalActionService.requestAllByTechnicalActionName();
		failureCauseTechnicalactionInfo.setTechnicalActionList(allTechnicalActionEvent.getTechnicalActionList());

		
		BeanUtils.copyProperties(requestFailureCauseTechnicalactionDetails.getFailureCauseTechnicalactionDetails(), failureCauseTechnicalactionInfo);
		request.getSession().setAttribute("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
		
		return failureCauseTechnicalactionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo=(FailureCauseTechnicalactionInfo) req.getSession().getAttribute("failureCauseTechnicalactionInfo");
			modelAndView.addObject("failureCauseTechnicalactionInfo", failureCauseTechnicalactionInfo);
			modelAndView.addObject("failureCauseTechnicalactionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, failureCauseTechnicalactionInfo);
			modelAndView.setViewName("/maintenance/failureCauseTechnicalaction/modifyFailureCauseTechnicalaction");
		}
		else{
			modelAndView.addObject("failureCauseTechnicalactionInfo", req.getSession().getAttribute("failureCauseTechnicalactionInfo"));
			modelAndView.addObject("failureCauseTechnicalactionModificationException",true);
			modelAndView.setViewName("/maintenance/failureCauseTechnicalaction/modifyFailureCauseTechnicalaction");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, FailureCauseTechnicalactionInfo failureCauseTechnicalactionInfo){
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