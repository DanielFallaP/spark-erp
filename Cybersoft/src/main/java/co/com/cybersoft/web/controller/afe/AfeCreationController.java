package co.com.cybersoft.web.controller.afe;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.CreateAfeEvent;
import co.com.cybersoft.web.domain.afe.AfeInfo;

/**
 * Controller for afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afe/createAfe/{from}")
public class AfeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(AfeCreationController.class);
	
	@Autowired
	private AfeService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String afeCreation(){
		return "/configuration/afe/createAfe";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createAfe(@Valid @ModelAttribute("afeInfo") AfeInfo afeInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of an afe!!!");
		afeInfo.setCreated(false);
		AfeDetails afeDetails = createAfeDetails(afeInfo);
		afeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		request.getSession().setAttribute("afeInfo", afeInfo);
		afeService.createAfe(new CreateAfeEvent(afeDetails));
		String calledFrom = afeInfo.getCalledFrom();
		afeInfo=new AfeInfo();
		afeInfo.setCreated(true);
		afeInfo.setCalledFrom(calledFrom);
		model.addAttribute("afeInfo", afeInfo);
		return "/configuration/afe/createAfe";
	}
	
	private AfeDetails createAfeDetails(AfeInfo afeInfo){
		AfeDetails afeDetails = new AfeDetails();
		LOG.debug(afeInfo.getCode());
		BeanUtils.copyProperties(afeInfo, afeDetails);
		return afeDetails;
	}
	
	@ModelAttribute("afeInfo")
	private AfeInfo getAfeInfo(@PathVariable("from") String calledFrom){
		AfeInfo afeInfo = new AfeInfo();
		afeInfo.setCalledFrom(calledFrom);
		return afeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("afeInfo", req.getSession().getAttribute("afeInfo"));
		modelAndView.addObject("afeCreateException",true);
		modelAndView.setViewName("/configuration/afe/createAfe");
		exception.printStackTrace();
		return modelAndView;
	}
	
}