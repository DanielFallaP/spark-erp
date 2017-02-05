package co.com.cybersoft.maintenance.tables.web.controller.company;

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
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyModificationEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyInfo;

/**
 * Controller class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/company/modifyCompany/{id}")
public class CompanyModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CompanyModificationController.class);
	
	@Autowired
	private CompanyService companyService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/company/modifyCompany";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCompany(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("companyInfo") CompanyInfo companyInfo, HttpServletRequest request) throws Exception {
		
		CompanyDetails companyDetails = createCompanyDetails(companyInfo);
		companyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setDateOfModification(new Date());
		//companyDetails.set_companyId(((CompanyDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("companyInfo", companyInfo);
		companyService.modifyCompany(new CompanyModificationEvent(companyDetails));
		return "redirect:/maintenance/company/searchCompany";
	}
	
	private CompanyDetails createCompanyDetails(CompanyInfo companyInfo){
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyInfo, companyDetails);
		return companyDetails;
	}

	@ModelAttribute("companyInfo")
	private CompanyInfo getCompanyInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the company "+id);
		
		CompanyInfo companyInfo = new CompanyInfo();
			
		CompanyDetailsEvent requestCompanyDetails = companyService.requestCompanyDetails(new RequestCompanyDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestCompanyDetails.getCompanyDetails(), companyInfo);
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
			modelAndView.setViewName("/maintenance/company/modifyCompany");
		}
		else{
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyModificationException",true);
			modelAndView.setViewName("/maintenance/company/modifyCompany");
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