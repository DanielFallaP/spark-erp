package co.com.cybersoft.web.controller.active;

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

import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActiveDetailsEvent;
import co.com.cybersoft.events.active.ActiveModificationEvent;
import co.com.cybersoft.events.active.RequestActiveDetailsEvent;
import co.com.cybersoft.web.domain.active.ActiveInfo;

/**
 * Controller class for Active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/active/modifyActive/{code}")
public class ActiveModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ActiveModificationController.class);
	
	@Autowired
	private ActiveService activeService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/active/modifyActive";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyActive(@Valid @ModelAttribute("activeInfo") ActiveInfo activeInfo, HttpServletRequest request) throws Exception {
		ActiveDetails activeDetails = createActiveDetails(activeInfo);
		activeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		activeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("activeInfo", activeInfo);
		activeService.modifyActive(new ActiveModificationEvent(activeDetails));
		return "redirect:/configuration/active/searchActive";
	}
	
	private ActiveDetails createActiveDetails(ActiveInfo activeInfo){
		ActiveDetails activeDetails = new ActiveDetails();
		BeanUtils.copyProperties(activeInfo, activeDetails);
		return activeDetails;
	}

	@ModelAttribute("activeInfo")
	private ActiveInfo getActiveInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the active "+code);
		ActiveInfo activeInfo = new ActiveInfo();
		
		ActiveDetailsEvent requestActiveDetails = activeService.requestActiveDetails(new RequestActiveDetailsEvent(code));
		request.getSession().setAttribute("activeInfo", activeInfo);
		
		BeanUtils.copyProperties(requestActiveDetails.getActiveDetails(), activeInfo);
		return activeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("activeInfo", req.getSession().getAttribute("activeInfo"));
			modelAndView.addObject("activeValidationException",true);
			modelAndView.setViewName("/configuration/active/modifyActive");
		}
		else{
			modelAndView.addObject("activeInfo", req.getSession().getAttribute("activeInfo"));
			modelAndView.addObject("activeModificationException",true);
			modelAndView.setViewName("/configuration/active/modifyActive");
		}
		return modelAndView;
	}
}