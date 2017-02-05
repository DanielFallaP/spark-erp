package co.com.cybersoft.maintenance.tables.web.controller.typeWork;

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

import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.typeWork.TypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeWork.RequestTypeWorkPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkFilterInfo;

/**
 * Search controller class for typeWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeWork/searchTypeWork")
public class TypeWorkSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TypeWorkSearchController.class);

	@Autowired
	private TypeWorkService typeWorkService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TypeWorkFilterInfo f=(TypeWorkFilterInfo) request.getSession().getAttribute("typeWorkFilter");
		if (f!=null){
			f.getTypeWorkFilterList().clear();
		}
		
		LOG.debug("Retrieving  typeWork ");
		TypeWorkPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("typeWorkAscending");
			String oldField=(String)request.getSession().getAttribute("typeWorkField");
			if (oldField!=null && request.getSession().getAttribute("typeWorkAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeWorkAscending", direction);
			}
			else
				request.getSession().setAttribute("typeWorkAscending", true);
			request.getSession().setAttribute("typeWorkField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("typeWorkField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = typeWorkService.requestTypeWorkPage(new RequestTypeWorkPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("typeWorkAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("typeWorkAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("typeWorkField"):field));
			}
			details = typeWorkService.requestTypeWorkPage(new RequestTypeWorkPageEvent(pageRequest));
		}
		
		PageWrapper<TypeWork> page=new PageWrapper<TypeWork>(details.getTypeWorkPage(),"/maintenance/typeWork/searchTypeWork");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeWorkField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/typeWork/searchTypeWork";
	}
	
	private Boolean hasActions(TypeWorkFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("typeWorkFilterInfo")TypeWorkFilterInfo filter, HttpServletRequest request) throws Exception{
		TypeWorkFilterInfo f=(TypeWorkFilterInfo) request.getSession().getAttribute("typeWorkFilter");
		if (f!=null && f.getTypeWorkFilterList().size()!=0)
			filter.getTypeWorkFilterList().addAll(f.getTypeWorkFilterList());
		if (filter.getAaddFilter())
			filter.getTypeWorkFilterList().add((TypeWorkFilterInfo) (request.getSession().getAttribute("typeWorkFilterCopy")!=null?request.getSession().getAttribute("typeWorkFilterCopy"):new TypeWorkFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("typeWorkAscending");
			String oldField=(String)request.getSession().getAttribute("typeWorkField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("typeWorkAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeWorkAscending", direction);
			}
			else if (request.getSession().getAttribute("typeWorkAscending")==null)
				request.getSession().setAttribute("typeWorkAscending", true);
			request.getSession().setAttribute("typeWorkField", filter.getSelectedFilterField());
		}
		
		RequestTypeWorkPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("typeWorkField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTypeWorkPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("typeWorkAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("typeWorkAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("typeWorkField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTypeWorkPageEvent(pageRequest,filter);			}
		}
		
		TypeWorkPageEvent details = typeWorkService.requestTypeWorkFilterPage(pageEvent);
		PageWrapper<TypeWork> page=new PageWrapper<TypeWork>(details.getTypeWorkPage(),"/maintenance/typeWork/searchTypeWork");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeWorkField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("typeWorkFilter", filter);
    	request.getSession().setAttribute("typeWorkFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/typeWork/searchTypeWork";
	}
	
	@ModelAttribute("typeWorkFilterInfo")
	private TypeWorkFilterInfo getTypeWorkFilterInfo(){
		return new TypeWorkFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}