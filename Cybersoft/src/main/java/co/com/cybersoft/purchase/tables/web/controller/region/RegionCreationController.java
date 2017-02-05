package co.com.cybersoft.purchase.tables.web.controller.region;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;

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
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.events.region.CreateRegionEvent;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionInfo;
import co.com.cybersoft.purchase.tables.events.region.RegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionDetailsEvent;


import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;


/**
 * Controller for region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/region/createRegion/{from}")
public class RegionCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegionCreationController.class);
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
		private ContinentService continentService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/region/createRegion";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createRegion(@Valid @ModelAttribute("regionInfo") RegionInfo regionInfo, Model model, HttpServletRequest request) throws Exception{
		regionInfo.setCreated(false);
		RegionDetails regionDetails = createRegionDetails(regionInfo);
		regionDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		regionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		regionDetails.setDateOfCreation(new Date());
		regionDetails.setDateOfModification(new Date());
		//regionDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("regionInfo", regionInfo);
		regionService.createRegion(new CreateRegionEvent(regionDetails));
		String calledFrom = regionInfo.getCalledFrom();
		regionInfo=new RegionInfo();
		regionInfo.setCreated(true);
		regionInfo.setCalledFrom(calledFrom);
		ContinentPageEvent allContinentEvent = continentService.requestAllByContinent();
		regionInfo.setContinentList(allContinentEvent.getContinentList());

		
		regionInfo.setActive(true);

		
		model.addAttribute("regionInfo", regionInfo);
		return "/purchase/region/createRegion";
	}
	
	private RegionDetails createRegionDetails(RegionInfo regionInfo){
		RegionDetails regionDetails = new RegionDetails();
		BeanUtils.copyProperties(regionInfo, regionDetails);
		return regionDetails;
	}
	
	@ModelAttribute("regionInfo")
	private RegionInfo getRegionInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		RegionInfo regionInfo = new RegionInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		ContinentPageEvent allContinentEvent = continentService.requestAllByContinent();
		regionInfo.setContinentList(allContinentEvent.getContinentList());

		
		
		if (value!=null){
			RequestRegionDetailsEvent event = new RequestRegionDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			RegionDetailsEvent responseEvent = regionService.requestRegionDetails(event);
			if (responseEvent.getRegionDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getRegionDetails(), regionInfo);
		}
		
		
		regionInfo.setId(null);
		regionInfo.setActive(true);

		
		regionInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("regionInfo", regionInfo);
		
		return regionInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			RegionInfo regionInfo=(RegionInfo) req.getSession().getAttribute("regionInfo");
			modelAndView.addObject("regionInfo", regionInfo);
			modelAndView.addObject("regionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, regionInfo);
			modelAndView.setViewName("/purchase/region/createRegion");
		}
		else{
			modelAndView.addObject("regionInfo", req.getSession().getAttribute("regionInfo"));
			modelAndView.addObject("regionCreateException",true);
			modelAndView.setViewName("/purchase/region/createRegion");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, RegionInfo regionInfo){
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