package co.com.cybersoft.maintenance.tables.web.controller.stateCostCenters;

import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.CreateStateCostCentersEvent;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersInfo;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersDetailsEvent;



/**
 * Controller for stateCostCenters
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/stateCostCenters/createStateCostCenters/{from}")
public class StateCostCentersCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(StateCostCentersCreationController.class);
	
	@Autowired
	private StateCostCentersService stateCostCentersService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/stateCostCenters/createStateCostCenters";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createStateCostCenters(@Valid @ModelAttribute("stateCostCentersInfo") StateCostCentersInfo stateCostCentersInfo, Model model, HttpServletRequest request) throws Exception{
		stateCostCentersInfo.setCreated(false);
		StateCostCentersDetails stateCostCentersDetails = createStateCostCentersDetails(stateCostCentersInfo);
		stateCostCentersDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		stateCostCentersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		stateCostCentersDetails.setDateOfCreation(new Date());
		stateCostCentersDetails.setDateOfModification(new Date());
		//stateCostCentersDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("stateCostCentersInfo", stateCostCentersInfo);
		stateCostCentersService.createStateCostCenters(new CreateStateCostCentersEvent(stateCostCentersDetails));
		String calledFrom = stateCostCentersInfo.getCalledFrom();
		stateCostCentersInfo=new StateCostCentersInfo();
		stateCostCentersInfo.setCreated(true);
		stateCostCentersInfo.setCalledFrom(calledFrom);
		
		stateCostCentersInfo.setActive(true);

		
		model.addAttribute("stateCostCentersInfo", stateCostCentersInfo);
		return "/maintenance/stateCostCenters/createStateCostCenters";
	}
	
	private StateCostCentersDetails createStateCostCentersDetails(StateCostCentersInfo stateCostCentersInfo){
		StateCostCentersDetails stateCostCentersDetails = new StateCostCentersDetails();
		BeanUtils.copyProperties(stateCostCentersInfo, stateCostCentersDetails);
		return stateCostCentersDetails;
	}
	
	@ModelAttribute("stateCostCentersInfo")
	private StateCostCentersInfo getStateCostCentersInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		StateCostCentersInfo stateCostCentersInfo = new StateCostCentersInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		
		
		if (value!=null){
			RequestStateCostCentersDetailsEvent event = new RequestStateCostCentersDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			StateCostCentersDetailsEvent responseEvent = stateCostCentersService.requestStateCostCentersDetails(event);
			if (responseEvent.getStateCostCentersDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getStateCostCentersDetails(), stateCostCentersInfo);
		}
		
		
		stateCostCentersInfo.setId(null);
		stateCostCentersInfo.setActive(true);

		
		stateCostCentersInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("stateCostCentersInfo", stateCostCentersInfo);
		
		return stateCostCentersInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			StateCostCentersInfo stateCostCentersInfo=(StateCostCentersInfo) req.getSession().getAttribute("stateCostCentersInfo");
			modelAndView.addObject("stateCostCentersInfo", stateCostCentersInfo);
			modelAndView.addObject("stateCostCentersValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, stateCostCentersInfo);
			modelAndView.setViewName("/maintenance/stateCostCenters/createStateCostCenters");
		}
		else{
			modelAndView.addObject("stateCostCentersInfo", req.getSession().getAttribute("stateCostCentersInfo"));
			modelAndView.addObject("stateCostCentersCreateException",true);
			modelAndView.setViewName("/maintenance/stateCostCenters/createStateCostCenters");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, StateCostCentersInfo stateCostCentersInfo){
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