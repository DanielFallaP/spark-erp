package co.com.cybersoft.maintenance.tables.web.controller.effectFailTechnicalAction;

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

import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;

/**
 * Search controller class for effectFailTechnicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/effectFailTechnicalAction/searchEffectFailTechnicalAction")
public class EffectFailTechnicalActionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EffectFailTechnicalActionSearchController.class);

	@Autowired
	private EffectFailTechnicalActionService effectFailTechnicalActionService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		EffectFailTechnicalActionFilterInfo f=(EffectFailTechnicalActionFilterInfo) request.getSession().getAttribute("effectFailTechnicalActionFilter");
		if (f!=null){
			f.getEffectFailTechnicalActionFilterList().clear();
		}
		
		LOG.debug("Retrieving  effectFailTechnicalAction ");
		EffectFailTechnicalActionPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("effectFailTechnicalActionAscending");
			String oldField=(String)request.getSession().getAttribute("effectFailTechnicalActionField");
			if (oldField!=null && request.getSession().getAttribute("effectFailTechnicalActionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("effectFailTechnicalActionAscending", direction);
			}
			else
				request.getSession().setAttribute("effectFailTechnicalActionAscending", true);
			request.getSession().setAttribute("effectFailTechnicalActionField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("effectFailTechnicalActionField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = effectFailTechnicalActionService.requestEffectFailTechnicalActionPage(new RequestEffectFailTechnicalActionPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("effectFailTechnicalActionAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("effectFailTechnicalActionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("effectFailTechnicalActionField"):field));
			}
			details = effectFailTechnicalActionService.requestEffectFailTechnicalActionPage(new RequestEffectFailTechnicalActionPageEvent(pageRequest));
		}
		
		PageWrapper<EffectFailTechnicalAction> page=new PageWrapper<EffectFailTechnicalAction>(details.getEffectFailTechnicalActionPage(),"/maintenance/effectFailTechnicalAction/searchEffectFailTechnicalAction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("effectFailTechnicalActionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/effectFailTechnicalAction/searchEffectFailTechnicalAction";
	}
	
	private Boolean hasActions(EffectFailTechnicalActionFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("effectFailTechnicalActionFilterInfo")EffectFailTechnicalActionFilterInfo filter, HttpServletRequest request) throws Exception{
		EffectFailTechnicalActionFilterInfo f=(EffectFailTechnicalActionFilterInfo) request.getSession().getAttribute("effectFailTechnicalActionFilter");
		if (f!=null && f.getEffectFailTechnicalActionFilterList().size()!=0)
			filter.getEffectFailTechnicalActionFilterList().addAll(f.getEffectFailTechnicalActionFilterList());
		if (filter.getAaddFilter())
			filter.getEffectFailTechnicalActionFilterList().add((EffectFailTechnicalActionFilterInfo) (request.getSession().getAttribute("effectFailTechnicalActionFilterCopy")!=null?request.getSession().getAttribute("effectFailTechnicalActionFilterCopy"):new EffectFailTechnicalActionFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("effectFailTechnicalActionAscending");
			String oldField=(String)request.getSession().getAttribute("effectFailTechnicalActionField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("effectFailTechnicalActionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("effectFailTechnicalActionAscending", direction);
			}
			else if (request.getSession().getAttribute("effectFailTechnicalActionAscending")==null)
				request.getSession().setAttribute("effectFailTechnicalActionAscending", true);
			request.getSession().setAttribute("effectFailTechnicalActionField", filter.getSelectedFilterField());
		}
		
		RequestEffectFailTechnicalActionPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("effectFailTechnicalActionField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestEffectFailTechnicalActionPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("effectFailTechnicalActionAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("effectFailTechnicalActionAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("effectFailTechnicalActionField"):filter.getSelectedFilterField()));
				pageEvent = new RequestEffectFailTechnicalActionPageEvent(pageRequest,filter);			}
		}
		
		EffectFailTechnicalActionPageEvent details = effectFailTechnicalActionService.requestEffectFailTechnicalActionFilterPage(pageEvent);
		PageWrapper<EffectFailTechnicalAction> page=new PageWrapper<EffectFailTechnicalAction>(details.getEffectFailTechnicalActionPage(),"/maintenance/effectFailTechnicalAction/searchEffectFailTechnicalAction");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("effectFailTechnicalActionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("effectFailTechnicalActionFilter", filter);
    	request.getSession().setAttribute("effectFailTechnicalActionFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/effectFailTechnicalAction/searchEffectFailTechnicalAction";
	}
	
	@ModelAttribute("effectFailTechnicalActionFilterInfo")
	private EffectFailTechnicalActionFilterInfo getEffectFailTechnicalActionFilterInfo(){
		return new EffectFailTechnicalActionFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}