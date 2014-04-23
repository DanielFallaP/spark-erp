package co.com.cybersoft.web.controller.afeType;

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

import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypePageEvent;
import co.com.cybersoft.persistence.domain.AfeType;

/**
 * Search controller class for afeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afeType/searchAfeType")
public class AfeTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AfeTypeSearchController.class);

	@Autowired
	private AfeTypeService afeTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  afeType ");
		AfeTypePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("afeTypeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("afeTypeAscending");
			if (request.getSession().getAttribute("afeTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("afeTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("afeTypeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("afeTypeField")==null){
			details = afeTypeService.requestAfeTypePage(new RequestAfeTypePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("afeTypeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("afeTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("afeTypeField"):field));
			}
			details = afeTypeService.requestAfeTypePage(new RequestAfeTypePageEvent(pageRequest));
		}
		
		PageWrapper<AfeType> page=new PageWrapper<AfeType>(details.getAfeTypePage(),"/configuration/afeType/searchAfeType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/afeType/searchAfeType";
	}
}