package co.com.cybersoft.purchase.tables.web.controller.users;

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

import co.com.cybersoft.purchase.tables.core.services.users.UsersService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.users.UsersPageEvent;
import co.com.cybersoft.purchase.tables.events.users.RequestUsersPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersFilterInfo;

/**
 * Search controller class for users
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/users/searchUsers")
public class UsersSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsersSearchController.class);

	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		UsersFilterInfo f=(UsersFilterInfo) request.getSession().getAttribute("usersFilter");
		if (f!=null){
			f.getUsersFilterList().clear();
		}
		
		LOG.debug("Retrieving  users ");
		UsersPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("usersAscending");
			String oldField=(String)request.getSession().getAttribute("usersField");
			if (oldField!=null && request.getSession().getAttribute("usersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("usersAscending", direction);
			}
			else
				request.getSession().setAttribute("usersAscending", true);
			request.getSession().setAttribute("usersField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("usersField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = usersService.requestUsersPage(new RequestUsersPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("usersAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("usersAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("usersField"):field));
			}
			details = usersService.requestUsersPage(new RequestUsersPageEvent(pageRequest));
		}
		
		PageWrapper<Users> page=new PageWrapper<Users>(details.getUsersPage(),"/purchase/users/searchUsers");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("usersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/purchase/users/searchUsers";
	}
	
	private Boolean hasActions(UsersFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("usersFilterInfo")UsersFilterInfo filter, HttpServletRequest request) throws Exception{
		UsersFilterInfo f=(UsersFilterInfo) request.getSession().getAttribute("usersFilter");
		if (f!=null && f.getUsersFilterList().size()!=0)
			filter.getUsersFilterList().addAll(f.getUsersFilterList());
		if (filter.getAaddFilter())
			filter.getUsersFilterList().add((UsersFilterInfo) (request.getSession().getAttribute("usersFilterCopy")!=null?request.getSession().getAttribute("usersFilterCopy"):new UsersFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("usersAscending");
			String oldField=(String)request.getSession().getAttribute("usersField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("usersAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("usersAscending", direction);
			}
			else if (request.getSession().getAttribute("usersAscending")==null)
				request.getSession().setAttribute("usersAscending", true);
			request.getSession().setAttribute("usersField", filter.getSelectedFilterField());
		}
		
		RequestUsersPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("usersField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestUsersPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("usersAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("usersAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("usersField"):filter.getSelectedFilterField()));
				pageEvent = new RequestUsersPageEvent(pageRequest,filter);			}
		}
		
		UsersPageEvent details = usersService.requestUsersFilterPage(pageEvent);
		PageWrapper<Users> page=new PageWrapper<Users>(details.getUsersPage(),"/purchase/users/searchUsers");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("usersField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("usersFilter", filter);
    	request.getSession().setAttribute("usersFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/users/searchUsers";
	}
	
	@ModelAttribute("usersFilterInfo")
	private UsersFilterInfo getUsersFilterInfo(){
		return new UsersFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}