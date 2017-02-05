package co.com.cybersoft.maintenance.tables.web.controller.characteristic;

import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;
import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicService;
import co.com.cybersoft.maintenance.tables.events.characteristic.CreateCharacteristicEvent;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicInfo;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for characteristic
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/characteristic/createCharacteristic/{from}")
public class CharacteristicCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CharacteristicCreationController.class);
	
	@Autowired
	private CharacteristicService characteristicService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/characteristic/createCharacteristic";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCharacteristic(@Valid @ModelAttribute("characteristicInfo") CharacteristicInfo characteristicInfo, Model model, HttpServletRequest request) throws Exception{
		characteristicInfo.setCreated(false);
		CharacteristicDetails characteristicDetails = createCharacteristicDetails(characteristicInfo);
		characteristicDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		characteristicDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		characteristicDetails.setDateOfCreation(new Date());
		characteristicDetails.setDateOfModification(new Date());
		//characteristicDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("characteristicInfo", characteristicInfo);
		characteristicService.createCharacteristic(new CreateCharacteristicEvent(characteristicDetails));
		String calledFrom = characteristicInfo.getCalledFrom();
		characteristicInfo=new CharacteristicInfo();
		characteristicInfo.setCreated(true);
		characteristicInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		characteristicInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		characteristicInfo.setActive(true);

		
		model.addAttribute("characteristicInfo", characteristicInfo);
		return "/maintenance/characteristic/createCharacteristic";
	}
	
	private CharacteristicDetails createCharacteristicDetails(CharacteristicInfo characteristicInfo){
		CharacteristicDetails characteristicDetails = new CharacteristicDetails();
		BeanUtils.copyProperties(characteristicInfo, characteristicDetails);
		return characteristicDetails;
	}
	
	@ModelAttribute("characteristicInfo")
	private CharacteristicInfo getCharacteristicInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CharacteristicInfo characteristicInfo = new CharacteristicInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		characteristicInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestCharacteristicDetailsEvent event = new RequestCharacteristicDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CharacteristicDetailsEvent responseEvent = characteristicService.requestCharacteristicDetails(event);
			if (responseEvent.getCharacteristicDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCharacteristicDetails(), characteristicInfo);
		}
		
		
		characteristicInfo.setId(null);
		characteristicInfo.setActive(true);

		
		characteristicInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("characteristicInfo", characteristicInfo);
		
		return characteristicInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CharacteristicInfo characteristicInfo=(CharacteristicInfo) req.getSession().getAttribute("characteristicInfo");
			modelAndView.addObject("characteristicInfo", characteristicInfo);
			modelAndView.addObject("characteristicValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, characteristicInfo);
			modelAndView.setViewName("/maintenance/characteristic/createCharacteristic");
		}
		else{
			modelAndView.addObject("characteristicInfo", req.getSession().getAttribute("characteristicInfo"));
			modelAndView.addObject("characteristicCreateException",true);
			modelAndView.setViewName("/maintenance/characteristic/createCharacteristic");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CharacteristicInfo characteristicInfo){
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