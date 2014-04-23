package co.com.cybersoft.web.controller.jointVenture;

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

import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVenturePageEvent;
import co.com.cybersoft.persistence.domain.JointVenture;

/**
 * Search controller class for jointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/jointVenture/searchJointVenture")
public class JointVentureSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(JointVentureSearchController.class);

	@Autowired
	private JointVentureService jointVentureService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  jointVenture ");
		JointVenturePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("jointVentureField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("jointVentureAscending");
			if (request.getSession().getAttribute("jointVentureAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("jointVentureAscending", direction);
			}
			else
				request.getSession().setAttribute("jointVentureAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("jointVentureField")==null){
			details = jointVentureService.requestJointVenturePage(new RequestJointVenturePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("jointVentureAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("jointVentureAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("jointVentureField"):field));
			}
			details = jointVentureService.requestJointVenturePage(new RequestJointVenturePageEvent(pageRequest));
		}
		
		PageWrapper<JointVenture> page=new PageWrapper<JointVenture>(details.getJointVenturePage(),"/configuration/jointVenture/searchJointVenture");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/jointVenture/searchJointVenture";
	}
}