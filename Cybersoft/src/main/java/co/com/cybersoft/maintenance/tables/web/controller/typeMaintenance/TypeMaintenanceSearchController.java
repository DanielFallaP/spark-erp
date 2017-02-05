package co.com.cybersoft.maintenance.tables.web.controller.typeMaintenance;

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

import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceFilterInfo;

/**
 * Search controller class for typeMaintenance
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeMaintenance/searchTypeMaintenance")
public class TypeMaintenanceSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TypeMaintenanceSearchController.class);

	@Autowired
	private TypeMaintenanceService typeMaintenanceService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TypeMaintenanceFilterInfo f=(TypeMaintenanceFilterInfo) request.getSession().getAttribute("typeMaintenanceFilter");
		if (f!=null){
			f.getTypeMaintenanceFilterList().clear();
		}
		
		LOG.debug("Retrieving  typeMaintenance ");
		TypeMaintenancePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("typeMaintenanceAscending");
			String oldField=(String)request.getSession().getAttribute("typeMaintenanceField");
			if (oldField!=null && request.getSession().getAttribute("typeMaintenanceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeMaintenanceAscending", direction);
			}
			else
				request.getSession().setAttribute("typeMaintenanceAscending", true);
			request.getSession().setAttribute("typeMaintenanceField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("typeMaintenanceField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = typeMaintenanceService.requestTypeMaintenancePage(new RequestTypeMaintenancePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("typeMaintenanceAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("typeMaintenanceAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("typeMaintenanceField"):field));
			}
			details = typeMaintenanceService.requestTypeMaintenancePage(new RequestTypeMaintenancePageEvent(pageRequest));
		}
		
		PageWrapper<TypeMaintenance> page=new PageWrapper<TypeMaintenance>(details.getTypeMaintenancePage(),"/maintenance/typeMaintenance/searchTypeMaintenance");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeMaintenanceField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/typeMaintenance/searchTypeMaintenance";
	}
	
	private Boolean hasActions(TypeMaintenanceFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("typeMaintenanceFilterInfo")TypeMaintenanceFilterInfo filter, HttpServletRequest request) throws Exception{
		TypeMaintenanceFilterInfo f=(TypeMaintenanceFilterInfo) request.getSession().getAttribute("typeMaintenanceFilter");
		if (f!=null && f.getTypeMaintenanceFilterList().size()!=0)
			filter.getTypeMaintenanceFilterList().addAll(f.getTypeMaintenanceFilterList());
		if (filter.getAaddFilter())
			filter.getTypeMaintenanceFilterList().add((TypeMaintenanceFilterInfo) (request.getSession().getAttribute("typeMaintenanceFilterCopy")!=null?request.getSession().getAttribute("typeMaintenanceFilterCopy"):new TypeMaintenanceFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("typeMaintenanceAscending");
			String oldField=(String)request.getSession().getAttribute("typeMaintenanceField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("typeMaintenanceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeMaintenanceAscending", direction);
			}
			else if (request.getSession().getAttribute("typeMaintenanceAscending")==null)
				request.getSession().setAttribute("typeMaintenanceAscending", true);
			request.getSession().setAttribute("typeMaintenanceField", filter.getSelectedFilterField());
		}
		
		RequestTypeMaintenancePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("typeMaintenanceField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTypeMaintenancePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("typeMaintenanceAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("typeMaintenanceAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("typeMaintenanceField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTypeMaintenancePageEvent(pageRequest,filter);			}
		}
		
		TypeMaintenancePageEvent details = typeMaintenanceService.requestTypeMaintenanceFilterPage(pageEvent);
		PageWrapper<TypeMaintenance> page=new PageWrapper<TypeMaintenance>(details.getTypeMaintenancePage(),"/maintenance/typeMaintenance/searchTypeMaintenance");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeMaintenanceField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("typeMaintenanceFilter", filter);
    	request.getSession().setAttribute("typeMaintenanceFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/typeMaintenance/searchTypeMaintenance";
	}
	
	@ModelAttribute("typeMaintenanceFilterInfo")
	private TypeMaintenanceFilterInfo getTypeMaintenanceFilterInfo(){
		return new TypeMaintenanceFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}