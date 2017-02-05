package co.com.cybersoft.maintenance.tables.web.controller.accountant;

import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;
import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantService;
import co.com.cybersoft.maintenance.tables.events.accountant.CreateAccountantEvent;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantInfo;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for accountant
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/accountant/createAccountant/{from}")
public class AccountantCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountantCreationController.class);
	
	@Autowired
	private AccountantService accountantService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/accountant/createAccountant";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createAccountant(@Valid @ModelAttribute("accountantInfo") AccountantInfo accountantInfo, Model model, HttpServletRequest request) throws Exception{
		accountantInfo.setCreated(false);
		AccountantDetails accountantDetails = createAccountantDetails(accountantInfo);
		accountantDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		accountantDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		accountantDetails.setDateOfCreation(new Date());
		accountantDetails.setDateOfModification(new Date());
		//accountantDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("accountantInfo", accountantInfo);
		accountantService.createAccountant(new CreateAccountantEvent(accountantDetails));
		String calledFrom = accountantInfo.getCalledFrom();
		accountantInfo=new AccountantInfo();
		accountantInfo.setCreated(true);
		accountantInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		accountantInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		accountantInfo.setActive(true);

		
		model.addAttribute("accountantInfo", accountantInfo);
		return "/maintenance/accountant/createAccountant";
	}
	
	private AccountantDetails createAccountantDetails(AccountantInfo accountantInfo){
		AccountantDetails accountantDetails = new AccountantDetails();
		BeanUtils.copyProperties(accountantInfo, accountantDetails);
		return accountantDetails;
	}
	
	@ModelAttribute("accountantInfo")
	private AccountantInfo getAccountantInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		AccountantInfo accountantInfo = new AccountantInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		accountantInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestAccountantDetailsEvent event = new RequestAccountantDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			AccountantDetailsEvent responseEvent = accountantService.requestAccountantDetails(event);
			if (responseEvent.getAccountantDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getAccountantDetails(), accountantInfo);
		}
		
		
		accountantInfo.setId(null);
		accountantInfo.setActive(true);

		
		accountantInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("accountantInfo", accountantInfo);
		
		return accountantInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			AccountantInfo accountantInfo=(AccountantInfo) req.getSession().getAttribute("accountantInfo");
			modelAndView.addObject("accountantInfo", accountantInfo);
			modelAndView.addObject("accountantValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, accountantInfo);
			modelAndView.setViewName("/maintenance/accountant/createAccountant");
		}
		else{
			modelAndView.addObject("accountantInfo", req.getSession().getAttribute("accountantInfo"));
			modelAndView.addObject("accountantCreateException",true);
			modelAndView.setViewName("/maintenance/accountant/createAccountant");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, AccountantInfo accountantInfo){
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