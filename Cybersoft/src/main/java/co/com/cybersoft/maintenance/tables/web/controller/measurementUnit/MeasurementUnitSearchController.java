package co.com.cybersoft.maintenance.tables.web.controller.measurementUnit;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitFilterInfo;

/**
 * Search controller class for measurementUnit
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/measurementUnit/searchMeasurementUnit")
public class MeasurementUnitSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MeasurementUnitSearchController.class);

	@Autowired
	private MeasurementUnitService measurementUnitService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		MeasurementUnitFilterInfo f=(MeasurementUnitFilterInfo) request.getSession().getAttribute("measurementUnitFilter");
		if (f!=null){
			f.getMeasurementUnitFilterList().clear();
		}
		
		LOG.debug("Retrieving  measurementUnit ");
		MeasurementUnitPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("measurementUnitAscending");
			String oldField=(String)request.getSession().getAttribute("measurementUnitField");
			if (oldField!=null && request.getSession().getAttribute("measurementUnitAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("measurementUnitAscending", direction);
			}
			else
				request.getSession().setAttribute("measurementUnitAscending", true);
			request.getSession().setAttribute("measurementUnitField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("measurementUnitField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = measurementUnitService.requestMeasurementUnitPage(new RequestMeasurementUnitPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("measurementUnitAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("measurementUnitAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("measurementUnitField"):field));
			}
			details = measurementUnitService.requestMeasurementUnitPage(new RequestMeasurementUnitPageEvent(pageRequest));
		}
		
		PageWrapper<MeasurementUnit> page=new PageWrapper<MeasurementUnit>(details.getMeasurementUnitPage(),"/maintenance/measurementUnit/searchMeasurementUnit");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("measurementUnitField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/measurementUnit/searchMeasurementUnit";
	}
	
	private Boolean hasActions(MeasurementUnitFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("measurementUnitFilterInfo")MeasurementUnitFilterInfo filter, HttpServletRequest request) throws Exception{
		MeasurementUnitFilterInfo f=(MeasurementUnitFilterInfo) request.getSession().getAttribute("measurementUnitFilter");
		if (f!=null && f.getMeasurementUnitFilterList().size()!=0)
			filter.getMeasurementUnitFilterList().addAll(f.getMeasurementUnitFilterList());
		if (filter.getAaddFilter())
			filter.getMeasurementUnitFilterList().add((MeasurementUnitFilterInfo) (request.getSession().getAttribute("measurementUnitFilterCopy")!=null?request.getSession().getAttribute("measurementUnitFilterCopy"):new MeasurementUnitFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("measurementUnitAscending");
			String oldField=(String)request.getSession().getAttribute("measurementUnitField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("measurementUnitAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("measurementUnitAscending", direction);
			}
			else if (request.getSession().getAttribute("measurementUnitAscending")==null)
				request.getSession().setAttribute("measurementUnitAscending", true);
			request.getSession().setAttribute("measurementUnitField", filter.getSelectedFilterField());
		}
		
		RequestMeasurementUnitPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("measurementUnitField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestMeasurementUnitPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("measurementUnitAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("measurementUnitAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("measurementUnitField"):filter.getSelectedFilterField()));
				pageEvent = new RequestMeasurementUnitPageEvent(pageRequest,filter);			}
		}
		
		MeasurementUnitPageEvent details = measurementUnitService.requestMeasurementUnitFilterPage(pageEvent);
		PageWrapper<MeasurementUnit> page=new PageWrapper<MeasurementUnit>(details.getMeasurementUnitPage(),"/maintenance/measurementUnit/searchMeasurementUnit");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("measurementUnitField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("measurementUnitFilter", filter);
    	request.getSession().setAttribute("measurementUnitFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/measurementUnit/searchMeasurementUnit";
	}
	
	@ModelAttribute("measurementUnitFilterInfo")
	private MeasurementUnitFilterInfo getMeasurementUnitFilterInfo(){
		return new MeasurementUnitFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}