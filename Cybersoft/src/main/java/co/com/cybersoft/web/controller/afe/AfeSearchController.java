package co.com.cybersoft.web.controller.afe;

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

import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.events.afe.RequestAfePageEvent;
import co.com.cybersoft.persistence.domain.Afe;

/**
 * Search controller class for afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afe/searchAfe")
public class AfeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AfeSearchController.class);

	@Autowired
	private AfeService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  afe ");
		AfePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("afeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("afeAscending");
			if (request.getSession().getAttribute("afeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("afeAscending", direction);
			}
			else
				request.getSession().setAttribute("afeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("afeField")==null){
			details = afeService.requestAfePage(new RequestAfePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("afeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("afeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("afeField"):field));
			}
			details = afeService.requestAfePage(new RequestAfePageEvent(pageRequest));
		}
		
		PageWrapper<Afe> page=new PageWrapper<Afe>(details.getAfePage(),"/configuration/afe/searchAfe");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/afe/searchAfe";
	}
}