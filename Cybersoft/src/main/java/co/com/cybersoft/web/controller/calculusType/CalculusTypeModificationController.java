package co.com.cybersoft.web.controller.calculusType;

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

import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.events.calculusType.CalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeModificationEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypeDetailsEvent;
import co.com.cybersoft.web.domain.calculusType.CalculusTypeInfo;

/**
 * Controller class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/calculusType/modifyCalculusType/{code}")
public class CalculusTypeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CalculusTypeModificationController.class);
	
	@Autowired
	private CalculusTypeService calculusTypeService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/calculusType/modifyCalculusType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyCalculusType(@Valid @ModelAttribute("calculusTypeInfo") CalculusTypeInfo calculusTypeInfo, HttpServletRequest request) throws Exception {
		CalculusTypeDetails calculusTypeDetails = createCalculusTypeDetails(calculusTypeInfo);
		calculusTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		calculusTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("calculusTypeInfo", calculusTypeInfo);
		calculusTypeService.modifyCalculusType(new CalculusTypeModificationEvent(calculusTypeDetails));
		return "redirect:/configuration/calculusType/searchCalculusType";
	}
	
	private CalculusTypeDetails createCalculusTypeDetails(CalculusTypeInfo calculusTypeInfo){
		CalculusTypeDetails calculusTypeDetails = new CalculusTypeDetails();
		BeanUtils.copyProperties(calculusTypeInfo, calculusTypeDetails);
		return calculusTypeDetails;
	}

	@ModelAttribute("calculusTypeInfo")
	private CalculusTypeInfo getCalculusTypeInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the calculusType "+code);
		CalculusTypeInfo calculusTypeInfo = new CalculusTypeInfo();
		
		CalculusTypeDetailsEvent requestCalculusTypeDetails = calculusTypeService.requestCalculusTypeDetails(new RequestCalculusTypeDetailsEvent(code));
		request.getSession().setAttribute("calculusTypeInfo", calculusTypeInfo);
		
		BeanUtils.copyProperties(requestCalculusTypeDetails.getCalculusTypeDetails(), calculusTypeInfo);
		return calculusTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("calculusTypeInfo", req.getSession().getAttribute("calculusTypeInfo"));
			modelAndView.addObject("calculusTypeValidationException",true);
			modelAndView.setViewName("/configuration/calculusType/modifyCalculusType");
		}
		else{
			modelAndView.addObject("calculusTypeInfo", req.getSession().getAttribute("calculusTypeInfo"));
			modelAndView.addObject("calculusTypeModificationException",true);
			modelAndView.setViewName("/configuration/calculusType/modifyCalculusType");
		}
		return modelAndView;
	}
}