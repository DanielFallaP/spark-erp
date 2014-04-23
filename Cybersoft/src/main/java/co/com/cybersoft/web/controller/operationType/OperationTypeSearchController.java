package co.com.cybersoft.web.controller.operationType;

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

import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypePageEvent;
import co.com.cybersoft.persistence.domain.OperationType;

/**
 * Search controller class for operationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/operationType/searchOperationType")
public class OperationTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OperationTypeSearchController.class);

	@Autowired
	private OperationTypeService operationTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  operationType ");
		OperationTypePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("operationTypeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("operationTypeAscending");
			if (request.getSession().getAttribute("operationTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("operationTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("operationTypeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("operationTypeField")==null){
			details = operationTypeService.requestOperationTypePage(new RequestOperationTypePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("operationTypeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("operationTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("operationTypeField"):field));
			}
			details = operationTypeService.requestOperationTypePage(new RequestOperationTypePageEvent(pageRequest));
		}
		
		PageWrapper<OperationType> page=new PageWrapper<OperationType>(details.getOperationTypePage(),"/configuration/operationType/searchOperationType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/operationType/searchOperationType";
	}
}