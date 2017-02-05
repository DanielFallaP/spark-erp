package co.com.cybersoft.maintenance.tables.web.controller.job;

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

import co.com.cybersoft.maintenance.tables.core.services.job.JobService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;

/**
 * Search controller class for job
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/job/searchJob")
public class JobSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobSearchController.class);

	@Autowired
	private JobService jobService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		JobFilterInfo f=(JobFilterInfo) request.getSession().getAttribute("jobFilter");
		if (f!=null){
			f.getJobFilterList().clear();
		}
		
		LOG.debug("Retrieving  job ");
		JobPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("jobAscending");
			String oldField=(String)request.getSession().getAttribute("jobField");
			if (oldField!=null && request.getSession().getAttribute("jobAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("jobAscending", direction);
			}
			else
				request.getSession().setAttribute("jobAscending", true);
			request.getSession().setAttribute("jobField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("jobField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = jobService.requestJobPage(new RequestJobPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("jobAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("jobAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("jobField"):field));
			}
			details = jobService.requestJobPage(new RequestJobPageEvent(pageRequest));
		}
		
		PageWrapper<Job> page=new PageWrapper<Job>(details.getJobPage(),"/maintenance/job/searchJob");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("jobField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/job/searchJob";
	}
	
	private Boolean hasActions(JobFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("jobFilterInfo")JobFilterInfo filter, HttpServletRequest request) throws Exception{
		JobFilterInfo f=(JobFilterInfo) request.getSession().getAttribute("jobFilter");
		if (f!=null && f.getJobFilterList().size()!=0)
			filter.getJobFilterList().addAll(f.getJobFilterList());
		if (filter.getAaddFilter())
			filter.getJobFilterList().add((JobFilterInfo) (request.getSession().getAttribute("jobFilterCopy")!=null?request.getSession().getAttribute("jobFilterCopy"):new JobFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("jobAscending");
			String oldField=(String)request.getSession().getAttribute("jobField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("jobAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("jobAscending", direction);
			}
			else if (request.getSession().getAttribute("jobAscending")==null)
				request.getSession().setAttribute("jobAscending", true);
			request.getSession().setAttribute("jobField", filter.getSelectedFilterField());
		}
		
		RequestJobPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("jobField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestJobPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("jobAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("jobAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("jobField"):filter.getSelectedFilterField()));
				pageEvent = new RequestJobPageEvent(pageRequest,filter);			}
		}
		
		JobPageEvent details = jobService.requestJobFilterPage(pageEvent);
		PageWrapper<Job> page=new PageWrapper<Job>(details.getJobPage(),"/maintenance/job/searchJob");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("jobField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("jobFilter", filter);
    	request.getSession().setAttribute("jobFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/job/searchJob";
	}
	
	@ModelAttribute("jobFilterInfo")
	private JobFilterInfo getJobFilterInfo(){
		return new JobFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}