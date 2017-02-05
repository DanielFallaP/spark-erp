package co.com.cybersoft.maintenance.tables.web.controller.typeCauseClose;

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

import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.TypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeCauseClose.RequestTypeCauseClosePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseFilterInfo;

/**
 * Search controller class for typeCauseClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/typeCauseClose/searchTypeCauseClose")
public class TypeCauseCloseSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TypeCauseCloseSearchController.class);

	@Autowired
	private TypeCauseCloseService typeCauseCloseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		TypeCauseCloseFilterInfo f=(TypeCauseCloseFilterInfo) request.getSession().getAttribute("typeCauseCloseFilter");
		if (f!=null){
			f.getTypeCauseCloseFilterList().clear();
		}
		
		LOG.debug("Retrieving  typeCauseClose ");
		TypeCauseClosePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("typeCauseCloseAscending");
			String oldField=(String)request.getSession().getAttribute("typeCauseCloseField");
			if (oldField!=null && request.getSession().getAttribute("typeCauseCloseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeCauseCloseAscending", direction);
			}
			else
				request.getSession().setAttribute("typeCauseCloseAscending", true);
			request.getSession().setAttribute("typeCauseCloseField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("typeCauseCloseField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = typeCauseCloseService.requestTypeCauseClosePage(new RequestTypeCauseClosePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("typeCauseCloseAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("typeCauseCloseAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("typeCauseCloseField"):field));
			}
			details = typeCauseCloseService.requestTypeCauseClosePage(new RequestTypeCauseClosePageEvent(pageRequest));
		}
		
		PageWrapper<TypeCauseClose> page=new PageWrapper<TypeCauseClose>(details.getTypeCauseClosePage(),"/maintenance/typeCauseClose/searchTypeCauseClose");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeCauseCloseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/typeCauseClose/searchTypeCauseClose";
	}
	
	private Boolean hasActions(TypeCauseCloseFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("typeCauseCloseFilterInfo")TypeCauseCloseFilterInfo filter, HttpServletRequest request) throws Exception{
		TypeCauseCloseFilterInfo f=(TypeCauseCloseFilterInfo) request.getSession().getAttribute("typeCauseCloseFilter");
		if (f!=null && f.getTypeCauseCloseFilterList().size()!=0)
			filter.getTypeCauseCloseFilterList().addAll(f.getTypeCauseCloseFilterList());
		if (filter.getAaddFilter())
			filter.getTypeCauseCloseFilterList().add((TypeCauseCloseFilterInfo) (request.getSession().getAttribute("typeCauseCloseFilterCopy")!=null?request.getSession().getAttribute("typeCauseCloseFilterCopy"):new TypeCauseCloseFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("typeCauseCloseAscending");
			String oldField=(String)request.getSession().getAttribute("typeCauseCloseField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("typeCauseCloseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("typeCauseCloseAscending", direction);
			}
			else if (request.getSession().getAttribute("typeCauseCloseAscending")==null)
				request.getSession().setAttribute("typeCauseCloseAscending", true);
			request.getSession().setAttribute("typeCauseCloseField", filter.getSelectedFilterField());
		}
		
		RequestTypeCauseClosePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("typeCauseCloseField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestTypeCauseClosePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("typeCauseCloseAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("typeCauseCloseAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("typeCauseCloseField"):filter.getSelectedFilterField()));
				pageEvent = new RequestTypeCauseClosePageEvent(pageRequest,filter);			}
		}
		
		TypeCauseClosePageEvent details = typeCauseCloseService.requestTypeCauseCloseFilterPage(pageEvent);
		PageWrapper<TypeCauseClose> page=new PageWrapper<TypeCauseClose>(details.getTypeCauseClosePage(),"/maintenance/typeCauseClose/searchTypeCauseClose");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("typeCauseCloseField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("typeCauseCloseFilter", filter);
    	request.getSession().setAttribute("typeCauseCloseFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/typeCauseClose/searchTypeCauseClose";
	}
	
	@ModelAttribute("typeCauseCloseFilterInfo")
	private TypeCauseCloseFilterInfo getTypeCauseCloseFilterInfo(){
		return new TypeCauseCloseFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}