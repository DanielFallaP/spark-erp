package co.com.cybersoft.web.controller.calculusType;

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

import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypePageEvent;
import co.com.cybersoft.persistence.domain.CalculusType;

/**
 * Search controller class for calculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/calculusType/searchCalculusType")
public class CalculusTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalculusTypeSearchController.class);

	@Autowired
	private CalculusTypeService calculusTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  calculusType ");
		CalculusTypePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("calculusTypeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("calculusTypeAscending");
			if (request.getSession().getAttribute("calculusTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("calculusTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("calculusTypeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("calculusTypeField")==null){
			details = calculusTypeService.requestCalculusTypePage(new RequestCalculusTypePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("calculusTypeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("calculusTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("calculusTypeField"):field));
			}
			details = calculusTypeService.requestCalculusTypePage(new RequestCalculusTypePageEvent(pageRequest));
		}
		
		PageWrapper<CalculusType> page=new PageWrapper<CalculusType>(details.getCalculusTypePage(),"/configuration/calculusType/searchCalculusType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/calculusType/searchCalculusType";
	}
}