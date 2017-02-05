package co.com.cybersoft.maintenance.tables.web.controller.sorter;

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
import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;
import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterService;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;


/**
 * Controller class for Sorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/sorter/modifySorter/{id}")
public class SorterModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(SorterModificationController.class);
	
	@Autowired
	private SorterService sorterService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private TypeSorterService typeSorterService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/sorter/modifySorter";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifySorter(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("sorterInfo") SorterInfo sorterInfo, HttpServletRequest request) throws Exception {
		
		SorterDetails sorterDetails = createSorterDetails(sorterInfo);
		sorterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		sorterDetails.setDateOfModification(new Date());
		//sorterDetails.set_companyId(((SorterDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("sorterInfo", sorterInfo);
		sorterService.modifySorter(new SorterModificationEvent(sorterDetails));
		return "redirect:/maintenance/sorter/searchSorter";
	}
	
	private SorterDetails createSorterDetails(SorterInfo sorterInfo){
		SorterDetails sorterDetails = new SorterDetails();
		BeanUtils.copyProperties(sorterInfo, sorterDetails);
		return sorterDetails;
	}

	@ModelAttribute("sorterInfo")
	private SorterInfo getSorterInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the sorter "+id);
		
		SorterInfo sorterInfo = new SorterInfo();
			
		SorterDetailsEvent requestSorterDetails = sorterService.requestSorterDetails(new RequestSorterDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		sorterInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeSorterPageEvent allTypeSorterEvent = typeSorterService.requestAllByNameTypeSorter();
		sorterInfo.setTypeSorterList(allTypeSorterEvent.getTypeSorterList());

		
		BeanUtils.copyProperties(requestSorterDetails.getSorterDetails(), sorterInfo);
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
			modelAndView.setViewName("/maintenance/sorter/modifySorter");
		}
		else{
			modelAndView.addObject("sorterInfo", req.getSession().getAttribute("sorterInfo"));
			modelAndView.addObject("sorterModificationException",true);
			modelAndView.setViewName("/maintenance/sorter/modifySorter");
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