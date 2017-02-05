package co.com.cybersoft.maintenance.tables.web.controller.causeClose;

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

import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.causeClose.CauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.causeClose.RequestCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;

/**
 * Search controller class for causeClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/causeClose/searchCauseClose")
public class CauseCloseSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CauseCloseSearchController.class);

	@Autowired
	private CauseCloseService causeCloseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CauseCloseFilterInfo f=(CauseCloseFilterInfo) request.getSession().getAttribute("causeCloseFilter");
		if (f!=null){
			f.getCauseCloseFilterList().clear();
		}
		
		LOG.debug("Retrieving  causeClose ");
		CauseClosePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("causeCloseAscending");
			String oldField=(String)request.getSession().getAttribute("causeCloseField");
			if (oldField!=null && request.getSession().getAttribute("causeCloseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("causeCloseAscending", direction);
			}
			else
				request.getSession().setAttribute("causeCloseAscending", true);
			request.getSession().setAttribute("causeCloseField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("causeCloseField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = causeCloseService.requestCauseClosePage(new RequestCauseClosePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("causeCloseAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("causeCloseAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("causeCloseField"):field));
			}
			details = causeCloseService.requestCauseClosePage(new RequestCauseClosePageEvent(pageRequest));
		}
		
		PageWrapper<CauseClose> page=new PageWrapper<CauseClose>(details.getCauseClosePage(),"/maintenance/causeClose/searchCauseClose");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("causeCloseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/causeClose/searchCauseClose";
	}
	
	private Boolean hasActions(CauseCloseFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("causeCloseFilterInfo")CauseCloseFilterInfo filter, HttpServletRequest request) throws Exception{
		CauseCloseFilterInfo f=(CauseCloseFilterInfo) request.getSession().getAttribute("causeCloseFilter");
		if (f!=null && f.getCauseCloseFilterList().size()!=0)
			filter.getCauseCloseFilterList().addAll(f.getCauseCloseFilterList());
		if (filter.getAaddFilter())
			filter.getCauseCloseFilterList().add((CauseCloseFilterInfo) (request.getSession().getAttribute("causeCloseFilterCopy")!=null?request.getSession().getAttribute("causeCloseFilterCopy"):new CauseCloseFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("causeCloseAscending");
			String oldField=(String)request.getSession().getAttribute("causeCloseField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("causeCloseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("causeCloseAscending", direction);
			}
			else if (request.getSession().getAttribute("causeCloseAscending")==null)
				request.getSession().setAttribute("causeCloseAscending", true);
			request.getSession().setAttribute("causeCloseField", filter.getSelectedFilterField());
		}
		
		RequestCauseClosePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("causeCloseField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCauseClosePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("causeCloseAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("causeCloseAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("causeCloseField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCauseClosePageEvent(pageRequest,filter);			}
		}
		
		CauseClosePageEvent details = causeCloseService.requestCauseCloseFilterPage(pageEvent);
		PageWrapper<CauseClose> page=new PageWrapper<CauseClose>(details.getCauseClosePage(),"/maintenance/causeClose/searchCauseClose");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("causeCloseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("causeCloseFilter", filter);
    	request.getSession().setAttribute("causeCloseFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/causeClose/searchCauseClose";
	}
	
	@ModelAttribute("causeCloseFilterInfo")
	private CauseCloseFilterInfo getCauseCloseFilterInfo(){
		return new CauseCloseFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}