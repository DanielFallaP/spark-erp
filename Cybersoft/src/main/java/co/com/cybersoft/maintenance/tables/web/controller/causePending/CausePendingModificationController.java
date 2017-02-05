package co.com.cybersoft.maintenance.tables.web.controller.causePending;

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
import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;
import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingService;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for CausePending
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causePending/modifyCausePending/{id}")
public class CausePendingModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CausePendingModificationController.class);
	
	@Autowired
	private CausePendingService causePendingService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/causePending/modifyCausePending";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCausePending(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("causePendingInfo") CausePendingInfo causePendingInfo, HttpServletRequest request) throws Exception {
		
		CausePendingDetails causePendingDetails = createCausePendingDetails(causePendingInfo);
		causePendingDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		causePendingDetails.setDateOfModification(new Date());
		//causePendingDetails.set_companyId(((CausePendingDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("causePendingInfo", causePendingInfo);
		causePendingService.modifyCausePending(new CausePendingModificationEvent(causePendingDetails));
		return "redirect:/maintenance/causePending/searchCausePending";
	}
	
	private CausePendingDetails createCausePendingDetails(CausePendingInfo causePendingInfo){
		CausePendingDetails causePendingDetails = new CausePendingDetails();
		BeanUtils.copyProperties(causePendingInfo, causePendingDetails);
		return causePendingDetails;
	}

	@ModelAttribute("causePendingInfo")
	private CausePendingInfo getCausePendingInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the causePending "+id);
		
		CausePendingInfo causePendingInfo = new CausePendingInfo();
			
		CausePendingDetailsEvent requestCausePendingDetails = causePendingService.requestCausePendingDetails(new RequestCausePendingDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causePendingInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestCausePendingDetails.getCausePendingDetails(), causePendingInfo);
		request.getSession().setAttribute("causePendingInfo", causePendingInfo);
		
		return causePendingInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CausePendingInfo causePendingInfo=(CausePendingInfo) req.getSession().getAttribute("causePendingInfo");
			modelAndView.addObject("causePendingInfo", causePendingInfo);
			modelAndView.addObject("causePendingValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, causePendingInfo);
			modelAndView.setViewName("/maintenance/causePending/modifyCausePending");
		}
		else{
			modelAndView.addObject("causePendingInfo", req.getSession().getAttribute("causePendingInfo"));
			modelAndView.addObject("causePendingModificationException",true);
			modelAndView.setViewName("/maintenance/causePending/modifyCausePending");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CausePendingInfo causePendingInfo){
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