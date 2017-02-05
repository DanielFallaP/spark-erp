package co.com.cybersoft.maintenance.tables.web.controller.jobThird;

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
import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;
import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdService;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdInfo;
import co.com.cybersoft.maintenance.tables.core.services.job.JobService;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;


/**
 * Controller class for JobThird
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/jobThird/modifyJobThird/{id}")
public class JobThirdModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(JobThirdModificationController.class);
	
	@Autowired
	private JobThirdService jobThirdService;
	
	@Autowired
		private JobService jobService;

	@Autowired
		private ThirdService thirdService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/jobThird/modifyJobThird";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyJobThird(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("jobThirdInfo") JobThirdInfo jobThirdInfo, HttpServletRequest request) throws Exception {
		
		JobThirdDetails jobThirdDetails = createJobThirdDetails(jobThirdInfo);
		jobThirdDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		jobThirdDetails.setDateOfModification(new Date());
		//jobThirdDetails.set_companyId(((JobThirdDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("jobThirdInfo", jobThirdInfo);
		jobThirdService.modifyJobThird(new JobThirdModificationEvent(jobThirdDetails));
		return "redirect:/maintenance/jobThird/searchJobThird";
	}
	
	private JobThirdDetails createJobThirdDetails(JobThirdInfo jobThirdInfo){
		JobThirdDetails jobThirdDetails = new JobThirdDetails();
		BeanUtils.copyProperties(jobThirdInfo, jobThirdDetails);
		return jobThirdDetails;
	}

	@ModelAttribute("jobThirdInfo")
	private JobThirdInfo getJobThirdInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the jobThird "+id);
		
		JobThirdInfo jobThirdInfo = new JobThirdInfo();
			
		JobThirdDetailsEvent requestJobThirdDetails = jobThirdService.requestJobThirdDetails(new RequestJobThirdDetailsEvent(id));
		
		JobPageEvent allJobEvent = jobService.requestAllByNameJob();
		jobThirdInfo.setJobList(allJobEvent.getJobList());
		ThirdPageEvent allThirdEvent = thirdService.requestAllByNameThird();
		jobThirdInfo.setThirdList(allThirdEvent.getThirdList());

		
		BeanUtils.copyProperties(requestJobThirdDetails.getJobThirdDetails(), jobThirdInfo);
		request.getSession().setAttribute("jobThirdInfo", jobThirdInfo);
		
		return jobThirdInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			JobThirdInfo jobThirdInfo=(JobThirdInfo) req.getSession().getAttribute("jobThirdInfo");
			modelAndView.addObject("jobThirdInfo", jobThirdInfo);
			modelAndView.addObject("jobThirdValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, jobThirdInfo);
			modelAndView.setViewName("/maintenance/jobThird/modifyJobThird");
		}
		else{
			modelAndView.addObject("jobThirdInfo", req.getSession().getAttribute("jobThirdInfo"));
			modelAndView.addObject("jobThirdModificationException",true);
			modelAndView.setViewName("/maintenance/jobThird/modifyJobThird");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, JobThirdInfo jobThirdInfo){
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