package co.com.cybersoft.maintenance.tables.web.controller.responsible;

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
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsibleModificationEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsibleDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleInfo;
import co.com.cybersoft.maintenance.tables.core.services.job.JobService;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;


/**
 * Controller class for Responsible
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/responsible/modifyResponsible/{id}")
public class ResponsibleModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ResponsibleModificationController.class);
	
	@Autowired
	private ResponsibleService responsibleService;
	
	@Autowired
		private JobService jobService;

	@Autowired
		private ThirdService thirdService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/responsible/modifyResponsible";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyResponsible(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("responsibleInfo") ResponsibleInfo responsibleInfo, HttpServletRequest request) throws Exception {
		
		ResponsibleDetails responsibleDetails = createResponsibleDetails(responsibleInfo);
		responsibleDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		responsibleDetails.setDateOfModification(new Date());
		//responsibleDetails.set_companyId(((ResponsibleDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("responsibleInfo", responsibleInfo);
		responsibleService.modifyResponsible(new ResponsibleModificationEvent(responsibleDetails));
		return "redirect:/maintenance/responsible/searchResponsible";
	}
	
	private ResponsibleDetails createResponsibleDetails(ResponsibleInfo responsibleInfo){
		ResponsibleDetails responsibleDetails = new ResponsibleDetails();
		BeanUtils.copyProperties(responsibleInfo, responsibleDetails);
		return responsibleDetails;
	}

	@ModelAttribute("responsibleInfo")
	private ResponsibleInfo getResponsibleInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the responsible "+id);
		
		ResponsibleInfo responsibleInfo = new ResponsibleInfo();
			
		ResponsibleDetailsEvent requestResponsibleDetails = responsibleService.requestResponsibleDetails(new RequestResponsibleDetailsEvent(id));
		
		JobPageEvent allJobEvent = jobService.requestAllByNameJob();
		responsibleInfo.setJobList(allJobEvent.getJobList());
		ThirdPageEvent allThirdEvent = thirdService.requestAllByNameThird();
		responsibleInfo.setThirdList(allThirdEvent.getThirdList());

		
		BeanUtils.copyProperties(requestResponsibleDetails.getResponsibleDetails(), responsibleInfo);
		request.getSession().setAttribute("responsibleInfo", responsibleInfo);
		
		return responsibleInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ResponsibleInfo responsibleInfo=(ResponsibleInfo) req.getSession().getAttribute("responsibleInfo");
			modelAndView.addObject("responsibleInfo", responsibleInfo);
			modelAndView.addObject("responsibleValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, responsibleInfo);
			modelAndView.setViewName("/maintenance/responsible/modifyResponsible");
		}
		else{
			modelAndView.addObject("responsibleInfo", req.getSession().getAttribute("responsibleInfo"));
			modelAndView.addObject("responsibleModificationException",true);
			modelAndView.setViewName("/maintenance/responsible/modifyResponsible");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ResponsibleInfo responsibleInfo){
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