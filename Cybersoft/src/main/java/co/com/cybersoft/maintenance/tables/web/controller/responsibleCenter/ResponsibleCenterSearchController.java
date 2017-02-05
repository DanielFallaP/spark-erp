package co.com.cybersoft.maintenance.tables.web.controller.responsibleCenter;

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

import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;

/**
 * Search controller class for responsibleCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/responsibleCenter/searchResponsibleCenter")
public class ResponsibleCenterSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResponsibleCenterSearchController.class);

	@Autowired
	private ResponsibleCenterService responsibleCenterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ResponsibleCenterFilterInfo f=(ResponsibleCenterFilterInfo) request.getSession().getAttribute("responsibleCenterFilter");
		if (f!=null){
			f.getResponsibleCenterFilterList().clear();
		}
		
		LOG.debug("Retrieving  responsibleCenter ");
		ResponsibleCenterPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("responsibleCenterAscending");
			String oldField=(String)request.getSession().getAttribute("responsibleCenterField");
			if (oldField!=null && request.getSession().getAttribute("responsibleCenterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("responsibleCenterAscending", direction);
			}
			else
				request.getSession().setAttribute("responsibleCenterAscending", true);
			request.getSession().setAttribute("responsibleCenterField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("responsibleCenterField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = responsibleCenterService.requestResponsibleCenterPage(new RequestResponsibleCenterPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("responsibleCenterAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("responsibleCenterAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("responsibleCenterField"):field));
			}
			details = responsibleCenterService.requestResponsibleCenterPage(new RequestResponsibleCenterPageEvent(pageRequest));
		}
		
		PageWrapper<ResponsibleCenter> page=new PageWrapper<ResponsibleCenter>(details.getResponsibleCenterPage(),"/maintenance/responsibleCenter/searchResponsibleCenter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("responsibleCenterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/responsibleCenter/searchResponsibleCenter";
	}
	
	private Boolean hasActions(ResponsibleCenterFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("responsibleCenterFilterInfo")ResponsibleCenterFilterInfo filter, HttpServletRequest request) throws Exception{
		ResponsibleCenterFilterInfo f=(ResponsibleCenterFilterInfo) request.getSession().getAttribute("responsibleCenterFilter");
		if (f!=null && f.getResponsibleCenterFilterList().size()!=0)
			filter.getResponsibleCenterFilterList().addAll(f.getResponsibleCenterFilterList());
		if (filter.getAaddFilter())
			filter.getResponsibleCenterFilterList().add((ResponsibleCenterFilterInfo) (request.getSession().getAttribute("responsibleCenterFilterCopy")!=null?request.getSession().getAttribute("responsibleCenterFilterCopy"):new ResponsibleCenterFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("responsibleCenterAscending");
			String oldField=(String)request.getSession().getAttribute("responsibleCenterField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("responsibleCenterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("responsibleCenterAscending", direction);
			}
			else if (request.getSession().getAttribute("responsibleCenterAscending")==null)
				request.getSession().setAttribute("responsibleCenterAscending", true);
			request.getSession().setAttribute("responsibleCenterField", filter.getSelectedFilterField());
		}
		
		RequestResponsibleCenterPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("responsibleCenterField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestResponsibleCenterPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("responsibleCenterAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("responsibleCenterAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("responsibleCenterField"):filter.getSelectedFilterField()));
				pageEvent = new RequestResponsibleCenterPageEvent(pageRequest,filter);			}
		}
		
		ResponsibleCenterPageEvent details = responsibleCenterService.requestResponsibleCenterFilterPage(pageEvent);
		PageWrapper<ResponsibleCenter> page=new PageWrapper<ResponsibleCenter>(details.getResponsibleCenterPage(),"/maintenance/responsibleCenter/searchResponsibleCenter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("responsibleCenterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("responsibleCenterFilter", filter);
    	request.getSession().setAttribute("responsibleCenterFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/responsibleCenter/searchResponsibleCenter";
	}
	
	@ModelAttribute("responsibleCenterFilterInfo")
	private ResponsibleCenterFilterInfo getResponsibleCenterFilterInfo(){
		return new ResponsibleCenterFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}