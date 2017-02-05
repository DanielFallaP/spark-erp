package co.com.cybersoft.maintenance.tables.web.controller.warehouse;

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

import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehousePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseFilterInfo;

/**
 * Search controller class for warehouse
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/warehouse/searchWarehouse")
public class WarehouseSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WarehouseSearchController.class);

	@Autowired
	private WarehouseService warehouseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		WarehouseFilterInfo f=(WarehouseFilterInfo) request.getSession().getAttribute("warehouseFilter");
		if (f!=null){
			f.getWarehouseFilterList().clear();
		}
		
		LOG.debug("Retrieving  warehouse ");
		WarehousePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("warehouseAscending");
			String oldField=(String)request.getSession().getAttribute("warehouseField");
			if (oldField!=null && request.getSession().getAttribute("warehouseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("warehouseAscending", direction);
			}
			else
				request.getSession().setAttribute("warehouseAscending", true);
			request.getSession().setAttribute("warehouseField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("warehouseField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = warehouseService.requestWarehousePage(new RequestWarehousePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("warehouseAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("warehouseAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("warehouseField"):field));
			}
			details = warehouseService.requestWarehousePage(new RequestWarehousePageEvent(pageRequest));
		}
		
		PageWrapper<Warehouse> page=new PageWrapper<Warehouse>(details.getWarehousePage(),"/maintenance/warehouse/searchWarehouse");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("warehouseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/warehouse/searchWarehouse";
	}
	
	private Boolean hasActions(WarehouseFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("warehouseFilterInfo")WarehouseFilterInfo filter, HttpServletRequest request) throws Exception{
		WarehouseFilterInfo f=(WarehouseFilterInfo) request.getSession().getAttribute("warehouseFilter");
		if (f!=null && f.getWarehouseFilterList().size()!=0)
			filter.getWarehouseFilterList().addAll(f.getWarehouseFilterList());
		if (filter.getAaddFilter())
			filter.getWarehouseFilterList().add((WarehouseFilterInfo) (request.getSession().getAttribute("warehouseFilterCopy")!=null?request.getSession().getAttribute("warehouseFilterCopy"):new WarehouseFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("warehouseAscending");
			String oldField=(String)request.getSession().getAttribute("warehouseField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("warehouseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("warehouseAscending", direction);
			}
			else if (request.getSession().getAttribute("warehouseAscending")==null)
				request.getSession().setAttribute("warehouseAscending", true);
			request.getSession().setAttribute("warehouseField", filter.getSelectedFilterField());
		}
		
		RequestWarehousePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("warehouseField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestWarehousePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("warehouseAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("warehouseAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("warehouseField"):filter.getSelectedFilterField()));
				pageEvent = new RequestWarehousePageEvent(pageRequest,filter);			}
		}
		
		WarehousePageEvent details = warehouseService.requestWarehouseFilterPage(pageEvent);
		PageWrapper<Warehouse> page=new PageWrapper<Warehouse>(details.getWarehousePage(),"/maintenance/warehouse/searchWarehouse");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("warehouseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("warehouseFilter", filter);
    	request.getSession().setAttribute("warehouseFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/warehouse/searchWarehouse";
	}
	
	@ModelAttribute("warehouseFilterInfo")
	private WarehouseFilterInfo getWarehouseFilterInfo(){
		return new WarehouseFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}