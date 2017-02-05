package co.com.cybersoft.maintenance.tables.web.controller.storeType;

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
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeService;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeInfo;

/**
 * Controller class for StoreType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/storeType/modifyStoreType/{id}")
public class StoreTypeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(StoreTypeModificationController.class);
	
	@Autowired
	private StoreTypeService storeTypeService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/storeType/modifyStoreType";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyStoreType(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("storeTypeInfo") StoreTypeInfo storeTypeInfo, HttpServletRequest request) throws Exception {
		
		StoreTypeDetails storeTypeDetails = createStoreTypeDetails(storeTypeInfo);
		storeTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		storeTypeDetails.setDateOfModification(new Date());
		//storeTypeDetails.set_companyId(((StoreTypeDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("storeTypeInfo", storeTypeInfo);
		storeTypeService.modifyStoreType(new StoreTypeModificationEvent(storeTypeDetails));
		return "redirect:/maintenance/storeType/searchStoreType";
	}
	
	private StoreTypeDetails createStoreTypeDetails(StoreTypeInfo storeTypeInfo){
		StoreTypeDetails storeTypeDetails = new StoreTypeDetails();
		BeanUtils.copyProperties(storeTypeInfo, storeTypeDetails);
		return storeTypeDetails;
	}

	@ModelAttribute("storeTypeInfo")
	private StoreTypeInfo getStoreTypeInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the storeType "+id);
		
		StoreTypeInfo storeTypeInfo = new StoreTypeInfo();
			
		StoreTypeDetailsEvent requestStoreTypeDetails = storeTypeService.requestStoreTypeDetails(new RequestStoreTypeDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestStoreTypeDetails.getStoreTypeDetails(), storeTypeInfo);
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
			modelAndView.setViewName("/maintenance/storeType/modifyStoreType");
		}
		else{
			modelAndView.addObject("storeTypeInfo", req.getSession().getAttribute("storeTypeInfo"));
			modelAndView.addObject("storeTypeModificationException",true);
			modelAndView.setViewName("/maintenance/storeType/modifyStoreType");
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