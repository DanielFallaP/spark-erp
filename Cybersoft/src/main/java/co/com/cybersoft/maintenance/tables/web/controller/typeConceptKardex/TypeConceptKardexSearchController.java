package co.com.cybersoft.maintenance.tables.web.controller.typeConceptKardex;

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

import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexFilterInfo;

/**
 * Search controller class for typeConceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeConceptKardex/searchTypeConceptKardex")
public class TypeConceptKardexSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TypeConceptKardexSearchController.class);

	@Autowired
	private TypeConceptKardexService typeConceptKardexService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TypeConceptKardexFilterInfo f=(TypeConceptKardexFilterInfo) request.getSession().getAttribute("typeConceptKardexFilter");
		if (f!=null){
			f.getTypeConceptKardexFilterList().clear();
		}
		
		LOG.debug("Retrieving  typeConceptKardex ");
		TypeConceptKardexPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("typeConceptKardexAscending");
			String oldField=(String)request.getSession().getAttribute("typeConceptKardexField");
			if (oldField!=null && request.getSession().getAttribute("typeConceptKardexAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeConceptKardexAscending", direction);
			}
			else
				request.getSession().setAttribute("typeConceptKardexAscending", true);
			request.getSession().setAttribute("typeConceptKardexField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("typeConceptKardexField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = typeConceptKardexService.requestTypeConceptKardexPage(new RequestTypeConceptKardexPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("typeConceptKardexAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("typeConceptKardexAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("typeConceptKardexField"):field));
			}
			details = typeConceptKardexService.requestTypeConceptKardexPage(new RequestTypeConceptKardexPageEvent(pageRequest));
		}
		
		PageWrapper<TypeConceptKardex> page=new PageWrapper<TypeConceptKardex>(details.getTypeConceptKardexPage(),"/maintenance/typeConceptKardex/searchTypeConceptKardex");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeConceptKardexField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/typeConceptKardex/searchTypeConceptKardex";
	}
	
	private Boolean hasActions(TypeConceptKardexFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("typeConceptKardexFilterInfo")TypeConceptKardexFilterInfo filter, HttpServletRequest request) throws Exception{
		TypeConceptKardexFilterInfo f=(TypeConceptKardexFilterInfo) request.getSession().getAttribute("typeConceptKardexFilter");
		if (f!=null && f.getTypeConceptKardexFilterList().size()!=0)
			filter.getTypeConceptKardexFilterList().addAll(f.getTypeConceptKardexFilterList());
		if (filter.getAaddFilter())
			filter.getTypeConceptKardexFilterList().add((TypeConceptKardexFilterInfo) (request.getSession().getAttribute("typeConceptKardexFilterCopy")!=null?request.getSession().getAttribute("typeConceptKardexFilterCopy"):new TypeConceptKardexFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("typeConceptKardexAscending");
			String oldField=(String)request.getSession().getAttribute("typeConceptKardexField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("typeConceptKardexAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeConceptKardexAscending", direction);
			}
			else if (request.getSession().getAttribute("typeConceptKardexAscending")==null)
				request.getSession().setAttribute("typeConceptKardexAscending", true);
			request.getSession().setAttribute("typeConceptKardexField", filter.getSelectedFilterField());
		}
		
		RequestTypeConceptKardexPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("typeConceptKardexField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTypeConceptKardexPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("typeConceptKardexAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("typeConceptKardexAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("typeConceptKardexField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTypeConceptKardexPageEvent(pageRequest,filter);			}
		}
		
		TypeConceptKardexPageEvent details = typeConceptKardexService.requestTypeConceptKardexFilterPage(pageEvent);
		PageWrapper<TypeConceptKardex> page=new PageWrapper<TypeConceptKardex>(details.getTypeConceptKardexPage(),"/maintenance/typeConceptKardex/searchTypeConceptKardex");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeConceptKardexField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("typeConceptKardexFilter", filter);
    	request.getSession().setAttribute("typeConceptKardexFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/typeConceptKardex/searchTypeConceptKardex";
	}
	
	@ModelAttribute("typeConceptKardexFilterInfo")
	private TypeConceptKardexFilterInfo getTypeConceptKardexFilterInfo(){
		return new TypeConceptKardexFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}