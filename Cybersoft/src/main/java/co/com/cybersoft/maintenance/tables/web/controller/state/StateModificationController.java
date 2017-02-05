package co.com.cybersoft.maintenance.tables.web.controller.state;

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
import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;
import co.com.cybersoft.maintenance.tables.core.services.state.StateService;
import co.com.cybersoft.maintenance.tables.events.state.StateDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.state.StateModificationEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStateDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller class for State
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/state/modifyState/{id}")
public class StateModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(StateModificationController.class);
	
	@Autowired
	private StateService stateService;
	
	@Autowired
		private CompanyService companyService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/state/modifyState";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyState(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("stateInfo") StateInfo stateInfo, HttpServletRequest request) throws Exception {
		
		StateDetails stateDetails = createStateDetails(stateInfo);
		stateDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		stateDetails.setDateOfModification(new Date());
		//stateDetails.set_companyId(((StateDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("stateInfo", stateInfo);
		stateService.modifyState(new StateModificationEvent(stateDetails));
		return "redirect:/maintenance/state/searchState";
	}
	
	private StateDetails createStateDetails(StateInfo stateInfo){
		StateDetails stateDetails = new StateDetails();
		BeanUtils.copyProperties(stateInfo, stateDetails);
		return stateDetails;
	}

	@ModelAttribute("stateInfo")
	private StateInfo getStateInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the state "+id);
		
		StateInfo stateInfo = new StateInfo();
			
		StateDetailsEvent requestStateDetails = stateService.requestStateDetails(new RequestStateDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		stateInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		BeanUtils.copyProperties(requestStateDetails.getStateDetails(), stateInfo);
		request.getSession().setAttribute("stateInfo", stateInfo);
		
		return stateInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			StateInfo stateInfo=(StateInfo) req.getSession().getAttribute("stateInfo");
			modelAndView.addObject("stateInfo", stateInfo);
			modelAndView.addObject("stateValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, stateInfo);
			modelAndView.setViewName("/maintenance/state/modifyState");
		}
		else{
			modelAndView.addObject("stateInfo", req.getSession().getAttribute("stateInfo"));
			modelAndView.addObject("stateModificationException",true);
			modelAndView.setViewName("/maintenance/state/modifyState");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, StateInfo stateInfo){
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