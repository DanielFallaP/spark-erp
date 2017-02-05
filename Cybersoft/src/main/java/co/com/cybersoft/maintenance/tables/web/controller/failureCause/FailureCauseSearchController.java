package co.com.cybersoft.maintenance.tables.web.controller.failureCause;

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

import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.failureCause.FailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCause.RequestFailureCausePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseFilterInfo;

/**
 * Search controller class for failureCause
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCause/searchFailureCause")
public class FailureCauseSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FailureCauseSearchController.class);

	@Autowired
	private FailureCauseService failureCauseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		FailureCauseFilterInfo f=(FailureCauseFilterInfo) request.getSession().getAttribute("failureCauseFilter");
		if (f!=null){
			f.getFailureCauseFilterList().clear();
		}
		
		LOG.debug("Retrieving  failureCause ");
		FailureCausePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("failureCauseAscending");
			String oldField=(String)request.getSession().getAttribute("failureCauseField");
			if (oldField!=null && request.getSession().getAttribute("failureCauseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("failureCauseAscending", direction);
			}
			else
				request.getSession().setAttribute("failureCauseAscending", true);
			request.getSession().setAttribute("failureCauseField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("failureCauseField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = failureCauseService.requestFailureCausePage(new RequestFailureCausePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("failureCauseAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("failureCauseAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("failureCauseField"):field));
			}
			details = failureCauseService.requestFailureCausePage(new RequestFailureCausePageEvent(pageRequest));
		}
		
		PageWrapper<FailureCause> page=new PageWrapper<FailureCause>(details.getFailureCausePage(),"/maintenance/failureCause/searchFailureCause");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("failureCauseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/failureCause/searchFailureCause";
	}
	
	private Boolean hasActions(FailureCauseFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("failureCauseFilterInfo")FailureCauseFilterInfo filter, HttpServletRequest request) throws Exception{
		FailureCauseFilterInfo f=(FailureCauseFilterInfo) request.getSession().getAttribute("failureCauseFilter");
		if (f!=null && f.getFailureCauseFilterList().size()!=0)
			filter.getFailureCauseFilterList().addAll(f.getFailureCauseFilterList());
		if (filter.getAaddFilter())
			filter.getFailureCauseFilterList().add((FailureCauseFilterInfo) (request.getSession().getAttribute("failureCauseFilterCopy")!=null?request.getSession().getAttribute("failureCauseFilterCopy"):new FailureCauseFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("failureCauseAscending");
			String oldField=(String)request.getSession().getAttribute("failureCauseField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("failureCauseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("failureCauseAscending", direction);
			}
			else if (request.getSession().getAttribute("failureCauseAscending")==null)
				request.getSession().setAttribute("failureCauseAscending", true);
			request.getSession().setAttribute("failureCauseField", filter.getSelectedFilterField());
		}
		
		RequestFailureCausePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("failureCauseField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestFailureCausePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("failureCauseAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("failureCauseAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("failureCauseField"):filter.getSelectedFilterField()));
				pageEvent = new RequestFailureCausePageEvent(pageRequest,filter);			}
		}
		
		FailureCausePageEvent details = failureCauseService.requestFailureCauseFilterPage(pageEvent);
		PageWrapper<FailureCause> page=new PageWrapper<FailureCause>(details.getFailureCausePage(),"/maintenance/failureCause/searchFailureCause");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("failureCauseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("failureCauseFilter", filter);
    	request.getSession().setAttribute("failureCauseFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/failureCause/searchFailureCause";
	}
	
	@ModelAttribute("failureCauseFilterInfo")
	private FailureCauseFilterInfo getFailureCauseFilterInfo(){
		return new FailureCauseFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}