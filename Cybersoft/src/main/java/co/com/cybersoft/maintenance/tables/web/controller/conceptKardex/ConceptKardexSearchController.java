package co.com.cybersoft.maintenance.tables.web.controller.conceptKardex;

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

import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;

/**
 * Search controller class for conceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/conceptKardex/searchConceptKardex")
public class ConceptKardexSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConceptKardexSearchController.class);

	@Autowired
	private ConceptKardexService conceptKardexService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ConceptKardexFilterInfo f=(ConceptKardexFilterInfo) request.getSession().getAttribute("conceptKardexFilter");
		if (f!=null){
			f.getConceptKardexFilterList().clear();
		}
		
		LOG.debug("Retrieving  conceptKardex ");
		ConceptKardexPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("conceptKardexAscending");
			String oldField=(String)request.getSession().getAttribute("conceptKardexField");
			if (oldField!=null && request.getSession().getAttribute("conceptKardexAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("conceptKardexAscending", direction);
			}
			else
				request.getSession().setAttribute("conceptKardexAscending", true);
			request.getSession().setAttribute("conceptKardexField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("conceptKardexField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = conceptKardexService.requestConceptKardexPage(new RequestConceptKardexPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("conceptKardexAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("conceptKardexAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("conceptKardexField"):field));
			}
			details = conceptKardexService.requestConceptKardexPage(new RequestConceptKardexPageEvent(pageRequest));
		}
		
		PageWrapper<ConceptKardex> page=new PageWrapper<ConceptKardex>(details.getConceptKardexPage(),"/maintenance/conceptKardex/searchConceptKardex");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("conceptKardexField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/conceptKardex/searchConceptKardex";
	}
	
	private Boolean hasActions(ConceptKardexFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("conceptKardexFilterInfo")ConceptKardexFilterInfo filter, HttpServletRequest request) throws Exception{
		ConceptKardexFilterInfo f=(ConceptKardexFilterInfo) request.getSession().getAttribute("conceptKardexFilter");
		if (f!=null && f.getConceptKardexFilterList().size()!=0)
			filter.getConceptKardexFilterList().addAll(f.getConceptKardexFilterList());
		if (filter.getAaddFilter())
			filter.getConceptKardexFilterList().add((ConceptKardexFilterInfo) (request.getSession().getAttribute("conceptKardexFilterCopy")!=null?request.getSession().getAttribute("conceptKardexFilterCopy"):new ConceptKardexFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("conceptKardexAscending");
			String oldField=(String)request.getSession().getAttribute("conceptKardexField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("conceptKardexAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("conceptKardexAscending", direction);
			}
			else if (request.getSession().getAttribute("conceptKardexAscending")==null)
				request.getSession().setAttribute("conceptKardexAscending", true);
			request.getSession().setAttribute("conceptKardexField", filter.getSelectedFilterField());
		}
		
		RequestConceptKardexPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("conceptKardexField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestConceptKardexPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("conceptKardexAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("conceptKardexAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("conceptKardexField"):filter.getSelectedFilterField()));
				pageEvent = new RequestConceptKardexPageEvent(pageRequest,filter);			}
		}
		
		ConceptKardexPageEvent details = conceptKardexService.requestConceptKardexFilterPage(pageEvent);
		PageWrapper<ConceptKardex> page=new PageWrapper<ConceptKardex>(details.getConceptKardexPage(),"/maintenance/conceptKardex/searchConceptKardex");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("conceptKardexField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("conceptKardexFilter", filter);
    	request.getSession().setAttribute("conceptKardexFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/conceptKardex/searchConceptKardex";
	}
	
	@ModelAttribute("conceptKardexFilterInfo")
	private ConceptKardexFilterInfo getConceptKardexFilterInfo(){
		return new ConceptKardexFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}