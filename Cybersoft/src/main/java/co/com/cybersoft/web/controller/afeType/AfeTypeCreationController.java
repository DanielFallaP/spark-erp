package co.com.cybersoft.web.controller.afeType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;

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

import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.events.afeType.CreateAfeTypeEvent;
import co.com.cybersoft.web.domain.afeType.AfeTypeInfo;
import co.com.cybersoft.events.afeType.AfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypeDetailsEvent;



/**
 * Controller for afeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afeType/createAfeType/{from}")
public class AfeTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(AfeTypeCreationController.class);
	
	@Autowired
	private AfeTypeService afeTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String afeTypeCreation() throws Exception {
		return "/configuration/afeType/createAfeType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createAfeType(@Valid @ModelAttribute("afeTypeInfo") AfeTypeInfo afeTypeInfo, Model model, HttpServletRequest request) throws Exception{
		afeTypeInfo.setCreated(false);
		AfeTypeDetails afeTypeDetails = createAfeTypeDetails(afeTypeInfo);
		afeTypeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		afeTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		afeTypeDetails.setDateOfCreation(new Date());
		afeTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("afeTypeInfo", afeTypeInfo);
		afeTypeService.createAfeType(new CreateAfeTypeEvent(afeTypeDetails));
		String calledFrom = afeTypeInfo.getCalledFrom();
		afeTypeInfo=new AfeTypeInfo();
		afeTypeInfo.setCreated(true);
		afeTypeInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("afeTypeInfo", afeTypeInfo);
		return "/configuration/afeType/createAfeType";
	}
	
	private AfeTypeDetails createAfeTypeDetails(AfeTypeInfo afeTypeInfo){
		AfeTypeDetails afeTypeDetails = new AfeTypeDetails();
		BeanUtils.copyProperties(afeTypeInfo, afeTypeDetails);
		return afeTypeDetails;
	}
	
	@ModelAttribute("afeTypeInfo")
	private AfeTypeInfo getAfeTypeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		AfeTypeInfo afeTypeInfo = new AfeTypeInfo();
		
		String code = request.getParameter("id");
		String description = request.getParameter("desc");
		if (code!=null){
			AfeTypeDetailsEvent responseEvent = afeTypeService.requestAfeTypeDetails(new RequestAfeTypeDetailsEvent(code));
			if (responseEvent.getAfeTypeDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getAfeTypeDetails(), afeTypeInfo);
		}
		
		if (description!=null){
			RequestAfeTypeDetailsEvent event = new RequestAfeTypeDetailsEvent(null);
			event.setDescription(description);
			AfeTypeDetailsEvent responseEvent = afeTypeService.requestAfeTypeDetails(event);
			if (responseEvent.getAfeTypeDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getAfeTypeDetails(), afeTypeInfo);
		}
		
		afeTypeInfo.setId(null);
		
		
		afeTypeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("afeTypeInfo", afeTypeInfo);
		
		return afeTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("afeTypeInfo", req.getSession().getAttribute("afeTypeInfo"));
			modelAndView.addObject("afeTypeValidationException",true);
			modelAndView.setViewName("/configuration/afeType/createAfeType");
		}
		else{
			modelAndView.addObject("afeTypeInfo", req.getSession().getAttribute("afeTypeInfo"));
			modelAndView.addObject("afeTypeCreateException",true);
			modelAndView.setViewName("/configuration/afeType/createAfeType");
		}
		return modelAndView;
	}
	
}