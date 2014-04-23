package co.com.cybersoft.web.controller.active;

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

import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.events.active.RequestActivePageEvent;
import co.com.cybersoft.persistence.domain.Active;

/**
 * Search controller class for active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/active/searchActive")
public class ActiveSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ActiveSearchController.class);

	@Autowired
	private ActiveService activeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  active ");
		ActivePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("activeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("activeAscending");
			if (request.getSession().getAttribute("activeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("activeAscending", direction);
			}
			else
				request.getSession().setAttribute("activeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("activeField")==null){
			details = activeService.requestActivePage(new RequestActivePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("activeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("activeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("activeField"):field));
			}
			details = activeService.requestActivePage(new RequestActivePageEvent(pageRequest));
		}
		
		PageWrapper<Active> page=new PageWrapper<Active>(details.getActivePage(),"/configuration/active/searchActive");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/active/searchActive";
	}
}