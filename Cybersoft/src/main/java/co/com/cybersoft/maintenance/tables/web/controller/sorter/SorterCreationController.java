package co.com.cybersoft.maintenance.tables.web.controller.sorter;

import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;
import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterService;
import co.com.cybersoft.maintenance.tables.events.sorter.CreateSorterEvent;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterInfo;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;


/**
 * Controller for sorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/sorter/createSorter/{from}")
public class SorterCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(SorterCreationController.class);
	
	@Autowired
	private SorterService sorterService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private TypeSorterService typeSorterService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/sorter/createSorter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createSorter(@Valid @ModelAttribute("sorterInfo") SorterInfo sorterInfo, Model model, HttpServletRequest request) throws Exception{
		sorterInfo.setCreated(false);
		SorterDetails sorterDetails = createSorterDetails(sorterInfo);
		sorterDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		sorterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		sorterDetails.setDateOfCreation(new Date());
		sorterDetails.setDateOfModification(new Date());
		//sorterDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("sorterInfo", sorterInfo);
		sorterService.createSorter(new CreateSorterEvent(sorterDetails));
		String calledFrom = sorterInfo.getCalledFrom();
		sorterInfo=new SorterInfo();
		sorterInfo.setCreated(true);
		sorterInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		sorterInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeSorterPageEvent allTypeSorterEvent = typeSorterService.requestAllByNameTypeSorter();
		sorterInfo.setTypeSorterList(allTypeSorterEvent.getTypeSorterList());

		
		sorterInfo.setActive(true);

		
		model.addAttribute("sorterInfo", sorterInfo);
		return "/maintenance/sorter/createSorter";
	}
	
	private SorterDetails createSorterDetails(SorterInfo sorterInfo){
		SorterDetails sorterDetails = new SorterDetails();
		BeanUtils.copyProperties(sorterInfo, sorterDetails);
		return sorterDetails;
	}
	
	@ModelAttribute("sorterInfo")
	private SorterInfo getSorterInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		SorterInfo sorterInfo = new SorterInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		sorterInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeSorterPageEvent allTypeSorterEvent = typeSorterService.requestAllByNameTypeSorter();
		sorterInfo.setTypeSorterList(allTypeSorterEvent.getTypeSorterList());

		
		
		if (value!=null){
			RequestSorterDetailsEvent event = new RequestSorterDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			SorterDetailsEvent responseEvent = sorterService.requestSorterDetails(event);
			if (responseEvent.getSorterDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getSorterDetails(), sorterInfo);
		}
		
		
		sorterInfo.setId(null);
		sorterInfo.setActive(true);

		
		sorterInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("sorterInfo", sorterInfo);
		
		return sorterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			SorterInfo sorterInfo=(SorterInfo) req.getSession().getAttribute("sorterInfo");
			modelAndView.addObject("sorterInfo", sorterInfo);
			modelAndView.addObject("sorterValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, sorterInfo);
			modelAndView.setViewName("/maintenance/sorter/createSorter");
		}
		else{
			modelAndView.addObject("sorterInfo", req.getSession().getAttribute("sorterInfo"));
			modelAndView.addObject("sorterCreateException",true);
			modelAndView.setViewName("/maintenance/sorter/createSorter");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, SorterInfo sorterInfo){
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