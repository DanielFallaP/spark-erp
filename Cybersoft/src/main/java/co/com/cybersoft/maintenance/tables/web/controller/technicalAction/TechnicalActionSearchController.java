package co.com.cybersoft.maintenance.tables.web.controller.technicalAction;

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

import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.technicalAction.TechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.technicalAction.RequestTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionFilterInfo;

/**
 * Search controller class for technicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/technicalAction/searchTechnicalAction")
public class TechnicalActionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TechnicalActionSearchController.class);

	@Autowired
	private TechnicalActionService technicalActionService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TechnicalActionFilterInfo f=(TechnicalActionFilterInfo) request.getSession().getAttribute("technicalActionFilter");
		if (f!=null){
			f.getTechnicalActionFilterList().clear();
		}
		
		LOG.debug("Retrieving  technicalAction ");
		TechnicalActionPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("technicalActionAscending");
			String oldField=(String)request.getSession().getAttribute("technicalActionField");
			if (oldField!=null && request.getSession().getAttribute("technicalActionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("technicalActionAscending", direction);
			}
			else
				request.getSession().setAttribute("technicalActionAscending", true);
			request.getSession().setAttribute("technicalActionField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("technicalActionField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = technicalActionService.requestTechnicalActionPage(new RequestTechnicalActionPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("technicalActionAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("technicalActionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("technicalActionField"):field));
			}
			details = technicalActionService.requestTechnicalActionPage(new RequestTechnicalActionPageEvent(pageRequest));
		}
		
		PageWrapper<TechnicalAction> page=new PageWrapper<TechnicalAction>(details.getTechnicalActionPage(),"/maintenance/technicalAction/searchTechnicalAction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("technicalActionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/technicalAction/searchTechnicalAction";
	}
	
	private Boolean hasActions(TechnicalActionFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("technicalActionFilterInfo")TechnicalActionFilterInfo filter, HttpServletRequest request) throws Exception{
		TechnicalActionFilterInfo f=(TechnicalActionFilterInfo) request.getSession().getAttribute("technicalActionFilter");
		if (f!=null && f.getTechnicalActionFilterList().size()!=0)
			filter.getTechnicalActionFilterList().addAll(f.getTechnicalActionFilterList());
		if (filter.getAaddFilter())
			filter.getTechnicalActionFilterList().add((TechnicalActionFilterInfo) (request.getSession().getAttribute("technicalActionFilterCopy")!=null?request.getSession().getAttribute("technicalActionFilterCopy"):new TechnicalActionFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("technicalActionAscending");
			String oldField=(String)request.getSession().getAttribute("technicalActionField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("technicalActionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("technicalActionAscending", direction);
			}
			else if (request.getSession().getAttribute("technicalActionAscending")==null)
				request.getSession().setAttribute("technicalActionAscending", true);
			request.getSession().setAttribute("technicalActionField", filter.getSelectedFilterField());
		}
		
		RequestTechnicalActionPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("technicalActionField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTechnicalActionPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("technicalActionAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("technicalActionAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("technicalActionField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTechnicalActionPageEvent(pageRequest,filter);			}
		}
		
		TechnicalActionPageEvent details = technicalActionService.requestTechnicalActionFilterPage(pageEvent);
		PageWrapper<TechnicalAction> page=new PageWrapper<TechnicalAction>(details.getTechnicalActionPage(),"/maintenance/technicalAction/searchTechnicalAction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("technicalActionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("technicalActionFilter", filter);
    	request.getSession().setAttribute("technicalActionFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/technicalAction/searchTechnicalAction";
	}
	
	@ModelAttribute("technicalActionFilterInfo")
	private TechnicalActionFilterInfo getTechnicalActionFilterInfo(){
		return new TechnicalActionFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}