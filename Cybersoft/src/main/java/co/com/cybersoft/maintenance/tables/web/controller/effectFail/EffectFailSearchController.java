package co.com.cybersoft.maintenance.tables.web.controller.effectFail;

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

import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailFilterInfo;

/**
 * Search controller class for effectFail
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/effectFail/searchEffectFail")
public class EffectFailSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EffectFailSearchController.class);

	@Autowired
	private EffectFailService effectFailService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		EffectFailFilterInfo f=(EffectFailFilterInfo) request.getSession().getAttribute("effectFailFilter");
		if (f!=null){
			f.getEffectFailFilterList().clear();
		}
		
		LOG.debug("Retrieving  effectFail ");
		EffectFailPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("effectFailAscending");
			String oldField=(String)request.getSession().getAttribute("effectFailField");
			if (oldField!=null && request.getSession().getAttribute("effectFailAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("effectFailAscending", direction);
			}
			else
				request.getSession().setAttribute("effectFailAscending", true);
			request.getSession().setAttribute("effectFailField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("effectFailField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = effectFailService.requestEffectFailPage(new RequestEffectFailPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("effectFailAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("effectFailAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("effectFailField"):field));
			}
			details = effectFailService.requestEffectFailPage(new RequestEffectFailPageEvent(pageRequest));
		}
		
		PageWrapper<EffectFail> page=new PageWrapper<EffectFail>(details.getEffectFailPage(),"/maintenance/effectFail/searchEffectFail");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("effectFailField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/effectFail/searchEffectFail";
	}
	
	private Boolean hasActions(EffectFailFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("effectFailFilterInfo")EffectFailFilterInfo filter, HttpServletRequest request) throws Exception{
		EffectFailFilterInfo f=(EffectFailFilterInfo) request.getSession().getAttribute("effectFailFilter");
		if (f!=null && f.getEffectFailFilterList().size()!=0)
			filter.getEffectFailFilterList().addAll(f.getEffectFailFilterList());
		if (filter.getAaddFilter())
			filter.getEffectFailFilterList().add((EffectFailFilterInfo) (request.getSession().getAttribute("effectFailFilterCopy")!=null?request.getSession().getAttribute("effectFailFilterCopy"):new EffectFailFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("effectFailAscending");
			String oldField=(String)request.getSession().getAttribute("effectFailField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("effectFailAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("effectFailAscending", direction);
			}
			else if (request.getSession().getAttribute("effectFailAscending")==null)
				request.getSession().setAttribute("effectFailAscending", true);
			request.getSession().setAttribute("effectFailField", filter.getSelectedFilterField());
		}
		
		RequestEffectFailPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("effectFailField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestEffectFailPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("effectFailAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("effectFailAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("effectFailField"):filter.getSelectedFilterField()));
				pageEvent = new RequestEffectFailPageEvent(pageRequest,filter);			}
		}
		
		EffectFailPageEvent details = effectFailService.requestEffectFailFilterPage(pageEvent);
		PageWrapper<EffectFail> page=new PageWrapper<EffectFail>(details.getEffectFailPage(),"/maintenance/effectFail/searchEffectFail");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("effectFailField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("effectFailFilter", filter);
    	request.getSession().setAttribute("effectFailFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/effectFail/searchEffectFail";
	}
	
	@ModelAttribute("effectFailFilterInfo")
	private EffectFailFilterInfo getEffectFailFilterInfo(){
		return new EffectFailFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}