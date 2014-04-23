package co.com.cybersoft.web.controller.items;

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

import co.com.cybersoft.core.services.items.ItemsService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.items.ItemsPageEvent;
import co.com.cybersoft.events.items.RequestItemsPageEvent;
import co.com.cybersoft.persistence.domain.Items;

/**
 * Search controller class for items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/items/searchItems")
public class ItemsSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemsSearchController.class);

	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  items ");
		ItemsPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("itemsField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("itemsAscending");
			if (request.getSession().getAttribute("itemsAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("itemsAscending", direction);
			}
			else
				request.getSession().setAttribute("itemsAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("itemsField")==null){
			details = itemsService.requestItemsPage(new RequestItemsPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("itemsAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("itemsAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("itemsField"):field));
			}
			details = itemsService.requestItemsPage(new RequestItemsPageEvent(pageRequest));
		}
		
		PageWrapper<Items> page=new PageWrapper<Items>(details.getItemsPage(),"/configuration/items/searchItems");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/items/searchItems";
	}
}