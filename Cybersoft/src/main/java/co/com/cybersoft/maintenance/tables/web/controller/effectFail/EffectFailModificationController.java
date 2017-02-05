package co.com.cybersoft.maintenance.tables.web.controller.effectFail;

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
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailService;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for EffectFail
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/effectFail/modifyEffectFail/{id}")
public class EffectFailModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(EffectFailModificationController.class);
	
	@Autowired
	private EffectFailService effectFailService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/effectFail/modifyEffectFail";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyEffectFail(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("effectFailInfo") EffectFailInfo effectFailInfo, HttpServletRequest request) throws Exception {
		
		EffectFailDetails effectFailDetails = createEffectFailDetails(effectFailInfo);
		effectFailDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		effectFailDetails.setDateOfModification(new Date());
		//effectFailDetails.set_companyId(((EffectFailDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("effectFailInfo", effectFailInfo);
		effectFailService.modifyEffectFail(new EffectFailModificationEvent(effectFailDetails));
		return "redirect:/maintenance/effectFail/searchEffectFail";
	}
	
	private EffectFailDetails createEffectFailDetails(EffectFailInfo effectFailInfo){
		EffectFailDetails effectFailDetails = new EffectFailDetails();
		BeanUtils.copyProperties(effectFailInfo, effectFailDetails);
		return effectFailDetails;
	}

	@ModelAttribute("effectFailInfo")
	private EffectFailInfo getEffectFailInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the effectFail "+id);
		
		EffectFailInfo effectFailInfo = new EffectFailInfo();
			
		EffectFailDetailsEvent requestEffectFailDetails = effectFailService.requestEffectFailDetails(new RequestEffectFailDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		effectFailInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestEffectFailDetails.getEffectFailDetails(), effectFailInfo);
		request.getSession().setAttribute("effectFailInfo", effectFailInfo);
		
		return effectFailInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			EffectFailInfo effectFailInfo=(EffectFailInfo) req.getSession().getAttribute("effectFailInfo");
			modelAndView.addObject("effectFailInfo", effectFailInfo);
			modelAndView.addObject("effectFailValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, effectFailInfo);
			modelAndView.setViewName("/maintenance/effectFail/modifyEffectFail");
		}
		else{
			modelAndView.addObject("effectFailInfo", req.getSession().getAttribute("effectFailInfo"));
			modelAndView.addObject("effectFailModificationException",true);
			modelAndView.setViewName("/maintenance/effectFail/modifyEffectFail");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, EffectFailInfo effectFailInfo){
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