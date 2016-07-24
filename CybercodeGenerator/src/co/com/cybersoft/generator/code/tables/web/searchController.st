package co.com.cybersoft.$module$.tables.web.controller.$entityName$;

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

import co.com.cybersoft.$module$.tables.core.services.$entityName$.$coreService$;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.$module$.tables.events.$entityName$.$responseEvent$;
import co.com.cybersoft.$module$.tables.events.$entityName$.$requestEvent$;
import co.com.cybersoft.$module$.tables.persistence.domain.$entityUpperCaseName$;
import co.com.cybersoft.$module$.tables.web.domain.$entityName$.$entityUpperCaseName$FilterInfo;

/**
 * Search controller class for $entityName$
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("$url$")
public class $searchControllerName$ {
	
	private static final Logger LOG = LoggerFactory.getLogger($searchControllerName$.class);

	@Autowired
	private $coreService$ $entityName$Service;
	
	$actionClasses$
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		$entityUpperCaseName$FilterInfo f=($entityUpperCaseName$FilterInfo) request.getSession().getAttribute("$entityName$Filter");
		if (f!=null){
			f.get$entityUpperCaseName$FilterList().clear();
		}
		
		LOG.debug("Retrieving  $entityName$ ");
		$responseEvent$ details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("$entityName$Ascending");
			String oldField=(String)request.getSession().getAttribute("$entityName$Field");
			if (oldField!=null && request.getSession().getAttribute("$entityName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$entityName$Ascending", direction);
			}
			else
				request.getSession().setAttribute("$entityName$Ascending", true);
			request.getSession().setAttribute("$entityName$Field", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("$entityName$Field")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = $entityName$Service.request$entityUpperCaseName$Page(new $requestEvent$(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("$entityName$Ascending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("$entityName$Ascending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("$entityName$Field"):field));
			}
			details = $entityName$Service.request$requestMethodName$Page(new $requestEvent$(pageRequest));
		}
		
		PageWrapper<$entityUpperCaseName$> page=new PageWrapper<$entityUpperCaseName$>(details.$getListMethod$(),"$url$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("$entityName$Field"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		
		return "$viewURL$";
	}
	
	private Boolean hasActions($entityUpperCaseName$FilterInfo filter){
		$hasActions$
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("$entityName$FilterInfo")$entityUpperCaseName$FilterInfo filter, HttpServletRequest request) throws Exception{
		$entityUpperCaseName$FilterInfo f=($entityUpperCaseName$FilterInfo) request.getSession().getAttribute("$entityName$Filter");
		if (f!=null && f.get$entityUpperCaseName$FilterList().size()!=0)
			filter.get$entityUpperCaseName$FilterList().addAll(f.get$entityUpperCaseName$FilterList());
		if (filter.getAaddFilter())
			filter.get$entityUpperCaseName$FilterList().add(($entityUpperCaseName$FilterInfo) (request.getSession().getAttribute("$entityName$FilterCopy")!=null?request.getSession().getAttribute("$entityName$FilterCopy"):new $entityUpperCaseName$FilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("$entityName$Ascending");
			String oldField=(String)request.getSession().getAttribute("$entityName$Field");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("$entityName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$entityName$Ascending", direction);
			}
			else if (request.getSession().getAttribute("$entityName$Ascending")==null)
				request.getSession().setAttribute("$entityName$Ascending", true);
			request.getSession().setAttribute("$entityName$Field", filter.getSelectedFilterField());
		}
		
		Request$entityUpperCaseName$PageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("$entityName$Field")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new Request$entityUpperCaseName$PageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("$entityName$Ascending")!=null){
				direction=(Boolean) request.getSession().getAttribute("$entityName$Ascending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("$entityName$Field"):filter.getSelectedFilterField()));
				pageEvent = new Request$entityUpperCaseName$PageEvent(pageRequest,filter);			}
		}
		
		$entityUpperCaseName$PageEvent details = $entityName$Service.request$entityUpperCaseName$FilterPage(pageEvent);
		PageWrapper<$entityUpperCaseName$> page=new PageWrapper<$entityUpperCaseName$>(details.get$entityUpperCaseName$Page(),"/$module$/$entityName$/search$entityUpperCaseName$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("$entityName$Field"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		
		if (hasActions(filter)){
			$actions$
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("$entityName$Filter", filter);
    	request.getSession().setAttribute("$entityName$FilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "$viewURL$";
	}
	
	@ModelAttribute("$entityName$FilterInfo")
	private $entityUpperCaseName$FilterInfo get$entityUpperCaseName$FilterInfo(){
		return new $entityUpperCaseName$FilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}
