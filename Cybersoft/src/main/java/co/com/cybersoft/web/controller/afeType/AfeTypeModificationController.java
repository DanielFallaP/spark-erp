package co.com.cybersoft.web.controller.afeType;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.events.afeType.AfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.AfeTypeModificationEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypeDetailsEvent;
import co.com.cybersoft.web.domain.afeType.AfeTypeInfo;

/**
 * Controller class for AfeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afeType/modifyAfeType/{code}")
public class AfeTypeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(AfeTypeModificationController.class);
	
	@Autowired
	private AfeTypeService afeTypeService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/afeType/modifyAfeType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyAfeType(@Valid @ModelAttribute("afeTypeInfo") AfeTypeInfo afeTypeInfo, HttpServletRequest request) throws Exception {
		AfeTypeDetails afeTypeDetails = createAfeTypeDetails(afeTypeInfo);
		afeTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		afeTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("afeTypeInfo", afeTypeInfo);
		afeTypeService.modifyAfeType(new AfeTypeModificationEvent(afeTypeDetails));
		return "redirect:/configuration/afeType/searchAfeType";
	}
	
	private AfeTypeDetails createAfeTypeDetails(AfeTypeInfo afeTypeInfo){
		AfeTypeDetails afeTypeDetails = new AfeTypeDetails();
		BeanUtils.copyProperties(afeTypeInfo, afeTypeDetails);
		return afeTypeDetails;
	}

	@ModelAttribute("afeTypeInfo")
	private AfeTypeInfo getAfeTypeInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the afeType "+code);
		AfeTypeInfo afeTypeInfo = new AfeTypeInfo();
		
		AfeTypeDetailsEvent requestAfeTypeDetails = afeTypeService.requestAfeTypeDetails(new RequestAfeTypeDetailsEvent(code));
		request.getSession().setAttribute("afeTypeInfo", afeTypeInfo);
		
		BeanUtils.copyProperties(requestAfeTypeDetails.getAfeTypeDetails(), afeTypeInfo);
		return afeTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("afeTypeInfo", req.getSession().getAttribute("afeTypeInfo"));
			modelAndView.addObject("afeTypeValidationException",true);
			modelAndView.setViewName("/configuration/afeType/modifyAfeType");
		}
		else{
			modelAndView.addObject("afeTypeInfo", req.getSession().getAttribute("afeTypeInfo"));
			modelAndView.addObject("afeTypeModificationException",true);
			modelAndView.setViewName("/configuration/afeType/modifyAfeType");
		}
		return modelAndView;
	}
}