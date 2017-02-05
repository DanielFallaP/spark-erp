package co.com.cybersoft.maintenance.tables.web.controller.failureCause;

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
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCauseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCauseDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for FailureCause
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCause/modifyFailureCause/{id}")
public class FailureCauseModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(FailureCauseModificationController.class);
	
	@Autowired
	private FailureCauseService failureCauseService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/failureCause/modifyFailureCause";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyFailureCause(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("failureCauseInfo") FailureCauseInfo failureCauseInfo, HttpServletRequest request) throws Exception {
		
		FailureCauseDetails failureCauseDetails = createFailureCauseDetails(failureCauseInfo);
		failureCauseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		failureCauseDetails.setDateOfModification(new Date());
		//failureCauseDetails.set_companyId(((FailureCauseDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("failureCauseInfo", failureCauseInfo);
		failureCauseService.modifyFailureCause(new FailureCauseModificationEvent(failureCauseDetails));
		return "redirect:/maintenance/failureCause/searchFailureCause";
	}
	
	private FailureCauseDetails createFailureCauseDetails(FailureCauseInfo failureCauseInfo){
		FailureCauseDetails failureCauseDetails = new FailureCauseDetails();
		BeanUtils.copyProperties(failureCauseInfo, failureCauseDetails);
		return failureCauseDetails;
	}

	@ModelAttribute("failureCauseInfo")
	private FailureCauseInfo getFailureCauseInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the failureCause "+id);
		
		FailureCauseInfo failureCauseInfo = new FailureCauseInfo();
			
		FailureCauseDetailsEvent requestFailureCauseDetails = failureCauseService.requestFailureCauseDetails(new RequestFailureCauseDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		failureCauseInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestFailureCauseDetails.getFailureCauseDetails(), failureCauseInfo);
		request.getSession().setAttribute("failureCauseInfo", failureCauseInfo);
		
		return failureCauseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			FailureCauseInfo failureCauseInfo=(FailureCauseInfo) req.getSession().getAttribute("failureCauseInfo");
			modelAndView.addObject("failureCauseInfo", failureCauseInfo);
			modelAndView.addObject("failureCauseValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, failureCauseInfo);
			modelAndView.setViewName("/maintenance/failureCause/modifyFailureCause");
		}
		else{
			modelAndView.addObject("failureCauseInfo", req.getSession().getAttribute("failureCauseInfo"));
			modelAndView.addObject("failureCauseModificationException",true);
			modelAndView.setViewName("/maintenance/failureCause/modifyFailureCause");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, FailureCauseInfo failureCauseInfo){
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