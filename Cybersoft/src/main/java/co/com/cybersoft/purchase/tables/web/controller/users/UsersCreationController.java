package co.com.cybersoft.purchase.tables.web.controller.users;

import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;

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
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.core.services.users.UsersService;
import co.com.cybersoft.purchase.tables.events.users.CreateUsersEvent;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersInfo;
import co.com.cybersoft.purchase.tables.events.users.UsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for users
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/users/createUsers/{from}")
public class UsersCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(UsersCreationController.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/users/createUsers";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createUsers(@Valid @ModelAttribute("usersInfo") UsersInfo usersInfo, Model model, HttpServletRequest request) throws Exception{
		usersInfo.setCreated(false);
		UsersDetails usersDetails = createUsersDetails(usersInfo);
		usersDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		usersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		usersDetails.setDateOfCreation(new Date());
		usersDetails.setDateOfModification(new Date());
		//usersDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("usersInfo", usersInfo);
		usersService.createUsers(new CreateUsersEvent(usersDetails));
		String calledFrom = usersInfo.getCalledFrom();
		usersInfo=new UsersInfo();
		usersInfo.setCreated(true);
		usersInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		usersInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		usersInfo.setActive(true);
		usersInfo.setUsersCreate(true);
		usersInfo.setUsersRead(true);
		usersInfo.setUsersUpdate(true);
		usersInfo.setUsersExport(true);

		
		model.addAttribute("usersInfo", usersInfo);
		return "/purchase/users/createUsers";
	}
	
	private UsersDetails createUsersDetails(UsersInfo usersInfo){
		UsersDetails usersDetails = new UsersDetails();
		BeanUtils.copyProperties(usersInfo, usersDetails);
		return usersDetails;
	}
	
	@ModelAttribute("usersInfo")
	private UsersInfo getUsersInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		UsersInfo usersInfo = new UsersInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		usersInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestUsersDetailsEvent event = new RequestUsersDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			UsersDetailsEvent responseEvent = usersService.requestUsersDetails(event);
			if (responseEvent.getUsersDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getUsersDetails(), usersInfo);
		}
		
		
		usersInfo.setId(null);
		usersInfo.setActive(true);
		usersInfo.setUsersCreate(true);
		usersInfo.setUsersRead(true);
		usersInfo.setUsersUpdate(true);
		usersInfo.setUsersExport(true);

		
		usersInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("usersInfo", usersInfo);
		
		return usersInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			UsersInfo usersInfo=(UsersInfo) req.getSession().getAttribute("usersInfo");
			modelAndView.addObject("usersInfo", usersInfo);
			modelAndView.addObject("usersValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, usersInfo);
			modelAndView.setViewName("/purchase/users/createUsers");
		}
		else{
			modelAndView.addObject("usersInfo", req.getSession().getAttribute("usersInfo"));
			modelAndView.addObject("usersCreateException",true);
			modelAndView.setViewName("/purchase/users/createUsers");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, UsersInfo usersInfo){
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