package co.com.cybersoft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.ItemService;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.web.domain.ItemInfo;

@Controller
@RequestMapping("/configuration/items/searchItem")
public class ItemSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String searchItem(){
		return "/configuration/items/searchItem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String searchItemDetails(@ModelAttribute("itemInfo") ItemInfo itemInfo){
		LOG.debug("Searching item ");
		ItemDetailsEvent requestItemDetails = itemService.requestItemDetails(new RequestItemDetailsEvent(itemInfo.getCode()));
		BeanUtils.copyProperties(requestItemDetails.getItemDetails(), itemInfo);
		return "/configuration/items/searchItem";
	}
	
	@ModelAttribute("itemInfo")
	private ItemInfo getItemInfo(){
		return new ItemInfo();
	}
	
}
