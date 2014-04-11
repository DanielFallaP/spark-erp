package co.com.cybersoft.web.controller.measurementUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.web.domain.measurementUnit.MeasurementUnitInfo;


/**
 * Event class for MeasurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnit/modifyMeasurementUnit/{code}")
public class MeasurementUnitModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(MeasurementUnitModificationController.class);
	
	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/measurementUnit/modifyMeasurementUnit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyMeasurementUnit(@ModelAttribute("measurementUnitInfo") MeasurementUnitDetails measurementUnitInfo) throws Exception {
		LOG.debug("Modification of measurementUnit "+measurementUnitInfo.getCode());
		MeasurementUnitDetails measurementUnitDetails = createMeasurementUnitDetails(measurementUnitInfo);
		measurementUnitDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		measurementUnitService.modifyMeasurementUnit(new MeasurementUnitModificationEvent(measurementUnitDetails));
		return "redirect:/configuration/measurementUnit/searchMeasurementUnit";
	}
	
	private MeasurementUnitDetails createMeasurementUnitDetails(MeasurementUnitDetails measurementUnitInfo){
		MeasurementUnitDetails measurementUnitDetails = new MeasurementUnitDetails();
		LOG.debug(measurementUnitInfo.getCode());
		BeanUtils.copyProperties(measurementUnitInfo, measurementUnitDetails);
		return measurementUnitDetails;
	}

	@ModelAttribute("measurementUnitInfo")
	private MeasurementUnitDetails getMeasurementUnitInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the measurementUnit "+code);
		MeasurementUnitDetailsEvent requestMeasurementUnitDetails = measurementUnitService.requestMeasurementUnitDetails(new RequestMeasurementUnitDetailsEvent(code));
		MeasurementUnitDetails measurementUnitInfo = new MeasurementUnitDetails();
		BeanUtils.copyProperties(requestMeasurementUnitDetails.getMeasurementUnitDetails(), measurementUnitInfo);
		return measurementUnitInfo;
	}
}