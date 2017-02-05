package co.com.cybersoft.maintenance.tables.web.controller.third;

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

import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdFilterInfo;

/**
 * Search controller class for third
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/third/searchThird")
public class ThirdSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThirdSearchController.class);

	@Autowired
	private ThirdService thirdService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ThirdFilterInfo f=(ThirdFilterInfo) request.getSession().getAttribute("thirdFilter");
		if (f!=null){
			f.getThirdFilterList().clear();
		}
		
		LOG.debug("Retrieving  third ");
		ThirdPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("thirdAscending");
			String oldField=(String)request.getSession().getAttribute("thirdField");
			if (oldField!=null && request.getSession().getAttribute("thirdAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("thirdAscending", direction);
			}
			else
				request.getSession().setAttribute("thirdAscending", true);
			request.getSession().setAttribute("thirdField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("thirdField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = thirdService.requestThirdPage(new RequestThirdPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("thirdAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("thirdAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("thirdField"):field));
			}
			details = thirdService.requestThirdPage(new RequestThirdPageEvent(pageRequest));
		}
		
		PageWrapper<Third> page=new PageWrapper<Third>(details.getThirdPage(),"/maintenance/third/searchThird");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("thirdField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/third/searchThird";
	}
	
	private Boolean hasActions(ThirdFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("thirdFilterInfo")ThirdFilterInfo filter, HttpServletRequest request) throws Exception{
		ThirdFilterInfo f=(ThirdFilterInfo) request.getSession().getAttribute("thirdFilter");
		if (f!=null && f.getThirdFilterList().size()!=0)
			filter.getThirdFilterList().addAll(f.getThirdFilterList());
		if (filter.getAaddFilter())
			filter.getThirdFilterList().add((ThirdFilterInfo) (request.getSession().getAttribute("thirdFilterCopy")!=null?request.getSession().getAttribute("thirdFilterCopy"):new ThirdFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("thirdAscending");
			String oldField=(String)request.getSession().getAttribute("thirdField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("thirdAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("thirdAscending", direction);
			}
			else if (request.getSession().getAttribute("thirdAscending")==null)
				request.getSession().setAttribute("thirdAscending", true);
			request.getSession().setAttribute("thirdField", filter.getSelectedFilterField());
		}
		
		RequestThirdPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("thirdField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestThirdPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("thirdAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("thirdAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("thirdField"):filter.getSelectedFilterField()));
				pageEvent = new RequestThirdPageEvent(pageRequest,filter);			}
		}
		
		ThirdPageEvent details = thirdService.requestThirdFilterPage(pageEvent);
		PageWrapper<Third> page=new PageWrapper<Third>(details.getThirdPage(),"/maintenance/third/searchThird");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("thirdField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("thirdFilter", filter);
    	request.getSession().setAttribute("thirdFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/third/searchThird";
	}
	
	@ModelAttribute("thirdFilterInfo")
	private ThirdFilterInfo getThirdFilterInfo(){
		return new ThirdFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}