package co.com.cybersoft.maintenance.tables.web.controller.operation;

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

import co.com.cybersoft.maintenance.tables.core.services.operation.OperationService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.operation.OperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.operation.RequestOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationFilterInfo;

/**
 * Search controller class for operation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/operation/searchOperation")
public class OperationSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OperationSearchController.class);

	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		OperationFilterInfo f=(OperationFilterInfo) request.getSession().getAttribute("operationFilter");
		if (f!=null){
			f.getOperationFilterList().clear();
		}
		
		LOG.debug("Retrieving  operation ");
		OperationPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("operationAscending");
			String oldField=(String)request.getSession().getAttribute("operationField");
			if (oldField!=null && request.getSession().getAttribute("operationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("operationAscending", direction);
			}
			else
				request.getSession().setAttribute("operationAscending", true);
			request.getSession().setAttribute("operationField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("operationField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = operationService.requestOperationPage(new RequestOperationPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("operationAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("operationAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("operationField"):field));
			}
			details = operationService.requestOperationPage(new RequestOperationPageEvent(pageRequest));
		}
		
		PageWrapper<Operation> page=new PageWrapper<Operation>(details.getOperationPage(),"/maintenance/operation/searchOperation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("operationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/operation/searchOperation";
	}
	
	private Boolean hasActions(OperationFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("operationFilterInfo")OperationFilterInfo filter, HttpServletRequest request) throws Exception{
		OperationFilterInfo f=(OperationFilterInfo) request.getSession().getAttribute("operationFilter");
		if (f!=null && f.getOperationFilterList().size()!=0)
			filter.getOperationFilterList().addAll(f.getOperationFilterList());
		if (filter.getAaddFilter())
			filter.getOperationFilterList().add((OperationFilterInfo) (request.getSession().getAttribute("operationFilterCopy")!=null?request.getSession().getAttribute("operationFilterCopy"):new OperationFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("operationAscending");
			String oldField=(String)request.getSession().getAttribute("operationField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("operationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("operationAscending", direction);
			}
			else if (request.getSession().getAttribute("operationAscending")==null)
				request.getSession().setAttribute("operationAscending", true);
			request.getSession().setAttribute("operationField", filter.getSelectedFilterField());
		}
		
		RequestOperationPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("operationField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestOperationPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("operationAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("operationAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("operationField"):filter.getSelectedFilterField()));
				pageEvent = new RequestOperationPageEvent(pageRequest,filter);			}
		}
		
		OperationPageEvent details = operationService.requestOperationFilterPage(pageEvent);
		PageWrapper<Operation> page=new PageWrapper<Operation>(details.getOperationPage(),"/maintenance/operation/searchOperation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("operationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("operationFilter", filter);
    	request.getSession().setAttribute("operationFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/operation/searchOperation";
	}
	
	@ModelAttribute("operationFilterInfo")
	private OperationFilterInfo getOperationFilterInfo(){
		return new OperationFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}