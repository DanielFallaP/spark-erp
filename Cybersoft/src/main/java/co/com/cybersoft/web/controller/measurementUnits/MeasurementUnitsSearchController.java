package co.com.cybersoft.web.controller.measurementUnits;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsPageEvent;
import co.com.cybersoft.events.measurementUnits.RequestMeasurementUnitsPageEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnits;

/**
 * Search controller class for measurementUnits
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnits/searchMeasurementUnits")
public class MeasurementUnitsSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitsSearchController.class);

	@Autowired
	private MeasurementUnitsService measurementUnitsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  measurementUnits ");
		MeasurementUnitsPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("measurementUnitsField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("measurementUnitsAscending");
			if (request.getSession().getAttribute("measurementUnitsAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("measurementUnitsAscending", direction);
			}
			else
				request.getSession().setAttribute("measurementUnitsAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("measurementUnitsField")==null){
			details = measurementUnitsService.requestMeasurementUnitsPage(new RequestMeasurementUnitsPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("measurementUnitsAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("measurementUnitsAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("measurementUnitsField"):field));
			}
			details = measurementUnitsService.requestMeasurementUnitsPage(new RequestMeasurementUnitsPageEvent(pageRequest));
		}
		
		PageWrapper<MeasurementUnits> page=new PageWrapper<MeasurementUnits>(details.getMeasurementUnitsPage(),"/configuration/measurementUnits/searchMeasurementUnits");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/measurementUnits/searchMeasurementUnits";
	}
}