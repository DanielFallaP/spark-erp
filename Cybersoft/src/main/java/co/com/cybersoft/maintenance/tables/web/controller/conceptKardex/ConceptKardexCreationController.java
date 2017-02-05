package co.com.cybersoft.maintenance.tables.web.controller.conceptKardex;

import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexService;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.CreateConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexInfo;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexService;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;


/**
 * Controller for conceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/conceptKardex/createConceptKardex/{from}")
public class ConceptKardexCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ConceptKardexCreationController.class);
	
	@Autowired
	private ConceptKardexService conceptKardexService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private WarehouseService warehouseService;

	@Autowired
		private TypeConceptKardexService typeConceptKardexService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/conceptKardex/createConceptKardex";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createConceptKardex(@Valid @ModelAttribute("conceptKardexInfo") ConceptKardexInfo conceptKardexInfo, Model model, HttpServletRequest request) throws Exception{
		conceptKardexInfo.setCreated(false);
		ConceptKardexDetails conceptKardexDetails = createConceptKardexDetails(conceptKardexInfo);
		conceptKardexDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		conceptKardexDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		conceptKardexDetails.setDateOfCreation(new Date());
		conceptKardexDetails.setDateOfModification(new Date());
		//conceptKardexDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("conceptKardexInfo", conceptKardexInfo);
		conceptKardexService.createConceptKardex(new CreateConceptKardexEvent(conceptKardexDetails));
		String calledFrom = conceptKardexInfo.getCalledFrom();
		conceptKardexInfo=new ConceptKardexInfo();
		conceptKardexInfo.setCreated(true);
		conceptKardexInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		conceptKardexInfo.setCompanyList(allCompanyEvent.getCompanyList());
		WarehousePageEvent allStoreEvent = warehouseService.requestAllByStoreName();
		conceptKardexInfo.setStoreList(allStoreEvent.getWarehouseList());
		TypeConceptKardexPageEvent allTypeConceptKardexEvent = typeConceptKardexService.requestAllByTypeConceptKardex();
		conceptKardexInfo.setTypeConceptKardexList(allTypeConceptKardexEvent.getTypeConceptKardexList());

		
		conceptKardexInfo.setActive(true);

		
		model.addAttribute("conceptKardexInfo", conceptKardexInfo);
		return "/maintenance/conceptKardex/createConceptKardex";
	}
	
	private ConceptKardexDetails createConceptKardexDetails(ConceptKardexInfo conceptKardexInfo){
		ConceptKardexDetails conceptKardexDetails = new ConceptKardexDetails();
		BeanUtils.copyProperties(conceptKardexInfo, conceptKardexDetails);
		return conceptKardexDetails;
	}
	
	@ModelAttribute("conceptKardexInfo")
	private ConceptKardexInfo getConceptKardexInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ConceptKardexInfo conceptKardexInfo = new ConceptKardexInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		conceptKardexInfo.setCompanyList(allCompanyEvent.getCompanyList());
		WarehousePageEvent allStoreEvent = warehouseService.requestAllByStoreName();
		conceptKardexInfo.setStoreList(allStoreEvent.getWarehouseList());
		TypeConceptKardexPageEvent allTypeConceptKardexEvent = typeConceptKardexService.requestAllByTypeConceptKardex();
		conceptKardexInfo.setTypeConceptKardexList(allTypeConceptKardexEvent.getTypeConceptKardexList());

		
		
		if (value!=null){
			RequestConceptKardexDetailsEvent event = new RequestConceptKardexDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ConceptKardexDetailsEvent responseEvent = conceptKardexService.requestConceptKardexDetails(event);
			if (responseEvent.getConceptKardexDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getConceptKardexDetails(), conceptKardexInfo);
		}
		
		
		conceptKardexInfo.setId(null);
		conceptKardexInfo.setActive(true);

		
		conceptKardexInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("conceptKardexInfo", conceptKardexInfo);
		
		return conceptKardexInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ConceptKardexInfo conceptKardexInfo=(ConceptKardexInfo) req.getSession().getAttribute("conceptKardexInfo");
			modelAndView.addObject("conceptKardexInfo", conceptKardexInfo);
			modelAndView.addObject("conceptKardexValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, conceptKardexInfo);
			modelAndView.setViewName("/maintenance/conceptKardex/createConceptKardex");
		}
		else{
			modelAndView.addObject("conceptKardexInfo", req.getSession().getAttribute("conceptKardexInfo"));
			modelAndView.addObject("conceptKardexCreateException",true);
			modelAndView.setViewName("/maintenance/conceptKardex/createConceptKardex");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ConceptKardexInfo conceptKardexInfo){
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