package co.com.cybersoft.maintenance.tables.web.controller.failureCauseTechnicalaction;

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

import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.FailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction.RequestFailureCauseTechnicalactionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;

/**
 * Search controller class for failureCauseTechnicalaction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction")
public class FailureCauseTechnicalactionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FailureCauseTechnicalactionSearchController.class);

	@Autowired
	private FailureCauseTechnicalactionService failureCauseTechnicalactionService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		FailureCauseTechnicalactionFilterInfo f=(FailureCauseTechnicalactionFilterInfo) request.getSession().getAttribute("failureCauseTechnicalactionFilter");
		if (f!=null){
			f.getFailureCauseTechnicalactionFilterList().clear();
		}
		
		LOG.debug("Retrieving  failureCauseTechnicalaction ");
		FailureCauseTechnicalactionPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("failureCauseTechnicalactionAscending");
			String oldField=(String)request.getSession().getAttribute("failureCauseTechnicalactionField");
			if (oldField!=null && request.getSession().getAttribute("failureCauseTechnicalactionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("failureCauseTechnicalactionAscending", direction);
			}
			else
				request.getSession().setAttribute("failureCauseTechnicalactionAscending", true);
			request.getSession().setAttribute("failureCauseTechnicalactionField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("failureCauseTechnicalactionField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = failureCauseTechnicalactionService.requestFailureCauseTechnicalactionPage(new RequestFailureCauseTechnicalactionPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("failureCauseTechnicalactionAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("failureCauseTechnicalactionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("failureCauseTechnicalactionField"):field));
			}
			details = failureCauseTechnicalactionService.requestFailureCauseTechnicalactionPage(new RequestFailureCauseTechnicalactionPageEvent(pageRequest));
		}
		
		PageWrapper<FailureCauseTechnicalaction> page=new PageWrapper<FailureCauseTechnicalaction>(details.getFailureCauseTechnicalactionPage(),"/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("failureCauseTechnicalactionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction";
	}
	
	private Boolean hasActions(FailureCauseTechnicalactionFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("failureCauseTechnicalactionFilterInfo")FailureCauseTechnicalactionFilterInfo filter, HttpServletRequest request) throws Exception{
		FailureCauseTechnicalactionFilterInfo f=(FailureCauseTechnicalactionFilterInfo) request.getSession().getAttribute("failureCauseTechnicalactionFilter");
		if (f!=null && f.getFailureCauseTechnicalactionFilterList().size()!=0)
			filter.getFailureCauseTechnicalactionFilterList().addAll(f.getFailureCauseTechnicalactionFilterList());
		if (filter.getAaddFilter())
			filter.getFailureCauseTechnicalactionFilterList().add((FailureCauseTechnicalactionFilterInfo) (request.getSession().getAttribute("failureCauseTechnicalactionFilterCopy")!=null?request.getSession().getAttribute("failureCauseTechnicalactionFilterCopy"):new FailureCauseTechnicalactionFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("failureCauseTechnicalactionAscending");
			String oldField=(String)request.getSession().getAttribute("failureCauseTechnicalactionField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("failureCauseTechnicalactionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("failureCauseTechnicalactionAscending", direction);
			}
			else if (request.getSession().getAttribute("failureCauseTechnicalactionAscending")==null)
				request.getSession().setAttribute("failureCauseTechnicalactionAscending", true);
			request.getSession().setAttribute("failureCauseTechnicalactionField", filter.getSelectedFilterField());
		}
		
		RequestFailureCauseTechnicalactionPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("failureCauseTechnicalactionField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestFailureCauseTechnicalactionPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("failureCauseTechnicalactionAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("failureCauseTechnicalactionAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("failureCauseTechnicalactionField"):filter.getSelectedFilterField()));
				pageEvent = new RequestFailureCauseTechnicalactionPageEvent(pageRequest,filter);			}
		}
		
		FailureCauseTechnicalactionPageEvent details = failureCauseTechnicalactionService.requestFailureCauseTechnicalactionFilterPage(pageEvent);
		PageWrapper<FailureCauseTechnicalaction> page=new PageWrapper<FailureCauseTechnicalaction>(details.getFailureCauseTechnicalactionPage(),"/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("failureCauseTechnicalactionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("failureCauseTechnicalactionFilter", filter);
    	request.getSession().setAttribute("failureCauseTechnicalactionFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/failureCauseTechnicalaction/searchFailureCauseTechnicalaction";
	}
	
	@ModelAttribute("failureCauseTechnicalactionFilterInfo")
	private FailureCauseTechnicalactionFilterInfo getFailureCauseTechnicalactionFilterInfo(){
		return new FailureCauseTechnicalactionFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}