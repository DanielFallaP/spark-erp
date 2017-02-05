package co.com.cybersoft.maintenance.tables.web.controller.stateCostCenters;

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
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersInfo;

/**
 * Controller class for StateCostCenters
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/stateCostCenters/modifyStateCostCenters/{id}")
public class StateCostCentersModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(StateCostCentersModificationController.class);
	
	@Autowired
	private StateCostCentersService stateCostCentersService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/stateCostCenters/modifyStateCostCenters";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyStateCostCenters(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("stateCostCentersInfo") StateCostCentersInfo stateCostCentersInfo, HttpServletRequest request) throws Exception {
		
		StateCostCentersDetails stateCostCentersDetails = createStateCostCentersDetails(stateCostCentersInfo);
		stateCostCentersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		stateCostCentersDetails.setDateOfModification(new Date());
		//stateCostCentersDetails.set_companyId(((StateCostCentersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("stateCostCentersInfo", stateCostCentersInfo);
		stateCostCentersService.modifyStateCostCenters(new StateCostCentersModificationEvent(stateCostCentersDetails));
		return "redirect:/maintenance/stateCostCenters/searchStateCostCenters";
	}
	
	private StateCostCentersDetails createStateCostCentersDetails(StateCostCentersInfo stateCostCentersInfo){
		StateCostCentersDetails stateCostCentersDetails = new StateCostCentersDetails();
		BeanUtils.copyProperties(stateCostCentersInfo, stateCostCentersDetails);
		return stateCostCentersDetails;
	}

	@ModelAttribute("stateCostCentersInfo")
	private StateCostCentersInfo getStateCostCentersInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the stateCostCenters "+id);
		
		StateCostCentersInfo stateCostCentersInfo = new StateCostCentersInfo();
			
		StateCostCentersDetailsEvent requestStateCostCentersDetails = stateCostCentersService.requestStateCostCentersDetails(new RequestStateCostCentersDetailsEvent(id));
		
		
		BeanUtils.copyProperties(requestStateCostCentersDetails.getStateCostCentersDetails(), stateCostCentersInfo);
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
			modelAndView.setViewName("/maintenance/stateCostCenters/modifyStateCostCenters");
		}
		else{
			modelAndView.addObject("stateCostCentersInfo", req.getSession().getAttribute("stateCostCentersInfo"));
			modelAndView.addObject("stateCostCentersModificationException",true);
			modelAndView.setViewName("/maintenance/stateCostCenters/modifyStateCostCenters");
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