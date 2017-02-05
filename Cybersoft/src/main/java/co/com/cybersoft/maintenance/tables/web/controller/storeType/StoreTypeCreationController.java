package co.com.cybersoft.maintenance.tables.web.controller.storeType;

import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeService;
import co.com.cybersoft.maintenance.tables.events.storeType.CreateStoreTypeEvent;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeInfo;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypeDetailsEvent;



/**
 * Controller for storeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/storeType/createStoreType/{from}")
public class StoreTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(StoreTypeCreationController.class);
	
	@Autowired
	private StoreTypeService storeTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/storeType/createStoreType";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createStoreType(@Valid @ModelAttribute("storeTypeInfo") StoreTypeInfo storeTypeInfo, Model model, HttpServletRequest request) throws Exception{
		storeTypeInfo.setCreated(false);
		StoreTypeDetails storeTypeDetails = createStoreTypeDetails(storeTypeInfo);
		storeTypeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		storeTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		storeTypeDetails.setDateOfCreation(new Date());
		storeTypeDetails.setDateOfModification(new Date());
		//storeTypeDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("storeTypeInfo", storeTypeInfo);
		storeTypeService.createStoreType(new CreateStoreTypeEvent(storeTypeDetails));
		String calledFrom = storeTypeInfo.getCalledFrom();
		storeTypeInfo=new StoreTypeInfo();
		storeTypeInfo.setCreated(true);
		storeTypeInfo.setCalledFrom(calledFrom);
		
		storeTypeInfo.setActive(true);

		
		model.addAttribute("storeTypeInfo", storeTypeInfo);
		return "/maintenance/storeType/createStoreType";
	}
	
	private StoreTypeDetails createStoreTypeDetails(StoreTypeInfo storeTypeInfo){
		StoreTypeDetails storeTypeDetails = new StoreTypeDetails();
		BeanUtils.copyProperties(storeTypeInfo, storeTypeDetails);
		return storeTypeDetails;
	}
	
	@ModelAttribute("storeTypeInfo")
	private StoreTypeInfo getStoreTypeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		StoreTypeInfo storeTypeInfo = new StoreTypeInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestStoreTypeDetailsEvent event = new RequestStoreTypeDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			StoreTypeDetailsEvent responseEvent = storeTypeService.requestStoreTypeDetails(event);
			if (responseEvent.getStoreTypeDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getStoreTypeDetails(), storeTypeInfo);
		}
		
		
		storeTypeInfo.setId(null);
		storeTypeInfo.setActive(true);

		
		storeTypeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("storeTypeInfo", storeTypeInfo);
		
		return storeTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			StoreTypeInfo storeTypeInfo=(StoreTypeInfo) req.getSession().getAttribute("storeTypeInfo");
			modelAndView.addObject("storeTypeInfo", storeTypeInfo);
			modelAndView.addObject("storeTypeValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, storeTypeInfo);
			modelAndView.setViewName("/maintenance/storeType/createStoreType");
		}
		else{
			modelAndView.addObject("storeTypeInfo", req.getSession().getAttribute("storeTypeInfo"));
			modelAndView.addObject("storeTypeCreateException",true);
			modelAndView.setViewName("/maintenance/storeType/createStoreType");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, StoreTypeInfo storeTypeInfo){
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