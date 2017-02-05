package co.com.cybersoft.maintenance.tables.web.controller.responsible;

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

import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.events.responsible.RequestResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleFilterInfo;

/**
 * Search controller class for responsible
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/responsible/searchResponsible")
public class ResponsibleSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResponsibleSearchController.class);

	@Autowired
	private ResponsibleService responsibleService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ResponsibleFilterInfo f=(ResponsibleFilterInfo) request.getSession().getAttribute("responsibleFilter");
		if (f!=null){
			f.getResponsibleFilterList().clear();
		}
		
		LOG.debug("Retrieving  responsible ");
		ResponsiblePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("responsibleAscending");
			String oldField=(String)request.getSession().getAttribute("responsibleField");
			if (oldField!=null && request.getSession().getAttribute("responsibleAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("responsibleAscending", direction);
			}
			else
				request.getSession().setAttribute("responsibleAscending", true);
			request.getSession().setAttribute("responsibleField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("responsibleField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = responsibleService.requestResponsiblePage(new RequestResponsiblePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("responsibleAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("responsibleAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("responsibleField"):field));
			}
			details = responsibleService.requestResponsiblePage(new RequestResponsiblePageEvent(pageRequest));
		}
		
		PageWrapper<Responsible> page=new PageWrapper<Responsible>(details.getResponsiblePage(),"/maintenance/responsible/searchResponsible");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("responsibleField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/responsible/searchResponsible";
	}
	
	private Boolean hasActions(ResponsibleFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("responsibleFilterInfo")ResponsibleFilterInfo filter, HttpServletRequest request) throws Exception{
		ResponsibleFilterInfo f=(ResponsibleFilterInfo) request.getSession().getAttribute("responsibleFilter");
		if (f!=null && f.getResponsibleFilterList().size()!=0)
			filter.getResponsibleFilterList().addAll(f.getResponsibleFilterList());
		if (filter.getAaddFilter())
			filter.getResponsibleFilterList().add((ResponsibleFilterInfo) (request.getSession().getAttribute("responsibleFilterCopy")!=null?request.getSession().getAttribute("responsibleFilterCopy"):new ResponsibleFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("responsibleAscending");
			String oldField=(String)request.getSession().getAttribute("responsibleField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("responsibleAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("responsibleAscending", direction);
			}
			else if (request.getSession().getAttribute("responsibleAscending")==null)
				request.getSession().setAttribute("responsibleAscending", true);
			request.getSession().setAttribute("responsibleField", filter.getSelectedFilterField());
		}
		
		RequestResponsiblePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("responsibleField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestResponsiblePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("responsibleAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("responsibleAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("responsibleField"):filter.getSelectedFilterField()));
				pageEvent = new RequestResponsiblePageEvent(pageRequest,filter);			}
		}
		
		ResponsiblePageEvent details = responsibleService.requestResponsibleFilterPage(pageEvent);
		PageWrapper<Responsible> page=new PageWrapper<Responsible>(details.getResponsiblePage(),"/maintenance/responsible/searchResponsible");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("responsibleField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("responsibleFilter", filter);
    	request.getSession().setAttribute("responsibleFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/responsible/searchResponsible";
	}
	
	@ModelAttribute("responsibleFilterInfo")
	private ResponsibleFilterInfo getResponsibleFilterInfo(){
		return new ResponsibleFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}