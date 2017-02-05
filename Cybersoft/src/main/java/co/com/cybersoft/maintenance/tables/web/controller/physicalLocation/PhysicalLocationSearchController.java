package co.com.cybersoft.maintenance.tables.web.controller.physicalLocation;

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

import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.RequestPhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;

/**
 * Search controller class for physicalLocation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/physicalLocation/searchPhysicalLocation")
public class PhysicalLocationSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PhysicalLocationSearchController.class);

	@Autowired
	private PhysicalLocationService physicalLocationService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		PhysicalLocationFilterInfo f=(PhysicalLocationFilterInfo) request.getSession().getAttribute("physicalLocationFilter");
		if (f!=null){
			f.getPhysicalLocationFilterList().clear();
		}
		
		LOG.debug("Retrieving  physicalLocation ");
		PhysicalLocationPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("physicalLocationAscending");
			String oldField=(String)request.getSession().getAttribute("physicalLocationField");
			if (oldField!=null && request.getSession().getAttribute("physicalLocationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("physicalLocationAscending", direction);
			}
			else
				request.getSession().setAttribute("physicalLocationAscending", true);
			request.getSession().setAttribute("physicalLocationField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("physicalLocationField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = physicalLocationService.requestPhysicalLocationPage(new RequestPhysicalLocationPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("physicalLocationAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("physicalLocationAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("physicalLocationField"):field));
			}
			details = physicalLocationService.requestPhysicalLocationPage(new RequestPhysicalLocationPageEvent(pageRequest));
		}
		
		PageWrapper<PhysicalLocation> page=new PageWrapper<PhysicalLocation>(details.getPhysicalLocationPage(),"/maintenance/physicalLocation/searchPhysicalLocation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("physicalLocationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/physicalLocation/searchPhysicalLocation";
	}
	
	private Boolean hasActions(PhysicalLocationFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("physicalLocationFilterInfo")PhysicalLocationFilterInfo filter, HttpServletRequest request) throws Exception{
		PhysicalLocationFilterInfo f=(PhysicalLocationFilterInfo) request.getSession().getAttribute("physicalLocationFilter");
		if (f!=null && f.getPhysicalLocationFilterList().size()!=0)
			filter.getPhysicalLocationFilterList().addAll(f.getPhysicalLocationFilterList());
		if (filter.getAaddFilter())
			filter.getPhysicalLocationFilterList().add((PhysicalLocationFilterInfo) (request.getSession().getAttribute("physicalLocationFilterCopy")!=null?request.getSession().getAttribute("physicalLocationFilterCopy"):new PhysicalLocationFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("physicalLocationAscending");
			String oldField=(String)request.getSession().getAttribute("physicalLocationField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("physicalLocationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("physicalLocationAscending", direction);
			}
			else if (request.getSession().getAttribute("physicalLocationAscending")==null)
				request.getSession().setAttribute("physicalLocationAscending", true);
			request.getSession().setAttribute("physicalLocationField", filter.getSelectedFilterField());
		}
		
		RequestPhysicalLocationPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("physicalLocationField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestPhysicalLocationPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("physicalLocationAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("physicalLocationAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("physicalLocationField"):filter.getSelectedFilterField()));
				pageEvent = new RequestPhysicalLocationPageEvent(pageRequest,filter);			}
		}
		
		PhysicalLocationPageEvent details = physicalLocationService.requestPhysicalLocationFilterPage(pageEvent);
		PageWrapper<PhysicalLocation> page=new PageWrapper<PhysicalLocation>(details.getPhysicalLocationPage(),"/maintenance/physicalLocation/searchPhysicalLocation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("physicalLocationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("physicalLocationFilter", filter);
    	request.getSession().setAttribute("physicalLocationFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/physicalLocation/searchPhysicalLocation";
	}
	
	@ModelAttribute("physicalLocationFilterInfo")
	private PhysicalLocationFilterInfo getPhysicalLocationFilterInfo(){
		return new PhysicalLocationFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}