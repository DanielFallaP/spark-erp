package co.com.cybersoft.tables.web.controller.$entityName$;

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

import co.com.cybersoft.tables.core.services.$entityName$.$coreService$;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.tables.events.$entityName$.$responseEvent$;
import co.com.cybersoft.tables.events.$entityName$.$requestEvent$;
import co.com.cybersoft.tables.persistence.domain.$entityUpperCaseName$;

/**
 * Search controller class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("$url$")
public class $searchControllerName$ {
	
	private static final Logger LOG = LoggerFactory.getLogger($searchControllerName$.class);

	@Autowired
	private $coreService$ $entityName$Service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  $entityName$ ");
		$responseEvent$ details;
		if (field!=null){
			request.getSession().setAttribute("$entityName$Field", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("$entityName$Ascending");
			if (request.getSession().getAttribute("$entityName$Ascending")!=null){
				direction=!direction;
				request.getSession().setAttribute("$entityName$Ascending", direction);
			}
			else
				request.getSession().setAttribute("$entityName$Ascending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("$entityName$Field")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			details = $entityName$Service.request$entityUpperCaseName$Page(new $requestEvent$(pageRequest));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("$entityName$Ascending")!=null){
								
				direction=(boolean) request.getSession().getAttribute("$entityName$Ascending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("$entityName$Field"):field));
			}
			details = $entityName$Service.request$requestMethodName$Page(new $requestEvent$(pageRequest));
		}
		
		PageWrapper<$entityUpperCaseName$> page=new PageWrapper<$entityUpperCaseName$>(details.$getListMethod$(),"$url$");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "$viewURL$";
	}
}
