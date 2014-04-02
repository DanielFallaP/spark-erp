package co.com.cybersoft.web.controller.items;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.ItemService;
import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemDetails;
import co.com.cybersoft.web.domain.items.ItemInfo;

@Controller
@RequestMapping("/configuration/items/createItem")
public class ItemCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String itemCreation(){
		return "/configuration/items/createItem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createItem(@Valid @ModelAttribute("itemInfo") ItemInfo itemInfo, Model model){
		LOG.debug("Creation of an item!!!");
		itemInfo.setItemCreated(false);
		ItemDetails itemDetails = createItemDetails(itemInfo);
		itemService.createItem(new CreateItemEvent(itemDetails));
		itemInfo=new ItemInfo();
		itemInfo.setItemCreated(true);
		model.addAttribute("itemInfo", itemInfo);
		return "/configuration/items/createItem";
	}
	
	private ItemDetails createItemDetails(ItemInfo itemInfo){
		ItemDetails itemDetails = new ItemDetails();
		LOG.debug(itemInfo.getCode());
		BeanUtils.copyProperties(itemInfo, itemDetails);
		return itemDetails;
	}
	
	@ModelAttribute("itemInfo")
	private ItemInfo getItemssInfo(){
		return new ItemInfo();
	}
}
