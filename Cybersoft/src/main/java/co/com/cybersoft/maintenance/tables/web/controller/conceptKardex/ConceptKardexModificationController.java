package co.com.cybersoft.maintenance.tables.web.controller.conceptKardex;

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
import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexService;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexService;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;


/**
 * Controller class for ConceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/conceptKardex/modifyConceptKardex/{id}")
public class ConceptKardexModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ConceptKardexModificationController.class);
	
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
		return "/maintenance/conceptKardex/modifyConceptKardex";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyConceptKardex(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("conceptKardexInfo") ConceptKardexInfo conceptKardexInfo, HttpServletRequest request) throws Exception {
		
		ConceptKardexDetails conceptKardexDetails = createConceptKardexDetails(conceptKardexInfo);
		conceptKardexDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		conceptKardexDetails.setDateOfModification(new Date());
		//conceptKardexDetails.set_companyId(((ConceptKardexDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("conceptKardexInfo", conceptKardexInfo);
		conceptKardexService.modifyConceptKardex(new ConceptKardexModificationEvent(conceptKardexDetails));
		return "redirect:/maintenance/conceptKardex/searchConceptKardex";
	}
	
	private ConceptKardexDetails createConceptKardexDetails(ConceptKardexInfo conceptKardexInfo){
		ConceptKardexDetails conceptKardexDetails = new ConceptKardexDetails();
		BeanUtils.copyProperties(conceptKardexInfo, conceptKardexDetails);
		return conceptKardexDetails;
	}

	@ModelAttribute("conceptKardexInfo")
	private ConceptKardexInfo getConceptKardexInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the conceptKardex "+id);
		
		ConceptKardexInfo conceptKardexInfo = new ConceptKardexInfo();
			
		ConceptKardexDetailsEvent requestConceptKardexDetails = conceptKardexService.requestConceptKardexDetails(new RequestConceptKardexDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		conceptKardexInfo.setCompanyList(allCompanyEvent.getCompanyList());
		WarehousePageEvent allStoreEvent = warehouseService.requestAllByStoreName();
		conceptKardexInfo.setStoreList(allStoreEvent.getWarehouseList());
		TypeConceptKardexPageEvent allTypeConceptKardexEvent = typeConceptKardexService.requestAllByTypeConceptKardex();
		conceptKardexInfo.setTypeConceptKardexList(allTypeConceptKardexEvent.getTypeConceptKardexList());

		
		BeanUtils.copyProperties(requestConceptKardexDetails.getConceptKardexDetails(), conceptKardexInfo);
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
			modelAndView.setViewName("/maintenance/conceptKardex/modifyConceptKardex");
		}
		else{
			modelAndView.addObject("conceptKardexInfo", req.getSession().getAttribute("conceptKardexInfo"));
			modelAndView.addObject("conceptKardexModificationException",true);
			modelAndView.setViewName("/maintenance/conceptKardex/modifyConceptKardex");
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