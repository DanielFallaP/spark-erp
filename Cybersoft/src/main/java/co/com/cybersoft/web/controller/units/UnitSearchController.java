package co.com.cybersoft.web.controller.units;

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

import co.com.cybersoft.core.services.units.UnitService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.units.RequestUnitsEvent;
import co.com.cybersoft.events.units.UnitsEvent;
import co.com.cybersoft.persistence.domain.MeasurementUnit;
import co.com.cybersoft.web.controller.items.ItemCreationController;

@Controller
@RequestMapping("/configuration/units/searchUnits")
public class UnitSearchController {

	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);

	@Autowired
	private UnitService unitService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String searchUnits(Model model, Pageable pageable, String field, HttpServletRequest request){
		LOG.debug("Retrieving units ");
		UnitsEvent requestUnits;
		if (field!=null){
			request.getSession().setAttribute("unitField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("unitAscending");
			if (request.getSession().getAttribute("unitAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("unitAscending", direction);
			}
			else
				request.getSession().setAttribute("unitAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("unitField")==null){
			requestUnits = unitService.requestUnits(new RequestUnitsEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("unitAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("unitAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("unitField"):field));
			}
			requestUnits = unitService.requestUnits(new RequestUnitsEvent(pageRequest));
		}
		
		PageWrapper<MeasurementUnit> page=new PageWrapper<MeasurementUnit>(requestUnits.getUnits(),"/configuration/units/searchUnits");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		
		return "/configuration/units/searchUnits";
	}
	
}
