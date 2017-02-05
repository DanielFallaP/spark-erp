package co.com.cybersoft.maintenance.tables.web.controller.costCentersCustomers;

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
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;


/**
 * Controller class for CostCentersCustomers
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/costCentersCustomers/modifyCostCentersCustomers/{id}")
public class CostCentersCustomersModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CostCentersCustomersModificationController.class);
	
	@Autowired
	private CostCentersCustomersService costCentersCustomersService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private StateCostCentersService stateCostCentersService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/costCentersCustomers/modifyCostCentersCustomers";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCostCentersCustomers(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("costCentersCustomersInfo") CostCentersCustomersInfo costCentersCustomersInfo, HttpServletRequest request) throws Exception {
		
		CostCentersCustomersDetails costCentersCustomersDetails = createCostCentersCustomersDetails(costCentersCustomersInfo);
		costCentersCustomersDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		costCentersCustomersDetails.setDateOfModification(new Date());
		//costCentersCustomersDetails.set_companyId(((CostCentersCustomersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("costCentersCustomersInfo", costCentersCustomersInfo);
		costCentersCustomersService.modifyCostCentersCustomers(new CostCentersCustomersModificationEvent(costCentersCustomersDetails));
		return "redirect:/maintenance/costCentersCustomers/searchCostCentersCustomers";
	}
	
	private CostCentersCustomersDetails createCostCentersCustomersDetails(CostCentersCustomersInfo costCentersCustomersInfo){
		CostCentersCustomersDetails costCentersCustomersDetails = new CostCentersCustomersDetails();
		BeanUtils.copyProperties(costCentersCustomersInfo, costCentersCustomersDetails);
		return costCentersCustomersDetails;
	}

	@ModelAttribute("costCentersCustomersInfo")
	private CostCentersCustomersInfo getCostCentersCustomersInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the costCentersCustomers "+id);
		
		CostCentersCustomersInfo costCentersCustomersInfo = new CostCentersCustomersInfo();
			
		CostCentersCustomersDetailsEvent requestCostCentersCustomersDetails = costCentersCustomersService.requestCostCentersCustomersDetails(new RequestCostCentersCustomersDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		costCentersCustomersInfo.setCompanyList(allCompanyEvent.getCompanyList());
		StateCostCentersPageEvent allStateEvent = stateCostCentersService.requestAllByStateCostCenters();
		costCentersCustomersInfo.setStateList(allStateEvent.getStateCostCentersList());

		
		BeanUtils.copyProperties(requestCostCentersCustomersDetails.getCostCentersCustomersDetails(), costCentersCustomersInfo);
		request.getSession().setAttribute("costCentersCustomersInfo", costCentersCustomersInfo);
		
		return costCentersCustomersInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CostCentersCustomersInfo costCentersCustomersInfo=(CostCentersCustomersInfo) req.getSession().getAttribute("costCentersCustomersInfo");
			modelAndView.addObject("costCentersCustomersInfo", costCentersCustomersInfo);
			modelAndView.addObject("costCentersCustomersValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, costCentersCustomersInfo);
			modelAndView.setViewName("/maintenance/costCentersCustomers/modifyCostCentersCustomers");
		}
		else{
			modelAndView.addObject("costCentersCustomersInfo", req.getSession().getAttribute("costCentersCustomersInfo"));
			modelAndView.addObject("costCentersCustomersModificationException",true);
			modelAndView.setViewName("/maintenance/costCentersCustomers/modifyCostCentersCustomers");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CostCentersCustomersInfo costCentersCustomersInfo){
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