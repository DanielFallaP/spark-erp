package co.com.cybersoft.docs.web.controller.requisition;

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

import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.docs.events.requisition.RequestRequisitionPageEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionPageEvent;
import co.com.cybersoft.docs.persistence.domain.Requisition;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceService;

/**
 * Search controller class for requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/docs/searchRequisition")
public class RequisitionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RequisitionSearchController.class);

	@Autowired
	private RequisitionPersistenceService requisitionService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  requisition ");
		RequisitionPageEvent info;
		if (field!=null){
			request.getSession().setAttribute("requisitionField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("requisitionAscending");
			if (request.getSession().getAttribute("requisitionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("requisitionAscending", direction);
			}
			else
				request.getSession().setAttribute("requisitionAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("requisitionField")==null){
			info = requisitionService.requestRequisitionPage(new RequestRequisitionPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("requisitionAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("requisitionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("requisitionField"):field));
			}
			info = requisitionService.requestRequisitionPage(new RequestRequisitionPageEvent(pageRequest));
		}
		
		PageWrapper<Requisition> page=new PageWrapper<Requisition>(info.getRequisitionPage(),"/docs/searchRequisition");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/docs/requisition/searchRequisition";
	}
}