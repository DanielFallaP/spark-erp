package co.com.cybersoft.maintenance.tables.web.controller.job;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.core.services.job.JobService;
import co.com.cybersoft.maintenance.tables.events.job.CreateJobEvent;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobInfo;
import co.com.cybersoft.maintenance.tables.events.job.JobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterService;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;


/**
 * Controller for job
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/job/createJob/{from}")
public class JobCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(JobCreationController.class);
	
	@Autowired
	private JobService jobService;
	
	@Autowired
		private ResponsibleCenterService responsibleCenterService;

	@Autowired
		private TypeWorkService typeWorkService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/job/createJob";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createJob(@Valid @ModelAttribute("jobInfo") JobInfo jobInfo, Model model, HttpServletRequest request) throws Exception{
		jobInfo.setCreated(false);
		JobDetails jobDetails = createJobDetails(jobInfo);
		jobDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		jobDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		jobDetails.setDateOfCreation(new Date());
		jobDetails.setDateOfModification(new Date());
		//jobDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("jobInfo", jobInfo);
		jobService.createJob(new CreateJobEvent(jobDetails));
		String calledFrom = jobInfo.getCalledFrom();
		jobInfo=new JobInfo();
		jobInfo.setCreated(true);
		jobInfo.setCalledFrom(calledFrom);
		ResponsibleCenterPageEvent allResponsibleCenterEvent = responsibleCenterService.requestAllByNameResponsibleCenter();
		jobInfo.setResponsibleCenterList(allResponsibleCenterEvent.getResponsibleCenterList());
		TypeWorkPageEvent allTypeWorkEvent = typeWorkService.requestAllByTypeWork();
		jobInfo.setTypeWorkList(allTypeWorkEvent.getTypeWorkList());

		
		jobInfo.setActive(true);

		
		model.addAttribute("jobInfo", jobInfo);
		return "/maintenance/job/createJob";
	}
	
	private JobDetails createJobDetails(JobInfo jobInfo){
		JobDetails jobDetails = new JobDetails();
		BeanUtils.copyProperties(jobInfo, jobDetails);
		return jobDetails;
	}
	
	@ModelAttribute("jobInfo")
	private JobInfo getJobInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		JobInfo jobInfo = new JobInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		ResponsibleCenterPageEvent allResponsibleCenterEvent = responsibleCenterService.requestAllByNameResponsibleCenter();
		jobInfo.setResponsibleCenterList(allResponsibleCenterEvent.getResponsibleCenterList());
		TypeWorkPageEvent allTypeWorkEvent = typeWorkService.requestAllByTypeWork();
		jobInfo.setTypeWorkList(allTypeWorkEvent.getTypeWorkList());

		
		
		if (value!=null){
			RequestJobDetailsEvent event = new RequestJobDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			JobDetailsEvent responseEvent = jobService.requestJobDetails(event);
			if (responseEvent.getJobDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getJobDetails(), jobInfo);
		}
		
		
		jobInfo.setId(null);
		jobInfo.setActive(true);

		
		jobInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("jobInfo", jobInfo);
		
		return jobInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			JobInfo jobInfo=(JobInfo) req.getSession().getAttribute("jobInfo");
			modelAndView.addObject("jobInfo", jobInfo);
			modelAndView.addObject("jobValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, jobInfo);
			modelAndView.setViewName("/maintenance/job/createJob");
		}
		else{
			modelAndView.addObject("jobInfo", req.getSession().getAttribute("jobInfo"));
			modelAndView.addObject("jobCreateException",true);
			modelAndView.setViewName("/maintenance/job/createJob");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, JobInfo jobInfo){
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