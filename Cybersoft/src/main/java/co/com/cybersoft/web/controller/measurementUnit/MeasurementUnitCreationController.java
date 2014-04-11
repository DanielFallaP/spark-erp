package co.com.cybersoft.web.controller.measurementUnit;

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

import co.com.cybersoft.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.web.domain.measurementUnit.MeasurementUnitInfo;

/**
 * Controller for measurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnit/createMeasurementUnit/{from}")
public class MeasurementUnitCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitCreationController.class);
	
	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String measurementUnitCreation(){
		return "/configuration/measurementUnit/createMeasurementUnit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createMeasurementUnit(@Valid @ModelAttribute("measurementUnitInfo") MeasurementUnitInfo measurementUnitInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of an measurementUnit!!!");
		measurementUnitInfo.setCreated(false);
		MeasurementUnitDetails measurementUnitDetails = createMeasurementUnitDetails(measurementUnitInfo);
		measurementUnitDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		request.getSession().setAttribute("measurementUnitInfo", measurementUnitInfo);
		measurementUnitService.createMeasurementUnit(new CreateMeasurementUnitEvent(measurementUnitDetails));
		String calledFrom = measurementUnitInfo.getCalledFrom();
		measurementUnitInfo=new MeasurementUnitInfo();
		measurementUnitInfo.setCreated(true);
		measurementUnitInfo.setCalledFrom(calledFrom);
		model.addAttribute("measurementUnitInfo", measurementUnitInfo);
		return "/configuration/measurementUnit/createMeasurementUnit";
	}
	
	private MeasurementUnitDetails createMeasurementUnitDetails(MeasurementUnitInfo measurementUnitInfo){
		MeasurementUnitDetails measurementUnitDetails = new MeasurementUnitDetails();
		LOG.debug(measurementUnitInfo.getCode());
		BeanUtils.copyProperties(measurementUnitInfo, measurementUnitDetails);
		return measurementUnitDetails;
	}
	
	@ModelAttribute("measurementUnitInfo")
	private MeasurementUnitInfo getMeasurementUnitInfo(@PathVariable("from") String calledFrom){
		MeasurementUnitInfo measurementUnitInfo = new MeasurementUnitInfo();
		measurementUnitInfo.setCalledFrom(calledFrom);
		return measurementUnitInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("measurementUnitInfo", req.getSession().getAttribute("measurementUnitInfo"));
		modelAndView.addObject("measurementUnitCreateException",true);
		modelAndView.setViewName("/configuration/measurementUnit/createMeasurementUnit");
		exception.printStackTrace();
		return modelAndView;
	}
	
}