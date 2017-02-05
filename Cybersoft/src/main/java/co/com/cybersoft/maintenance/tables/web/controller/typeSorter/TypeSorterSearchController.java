package co.com.cybersoft.maintenance.tables.web.controller.typeSorter;

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

import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterFilterInfo;

/**
 * Search controller class for typeSorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeSorter/searchTypeSorter")
public class TypeSorterSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TypeSorterSearchController.class);

	@Autowired
	private TypeSorterService typeSorterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TypeSorterFilterInfo f=(TypeSorterFilterInfo) request.getSession().getAttribute("typeSorterFilter");
		if (f!=null){
			f.getTypeSorterFilterList().clear();
		}
		
		LOG.debug("Retrieving  typeSorter ");
		TypeSorterPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("typeSorterAscending");
			String oldField=(String)request.getSession().getAttribute("typeSorterField");
			if (oldField!=null && request.getSession().getAttribute("typeSorterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeSorterAscending", direction);
			}
			else
				request.getSession().setAttribute("typeSorterAscending", true);
			request.getSession().setAttribute("typeSorterField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("typeSorterField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = typeSorterService.requestTypeSorterPage(new RequestTypeSorterPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("typeSorterAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("typeSorterAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("typeSorterField"):field));
			}
			details = typeSorterService.requestTypeSorterPage(new RequestTypeSorterPageEvent(pageRequest));
		}
		
		PageWrapper<TypeSorter> page=new PageWrapper<TypeSorter>(details.getTypeSorterPage(),"/maintenance/typeSorter/searchTypeSorter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeSorterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/typeSorter/searchTypeSorter";
	}
	
	private Boolean hasActions(TypeSorterFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("typeSorterFilterInfo")TypeSorterFilterInfo filter, HttpServletRequest request) throws Exception{
		TypeSorterFilterInfo f=(TypeSorterFilterInfo) request.getSession().getAttribute("typeSorterFilter");
		if (f!=null && f.getTypeSorterFilterList().size()!=0)
			filter.getTypeSorterFilterList().addAll(f.getTypeSorterFilterList());
		if (filter.getAaddFilter())
			filter.getTypeSorterFilterList().add((TypeSorterFilterInfo) (request.getSession().getAttribute("typeSorterFilterCopy")!=null?request.getSession().getAttribute("typeSorterFilterCopy"):new TypeSorterFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("typeSorterAscending");
			String oldField=(String)request.getSession().getAttribute("typeSorterField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("typeSorterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeSorterAscending", direction);
			}
			else if (request.getSession().getAttribute("typeSorterAscending")==null)
				request.getSession().setAttribute("typeSorterAscending", true);
			request.getSession().setAttribute("typeSorterField", filter.getSelectedFilterField());
		}
		
		RequestTypeSorterPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("typeSorterField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTypeSorterPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("typeSorterAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("typeSorterAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("typeSorterField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTypeSorterPageEvent(pageRequest,filter);			}
		}
		
		TypeSorterPageEvent details = typeSorterService.requestTypeSorterFilterPage(pageEvent);
		PageWrapper<TypeSorter> page=new PageWrapper<TypeSorter>(details.getTypeSorterPage(),"/maintenance/typeSorter/searchTypeSorter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeSorterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("typeSorterFilter", filter);
    	request.getSession().setAttribute("typeSorterFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/typeSorter/searchTypeSorter";
	}
	
	@ModelAttribute("typeSorterFilterInfo")
	private TypeSorterFilterInfo getTypeSorterFilterInfo(){
		return new TypeSorterFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}