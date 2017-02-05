package co.com.cybersoft.maintenance.tables.web.controller.causePending;

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

import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.causePending.CausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.events.causePending.RequestCausePendingPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingFilterInfo;

/**
 * Search controller class for causePending
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causePending/searchCausePending")
public class CausePendingSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CausePendingSearchController.class);

	@Autowired
	private CausePendingService causePendingService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CausePendingFilterInfo f=(CausePendingFilterInfo) request.getSession().getAttribute("causePendingFilter");
		if (f!=null){
			f.getCausePendingFilterList().clear();
		}
		
		LOG.debug("Retrieving  causePending ");
		CausePendingPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("causePendingAscending");
			String oldField=(String)request.getSession().getAttribute("causePendingField");
			if (oldField!=null && request.getSession().getAttribute("causePendingAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("causePendingAscending", direction);
			}
			else
				request.getSession().setAttribute("causePendingAscending", true);
			request.getSession().setAttribute("causePendingField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("causePendingField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = causePendingService.requestCausePendingPage(new RequestCausePendingPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("causePendingAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("causePendingAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("causePendingField"):field));
			}
			details = causePendingService.requestCausePendingPage(new RequestCausePendingPageEvent(pageRequest));
		}
		
		PageWrapper<CausePending> page=new PageWrapper<CausePending>(details.getCausePendingPage(),"/maintenance/causePending/searchCausePending");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("causePendingField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/causePending/searchCausePending";
	}
	
	private Boolean hasActions(CausePendingFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("causePendingFilterInfo")CausePendingFilterInfo filter, HttpServletRequest request) throws Exception{
		CausePendingFilterInfo f=(CausePendingFilterInfo) request.getSession().getAttribute("causePendingFilter");
		if (f!=null && f.getCausePendingFilterList().size()!=0)
			filter.getCausePendingFilterList().addAll(f.getCausePendingFilterList());
		if (filter.getAaddFilter())
			filter.getCausePendingFilterList().add((CausePendingFilterInfo) (request.getSession().getAttribute("causePendingFilterCopy")!=null?request.getSession().getAttribute("causePendingFilterCopy"):new CausePendingFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("causePendingAscending");
			String oldField=(String)request.getSession().getAttribute("causePendingField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("causePendingAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("causePendingAscending", direction);
			}
			else if (request.getSession().getAttribute("causePendingAscending")==null)
				request.getSession().setAttribute("causePendingAscending", true);
			request.getSession().setAttribute("causePendingField", filter.getSelectedFilterField());
		}
		
		RequestCausePendingPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("causePendingField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCausePendingPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("causePendingAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("causePendingAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("causePendingField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCausePendingPageEvent(pageRequest,filter);			}
		}
		
		CausePendingPageEvent details = causePendingService.requestCausePendingFilterPage(pageEvent);
		PageWrapper<CausePending> page=new PageWrapper<CausePending>(details.getCausePendingPage(),"/maintenance/causePending/searchCausePending");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("causePendingField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("causePendingFilter", filter);
    	request.getSession().setAttribute("causePendingFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/causePending/searchCausePending";
	}
	
	@ModelAttribute("causePendingFilterInfo")
	private CausePendingFilterInfo getCausePendingFilterInfo(){
		return new CausePendingFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}