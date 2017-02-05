package co.com.cybersoft.maintenance.tables.web.controller.company;

import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CreateCompanyEvent;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyInfo;
import co.com.cybersoft.maintenance.tables.events.company.CompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyDetailsEvent;



/**
 * Controller for company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/company/createCompany/{from}")
public class CompanyCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyCreationController.class);
	
	@Autowired
	private CompanyService companyService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/company/createCompany";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCompany(@Valid @ModelAttribute("companyInfo") CompanyInfo companyInfo, Model model, HttpServletRequest request) throws Exception{
		companyInfo.setCreated(false);
		CompanyDetails companyDetails = createCompanyDetails(companyInfo);
		companyDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setDateOfCreation(new Date());
		companyDetails.setDateOfModification(new Date());
		//companyDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("companyInfo", companyInfo);
		companyService.createCompany(new CreateCompanyEvent(companyDetails));
		String calledFrom = companyInfo.getCalledFrom();
		companyInfo=new CompanyInfo();
		companyInfo.setCreated(true);
		companyInfo.setCalledFrom(calledFrom);
		
		companyInfo.setLanguage(1);
		companyInfo.setActiveCompany(1);
		companyInfo.setActive(true);

		
		model.addAttribute("companyInfo", companyInfo);
		return "/maintenance/company/createCompany";
	}
	
	private CompanyDetails createCompanyDetails(CompanyInfo companyInfo){
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyInfo, companyDetails);
		return companyDetails;
	}
	
	@ModelAttribute("companyInfo")
	private CompanyInfo getCompanyInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CompanyInfo companyInfo = new CompanyInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestCompanyDetailsEvent event = new RequestCompanyDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CompanyDetailsEvent responseEvent = companyService.requestCompanyDetails(event);
			if (responseEvent.getCompanyDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCompanyDetails(), companyInfo);
		}
		
		
		companyInfo.setId(null);
		companyInfo.setLanguage(1);
		companyInfo.setActiveCompany(1);
		companyInfo.setActive(true);

		
		companyInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("companyInfo", companyInfo);
		
		return companyInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CompanyInfo companyInfo=(CompanyInfo) req.getSession().getAttribute("companyInfo");
			modelAndView.addObject("companyInfo", companyInfo);
			modelAndView.addObject("companyValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, companyInfo);
			modelAndView.setViewName("/maintenance/company/createCompany");
		}
		else{
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyCreateException",true);
			modelAndView.setViewName("/maintenance/company/createCompany");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CompanyInfo companyInfo){
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