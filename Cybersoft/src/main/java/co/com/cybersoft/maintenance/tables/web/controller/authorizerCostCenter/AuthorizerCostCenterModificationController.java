package co.com.cybersoft.maintenance.tables.web.controller.authorizerCostCenter;

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
import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;
import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterService;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterInfo;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;


/**
 * Controller class for AuthorizerCostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/authorizerCostCenter/modifyAuthorizerCostCenter/{id}")
public class AuthorizerCostCenterModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(AuthorizerCostCenterModificationController.class);
	
	@Autowired
	private AuthorizerCostCenterService authorizerCostCenterService;
	
	@Autowired
		private CostCentersCustomersService costCentersCustomersService;

	@Autowired
		private ResponsibleService responsibleService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/authorizerCostCenter/modifyAuthorizerCostCenter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyAuthorizerCostCenter(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("authorizerCostCenterInfo") AuthorizerCostCenterInfo authorizerCostCenterInfo, HttpServletRequest request) throws Exception {
		
		AuthorizerCostCenterDetails authorizerCostCenterDetails = createAuthorizerCostCenterDetails(authorizerCostCenterInfo);
		authorizerCostCenterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		authorizerCostCenterDetails.setDateOfModification(new Date());
		//authorizerCostCenterDetails.set_companyId(((AuthorizerCostCenterDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("authorizerCostCenterInfo", authorizerCostCenterInfo);
		authorizerCostCenterService.modifyAuthorizerCostCenter(new AuthorizerCostCenterModificationEvent(authorizerCostCenterDetails));
		return "redirect:/maintenance/authorizerCostCenter/searchAuthorizerCostCenter";
	}
	
	private AuthorizerCostCenterDetails createAuthorizerCostCenterDetails(AuthorizerCostCenterInfo authorizerCostCenterInfo){
		AuthorizerCostCenterDetails authorizerCostCenterDetails = new AuthorizerCostCenterDetails();
		BeanUtils.copyProperties(authorizerCostCenterInfo, authorizerCostCenterDetails);
		return authorizerCostCenterDetails;
	}

	@ModelAttribute("authorizerCostCenterInfo")
	private AuthorizerCostCenterInfo getAuthorizerCostCenterInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the authorizerCostCenter "+id);
		
		AuthorizerCostCenterInfo authorizerCostCenterInfo = new AuthorizerCostCenterInfo();
			
		AuthorizerCostCenterDetailsEvent requestAuthorizerCostCenterDetails = authorizerCostCenterService.requestAuthorizerCostCenterDetails(new RequestAuthorizerCostCenterDetailsEvent(id));
		
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		authorizerCostCenterInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());
		ResponsiblePageEvent allResponsibleEvent = responsibleService.requestAllByThird();
		authorizerCostCenterInfo.setResponsibleList(allResponsibleEvent.getResponsibleList());

		
		BeanUtils.copyProperties(requestAuthorizerCostCenterDetails.getAuthorizerCostCenterDetails(), authorizerCostCenterInfo);
		request.getSession().setAttribute("authorizerCostCenterInfo", authorizerCostCenterInfo);
		
		return authorizerCostCenterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			AuthorizerCostCenterInfo authorizerCostCenterInfo=(AuthorizerCostCenterInfo) req.getSession().getAttribute("authorizerCostCenterInfo");
			modelAndView.addObject("authorizerCostCenterInfo", authorizerCostCenterInfo);
			modelAndView.addObject("authorizerCostCenterValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, authorizerCostCenterInfo);
			modelAndView.setViewName("/maintenance/authorizerCostCenter/modifyAuthorizerCostCenter");
		}
		else{
			modelAndView.addObject("authorizerCostCenterInfo", req.getSession().getAttribute("authorizerCostCenterInfo"));
			modelAndView.addObject("authorizerCostCenterModificationException",true);
			modelAndView.setViewName("/maintenance/authorizerCostCenter/modifyAuthorizerCostCenter");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, AuthorizerCostCenterInfo authorizerCostCenterInfo){
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