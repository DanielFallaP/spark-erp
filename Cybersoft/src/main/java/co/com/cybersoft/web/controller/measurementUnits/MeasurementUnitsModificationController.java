package co.com.cybersoft.web.controller.measurementUnits;

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

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;
import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsService;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsDetailsEvent;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsModificationEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsDetailsEvent;
import co.com.cybersoft.web.domain.measurementUnits.MeasurementUnitsInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for MeasurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnits/modifyMeasurementUnits/{code}")
public class MeasurementUnitsModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(MeasurementUnitsModificationController.class);
	
	@Autowired
	private MeasurementUnitsService measurementUnitsService;
	
	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/measurementUnits/modifyMeasurementUnits";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyMeasurementUnits(@Valid @ModelAttribute("measurementUnitsInfo") MeasurementUnitsInfo measurementUnitsInfo, HttpServletRequest request) throws Exception {
		MeasurementUnitsDetails measurementUnitsDetails = createMeasurementUnitsDetails(measurementUnitsInfo);
		measurementUnitsDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitsDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("measurementUnitsInfo", measurementUnitsInfo);
		measurementUnitsService.modifyMeasurementUnits(new MeasurementUnitsModificationEvent(measurementUnitsDetails));
		return "redirect:/configuration/measurementUnits/searchMeasurementUnits";
	}
	
	private MeasurementUnitsDetails createMeasurementUnitsDetails(MeasurementUnitsInfo measurementUnitsInfo){
		MeasurementUnitsDetails measurementUnitsDetails = new MeasurementUnitsDetails();
		BeanUtils.copyProperties(measurementUnitsInfo, measurementUnitsDetails);
		return measurementUnitsDetails;
	}

	@ModelAttribute("measurementUnitsInfo")
	private MeasurementUnitsInfo getMeasurementUnitsInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the measurementUnits "+code);
		MeasurementUnitsInfo measurementUnitsInfo = new MeasurementUnitsInfo();
		
		MeasurementUnitsDetailsEvent requestMeasurementUnitsDetails = measurementUnitsService.requestMeasurementUnitsDetails(new RequestMeasurementUnitsDetailsEvent(code));
		ActivePageEvent allActiveEvent = activeService.requestAll();
		measurementUnitsInfo.setActiveList(allActiveEvent.getActiveList());
		measurementUnitsInfo.rearrangeActiveList(measurementUnitsInfo.getActive());

		request.getSession().setAttribute("measurementUnitsInfo", measurementUnitsInfo);
		
		BeanUtils.copyProperties(requestMeasurementUnitsDetails.getMeasurementUnitsDetails(), measurementUnitsInfo);
		return measurementUnitsInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("measurementUnitsInfo", req.getSession().getAttribute("measurementUnitsInfo"));
			modelAndView.addObject("measurementUnitsValidationException",true);
			modelAndView.setViewName("/configuration/measurementUnits/modifyMeasurementUnits");
		}
		else{
			modelAndView.addObject("measurementUnitsInfo", req.getSession().getAttribute("measurementUnitsInfo"));
			modelAndView.addObject("measurementUnitsModificationException",true);
			modelAndView.setViewName("/configuration/measurementUnits/modifyMeasurementUnits");
		}
		return modelAndView;
	}
}