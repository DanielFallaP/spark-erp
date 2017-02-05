package co.com.cybersoft.maintenance.tables.web.controller.state;

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

import co.com.cybersoft.maintenance.tables.core.services.state.StateService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.state.StatePageEvent;
import co.com.cybersoft.maintenance.tables.events.state.RequestStatePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateFilterInfo;

/**
 * Search controller class for state
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/state/searchState")
public class StateSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StateSearchController.class);

	@Autowired
	private StateService stateService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		StateFilterInfo f=(StateFilterInfo) request.getSession().getAttribute("stateFilter");
		if (f!=null){
			f.getStateFilterList().clear();
		}
		
		LOG.debug("Retrieving  state ");
		StatePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("stateAscending");
			String oldField=(String)request.getSession().getAttribute("stateField");
			if (oldField!=null && request.getSession().getAttribute("stateAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("stateAscending", direction);
			}
			else
				request.getSession().setAttribute("stateAscending", true);
			request.getSession().setAttribute("stateField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("stateField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = stateService.requestStatePage(new RequestStatePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("stateAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("stateAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("stateField"):field));
			}
			details = stateService.requestStatePage(new RequestStatePageEvent(pageRequest));
		}
		
		PageWrapper<State> page=new PageWrapper<State>(details.getStatePage(),"/maintenance/state/searchState");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("stateField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/state/searchState";
	}
	
	private Boolean hasActions(StateFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("stateFilterInfo")StateFilterInfo filter, HttpServletRequest request) throws Exception{
		StateFilterInfo f=(StateFilterInfo) request.getSession().getAttribute("stateFilter");
		if (f!=null && f.getStateFilterList().size()!=0)
			filter.getStateFilterList().addAll(f.getStateFilterList());
		if (filter.getAaddFilter())
			filter.getStateFilterList().add((StateFilterInfo) (request.getSession().getAttribute("stateFilterCopy")!=null?request.getSession().getAttribute("stateFilterCopy"):new StateFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("stateAscending");
			String oldField=(String)request.getSession().getAttribute("stateField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("stateAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("stateAscending", direction);
			}
			else if (request.getSession().getAttribute("stateAscending")==null)
				request.getSession().setAttribute("stateAscending", true);
			request.getSession().setAttribute("stateField", filter.getSelectedFilterField());
		}
		
		RequestStatePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("stateField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestStatePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("stateAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("stateAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("stateField"):filter.getSelectedFilterField()));
				pageEvent = new RequestStatePageEvent(pageRequest,filter);			}
		}
		
		StatePageEvent details = stateService.requestStateFilterPage(pageEvent);
		PageWrapper<State> page=new PageWrapper<State>(details.getStatePage(),"/maintenance/state/searchState");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("stateField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("stateFilter", filter);
    	request.getSession().setAttribute("stateFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/state/searchState";
	}
	
	@ModelAttribute("stateFilterInfo")
	private StateFilterInfo getStateFilterInfo(){
		return new StateFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}