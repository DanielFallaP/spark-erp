package co.com.cybersoft.maintenance.tables.web.controller.referenceOperation;

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

import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.ReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.events.referenceOperation.RequestReferenceOperationPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationFilterInfo;

/**
 * Search controller class for referenceOperation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/referenceOperation/searchReferenceOperation")
public class ReferenceOperationSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReferenceOperationSearchController.class);

	@Autowired
	private ReferenceOperationService referenceOperationService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ReferenceOperationFilterInfo f=(ReferenceOperationFilterInfo) request.getSession().getAttribute("referenceOperationFilter");
		if (f!=null){
			f.getReferenceOperationFilterList().clear();
		}
		
		LOG.debug("Retrieving  referenceOperation ");
		ReferenceOperationPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("referenceOperationAscending");
			String oldField=(String)request.getSession().getAttribute("referenceOperationField");
			if (oldField!=null && request.getSession().getAttribute("referenceOperationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("referenceOperationAscending", direction);
			}
			else
				request.getSession().setAttribute("referenceOperationAscending", true);
			request.getSession().setAttribute("referenceOperationField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("referenceOperationField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = referenceOperationService.requestReferenceOperationPage(new RequestReferenceOperationPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("referenceOperationAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("referenceOperationAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("referenceOperationField"):field));
			}
			details = referenceOperationService.requestReferenceOperationPage(new RequestReferenceOperationPageEvent(pageRequest));
		}
		
		PageWrapper<ReferenceOperation> page=new PageWrapper<ReferenceOperation>(details.getReferenceOperationPage(),"/maintenance/referenceOperation/searchReferenceOperation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("referenceOperationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/referenceOperation/searchReferenceOperation";
	}
	
	private Boolean hasActions(ReferenceOperationFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("referenceOperationFilterInfo")ReferenceOperationFilterInfo filter, HttpServletRequest request) throws Exception{
		ReferenceOperationFilterInfo f=(ReferenceOperationFilterInfo) request.getSession().getAttribute("referenceOperationFilter");
		if (f!=null && f.getReferenceOperationFilterList().size()!=0)
			filter.getReferenceOperationFilterList().addAll(f.getReferenceOperationFilterList());
		if (filter.getAaddFilter())
			filter.getReferenceOperationFilterList().add((ReferenceOperationFilterInfo) (request.getSession().getAttribute("referenceOperationFilterCopy")!=null?request.getSession().getAttribute("referenceOperationFilterCopy"):new ReferenceOperationFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("referenceOperationAscending");
			String oldField=(String)request.getSession().getAttribute("referenceOperationField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("referenceOperationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("referenceOperationAscending", direction);
			}
			else if (request.getSession().getAttribute("referenceOperationAscending")==null)
				request.getSession().setAttribute("referenceOperationAscending", true);
			request.getSession().setAttribute("referenceOperationField", filter.getSelectedFilterField());
		}
		
		RequestReferenceOperationPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("referenceOperationField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestReferenceOperationPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("referenceOperationAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("referenceOperationAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("referenceOperationField"):filter.getSelectedFilterField()));
				pageEvent = new RequestReferenceOperationPageEvent(pageRequest,filter);			}
		}
		
		ReferenceOperationPageEvent details = referenceOperationService.requestReferenceOperationFilterPage(pageEvent);
		PageWrapper<ReferenceOperation> page=new PageWrapper<ReferenceOperation>(details.getReferenceOperationPage(),"/maintenance/referenceOperation/searchReferenceOperation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("referenceOperationField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("referenceOperationFilter", filter);
    	request.getSession().setAttribute("referenceOperationFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/referenceOperation/searchReferenceOperation";
	}
	
	@ModelAttribute("referenceOperationFilterInfo")
	private ReferenceOperationFilterInfo getReferenceOperationFilterInfo(){
		return new ReferenceOperationFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}