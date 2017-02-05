package co.com.cybersoft.maintenance.tables.web.controller.causeClose;

import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;
import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseService;
import co.com.cybersoft.maintenance.tables.events.causeClose.CreateCauseCloseEvent;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseInfo;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseCloseDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;


/**
 * Controller for causeClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causeClose/createCauseClose/{from}")
public class CauseCloseCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CauseCloseCreationController.class);
	
	@Autowired
	private CauseCloseService causeCloseService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private TypeCauseCloseService typeCauseCloseService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/causeClose/createCauseClose";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCauseClose(@Valid @ModelAttribute("causeCloseInfo") CauseCloseInfo causeCloseInfo, Model model, HttpServletRequest request) throws Exception{
		causeCloseInfo.setCreated(false);
		CauseCloseDetails causeCloseDetails = createCauseCloseDetails(causeCloseInfo);
		causeCloseDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		causeCloseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		causeCloseDetails.setDateOfCreation(new Date());
		causeCloseDetails.setDateOfModification(new Date());
		//causeCloseDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("causeCloseInfo", causeCloseInfo);
		causeCloseService.createCauseClose(new CreateCauseCloseEvent(causeCloseDetails));
		String calledFrom = causeCloseInfo.getCalledFrom();
		causeCloseInfo=new CauseCloseInfo();
		causeCloseInfo.setCreated(true);
		causeCloseInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causeCloseInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeCauseClosePageEvent allTypeCauseCloseEvent = typeCauseCloseService.requestAllByTypeCauseClose();
		causeCloseInfo.setTypeCauseCloseList(allTypeCauseCloseEvent.getTypeCauseCloseList());

		
		causeCloseInfo.setActive(true);

		
		model.addAttribute("causeCloseInfo", causeCloseInfo);
		return "/maintenance/causeClose/createCauseClose";
	}
	
	private CauseCloseDetails createCauseCloseDetails(CauseCloseInfo causeCloseInfo){
		CauseCloseDetails causeCloseDetails = new CauseCloseDetails();
		BeanUtils.copyProperties(causeCloseInfo, causeCloseDetails);
		return causeCloseDetails;
	}
	
	@ModelAttribute("causeCloseInfo")
	private CauseCloseInfo getCauseCloseInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CauseCloseInfo causeCloseInfo = new CauseCloseInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causeCloseInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeCauseClosePageEvent allTypeCauseCloseEvent = typeCauseCloseService.requestAllByTypeCauseClose();
		causeCloseInfo.setTypeCauseCloseList(allTypeCauseCloseEvent.getTypeCauseCloseList());

		
		
		if (value!=null){
			RequestCauseCloseDetailsEvent event = new RequestCauseCloseDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CauseCloseDetailsEvent responseEvent = causeCloseService.requestCauseCloseDetails(event);
			if (responseEvent.getCauseCloseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCauseCloseDetails(), causeCloseInfo);
		}
		
		
		causeCloseInfo.setId(null);
		causeCloseInfo.setActive(true);

		
		causeCloseInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("causeCloseInfo", causeCloseInfo);
		
		return causeCloseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CauseCloseInfo causeCloseInfo=(CauseCloseInfo) req.getSession().getAttribute("causeCloseInfo");
			modelAndView.addObject("causeCloseInfo", causeCloseInfo);
			modelAndView.addObject("causeCloseValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, causeCloseInfo);
			modelAndView.setViewName("/maintenance/causeClose/createCauseClose");
		}
		else{
			modelAndView.addObject("causeCloseInfo", req.getSession().getAttribute("causeCloseInfo"));
			modelAndView.addObject("causeCloseCreateException",true);
			modelAndView.setViewName("/maintenance/causeClose/createCauseClose");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CauseCloseInfo causeCloseInfo){
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