package co.com.cybersoft.maintenance.tables.web.controller.costingType;

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

import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeFilterInfo;

/**
 * Search controller class for costingType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/costingType/searchCostingType")
public class CostingTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CostingTypeSearchController.class);

	@Autowired
	private CostingTypeService costingTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CostingTypeFilterInfo f=(CostingTypeFilterInfo) request.getSession().getAttribute("costingTypeFilter");
		if (f!=null){
			f.getCostingTypeFilterList().clear();
		}
		
		LOG.debug("Retrieving  costingType ");
		CostingTypePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("costingTypeAscending");
			String oldField=(String)request.getSession().getAttribute("costingTypeField");
			if (oldField!=null && request.getSession().getAttribute("costingTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("costingTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("costingTypeAscending", true);
			request.getSession().setAttribute("costingTypeField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("costingTypeField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = costingTypeService.requestCostingTypePage(new RequestCostingTypePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("costingTypeAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("costingTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("costingTypeField"):field));
			}
			details = costingTypeService.requestCostingTypePage(new RequestCostingTypePageEvent(pageRequest));
		}
		
		PageWrapper<CostingType> page=new PageWrapper<CostingType>(details.getCostingTypePage(),"/maintenance/costingType/searchCostingType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("costingTypeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/costingType/searchCostingType";
	}
	
	private Boolean hasActions(CostingTypeFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("costingTypeFilterInfo")CostingTypeFilterInfo filter, HttpServletRequest request) throws Exception{
		CostingTypeFilterInfo f=(CostingTypeFilterInfo) request.getSession().getAttribute("costingTypeFilter");
		if (f!=null && f.getCostingTypeFilterList().size()!=0)
			filter.getCostingTypeFilterList().addAll(f.getCostingTypeFilterList());
		if (filter.getAaddFilter())
			filter.getCostingTypeFilterList().add((CostingTypeFilterInfo) (request.getSession().getAttribute("costingTypeFilterCopy")!=null?request.getSession().getAttribute("costingTypeFilterCopy"):new CostingTypeFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("costingTypeAscending");
			String oldField=(String)request.getSession().getAttribute("costingTypeField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("costingTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("costingTypeAscending", direction);
			}
			else if (request.getSession().getAttribute("costingTypeAscending")==null)
				request.getSession().setAttribute("costingTypeAscending", true);
			request.getSession().setAttribute("costingTypeField", filter.getSelectedFilterField());
		}
		
		RequestCostingTypePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("costingTypeField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCostingTypePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("costingTypeAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("costingTypeAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("costingTypeField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCostingTypePageEvent(pageRequest,filter);			}
		}
		
		CostingTypePageEvent details = costingTypeService.requestCostingTypeFilterPage(pageEvent);
		PageWrapper<CostingType> page=new PageWrapper<CostingType>(details.getCostingTypePage(),"/maintenance/costingType/searchCostingType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("costingTypeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("costingTypeFilter", filter);
    	request.getSession().setAttribute("costingTypeFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/costingType/searchCostingType";
	}
	
	@ModelAttribute("costingTypeFilterInfo")
	private CostingTypeFilterInfo getCostingTypeFilterInfo(){
		return new CostingTypeFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}