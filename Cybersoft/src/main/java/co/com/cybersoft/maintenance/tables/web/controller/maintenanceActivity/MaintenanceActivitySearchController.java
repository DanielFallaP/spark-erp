package co.com.cybersoft.maintenance.tables.web.controller.maintenanceActivity;

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

import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.MaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceActivity.RequestMaintenanceActivityPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;

/**
 * Search controller class for maintenanceActivity
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceActivity/searchMaintenanceActivity")
public class MaintenanceActivitySearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MaintenanceActivitySearchController.class);

	@Autowired
	private MaintenanceActivityService maintenanceActivityService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		MaintenanceActivityFilterInfo f=(MaintenanceActivityFilterInfo) request.getSession().getAttribute("maintenanceActivityFilter");
		if (f!=null){
			f.getMaintenanceActivityFilterList().clear();
		}
		
		LOG.debug("Retrieving  maintenanceActivity ");
		MaintenanceActivityPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("maintenanceActivityAscending");
			String oldField=(String)request.getSession().getAttribute("maintenanceActivityField");
			if (oldField!=null && request.getSession().getAttribute("maintenanceActivityAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("maintenanceActivityAscending", direction);
			}
			else
				request.getSession().setAttribute("maintenanceActivityAscending", true);
			request.getSession().setAttribute("maintenanceActivityField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("maintenanceActivityField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = maintenanceActivityService.requestMaintenanceActivityPage(new RequestMaintenanceActivityPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("maintenanceActivityAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("maintenanceActivityAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("maintenanceActivityField"):field));
			}
			details = maintenanceActivityService.requestMaintenanceActivityPage(new RequestMaintenanceActivityPageEvent(pageRequest));
		}
		
		PageWrapper<MaintenanceActivity> page=new PageWrapper<MaintenanceActivity>(details.getMaintenanceActivityPage(),"/maintenance/maintenanceActivity/searchMaintenanceActivity");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("maintenanceActivityField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/maintenanceActivity/searchMaintenanceActivity";
	}
	
	private Boolean hasActions(MaintenanceActivityFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("maintenanceActivityFilterInfo")MaintenanceActivityFilterInfo filter, HttpServletRequest request) throws Exception{
		MaintenanceActivityFilterInfo f=(MaintenanceActivityFilterInfo) request.getSession().getAttribute("maintenanceActivityFilter");
		if (f!=null && f.getMaintenanceActivityFilterList().size()!=0)
			filter.getMaintenanceActivityFilterList().addAll(f.getMaintenanceActivityFilterList());
		if (filter.getAaddFilter())
			filter.getMaintenanceActivityFilterList().add((MaintenanceActivityFilterInfo) (request.getSession().getAttribute("maintenanceActivityFilterCopy")!=null?request.getSession().getAttribute("maintenanceActivityFilterCopy"):new MaintenanceActivityFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("maintenanceActivityAscending");
			String oldField=(String)request.getSession().getAttribute("maintenanceActivityField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("maintenanceActivityAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("maintenanceActivityAscending", direction);
			}
			else if (request.getSession().getAttribute("maintenanceActivityAscending")==null)
				request.getSession().setAttribute("maintenanceActivityAscending", true);
			request.getSession().setAttribute("maintenanceActivityField", filter.getSelectedFilterField());
		}
		
		RequestMaintenanceActivityPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("maintenanceActivityField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestMaintenanceActivityPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("maintenanceActivityAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("maintenanceActivityAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("maintenanceActivityField"):filter.getSelectedFilterField()));
				pageEvent = new RequestMaintenanceActivityPageEvent(pageRequest,filter);			}
		}
		
		MaintenanceActivityPageEvent details = maintenanceActivityService.requestMaintenanceActivityFilterPage(pageEvent);
		PageWrapper<MaintenanceActivity> page=new PageWrapper<MaintenanceActivity>(details.getMaintenanceActivityPage(),"/maintenance/maintenanceActivity/searchMaintenanceActivity");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("maintenanceActivityField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("maintenanceActivityFilter", filter);
    	request.getSession().setAttribute("maintenanceActivityFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/maintenanceActivity/searchMaintenanceActivity";
	}
	
	@ModelAttribute("maintenanceActivityFilterInfo")
	private MaintenanceActivityFilterInfo getMaintenanceActivityFilterInfo(){
		return new MaintenanceActivityFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}