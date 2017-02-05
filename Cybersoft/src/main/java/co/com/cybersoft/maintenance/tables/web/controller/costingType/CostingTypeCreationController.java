package co.com.cybersoft.maintenance.tables.web.controller.costingType;

import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeService;
import co.com.cybersoft.maintenance.tables.events.costingType.CreateCostingTypeEvent;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeInfo;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypeDetailsEvent;



/**
 * Controller for costingType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/costingType/createCostingType/{from}")
public class CostingTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CostingTypeCreationController.class);
	
	@Autowired
	private CostingTypeService costingTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/costingType/createCostingType";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCostingType(@Valid @ModelAttribute("costingTypeInfo") CostingTypeInfo costingTypeInfo, Model model, HttpServletRequest request) throws Exception{
		costingTypeInfo.setCreated(false);
		CostingTypeDetails costingTypeDetails = createCostingTypeDetails(costingTypeInfo);
		costingTypeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		costingTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		costingTypeDetails.setDateOfCreation(new Date());
		costingTypeDetails.setDateOfModification(new Date());
		//costingTypeDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("costingTypeInfo", costingTypeInfo);
		costingTypeService.createCostingType(new CreateCostingTypeEvent(costingTypeDetails));
		String calledFrom = costingTypeInfo.getCalledFrom();
		costingTypeInfo=new CostingTypeInfo();
		costingTypeInfo.setCreated(true);
		costingTypeInfo.setCalledFrom(calledFrom);
		
		costingTypeInfo.setActive(true);

		
		model.addAttribute("costingTypeInfo", costingTypeInfo);
		return "/maintenance/costingType/createCostingType";
	}
	
	private CostingTypeDetails createCostingTypeDetails(CostingTypeInfo costingTypeInfo){
		CostingTypeDetails costingTypeDetails = new CostingTypeDetails();
		BeanUtils.copyProperties(costingTypeInfo, costingTypeDetails);
		return costingTypeDetails;
	}
	
	@ModelAttribute("costingTypeInfo")
	private CostingTypeInfo getCostingTypeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CostingTypeInfo costingTypeInfo = new CostingTypeInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestCostingTypeDetailsEvent event = new RequestCostingTypeDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CostingTypeDetailsEvent responseEvent = costingTypeService.requestCostingTypeDetails(event);
			if (responseEvent.getCostingTypeDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCostingTypeDetails(), costingTypeInfo);
		}
		
		
		costingTypeInfo.setId(null);
		costingTypeInfo.setActive(true);

		
		costingTypeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("costingTypeInfo", costingTypeInfo);
		
		return costingTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CostingTypeInfo costingTypeInfo=(CostingTypeInfo) req.getSession().getAttribute("costingTypeInfo");
			modelAndView.addObject("costingTypeInfo", costingTypeInfo);
			modelAndView.addObject("costingTypeValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, costingTypeInfo);
			modelAndView.setViewName("/maintenance/costingType/createCostingType");
		}
		else{
			modelAndView.addObject("costingTypeInfo", req.getSession().getAttribute("costingTypeInfo"));
			modelAndView.addObject("costingTypeCreateException",true);
			modelAndView.setViewName("/maintenance/costingType/createCostingType");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CostingTypeInfo costingTypeInfo){
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