package co.com.cybersoft.web.controller.wareHouse;

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

import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHousePageEvent;
import co.com.cybersoft.persistence.domain.WareHouse;

/**
 * Search controller class for wareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/wareHouse/searchWareHouse")
public class WareHouseSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WareHouseSearchController.class);

	@Autowired
	private WareHouseService wareHouseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  wareHouse ");
		WareHousePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("wareHouseField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("wareHouseAscending");
			if (request.getSession().getAttribute("wareHouseAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("wareHouseAscending", direction);
			}
			else
				request.getSession().setAttribute("wareHouseAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("wareHouseField")==null){
			details = wareHouseService.requestWareHousePage(new RequestWareHousePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("wareHouseAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("wareHouseAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("wareHouseField"):field));
			}
			details = wareHouseService.requestWareHousePage(new RequestWareHousePageEvent(pageRequest));
		}
		
		PageWrapper<WareHouse> page=new PageWrapper<WareHouse>(details.getWareHousePage(),"/configuration/wareHouse/searchWareHouse");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/wareHouse/searchWareHouse";
	}
}