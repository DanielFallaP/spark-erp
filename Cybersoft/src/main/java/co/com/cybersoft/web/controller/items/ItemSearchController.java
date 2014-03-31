package co.com.cybersoft.web.controller.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.ItemService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.persistence.domain.Item;
import co.com.cybersoft.web.domain.ItemInfo;

@Controller
@RequestMapping("/configuration/items/searchItem")
public class ItemSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String searchItemDetails(Model model, Pageable pageable){
		LOG.debug("Retrieving  items ");
		ItemDetailsEvent requestItemDetails = itemService.requestItemDetails(new RequestItemDetailsEvent(pageable));
		
		PageWrapper<Item> page=new PageWrapper<Item>(requestItemDetails.getItems(),"/configuration/items/searchItem");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/items/searchItem";
	}
	
	@ModelAttribute("itemInfo")
	private ItemInfo getItemInfo(){
		return new ItemInfo();
	}
	
}
