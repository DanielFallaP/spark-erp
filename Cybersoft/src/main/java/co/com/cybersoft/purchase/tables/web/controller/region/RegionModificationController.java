package co.com.cybersoft.purchase.tables.web.controller.region;

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
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.events.region.RegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionModificationEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionDetailsEvent;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionInfo;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;


/**
 * Controller class for Region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/region/modifyRegion/{id}")
public class RegionModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(RegionModificationController.class);
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
		private ContinentService continentService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/purchase/region/modifyRegion";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyRegion(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("regionInfo") RegionInfo regionInfo, HttpServletRequest request) throws Exception {
		
		RegionDetails regionDetails = createRegionDetails(regionInfo);
		regionDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		regionDetails.setDateOfModification(new Date());
		//regionDetails.set_companyId(((RegionDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("regionInfo", regionInfo);
		regionService.modifyRegion(new RegionModificationEvent(regionDetails));
		return "redirect:/purchase/region/searchRegion";
	}
	
	private RegionDetails createRegionDetails(RegionInfo regionInfo){
		RegionDetails regionDetails = new RegionDetails();
		BeanUtils.copyProperties(regionInfo, regionDetails);
		return regionDetails;
	}

	@ModelAttribute("regionInfo")
	private RegionInfo getRegionInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the region "+id);
		
		RegionInfo regionInfo = new RegionInfo();
			
		RegionDetailsEvent requestRegionDetails = regionService.requestRegionDetails(new RequestRegionDetailsEvent(id));
		
		ContinentPageEvent allContinentEvent = continentService.requestAllByContinent();
		regionInfo.setContinentList(allContinentEvent.getContinentList());

		
		BeanUtils.copyProperties(requestRegionDetails.getRegionDetails(), regionInfo);
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
			modelAndView.setViewName("/purchase/region/modifyRegion");
		}
		else{
			modelAndView.addObject("regionInfo", req.getSession().getAttribute("regionInfo"));
			modelAndView.addObject("regionModificationException",true);
			modelAndView.setViewName("/purchase/region/modifyRegion");
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