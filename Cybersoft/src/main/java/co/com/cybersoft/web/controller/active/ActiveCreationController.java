package co.com.cybersoft.web.controller.active;

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

import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.CreateActiveEvent;
import co.com.cybersoft.web.domain.active.ActiveInfo;

/**
 * Controller for active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/active/createActive/{from}")
public class ActiveCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ActiveCreationController.class);
	
	@Autowired
	private ActiveService activeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String activeCreation() throws Exception {
		return "/configuration/active/createActive";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createActive(@Valid @ModelAttribute("activeInfo") ActiveInfo activeInfo, Model model, HttpServletRequest request) throws Exception{
		activeInfo.setCreated(false);
		ActiveDetails activeDetails = createActiveDetails(activeInfo);
		activeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		activeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		activeDetails.setDateOfCreation(new Date());
		activeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("activeInfo", activeInfo);
		activeService.createActive(new CreateActiveEvent(activeDetails));
		String calledFrom = activeInfo.getCalledFrom();
		activeInfo=new ActiveInfo();
		activeInfo.setCreated(true);
		activeInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("activeInfo", activeInfo);
		return "/configuration/active/createActive";
	}
	
	private ActiveDetails createActiveDetails(ActiveInfo activeInfo){
		ActiveDetails activeDetails = new ActiveDetails();
		BeanUtils.copyProperties(activeInfo, activeDetails);
		return activeDetails;
	}
	
	@ModelAttribute("activeInfo")
	private ActiveInfo getActiveInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ActiveInfo activeInfo = new ActiveInfo();
		
		
		activeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("activeInfo", activeInfo);
		
		return activeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("activeInfo", req.getSession().getAttribute("activeInfo"));
			modelAndView.addObject("activeValidationException",true);
			modelAndView.setViewName("/configuration/active/createActive");
		}
		else{
			modelAndView.addObject("activeInfo", req.getSession().getAttribute("activeInfo"));
			modelAndView.addObject("activeCreateException",true);
			modelAndView.setViewName("/configuration/active/createActive");
		}
		return modelAndView;
	}
	
}