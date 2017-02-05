package co.com.cybersoft.maintenance.tables.web.controller.sorter;

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

import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterFilterInfo;

/**
 * Search controller class for sorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/sorter/searchSorter")
public class SorterSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SorterSearchController.class);

	@Autowired
	private SorterService sorterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		SorterFilterInfo f=(SorterFilterInfo) request.getSession().getAttribute("sorterFilter");
		if (f!=null){
			f.getSorterFilterList().clear();
		}
		
		LOG.debug("Retrieving  sorter ");
		SorterPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("sorterAscending");
			String oldField=(String)request.getSession().getAttribute("sorterField");
			if (oldField!=null && request.getSession().getAttribute("sorterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("sorterAscending", direction);
			}
			else
				request.getSession().setAttribute("sorterAscending", true);
			request.getSession().setAttribute("sorterField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("sorterField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = sorterService.requestSorterPage(new RequestSorterPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("sorterAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("sorterAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("sorterField"):field));
			}
			details = sorterService.requestSorterPage(new RequestSorterPageEvent(pageRequest));
		}
		
		PageWrapper<Sorter> page=new PageWrapper<Sorter>(details.getSorterPage(),"/maintenance/sorter/searchSorter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("sorterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/sorter/searchSorter";
	}
	
	private Boolean hasActions(SorterFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("sorterFilterInfo")SorterFilterInfo filter, HttpServletRequest request) throws Exception{
		SorterFilterInfo f=(SorterFilterInfo) request.getSession().getAttribute("sorterFilter");
		if (f!=null && f.getSorterFilterList().size()!=0)
			filter.getSorterFilterList().addAll(f.getSorterFilterList());
		if (filter.getAaddFilter())
			filter.getSorterFilterList().add((SorterFilterInfo) (request.getSession().getAttribute("sorterFilterCopy")!=null?request.getSession().getAttribute("sorterFilterCopy"):new SorterFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("sorterAscending");
			String oldField=(String)request.getSession().getAttribute("sorterField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("sorterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("sorterAscending", direction);
			}
			else if (request.getSession().getAttribute("sorterAscending")==null)
				request.getSession().setAttribute("sorterAscending", true);
			request.getSession().setAttribute("sorterField", filter.getSelectedFilterField());
		}
		
		RequestSorterPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("sorterField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestSorterPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("sorterAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("sorterAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("sorterField"):filter.getSelectedFilterField()));
				pageEvent = new RequestSorterPageEvent(pageRequest,filter);			}
		}
		
		SorterPageEvent details = sorterService.requestSorterFilterPage(pageEvent);
		PageWrapper<Sorter> page=new PageWrapper<Sorter>(details.getSorterPage(),"/maintenance/sorter/searchSorter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("sorterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("sorterFilter", filter);
    	request.getSession().setAttribute("sorterFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/sorter/searchSorter";
	}
	
	@ModelAttribute("sorterFilterInfo")
	private SorterFilterInfo getSorterFilterInfo(){
		return new SorterFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}