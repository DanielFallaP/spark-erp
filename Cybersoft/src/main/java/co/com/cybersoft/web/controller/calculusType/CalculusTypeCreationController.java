package co.com.cybersoft.web.controller.calculusType;

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

import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.events.calculusType.CreateCalculusTypeEvent;
import co.com.cybersoft.web.domain.calculusType.CalculusTypeInfo;

/**
 * Controller for calculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/calculusType/createCalculusType/{from}")
public class CalculusTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CalculusTypeCreationController.class);
	
	@Autowired
	private CalculusTypeService calculusTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String calculusTypeCreation() throws Exception {
		return "/configuration/calculusType/createCalculusType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCalculusType(@Valid @ModelAttribute("calculusTypeInfo") CalculusTypeInfo calculusTypeInfo, Model model, HttpServletRequest request) throws Exception{
		calculusTypeInfo.setCreated(false);
		CalculusTypeDetails calculusTypeDetails = createCalculusTypeDetails(calculusTypeInfo);
		calculusTypeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		calculusTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		calculusTypeDetails.setDateOfCreation(new Date());
		calculusTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("calculusTypeInfo", calculusTypeInfo);
		calculusTypeService.createCalculusType(new CreateCalculusTypeEvent(calculusTypeDetails));
		String calledFrom = calculusTypeInfo.getCalledFrom();
		calculusTypeInfo=new CalculusTypeInfo();
		calculusTypeInfo.setCreated(true);
		calculusTypeInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("calculusTypeInfo", calculusTypeInfo);
		return "/configuration/calculusType/createCalculusType";
	}
	
	private CalculusTypeDetails createCalculusTypeDetails(CalculusTypeInfo calculusTypeInfo){
		CalculusTypeDetails calculusTypeDetails = new CalculusTypeDetails();
		BeanUtils.copyProperties(calculusTypeInfo, calculusTypeDetails);
		return calculusTypeDetails;
	}
	
	@ModelAttribute("calculusTypeInfo")
	private CalculusTypeInfo getCalculusTypeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CalculusTypeInfo calculusTypeInfo = new CalculusTypeInfo();
		
		
		calculusTypeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("calculusTypeInfo", calculusTypeInfo);
		
		return calculusTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("calculusTypeInfo", req.getSession().getAttribute("calculusTypeInfo"));
			modelAndView.addObject("calculusTypeValidationException",true);
			modelAndView.setViewName("/configuration/calculusType/createCalculusType");
		}
		else{
			modelAndView.addObject("calculusTypeInfo", req.getSession().getAttribute("calculusTypeInfo"));
			modelAndView.addObject("calculusTypeCreateException",true);
			modelAndView.setViewName("/configuration/calculusType/createCalculusType");
		}
		return modelAndView;
	}
	
}