package co.com.cybersoft.maintenance.tables.web.controller.storeType;

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

import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.storeType.RequestStoreTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeFilterInfo;

/**
 * Search controller class for storeType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/storeType/searchStoreType")
public class StoreTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StoreTypeSearchController.class);

	@Autowired
	private StoreTypeService storeTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		StoreTypeFilterInfo f=(StoreTypeFilterInfo) request.getSession().getAttribute("storeTypeFilter");
		if (f!=null){
			f.getStoreTypeFilterList().clear();
		}
		
		LOG.debug("Retrieving  storeType ");
		StoreTypePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("storeTypeAscending");
			String oldField=(String)request.getSession().getAttribute("storeTypeField");
			if (oldField!=null && request.getSession().getAttribute("storeTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("storeTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("storeTypeAscending", true);
			request.getSession().setAttribute("storeTypeField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("storeTypeField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = storeTypeService.requestStoreTypePage(new RequestStoreTypePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("storeTypeAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("storeTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("storeTypeField"):field));
			}
			details = storeTypeService.requestStoreTypePage(new RequestStoreTypePageEvent(pageRequest));
		}
		
		PageWrapper<StoreType> page=new PageWrapper<StoreType>(details.getStoreTypePage(),"/maintenance/storeType/searchStoreType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("storeTypeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/storeType/searchStoreType";
	}
	
	private Boolean hasActions(StoreTypeFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("storeTypeFilterInfo")StoreTypeFilterInfo filter, HttpServletRequest request) throws Exception{
		StoreTypeFilterInfo f=(StoreTypeFilterInfo) request.getSession().getAttribute("storeTypeFilter");
		if (f!=null && f.getStoreTypeFilterList().size()!=0)
			filter.getStoreTypeFilterList().addAll(f.getStoreTypeFilterList());
		if (filter.getAaddFilter())
			filter.getStoreTypeFilterList().add((StoreTypeFilterInfo) (request.getSession().getAttribute("storeTypeFilterCopy")!=null?request.getSession().getAttribute("storeTypeFilterCopy"):new StoreTypeFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("storeTypeAscending");
			String oldField=(String)request.getSession().getAttribute("storeTypeField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("storeTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("storeTypeAscending", direction);
			}
			else if (request.getSession().getAttribute("storeTypeAscending")==null)
				request.getSession().setAttribute("storeTypeAscending", true);
			request.getSession().setAttribute("storeTypeField", filter.getSelectedFilterField());
		}
		
		RequestStoreTypePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("storeTypeField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestStoreTypePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("storeTypeAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("storeTypeAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("storeTypeField"):filter.getSelectedFilterField()));
				pageEvent = new RequestStoreTypePageEvent(pageRequest,filter);			}
		}
		
		StoreTypePageEvent details = storeTypeService.requestStoreTypeFilterPage(pageEvent);
		PageWrapper<StoreType> page=new PageWrapper<StoreType>(details.getStoreTypePage(),"/maintenance/storeType/searchStoreType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("storeTypeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("storeTypeFilter", filter);
    	request.getSession().setAttribute("storeTypeFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/storeType/searchStoreType";
	}
	
	@ModelAttribute("storeTypeFilterInfo")
	private StoreTypeFilterInfo getStoreTypeFilterInfo(){
		return new StoreTypeFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}