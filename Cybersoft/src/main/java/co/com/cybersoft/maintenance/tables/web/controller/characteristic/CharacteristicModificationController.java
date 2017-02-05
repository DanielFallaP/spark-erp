package co.com.cybersoft.maintenance.tables.web.controller.characteristic;

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
import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;
import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicService;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicModificationEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for Characteristic
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/characteristic/modifyCharacteristic/{id}")
public class CharacteristicModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CharacteristicModificationController.class);
	
	@Autowired
	private CharacteristicService characteristicService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/characteristic/modifyCharacteristic";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCharacteristic(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("characteristicInfo") CharacteristicInfo characteristicInfo, HttpServletRequest request) throws Exception {
		
		CharacteristicDetails characteristicDetails = createCharacteristicDetails(characteristicInfo);
		characteristicDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		characteristicDetails.setDateOfModification(new Date());
		//characteristicDetails.set_companyId(((CharacteristicDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("characteristicInfo", characteristicInfo);
		characteristicService.modifyCharacteristic(new CharacteristicModificationEvent(characteristicDetails));
		return "redirect:/maintenance/characteristic/searchCharacteristic";
	}
	
	private CharacteristicDetails createCharacteristicDetails(CharacteristicInfo characteristicInfo){
		CharacteristicDetails characteristicDetails = new CharacteristicDetails();
		BeanUtils.copyProperties(characteristicInfo, characteristicDetails);
		return characteristicDetails;
	}

	@ModelAttribute("characteristicInfo")
	private CharacteristicInfo getCharacteristicInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the characteristic "+id);
		
		CharacteristicInfo characteristicInfo = new CharacteristicInfo();
			
		CharacteristicDetailsEvent requestCharacteristicDetails = characteristicService.requestCharacteristicDetails(new RequestCharacteristicDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		characteristicInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestCharacteristicDetails.getCharacteristicDetails(), characteristicInfo);
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
			modelAndView.setViewName("/maintenance/characteristic/modifyCharacteristic");
		}
		else{
			modelAndView.addObject("characteristicInfo", req.getSession().getAttribute("characteristicInfo"));
			modelAndView.addObject("characteristicModificationException",true);
			modelAndView.setViewName("/maintenance/characteristic/modifyCharacteristic");
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