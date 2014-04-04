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

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.items.ItemsEvent;
import co.com.cybersoft.events.items.RequestItemsEvent;
import co.com.cybersoft.persistence.domain.Item;

@Controller
@RequestMapping("/configuration/items/searchItem")
public class ItemSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String searchItemDetails(Model model, Pageable pageable, String field, HttpServletRequest request){
		LOG.debug("Retrieving  items ");
		ItemsEvent requestItemDetails;
		if (field!=null){
			request.getSession().setAttribute("itemField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("itemAscending");
			if (request.getSession().getAttribute("itemAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("itemAscending", direction);
			}
			else
				request.getSession().setAttribute("itemAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("itemField")==null){
			requestItemDetails = itemService.requestItems(new RequestItemsEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("itemAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("itemAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("itemField"):field));
			}
			requestItemDetails = itemService.requestItems(new RequestItemsEvent(pageRequest));
		}
		
		PageWrapper<Item> page=new PageWrapper<Item>(requestItemDetails.getItems(),"/configuration/items/searchItem");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/items/searchItem";
	}
}
