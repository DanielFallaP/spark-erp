package co.com.cybersoft.docs.web.controller.$docName$;

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

import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$PageEvent;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.persistence.services.$docName$.$upperDocName$PersistenceService;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$FilterInfo;

import co.com.cybersoft.util.PageWrapper;

/**
 * Search controller class for $docName$
 * @author Cybersystems 2015. All rights reserved.
 *
 */
@Controller
@RequestMapping("/docs/search$upperDocName$")
public class $upperDocName$SearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger($upperDocName$SearchController.class);

	@Autowired
	private $upperDocName$PersistenceService $docName$Service;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  $docName$ ");
		$upperDocName$PageEvent info;
		Boolean direction = null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("$docName$Ascending");
			String oldField = (String)request.getSession().getAttribute("$docName$Field");
			if (oldField!=null && request.getSession().getAttribute("$docName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$docName$Ascending", direction);
			}
			else
				request.getSession().setAttribute("$docName$Ascending", true);
			request.getSession().setAttribute("$docName$Field", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("$docName$Field")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			info = $docName$Service.request$upperDocName$Page(new Request$upperDocName$PageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("$docName$Ascending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("$docName$Ascending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("$docName$Field"):field));
			}
			info = $docName$Service.request$upperDocName$Page(new Request$upperDocName$PageEvent(pageRequest));
		}
		
		PageWrapper<$upperDocName$> page=new PageWrapper<$upperDocName$>(info.get$upperDocName$Page(),"/docs/search$upperDocName$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("$docName$Field"));
		model.addAttribute("_direction", direction);
		return "/docs/$docName$/search$upperDocName$";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("$docName$FilterInfo")$upperDocName$FilterInfo filter, HttpServletRequest request) throws Exception{
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("$docName$Ascending");
			String oldField=(String)request.getSession().getAttribute("$docName$Field");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("$docName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$docName$Ascending", direction);
			}
			else if (request.getSession().getAttribute("$docName$Ascending")==null)
				request.getSession().setAttribute("$docName$Ascending", true);
			request.getSession().setAttribute("$docName$Field", filter.getSelectedFilterField());
		}
	
	Request$upperDocName$PageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("$docName$Field")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"_id");
			pageEvent = new Request$upperDocName$PageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("$docName$Ascending")!=null){
				direction=(boolean) request.getSession().getAttribute("$docName$Ascending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("$docName$Field"):filter.getSelectedFilterField()));
				pageEvent = new Request$upperDocName$PageEvent(pageRequest,filter);			}
		}
		$upperDocName$PageEvent details = $docName$Service.request$upperDocName$FilterPage(pageEvent);
		PageWrapper<$upperDocName$> page=new PageWrapper<$upperDocName$>(details.get$upperDocName$Page(),"/configuration/$docName$/search$upperDocName$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("$docName$Field"));
		model.addAttribute("_direction", direction);
		return "/docs/$docName$/search$upperDocName$";
	}
	
	@ModelAttribute("$docName$FilterInfo")
	private $upperDocName$FilterInfo get$upperDocName$FilterInfo(){
		return new $upperDocName$FilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}
