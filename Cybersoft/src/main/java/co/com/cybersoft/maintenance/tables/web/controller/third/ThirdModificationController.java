package co.com.cybersoft.maintenance.tables.web.controller.third;

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
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.maintenance.tables.events.third.ThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;


/**
 * Controller class for Third
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/third/modifyThird/{id}")
public class ThirdModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ThirdModificationController.class);
	
	@Autowired
	private ThirdService thirdService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private CostCentersCustomersService costCentersCustomersService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/third/modifyThird";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyThird(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("thirdInfo") ThirdInfo thirdInfo, HttpServletRequest request) throws Exception {
		
		ThirdDetails thirdDetails = createThirdDetails(thirdInfo);
		thirdDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		thirdDetails.setDateOfModification(new Date());
		//thirdDetails.set_companyId(((ThirdDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("thirdInfo", thirdInfo);
		thirdService.modifyThird(new ThirdModificationEvent(thirdDetails));
		return "redirect:/maintenance/third/searchThird";
	}
	
	private ThirdDetails createThirdDetails(ThirdInfo thirdInfo){
		ThirdDetails thirdDetails = new ThirdDetails();
		BeanUtils.copyProperties(thirdInfo, thirdDetails);
		return thirdDetails;
	}

	@ModelAttribute("thirdInfo")
	private ThirdInfo getThirdInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the third "+id);
		
		ThirdInfo thirdInfo = new ThirdInfo();
			
		ThirdDetailsEvent requestThirdDetails = thirdService.requestThirdDetails(new RequestThirdDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		thirdInfo.setCompanyList(allCompanyEvent.getCompanyList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		thirdInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		BeanUtils.copyProperties(requestThirdDetails.getThirdDetails(), thirdInfo);
		request.getSession().setAttribute("thirdInfo", thirdInfo);
		
		return thirdInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ThirdInfo thirdInfo=(ThirdInfo) req.getSession().getAttribute("thirdInfo");
			modelAndView.addObject("thirdInfo", thirdInfo);
			modelAndView.addObject("thirdValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, thirdInfo);
			modelAndView.setViewName("/maintenance/third/modifyThird");
		}
		else{
			modelAndView.addObject("thirdInfo", req.getSession().getAttribute("thirdInfo"));
			modelAndView.addObject("thirdModificationException",true);
			modelAndView.setViewName("/maintenance/third/modifyThird");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ThirdInfo thirdInfo){
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