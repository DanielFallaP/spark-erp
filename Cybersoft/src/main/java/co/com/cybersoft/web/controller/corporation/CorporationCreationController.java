package co.com.cybersoft.web.controller.corporation;

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

import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CreateCorporationEvent;
import co.com.cybersoft.web.domain.corporation.CorporationInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/corporation/createCorporation/{from}")
public class CorporationCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CorporationCreationController.class);
	
	@Autowired
	private CorporationService corporationService;
	
	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String corporationCreation() throws Exception {
		return "/configuration/corporation/createCorporation";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCorporation(@Valid @ModelAttribute("corporationInfo") CorporationInfo corporationInfo, Model model, HttpServletRequest request) throws Exception{
		corporationInfo.setCreated(false);
		CorporationDetails corporationDetails = createCorporationDetails(corporationInfo);
		corporationDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		corporationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		corporationDetails.setDateOfCreation(new Date());
		corporationDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("corporationInfo", corporationInfo);
		corporationService.createCorporation(new CreateCorporationEvent(corporationDetails));
		String calledFrom = corporationInfo.getCalledFrom();
		corporationInfo=new CorporationInfo();
		corporationInfo.setCreated(true);
		corporationInfo.setCalledFrom(calledFrom);
		ActivePageEvent allActiveEvent = activeService.requestAll();
		corporationInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("corporationInfo", corporationInfo);
		return "/configuration/corporation/createCorporation";
	}
	
	private CorporationDetails createCorporationDetails(CorporationInfo corporationInfo){
		CorporationDetails corporationDetails = new CorporationDetails();
		BeanUtils.copyProperties(corporationInfo, corporationDetails);
		return corporationDetails;
	}
	
	@ModelAttribute("corporationInfo")
	private CorporationInfo getCorporationInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CorporationInfo corporationInfo = new CorporationInfo();
		
		ActivePageEvent allActiveEvent = activeService.requestAll();
		corporationInfo.setActiveList(allActiveEvent.getActiveList());

		
		corporationInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("corporationInfo", corporationInfo);
		
		return corporationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("corporationInfo", req.getSession().getAttribute("corporationInfo"));
			modelAndView.addObject("corporationValidationException",true);
			modelAndView.setViewName("/configuration/corporation/createCorporation");
		}
		else{
			modelAndView.addObject("corporationInfo", req.getSession().getAttribute("corporationInfo"));
			modelAndView.addObject("corporationCreateException",true);
			modelAndView.setViewName("/configuration/corporation/createCorporation");
		}
		return modelAndView;
	}
	
}