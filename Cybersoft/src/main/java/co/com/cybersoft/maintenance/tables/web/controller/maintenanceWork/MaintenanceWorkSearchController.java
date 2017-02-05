package co.com.cybersoft.maintenance.tables.web.controller.maintenanceWork;

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

import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.MaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.maintenanceWork.RequestMaintenanceWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkFilterInfo;

/**
 * Search controller class for maintenanceWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/maintenanceWork/searchMaintenanceWork")
public class MaintenanceWorkSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MaintenanceWorkSearchController.class);

	@Autowired
	private MaintenanceWorkService maintenanceWorkService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		MaintenanceWorkFilterInfo f=(MaintenanceWorkFilterInfo) request.getSession().getAttribute("maintenanceWorkFilter");
		if (f!=null){
			f.getMaintenanceWorkFilterList().clear();
		}
		
		LOG.debug("Retrieving  maintenanceWork ");
		MaintenanceWorkPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("maintenanceWorkAscending");
			String oldField=(String)request.getSession().getAttribute("maintenanceWorkField");
			if (oldField!=null && request.getSession().getAttribute("maintenanceWorkAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("maintenanceWorkAscending", direction);
			}
			else
				request.getSession().setAttribute("maintenanceWorkAscending", true);
			request.getSession().setAttribute("maintenanceWorkField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("maintenanceWorkField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = maintenanceWorkService.requestMaintenanceWorkPage(new RequestMaintenanceWorkPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("maintenanceWorkAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("maintenanceWorkAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("maintenanceWorkField"):field));
			}
			details = maintenanceWorkService.requestMaintenanceWorkPage(new RequestMaintenanceWorkPageEvent(pageRequest));
		}
		
		PageWrapper<MaintenanceWork> page=new PageWrapper<MaintenanceWork>(details.getMaintenanceWorkPage(),"/maintenance/maintenanceWork/searchMaintenanceWork");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("maintenanceWorkField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/maintenanceWork/searchMaintenanceWork";
	}
	
	private Boolean hasActions(MaintenanceWorkFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("maintenanceWorkFilterInfo")MaintenanceWorkFilterInfo filter, HttpServletRequest request) throws Exception{
		MaintenanceWorkFilterInfo f=(MaintenanceWorkFilterInfo) request.getSession().getAttribute("maintenanceWorkFilter");
		if (f!=null && f.getMaintenanceWorkFilterList().size()!=0)
			filter.getMaintenanceWorkFilterList().addAll(f.getMaintenanceWorkFilterList());
		if (filter.getAaddFilter())
			filter.getMaintenanceWorkFilterList().add((MaintenanceWorkFilterInfo) (request.getSession().getAttribute("maintenanceWorkFilterCopy")!=null?request.getSession().getAttribute("maintenanceWorkFilterCopy"):new MaintenanceWorkFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("maintenanceWorkAscending");
			String oldField=(String)request.getSession().getAttribute("maintenanceWorkField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("maintenanceWorkAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("maintenanceWorkAscending", direction);
			}
			else if (request.getSession().getAttribute("maintenanceWorkAscending")==null)
				request.getSession().setAttribute("maintenanceWorkAscending", true);
			request.getSession().setAttribute("maintenanceWorkField", filter.getSelectedFilterField());
		}
		
		RequestMaintenanceWorkPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("maintenanceWorkField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestMaintenanceWorkPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("maintenanceWorkAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("maintenanceWorkAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("maintenanceWorkField"):filter.getSelectedFilterField()));
				pageEvent = new RequestMaintenanceWorkPageEvent(pageRequest,filter);			}
		}
		
		MaintenanceWorkPageEvent details = maintenanceWorkService.requestMaintenanceWorkFilterPage(pageEvent);
		PageWrapper<MaintenanceWork> page=new PageWrapper<MaintenanceWork>(details.getMaintenanceWorkPage(),"/maintenance/maintenanceWork/searchMaintenanceWork");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("maintenanceWorkField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("maintenanceWorkFilter", filter);
    	request.getSession().setAttribute("maintenanceWorkFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/maintenanceWork/searchMaintenanceWork";
	}
	
	@ModelAttribute("maintenanceWorkFilterInfo")
	private MaintenanceWorkFilterInfo getMaintenanceWorkFilterInfo(){
		return new MaintenanceWorkFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}