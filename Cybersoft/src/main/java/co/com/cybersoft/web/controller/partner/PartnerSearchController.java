package co.com.cybersoft.web.controller.partner;

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

import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.events.partner.RequestPartnerPageEvent;
import co.com.cybersoft.persistence.domain.Partner;

/**
 * Search controller class for partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/partner/searchPartner")
public class PartnerSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PartnerSearchController.class);

	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  partner ");
		PartnerPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("partnerField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("partnerAscending");
			if (request.getSession().getAttribute("partnerAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("partnerAscending", direction);
			}
			else
				request.getSession().setAttribute("partnerAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("partnerField")==null){
			details = partnerService.requestPartnerPage(new RequestPartnerPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("partnerAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("partnerAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("partnerField"):field));
			}
			details = partnerService.requestPartnerPage(new RequestPartnerPageEvent(pageRequest));
		}
		
		PageWrapper<Partner> page=new PageWrapper<Partner>(details.getPartnerPage(),"/configuration/partner/searchPartner");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/partner/searchPartner";
	}
}