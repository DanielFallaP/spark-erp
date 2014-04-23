package co.com.cybersoft.web.controller.corporation;

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

import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.events.corporation.RequestCorporationPageEvent;
import co.com.cybersoft.persistence.domain.Corporation;

/**
 * Search controller class for corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/corporation/searchCorporation")
public class CorporationSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CorporationSearchController.class);

	@Autowired
	private CorporationService corporationService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  corporation ");
		CorporationPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("corporationField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("corporationAscending");
			if (request.getSession().getAttribute("corporationAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("corporationAscending", direction);
			}
			else
				request.getSession().setAttribute("corporationAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("corporationField")==null){
			details = corporationService.requestCorporationPage(new RequestCorporationPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("corporationAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("corporationAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("corporationField"):field));
			}
			details = corporationService.requestCorporationPage(new RequestCorporationPageEvent(pageRequest));
		}
		
		PageWrapper<Corporation> page=new PageWrapper<Corporation>(details.getCorporationPage(),"/configuration/corporation/searchCorporation");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/corporation/searchCorporation";
	}
}