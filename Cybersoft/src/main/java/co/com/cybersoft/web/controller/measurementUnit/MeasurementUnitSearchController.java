package co.com.cybersoft.web.controller.measurementUnit;

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

import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnit;

/**
 * Search controller class for measurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnit/searchMeasurementUnit")
public class MeasurementUnitSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitSearchController.class);

	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  measurementUnit ");
		MeasurementUnitPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("measurementUnitField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("measurementUnitAscending");
			if (request.getSession().getAttribute("measurementUnitAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("measurementUnitAscending", direction);
			}
			else
				request.getSession().setAttribute("measurementUnitAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("measurementUnitField")==null){
			details = measurementUnitService.requestMeasurementUnitPage(new RequestMeasurementUnitPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("measurementUnitAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("measurementUnitAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("measurementUnitField"):field));
			}
			details = measurementUnitService.requestMeasurementUnitPage(new RequestMeasurementUnitPageEvent(pageRequest));
		}
		
		PageWrapper<MeasurementUnit> page=new PageWrapper<MeasurementUnit>(details.getMeasurementUnitPage(),"/configuration/measurementUnit/searchMeasurementUnit");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/measurementUnit/searchMeasurementUnit";
	}
}