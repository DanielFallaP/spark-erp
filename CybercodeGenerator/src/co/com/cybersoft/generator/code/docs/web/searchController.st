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

import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$PageEvent;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.persistence.services.$docName$.$upperDocName$PersistenceService;
import co.com.cybersoft.util.PageWrapper;

/**
 * Search controller class for $docName$
 * @author Cybersystems 2014. All rights reserved.
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
		if (field!=null){
			request.getSession().setAttribute("$docName$Field", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("$docName$Ascending");
			if (request.getSession().getAttribute("$docName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$docName$Ascending", direction);
			}
			else
				request.getSession().setAttribute("$docName$Ascending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("$docName$Field")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			info = $docName$Service.request$upperDocName$Page(new Request$upperDocName$PageEvent(pageRequest));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("$docName$Ascending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("$docName$Ascending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("$docName$Field"):field));
			}
			info = $docName$Service.request$upperDocName$Page(new Request$upperDocName$PageEvent(pageRequest));
		}
		
		PageWrapper<$upperDocName$> page=new PageWrapper<$upperDocName$>(info.get$upperDocName$Page(),"/docs/search$upperDocName$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/docs/$docName$/search$upperDocName$";
	}
}