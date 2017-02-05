package co.com.cybersoft.maintenance.tables.web.controller.costCentersCustomers;

import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CreateCostCentersCustomersEvent;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersInfo;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;


/**
 * Controller for costCentersCustomers
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/costCentersCustomers/createCostCentersCustomers/{from}")
public class CostCentersCustomersCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CostCentersCustomersCreationController.class);
	
	@Autowired
	private CostCentersCustomersService costCentersCustomersService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private StateCostCentersService stateCostCentersService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/costCentersCustomers/createCostCentersCustomers";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCostCentersCustomers(@Valid @ModelAttribute("costCentersCustomersInfo") CostCentersCustomersInfo costCentersCustomersInfo, Model model, HttpServletRequest request) throws Exception{
		costCentersCustomersInfo.setCreated(false);
		CostCentersCustomersDetails costCentersCustomersDetails = createCostCentersCustomersDetails(costCentersCustomersInfo);
		costCentersCustomersDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		costCentersCustomersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		costCentersCustomersDetails.setDateOfCreation(new Date());
		costCentersCustomersDetails.setDateOfModification(new Date());
		//costCentersCustomersDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("costCentersCustomersInfo", costCentersCustomersInfo);
		costCentersCustomersService.createCostCentersCustomers(new CreateCostCentersCustomersEvent(costCentersCustomersDetails));
		String calledFrom = costCentersCustomersInfo.getCalledFrom();
		costCentersCustomersInfo=new CostCentersCustomersInfo();
		costCentersCustomersInfo.setCreated(true);
		costCentersCustomersInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		costCentersCustomersInfo.setCompanyList(allCompanyEvent.getCompanyList());
		StateCostCentersPageEvent allStateEvent = stateCostCentersService.requestAllByStateCostCenters();
		costCentersCustomersInfo.setStateList(allStateEvent.getStateCostCentersList());

		
		costCentersCustomersInfo.setActive(true);

		
		model.addAttribute("costCentersCustomersInfo", costCentersCustomersInfo);
		return "/maintenance/costCentersCustomers/createCostCentersCustomers";
	}
	
	private CostCentersCustomersDetails createCostCentersCustomersDetails(CostCentersCustomersInfo costCentersCustomersInfo){
		CostCentersCustomersDetails costCentersCustomersDetails = new CostCentersCustomersDetails();
		BeanUtils.copyProperties(costCentersCustomersInfo, costCentersCustomersDetails);
		return costCentersCustomersDetails;
	}
	
	@ModelAttribute("costCentersCustomersInfo")
	private CostCentersCustomersInfo getCostCentersCustomersInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CostCentersCustomersInfo costCentersCustomersInfo = new CostCentersCustomersInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		costCentersCustomersInfo.setCompanyList(allCompanyEvent.getCompanyList());
		StateCostCentersPageEvent allStateEvent = stateCostCentersService.requestAllByStateCostCenters();
		costCentersCustomersInfo.setStateList(allStateEvent.getStateCostCentersList());

		
		
		if (value!=null){
			RequestCostCentersCustomersDetailsEvent event = new RequestCostCentersCustomersDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CostCentersCustomersDetailsEvent responseEvent = costCentersCustomersService.requestCostCentersCustomersDetails(event);
			if (responseEvent.getCostCentersCustomersDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCostCentersCustomersDetails(), costCentersCustomersInfo);
		}
		
		
		costCentersCustomersInfo.setId(null);
		costCentersCustomersInfo.setActive(true);

		
		costCentersCustomersInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("costCentersCustomersInfo", costCentersCustomersInfo);
		
		return costCentersCustomersInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CostCentersCustomersInfo costCentersCustomersInfo=(CostCentersCustomersInfo) req.getSession().getAttribute("costCentersCustomersInfo");
			modelAndView.addObject("costCentersCustomersInfo", costCentersCustomersInfo);
			modelAndView.addObject("costCentersCustomersValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, costCentersCustomersInfo);
			modelAndView.setViewName("/maintenance/costCentersCustomers/createCostCentersCustomers");
		}
		else{
			modelAndView.addObject("costCentersCustomersInfo", req.getSession().getAttribute("costCentersCustomersInfo"));
			modelAndView.addObject("costCentersCustomersCreateException",true);
			modelAndView.setViewName("/maintenance/costCentersCustomers/createCostCentersCustomers");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CostCentersCustomersInfo costCentersCustomersInfo){
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