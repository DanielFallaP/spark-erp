package co.com.cybersoft.web.controller.measurementUnits;

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

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;
import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsService;
import co.com.cybersoft.events.measurementUnits.CreateMeasurementUnitsEvent;
import co.com.cybersoft.web.domain.measurementUnits.MeasurementUnitsInfo;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsDetailsEvent;


import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for measurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnits/createMeasurementUnits/{from}")
public class MeasurementUnitsCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitsCreationController.class);
	
	@Autowired
	private MeasurementUnitsService measurementUnitsService;
	
	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String measurementUnitsCreation() throws Exception {
		return "/configuration/measurementUnits/createMeasurementUnits";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createMeasurementUnits(@Valid @ModelAttribute("measurementUnitsInfo") MeasurementUnitsInfo measurementUnitsInfo, Model model, HttpServletRequest request) throws Exception{
		measurementUnitsInfo.setCreated(false);
		MeasurementUnitsDetails measurementUnitsDetails = createMeasurementUnitsDetails(measurementUnitsInfo);
		measurementUnitsDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitsDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitsDetails.setDateOfCreation(new Date());
		measurementUnitsDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("measurementUnitsInfo", measurementUnitsInfo);
		measurementUnitsService.createMeasurementUnits(new CreateMeasurementUnitsEvent(measurementUnitsDetails));
		String calledFrom = measurementUnitsInfo.getCalledFrom();
		measurementUnitsInfo=new MeasurementUnitsInfo();
		measurementUnitsInfo.setCreated(true);
		measurementUnitsInfo.setCalledFrom(calledFrom);
		ActivePageEvent allActiveEvent = activeService.requestAll();
		measurementUnitsInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("measurementUnitsInfo", measurementUnitsInfo);
		return "/configuration/measurementUnits/createMeasurementUnits";
	}
	
	private MeasurementUnitsDetails createMeasurementUnitsDetails(MeasurementUnitsInfo measurementUnitsInfo){
		MeasurementUnitsDetails measurementUnitsDetails = new MeasurementUnitsDetails();
		BeanUtils.copyProperties(measurementUnitsInfo, measurementUnitsDetails);
		return measurementUnitsDetails;
	}
	
	@ModelAttribute("measurementUnitsInfo")
	private MeasurementUnitsInfo getMeasurementUnitsInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		MeasurementUnitsInfo measurementUnitsInfo = new MeasurementUnitsInfo();
		
		String code = request.getParameter("id");
		String description = request.getParameter("desc");
		if (code!=null){
			MeasurementUnitsDetailsEvent responseEvent = measurementUnitsService.requestMeasurementUnitsDetails(new RequestMeasurementUnitsDetailsEvent(code));
			if (responseEvent.getMeasurementUnitsDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getMeasurementUnitsDetails(), measurementUnitsInfo);
		}
		
		if (description!=null){
			RequestMeasurementUnitsDetailsEvent event = new RequestMeasurementUnitsDetailsEvent(null);
			event.setDescription(description);
			MeasurementUnitsDetailsEvent responseEvent = measurementUnitsService.requestMeasurementUnitsDetails(event);
			if (responseEvent.getMeasurementUnitsDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getMeasurementUnitsDetails(), measurementUnitsInfo);
		}
		
		measurementUnitsInfo.setId(null);
		
		ActivePageEvent allActiveEvent = activeService.requestAll();
		measurementUnitsInfo.setActiveList(allActiveEvent.getActiveList());

		
		measurementUnitsInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("measurementUnitsInfo", measurementUnitsInfo);
		
		return measurementUnitsInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("measurementUnitsInfo", req.getSession().getAttribute("measurementUnitsInfo"));
			modelAndView.addObject("measurementUnitsValidationException",true);
			modelAndView.setViewName("/configuration/measurementUnits/createMeasurementUnits");
		}
		else{
			modelAndView.addObject("measurementUnitsInfo", req.getSession().getAttribute("measurementUnitsInfo"));
			modelAndView.addObject("measurementUnitsCreateException",true);
			modelAndView.setViewName("/configuration/measurementUnits/createMeasurementUnits");
		}
		return modelAndView;
	}
	
}