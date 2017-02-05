package co.com.cybersoft.maintenance.tables.web.controller.authorizerCostCenter;

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

import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterFilterInfo;

/**
 * Search controller class for authorizerCostCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/authorizerCostCenter/searchAuthorizerCostCenter")
public class AuthorizerCostCenterSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorizerCostCenterSearchController.class);

	@Autowired
	private AuthorizerCostCenterService authorizerCostCenterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		AuthorizerCostCenterFilterInfo f=(AuthorizerCostCenterFilterInfo) request.getSession().getAttribute("authorizerCostCenterFilter");
		if (f!=null){
			f.getAuthorizerCostCenterFilterList().clear();
		}
		
		LOG.debug("Retrieving  authorizerCostCenter ");
		AuthorizerCostCenterPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("authorizerCostCenterAscending");
			String oldField=(String)request.getSession().getAttribute("authorizerCostCenterField");
			if (oldField!=null && request.getSession().getAttribute("authorizerCostCenterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("authorizerCostCenterAscending", direction);
			}
			else
				request.getSession().setAttribute("authorizerCostCenterAscending", true);
			request.getSession().setAttribute("authorizerCostCenterField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("authorizerCostCenterField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = authorizerCostCenterService.requestAuthorizerCostCenterPage(new RequestAuthorizerCostCenterPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("authorizerCostCenterAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("authorizerCostCenterAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("authorizerCostCenterField"):field));
			}
			details = authorizerCostCenterService.requestAuthorizerCostCenterPage(new RequestAuthorizerCostCenterPageEvent(pageRequest));
		}
		
		PageWrapper<AuthorizerCostCenter> page=new PageWrapper<AuthorizerCostCenter>(details.getAuthorizerCostCenterPage(),"/maintenance/authorizerCostCenter/searchAuthorizerCostCenter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("authorizerCostCenterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/authorizerCostCenter/searchAuthorizerCostCenter";
	}
	
	private Boolean hasActions(AuthorizerCostCenterFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("authorizerCostCenterFilterInfo")AuthorizerCostCenterFilterInfo filter, HttpServletRequest request) throws Exception{
		AuthorizerCostCenterFilterInfo f=(AuthorizerCostCenterFilterInfo) request.getSession().getAttribute("authorizerCostCenterFilter");
		if (f!=null && f.getAuthorizerCostCenterFilterList().size()!=0)
			filter.getAuthorizerCostCenterFilterList().addAll(f.getAuthorizerCostCenterFilterList());
		if (filter.getAaddFilter())
			filter.getAuthorizerCostCenterFilterList().add((AuthorizerCostCenterFilterInfo) (request.getSession().getAttribute("authorizerCostCenterFilterCopy")!=null?request.getSession().getAttribute("authorizerCostCenterFilterCopy"):new AuthorizerCostCenterFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("authorizerCostCenterAscending");
			String oldField=(String)request.getSession().getAttribute("authorizerCostCenterField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("authorizerCostCenterAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("authorizerCostCenterAscending", direction);
			}
			else if (request.getSession().getAttribute("authorizerCostCenterAscending")==null)
				request.getSession().setAttribute("authorizerCostCenterAscending", true);
			request.getSession().setAttribute("authorizerCostCenterField", filter.getSelectedFilterField());
		}
		
		RequestAuthorizerCostCenterPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("authorizerCostCenterField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestAuthorizerCostCenterPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("authorizerCostCenterAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("authorizerCostCenterAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("authorizerCostCenterField"):filter.getSelectedFilterField()));
				pageEvent = new RequestAuthorizerCostCenterPageEvent(pageRequest,filter);			}
		}
		
		AuthorizerCostCenterPageEvent details = authorizerCostCenterService.requestAuthorizerCostCenterFilterPage(pageEvent);
		PageWrapper<AuthorizerCostCenter> page=new PageWrapper<AuthorizerCostCenter>(details.getAuthorizerCostCenterPage(),"/maintenance/authorizerCostCenter/searchAuthorizerCostCenter");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("authorizerCostCenterField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("authorizerCostCenterFilter", filter);
    	request.getSession().setAttribute("authorizerCostCenterFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/authorizerCostCenter/searchAuthorizerCostCenter";
	}
	
	@ModelAttribute("authorizerCostCenterFilterInfo")
	private AuthorizerCostCenterFilterInfo getAuthorizerCostCenterFilterInfo(){
		return new AuthorizerCostCenterFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}