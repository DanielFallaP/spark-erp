package co.com.cybersoft.maintenance.tables.web.controller.reference;

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

import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.reference.ReferencePageEvent;
import co.com.cybersoft.maintenance.tables.events.reference.RequestReferencePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceFilterInfo;

/**
 * Search controller class for reference
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/reference/searchReference")
public class ReferenceSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReferenceSearchController.class);

	@Autowired
	private ReferenceService referenceService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ReferenceFilterInfo f=(ReferenceFilterInfo) request.getSession().getAttribute("referenceFilter");
		if (f!=null){
			f.getReferenceFilterList().clear();
		}
		
		LOG.debug("Retrieving  reference ");
		ReferencePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("referenceAscending");
			String oldField=(String)request.getSession().getAttribute("referenceField");
			if (oldField!=null && request.getSession().getAttribute("referenceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("referenceAscending", direction);
			}
			else
				request.getSession().setAttribute("referenceAscending", true);
			request.getSession().setAttribute("referenceField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("referenceField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = referenceService.requestReferencePage(new RequestReferencePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("referenceAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("referenceAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("referenceField"):field));
			}
			details = referenceService.requestReferencePage(new RequestReferencePageEvent(pageRequest));
		}
		
		PageWrapper<Reference> page=new PageWrapper<Reference>(details.getReferencePage(),"/maintenance/reference/searchReference");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("referenceField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/reference/searchReference";
	}
	
	private Boolean hasActions(ReferenceFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("referenceFilterInfo")ReferenceFilterInfo filter, HttpServletRequest request) throws Exception{
		ReferenceFilterInfo f=(ReferenceFilterInfo) request.getSession().getAttribute("referenceFilter");
		if (f!=null && f.getReferenceFilterList().size()!=0)
			filter.getReferenceFilterList().addAll(f.getReferenceFilterList());
		if (filter.getAaddFilter())
			filter.getReferenceFilterList().add((ReferenceFilterInfo) (request.getSession().getAttribute("referenceFilterCopy")!=null?request.getSession().getAttribute("referenceFilterCopy"):new ReferenceFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("referenceAscending");
			String oldField=(String)request.getSession().getAttribute("referenceField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("referenceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("referenceAscending", direction);
			}
			else if (request.getSession().getAttribute("referenceAscending")==null)
				request.getSession().setAttribute("referenceAscending", true);
			request.getSession().setAttribute("referenceField", filter.getSelectedFilterField());
		}
		
		RequestReferencePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("referenceField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestReferencePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("referenceAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("referenceAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("referenceField"):filter.getSelectedFilterField()));
				pageEvent = new RequestReferencePageEvent(pageRequest,filter);			}
		}
		
		ReferencePageEvent details = referenceService.requestReferenceFilterPage(pageEvent);
		PageWrapper<Reference> page=new PageWrapper<Reference>(details.getReferencePage(),"/maintenance/reference/searchReference");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("referenceField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("referenceFilter", filter);
    	request.getSession().setAttribute("referenceFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/reference/searchReference";
	}
	
	@ModelAttribute("referenceFilterInfo")
	private ReferenceFilterInfo getReferenceFilterInfo(){
		return new ReferenceFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}