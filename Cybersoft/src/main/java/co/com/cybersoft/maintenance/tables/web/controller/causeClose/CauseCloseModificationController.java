package co.com.cybersoft.maintenance.tables.web.controller.causeClose;

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
import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;
import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseService;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseCloseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseCloseDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;


/**
 * Controller class for CauseClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causeClose/modifyCauseClose/{id}")
public class CauseCloseModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CauseCloseModificationController.class);
	
	@Autowired
	private CauseCloseService causeCloseService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private TypeCauseCloseService typeCauseCloseService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/causeClose/modifyCauseClose";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyCauseClose(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("causeCloseInfo") CauseCloseInfo causeCloseInfo, HttpServletRequest request) throws Exception {
		
		CauseCloseDetails causeCloseDetails = createCauseCloseDetails(causeCloseInfo);
		causeCloseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		causeCloseDetails.setDateOfModification(new Date());
		//causeCloseDetails.set_companyId(((CauseCloseDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("causeCloseInfo", causeCloseInfo);
		causeCloseService.modifyCauseClose(new CauseCloseModificationEvent(causeCloseDetails));
		return "redirect:/maintenance/causeClose/searchCauseClose";
	}
	
	private CauseCloseDetails createCauseCloseDetails(CauseCloseInfo causeCloseInfo){
		CauseCloseDetails causeCloseDetails = new CauseCloseDetails();
		BeanUtils.copyProperties(causeCloseInfo, causeCloseDetails);
		return causeCloseDetails;
	}

	@ModelAttribute("causeCloseInfo")
	private CauseCloseInfo getCauseCloseInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the causeClose "+id);
		
		CauseCloseInfo causeCloseInfo = new CauseCloseInfo();
			
		CauseCloseDetailsEvent requestCauseCloseDetails = causeCloseService.requestCauseCloseDetails(new RequestCauseCloseDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		causeCloseInfo.setCompanyList(allCompanyEvent.getCompanyList());
		TypeCauseClosePageEvent allTypeCauseCloseEvent = typeCauseCloseService.requestAllByTypeCauseClose();
		causeCloseInfo.setTypeCauseCloseList(allTypeCauseCloseEvent.getTypeCauseCloseList());

		
		BeanUtils.copyProperties(requestCauseCloseDetails.getCauseCloseDetails(), causeCloseInfo);
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
			modelAndView.setViewName("/maintenance/causeClose/modifyCauseClose");
		}
		else{
			modelAndView.addObject("causeCloseInfo", req.getSession().getAttribute("causeCloseInfo"));
			modelAndView.addObject("causeCloseModificationException",true);
			modelAndView.setViewName("/maintenance/causeClose/modifyCauseClose");
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