package co.com.cybersoft.maintenance.tables.web.controller.costCentersCustomers;

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

import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;

/**
 * Search controller class for costCentersCustomers
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/costCentersCustomers/searchCostCentersCustomers")
public class CostCentersCustomersSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CostCentersCustomersSearchController.class);

	@Autowired
	private CostCentersCustomersService costCentersCustomersService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CostCentersCustomersFilterInfo f=(CostCentersCustomersFilterInfo) request.getSession().getAttribute("costCentersCustomersFilter");
		if (f!=null){
			f.getCostCentersCustomersFilterList().clear();
		}
		
		LOG.debug("Retrieving  costCentersCustomers ");
		CostCentersCustomersPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("costCentersCustomersAscending");
			String oldField=(String)request.getSession().getAttribute("costCentersCustomersField");
			if (oldField!=null && request.getSession().getAttribute("costCentersCustomersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("costCentersCustomersAscending", direction);
			}
			else
				request.getSession().setAttribute("costCentersCustomersAscending", true);
			request.getSession().setAttribute("costCentersCustomersField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("costCentersCustomersField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = costCentersCustomersService.requestCostCentersCustomersPage(new RequestCostCentersCustomersPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("costCentersCustomersAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("costCentersCustomersAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("costCentersCustomersField"):field));
			}
			details = costCentersCustomersService.requestCostCentersCustomersPage(new RequestCostCentersCustomersPageEvent(pageRequest));
		}
		
		PageWrapper<CostCentersCustomers> page=new PageWrapper<CostCentersCustomers>(details.getCostCentersCustomersPage(),"/maintenance/costCentersCustomers/searchCostCentersCustomers");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("costCentersCustomersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/costCentersCustomers/searchCostCentersCustomers";
	}
	
	private Boolean hasActions(CostCentersCustomersFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("costCentersCustomersFilterInfo")CostCentersCustomersFilterInfo filter, HttpServletRequest request) throws Exception{
		CostCentersCustomersFilterInfo f=(CostCentersCustomersFilterInfo) request.getSession().getAttribute("costCentersCustomersFilter");
		if (f!=null && f.getCostCentersCustomersFilterList().size()!=0)
			filter.getCostCentersCustomersFilterList().addAll(f.getCostCentersCustomersFilterList());
		if (filter.getAaddFilter())
			filter.getCostCentersCustomersFilterList().add((CostCentersCustomersFilterInfo) (request.getSession().getAttribute("costCentersCustomersFilterCopy")!=null?request.getSession().getAttribute("costCentersCustomersFilterCopy"):new CostCentersCustomersFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("costCentersCustomersAscending");
			String oldField=(String)request.getSession().getAttribute("costCentersCustomersField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("costCentersCustomersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("costCentersCustomersAscending", direction);
			}
			else if (request.getSession().getAttribute("costCentersCustomersAscending")==null)
				request.getSession().setAttribute("costCentersCustomersAscending", true);
			request.getSession().setAttribute("costCentersCustomersField", filter.getSelectedFilterField());
		}
		
		RequestCostCentersCustomersPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("costCentersCustomersField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCostCentersCustomersPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("costCentersCustomersAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("costCentersCustomersAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("costCentersCustomersField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCostCentersCustomersPageEvent(pageRequest,filter);			}
		}
		
		CostCentersCustomersPageEvent details = costCentersCustomersService.requestCostCentersCustomersFilterPage(pageEvent);
		PageWrapper<CostCentersCustomers> page=new PageWrapper<CostCentersCustomers>(details.getCostCentersCustomersPage(),"/maintenance/costCentersCustomers/searchCostCentersCustomers");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("costCentersCustomersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("costCentersCustomersFilter", filter);
    	request.getSession().setAttribute("costCentersCustomersFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/costCentersCustomers/searchCostCentersCustomers";
	}
	
	@ModelAttribute("costCentersCustomersFilterInfo")
	private CostCentersCustomersFilterInfo getCostCentersCustomersFilterInfo(){
		return new CostCentersCustomersFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}