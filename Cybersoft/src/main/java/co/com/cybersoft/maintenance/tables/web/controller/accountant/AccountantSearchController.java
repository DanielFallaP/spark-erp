package co.com.cybersoft.maintenance.tables.web.controller.accountant;

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

import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.accountant.AccountantPageEvent;
import co.com.cybersoft.maintenance.tables.events.accountant.RequestAccountantPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantFilterInfo;

/**
 * Search controller class for accountant
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/accountant/searchAccountant")
public class AccountantSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountantSearchController.class);

	@Autowired
	private AccountantService accountantService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		AccountantFilterInfo f=(AccountantFilterInfo) request.getSession().getAttribute("accountantFilter");
		if (f!=null){
			f.getAccountantFilterList().clear();
		}
		
		LOG.debug("Retrieving  accountant ");
		AccountantPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("accountantAscending");
			String oldField=(String)request.getSession().getAttribute("accountantField");
			if (oldField!=null && request.getSession().getAttribute("accountantAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("accountantAscending", direction);
			}
			else
				request.getSession().setAttribute("accountantAscending", true);
			request.getSession().setAttribute("accountantField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("accountantField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = accountantService.requestAccountantPage(new RequestAccountantPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("accountantAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("accountantAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("accountantField"):field));
			}
			details = accountantService.requestAccountantPage(new RequestAccountantPageEvent(pageRequest));
		}
		
		PageWrapper<Accountant> page=new PageWrapper<Accountant>(details.getAccountantPage(),"/maintenance/accountant/searchAccountant");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("accountantField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/accountant/searchAccountant";
	}
	
	private Boolean hasActions(AccountantFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("accountantFilterInfo")AccountantFilterInfo filter, HttpServletRequest request) throws Exception{
		AccountantFilterInfo f=(AccountantFilterInfo) request.getSession().getAttribute("accountantFilter");
		if (f!=null && f.getAccountantFilterList().size()!=0)
			filter.getAccountantFilterList().addAll(f.getAccountantFilterList());
		if (filter.getAaddFilter())
			filter.getAccountantFilterList().add((AccountantFilterInfo) (request.getSession().getAttribute("accountantFilterCopy")!=null?request.getSession().getAttribute("accountantFilterCopy"):new AccountantFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("accountantAscending");
			String oldField=(String)request.getSession().getAttribute("accountantField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("accountantAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("accountantAscending", direction);
			}
			else if (request.getSession().getAttribute("accountantAscending")==null)
				request.getSession().setAttribute("accountantAscending", true);
			request.getSession().setAttribute("accountantField", filter.getSelectedFilterField());
		}
		
		RequestAccountantPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("accountantField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestAccountantPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("accountantAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("accountantAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("accountantField"):filter.getSelectedFilterField()));
				pageEvent = new RequestAccountantPageEvent(pageRequest,filter);			}
		}
		
		AccountantPageEvent details = accountantService.requestAccountantFilterPage(pageEvent);
		PageWrapper<Accountant> page=new PageWrapper<Accountant>(details.getAccountantPage(),"/maintenance/accountant/searchAccountant");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("accountantField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("accountantFilter", filter);
    	request.getSession().setAttribute("accountantFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/accountant/searchAccountant";
	}
	
	@ModelAttribute("accountantFilterInfo")
	private AccountantFilterInfo getAccountantFilterInfo(){
		return new AccountantFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}