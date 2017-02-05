package co.com.cybersoft.purchase.tables.web.controller.users;

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
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.core.services.users.UsersService;
import co.com.cybersoft.purchase.tables.events.users.UsersDetailsEvent;
import co.com.cybersoft.purchase.tables.events.users.UsersModificationEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for Users
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/users/modifyUsers/{id}")
public class UsersModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(UsersModificationController.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/users/modifyUsers";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyUsers(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("usersInfo") UsersInfo usersInfo, HttpServletRequest request) throws Exception {
		
		UsersDetails usersDetails = createUsersDetails(usersInfo);
		usersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		usersDetails.setDateOfModification(new Date());
		//usersDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("usersInfo", usersInfo);
		usersService.modifyUsers(new UsersModificationEvent(usersDetails));
		return "redirect:/purchase/users/searchUsers";
	}
	
	private UsersDetails createUsersDetails(UsersInfo usersInfo){
		UsersDetails usersDetails = new UsersDetails();
		BeanUtils.copyProperties(usersInfo, usersDetails);
		return usersDetails;
	}

	@ModelAttribute("usersInfo")
	private UsersInfo getUsersInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the users "+id);
		
		UsersInfo usersInfo = new UsersInfo();
			
		UsersDetailsEvent requestUsersDetails = usersService.requestUsersDetails(new RequestUsersDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		usersInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestUsersDetails.getUsersDetails(), usersInfo);
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
			modelAndView.setViewName("/purchase/users/modifyUsers");
		}
		else{
			modelAndView.addObject("usersInfo", req.getSession().getAttribute("usersInfo"));
			modelAndView.addObject("usersModificationException",true);
			modelAndView.setViewName("/purchase/users/modifyUsers");
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