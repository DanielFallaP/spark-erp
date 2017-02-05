package co.com.cybersoft.maintenance.tables.web.controller.third;

import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.maintenance.tables.events.third.CreateThirdEvent;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdInfo;
import co.com.cybersoft.maintenance.tables.events.third.ThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;


/**
 * Controller for third
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/third/createThird/{from}")
public class ThirdCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ThirdCreationController.class);
	
	@Autowired
	private ThirdService thirdService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private CostCentersCustomersService costCentersCustomersService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/third/createThird";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createThird(@Valid @ModelAttribute("thirdInfo") ThirdInfo thirdInfo, Model model, HttpServletRequest request) throws Exception{
		thirdInfo.setCreated(false);
		ThirdDetails thirdDetails = createThirdDetails(thirdInfo);
		thirdDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		thirdDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		thirdDetails.setDateOfCreation(new Date());
		thirdDetails.setDateOfModification(new Date());
		//thirdDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("thirdInfo", thirdInfo);
		thirdService.createThird(new CreateThirdEvent(thirdDetails));
		String calledFrom = thirdInfo.getCalledFrom();
		thirdInfo=new ThirdInfo();
		thirdInfo.setCreated(true);
		thirdInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		thirdInfo.setCompanyList(allCompanyEvent.getCompanyList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		thirdInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		thirdInfo.setActive(true);

		
		model.addAttribute("thirdInfo", thirdInfo);
		return "/maintenance/third/createThird";
	}
	
	private ThirdDetails createThirdDetails(ThirdInfo thirdInfo){
		ThirdDetails thirdDetails = new ThirdDetails();
		BeanUtils.copyProperties(thirdInfo, thirdDetails);
		return thirdDetails;
	}
	
	@ModelAttribute("thirdInfo")
	private ThirdInfo getThirdInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ThirdInfo thirdInfo = new ThirdInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		thirdInfo.setCompanyList(allCompanyEvent.getCompanyList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		thirdInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		
		if (value!=null){
			RequestThirdDetailsEvent event = new RequestThirdDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ThirdDetailsEvent responseEvent = thirdService.requestThirdDetails(event);
			if (responseEvent.getThirdDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getThirdDetails(), thirdInfo);
		}
		
		
		thirdInfo.setId(null);
		thirdInfo.setActive(true);

		
		thirdInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("thirdInfo", thirdInfo);
		
		return thirdInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ThirdInfo thirdInfo=(ThirdInfo) req.getSession().getAttribute("thirdInfo");
			modelAndView.addObject("thirdInfo", thirdInfo);
			modelAndView.addObject("thirdValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, thirdInfo);
			modelAndView.setViewName("/maintenance/third/createThird");
		}
		else{
			modelAndView.addObject("thirdInfo", req.getSession().getAttribute("thirdInfo"));
			modelAndView.addObject("thirdCreateException",true);
			modelAndView.setViewName("/maintenance/third/createThird");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ThirdInfo thirdInfo){
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