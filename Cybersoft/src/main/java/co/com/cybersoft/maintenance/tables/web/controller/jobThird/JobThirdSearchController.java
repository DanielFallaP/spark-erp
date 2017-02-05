package co.com.cybersoft.maintenance.tables.web.controller.jobThird;

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

import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdFilterInfo;

/**
 * Search controller class for jobThird
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/jobThird/searchJobThird")
public class JobThirdSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobThirdSearchController.class);

	@Autowired
	private JobThirdService jobThirdService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		JobThirdFilterInfo f=(JobThirdFilterInfo) request.getSession().getAttribute("jobThirdFilter");
		if (f!=null){
			f.getJobThirdFilterList().clear();
		}
		
		LOG.debug("Retrieving  jobThird ");
		JobThirdPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("jobThirdAscending");
			String oldField=(String)request.getSession().getAttribute("jobThirdField");
			if (oldField!=null && request.getSession().getAttribute("jobThirdAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("jobThirdAscending", direction);
			}
			else
				request.getSession().setAttribute("jobThirdAscending", true);
			request.getSession().setAttribute("jobThirdField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("jobThirdField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = jobThirdService.requestJobThirdPage(new RequestJobThirdPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("jobThirdAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("jobThirdAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("jobThirdField"):field));
			}
			details = jobThirdService.requestJobThirdPage(new RequestJobThirdPageEvent(pageRequest));
		}
		
		PageWrapper<JobThird> page=new PageWrapper<JobThird>(details.getJobThirdPage(),"/maintenance/jobThird/searchJobThird");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("jobThirdField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/jobThird/searchJobThird";
	}
	
	private Boolean hasActions(JobThirdFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("jobThirdFilterInfo")JobThirdFilterInfo filter, HttpServletRequest request) throws Exception{
		JobThirdFilterInfo f=(JobThirdFilterInfo) request.getSession().getAttribute("jobThirdFilter");
		if (f!=null && f.getJobThirdFilterList().size()!=0)
			filter.getJobThirdFilterList().addAll(f.getJobThirdFilterList());
		if (filter.getAaddFilter())
			filter.getJobThirdFilterList().add((JobThirdFilterInfo) (request.getSession().getAttribute("jobThirdFilterCopy")!=null?request.getSession().getAttribute("jobThirdFilterCopy"):new JobThirdFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("jobThirdAscending");
			String oldField=(String)request.getSession().getAttribute("jobThirdField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("jobThirdAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("jobThirdAscending", direction);
			}
			else if (request.getSession().getAttribute("jobThirdAscending")==null)
				request.getSession().setAttribute("jobThirdAscending", true);
			request.getSession().setAttribute("jobThirdField", filter.getSelectedFilterField());
		}
		
		RequestJobThirdPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("jobThirdField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestJobThirdPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("jobThirdAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("jobThirdAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("jobThirdField"):filter.getSelectedFilterField()));
				pageEvent = new RequestJobThirdPageEvent(pageRequest,filter);			}
		}
		
		JobThirdPageEvent details = jobThirdService.requestJobThirdFilterPage(pageEvent);
		PageWrapper<JobThird> page=new PageWrapper<JobThird>(details.getJobThirdPage(),"/maintenance/jobThird/searchJobThird");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("jobThirdField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("jobThirdFilter", filter);
    	request.getSession().setAttribute("jobThirdFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/jobThird/searchJobThird";
	}
	
	@ModelAttribute("jobThirdFilterInfo")
	private JobThirdFilterInfo getJobThirdFilterInfo(){
		return new JobThirdFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}