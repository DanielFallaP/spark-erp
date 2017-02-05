package co.com.cybersoft.maintenance.tables.web.controller.accountant;

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
import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;
import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantService;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantModificationEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for Accountant
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/accountant/modifyAccountant/{id}")
public class AccountantModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(AccountantModificationController.class);
	
	@Autowired
	private AccountantService accountantService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/accountant/modifyAccountant";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyAccountant(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("accountantInfo") AccountantInfo accountantInfo, HttpServletRequest request) throws Exception {
		
		AccountantDetails accountantDetails = createAccountantDetails(accountantInfo);
		accountantDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		accountantDetails.setDateOfModification(new Date());
		//accountantDetails.set_companyId(((AccountantDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("accountantInfo", accountantInfo);
		accountantService.modifyAccountant(new AccountantModificationEvent(accountantDetails));
		return "redirect:/maintenance/accountant/searchAccountant";
	}
	
	private AccountantDetails createAccountantDetails(AccountantInfo accountantInfo){
		AccountantDetails accountantDetails = new AccountantDetails();
		BeanUtils.copyProperties(accountantInfo, accountantDetails);
		return accountantDetails;
	}

	@ModelAttribute("accountantInfo")
	private AccountantInfo getAccountantInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the accountant "+id);
		
		AccountantInfo accountantInfo = new AccountantInfo();
			
		AccountantDetailsEvent requestAccountantDetails = accountantService.requestAccountantDetails(new RequestAccountantDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		accountantInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestAccountantDetails.getAccountantDetails(), accountantInfo);
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
			modelAndView.setViewName("/maintenance/accountant/modifyAccountant");
		}
		else{
			modelAndView.addObject("accountantInfo", req.getSession().getAttribute("accountantInfo"));
			modelAndView.addObject("accountantModificationException",true);
			modelAndView.setViewName("/maintenance/accountant/modifyAccountant");
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