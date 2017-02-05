package co.com.cybersoft.maintenance.tables.web.controller.stateCostCenters;

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

import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.StateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.events.stateCostCenters.RequestStateCostCentersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersFilterInfo;

/**
 * Search controller class for stateCostCenters
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/stateCostCenters/searchStateCostCenters")
public class StateCostCentersSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StateCostCentersSearchController.class);

	@Autowired
	private StateCostCentersService stateCostCentersService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		StateCostCentersFilterInfo f=(StateCostCentersFilterInfo) request.getSession().getAttribute("stateCostCentersFilter");
		if (f!=null){
			f.getStateCostCentersFilterList().clear();
		}
		
		LOG.debug("Retrieving  stateCostCenters ");
		StateCostCentersPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("stateCostCentersAscending");
			String oldField=(String)request.getSession().getAttribute("stateCostCentersField");
			if (oldField!=null && request.getSession().getAttribute("stateCostCentersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("stateCostCentersAscending", direction);
			}
			else
				request.getSession().setAttribute("stateCostCentersAscending", true);
			request.getSession().setAttribute("stateCostCentersField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("stateCostCentersField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = stateCostCentersService.requestStateCostCentersPage(new RequestStateCostCentersPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("stateCostCentersAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("stateCostCentersAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("stateCostCentersField"):field));
			}
			details = stateCostCentersService.requestStateCostCentersPage(new RequestStateCostCentersPageEvent(pageRequest));
		}
		
		PageWrapper<StateCostCenters> page=new PageWrapper<StateCostCenters>(details.getStateCostCentersPage(),"/maintenance/stateCostCenters/searchStateCostCenters");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("stateCostCentersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/stateCostCenters/searchStateCostCenters";
	}
	
	private Boolean hasActions(StateCostCentersFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("stateCostCentersFilterInfo")StateCostCentersFilterInfo filter, HttpServletRequest request) throws Exception{
		StateCostCentersFilterInfo f=(StateCostCentersFilterInfo) request.getSession().getAttribute("stateCostCentersFilter");
		if (f!=null && f.getStateCostCentersFilterList().size()!=0)
			filter.getStateCostCentersFilterList().addAll(f.getStateCostCentersFilterList());
		if (filter.getAaddFilter())
			filter.getStateCostCentersFilterList().add((StateCostCentersFilterInfo) (request.getSession().getAttribute("stateCostCentersFilterCopy")!=null?request.getSession().getAttribute("stateCostCentersFilterCopy"):new StateCostCentersFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("stateCostCentersAscending");
			String oldField=(String)request.getSession().getAttribute("stateCostCentersField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("stateCostCentersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("stateCostCentersAscending", direction);
			}
			else if (request.getSession().getAttribute("stateCostCentersAscending")==null)
				request.getSession().setAttribute("stateCostCentersAscending", true);
			request.getSession().setAttribute("stateCostCentersField", filter.getSelectedFilterField());
		}
		
		RequestStateCostCentersPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("stateCostCentersField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestStateCostCentersPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("stateCostCentersAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("stateCostCentersAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("stateCostCentersField"):filter.getSelectedFilterField()));
				pageEvent = new RequestStateCostCentersPageEvent(pageRequest,filter);			}
		}
		
		StateCostCentersPageEvent details = stateCostCentersService.requestStateCostCentersFilterPage(pageEvent);
		PageWrapper<StateCostCenters> page=new PageWrapper<StateCostCenters>(details.getStateCostCentersPage(),"/maintenance/stateCostCenters/searchStateCostCenters");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("stateCostCentersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("stateCostCentersFilter", filter);
    	request.getSession().setAttribute("stateCostCentersFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/stateCostCenters/searchStateCostCenters";
	}
	
	@ModelAttribute("stateCostCentersFilterInfo")
	private StateCostCentersFilterInfo getStateCostCentersFilterInfo(){
		return new StateCostCentersFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}