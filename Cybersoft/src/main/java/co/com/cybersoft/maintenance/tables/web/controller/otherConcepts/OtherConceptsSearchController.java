package co.com.cybersoft.maintenance.tables.web.controller.otherConcepts;

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

import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.OtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.events.otherConcepts.RequestOtherConceptsPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;

/**
 * Search controller class for otherConcepts
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/otherConcepts/searchOtherConcepts")
public class OtherConceptsSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OtherConceptsSearchController.class);

	@Autowired
	private OtherConceptsService otherConceptsService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		OtherConceptsFilterInfo f=(OtherConceptsFilterInfo) request.getSession().getAttribute("otherConceptsFilter");
		if (f!=null){
			f.getOtherConceptsFilterList().clear();
		}
		
		LOG.debug("Retrieving  otherConcepts ");
		OtherConceptsPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("otherConceptsAscending");
			String oldField=(String)request.getSession().getAttribute("otherConceptsField");
			if (oldField!=null && request.getSession().getAttribute("otherConceptsAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("otherConceptsAscending", direction);
			}
			else
				request.getSession().setAttribute("otherConceptsAscending", true);
			request.getSession().setAttribute("otherConceptsField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("otherConceptsField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = otherConceptsService.requestOtherConceptsPage(new RequestOtherConceptsPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("otherConceptsAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("otherConceptsAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("otherConceptsField"):field));
			}
			details = otherConceptsService.requestOtherConceptsPage(new RequestOtherConceptsPageEvent(pageRequest));
		}
		
		PageWrapper<OtherConcepts> page=new PageWrapper<OtherConcepts>(details.getOtherConceptsPage(),"/maintenance/otherConcepts/searchOtherConcepts");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("otherConceptsField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/otherConcepts/searchOtherConcepts";
	}
	
	private Boolean hasActions(OtherConceptsFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("otherConceptsFilterInfo")OtherConceptsFilterInfo filter, HttpServletRequest request) throws Exception{
		OtherConceptsFilterInfo f=(OtherConceptsFilterInfo) request.getSession().getAttribute("otherConceptsFilter");
		if (f!=null && f.getOtherConceptsFilterList().size()!=0)
			filter.getOtherConceptsFilterList().addAll(f.getOtherConceptsFilterList());
		if (filter.getAaddFilter())
			filter.getOtherConceptsFilterList().add((OtherConceptsFilterInfo) (request.getSession().getAttribute("otherConceptsFilterCopy")!=null?request.getSession().getAttribute("otherConceptsFilterCopy"):new OtherConceptsFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("otherConceptsAscending");
			String oldField=(String)request.getSession().getAttribute("otherConceptsField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("otherConceptsAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("otherConceptsAscending", direction);
			}
			else if (request.getSession().getAttribute("otherConceptsAscending")==null)
				request.getSession().setAttribute("otherConceptsAscending", true);
			request.getSession().setAttribute("otherConceptsField", filter.getSelectedFilterField());
		}
		
		RequestOtherConceptsPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("otherConceptsField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestOtherConceptsPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("otherConceptsAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("otherConceptsAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("otherConceptsField"):filter.getSelectedFilterField()));
				pageEvent = new RequestOtherConceptsPageEvent(pageRequest,filter);			}
		}
		
		OtherConceptsPageEvent details = otherConceptsService.requestOtherConceptsFilterPage(pageEvent);
		PageWrapper<OtherConcepts> page=new PageWrapper<OtherConcepts>(details.getOtherConceptsPage(),"/maintenance/otherConcepts/searchOtherConcepts");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("otherConceptsField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("otherConceptsFilter", filter);
    	request.getSession().setAttribute("otherConceptsFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/otherConcepts/searchOtherConcepts";
	}
	
	@ModelAttribute("otherConceptsFilterInfo")
	private OtherConceptsFilterInfo getOtherConceptsFilterInfo(){
		return new OtherConceptsFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}