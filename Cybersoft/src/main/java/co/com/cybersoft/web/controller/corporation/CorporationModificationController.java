package co.com.cybersoft.web.controller.corporation;

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

import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationDetailsEvent;
import co.com.cybersoft.events.corporation.CorporationModificationEvent;
import co.com.cybersoft.events.corporation.RequestCorporationDetailsEvent;
import co.com.cybersoft.web.domain.corporation.CorporationInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/corporation/modifyCorporation/{code}")
public class CorporationModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CorporationModificationController.class);
	
	@Autowired
	private CorporationService corporationService;
	
	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/corporation/modifyCorporation";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyCorporation(@Valid @ModelAttribute("corporationInfo") CorporationInfo corporationInfo, HttpServletRequest request) throws Exception {
		CorporationDetails corporationDetails = createCorporationDetails(corporationInfo);
		corporationDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		corporationDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("corporationInfo", corporationInfo);
		corporationService.modifyCorporation(new CorporationModificationEvent(corporationDetails));
		return "redirect:/configuration/corporation/searchCorporation";
	}
	
	private CorporationDetails createCorporationDetails(CorporationInfo corporationInfo){
		CorporationDetails corporationDetails = new CorporationDetails();
		BeanUtils.copyProperties(corporationInfo, corporationDetails);
		return corporationDetails;
	}

	@ModelAttribute("corporationInfo")
	private CorporationInfo getCorporationInfo(@PathVariable("code") Integer code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the corporation "+code);
		CorporationInfo corporationInfo = new CorporationInfo();
		
		CorporationDetailsEvent requestCorporationDetails = corporationService.requestCorporationDetails(new RequestCorporationDetailsEvent(code));
		ActivePageEvent allActiveEvent = activeService.requestAll();
		corporationInfo.setActiveList(allActiveEvent.getActiveList());
		corporationInfo.rearrangeActiveList(corporationInfo.getActive());

		request.getSession().setAttribute("corporationInfo", corporationInfo);
		
		BeanUtils.copyProperties(requestCorporationDetails.getCorporationDetails(), corporationInfo);
		return corporationInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("corporationInfo", req.getSession().getAttribute("corporationInfo"));
			modelAndView.addObject("corporationValidationException",true);
			modelAndView.setViewName("/configuration/corporation/modifyCorporation");
		}
		else{
			modelAndView.addObject("corporationInfo", req.getSession().getAttribute("corporationInfo"));
			modelAndView.addObject("corporationModificationException",true);
			modelAndView.setViewName("/configuration/corporation/modifyCorporation");
		}
		return modelAndView;
	}
}