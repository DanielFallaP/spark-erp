package co.com.cybersoft.web.controller.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.ItemDetails;
import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.events.items.ItemDetailsEvent;
import co.com.cybersoft.events.items.ModifyItemEvent;
import co.com.cybersoft.events.items.RequestItemDetailsEvent;
import co.com.cybersoft.web.domain.items.ItemInfo;

@Controller
@RequestMapping("/configuration/items/modifyItem/{itemId}")
public class ItemModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ItemModificationController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String itemModification(){
		return "/configuration/items/modifyItem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyItem(@ModelAttribute("itemInfo") ItemInfo itemInfo){
		LOG.debug("Modification of item "+itemInfo.getCode());
		ItemDetails itemDetails = createItemDetails(itemInfo);
		itemService.modifyItem(new ModifyItemEvent(itemDetails));
		return "redirect:/configuration/items/searchItem";
	}
	
	private ItemDetails createItemDetails(ItemInfo itemInfo){
		ItemDetails itemDetails = new ItemDetails();
		LOG.debug(itemInfo.getCode());
		BeanUtils.copyProperties(itemInfo, itemDetails);
		return itemDetails;
	}

	@ModelAttribute("itemInfo")
	private ItemInfo getItemInfo(@PathVariable("itemId") String itemId){
		LOG.debug("Retrieving the item "+itemId);
		ItemDetailsEvent requestItemDetails = itemService.requestItemDetails(new RequestItemDetailsEvent(itemId));
		ItemInfo itemInfo = new ItemInfo();
		BeanUtils.copyProperties(requestItemDetails.getItemDetails(), itemInfo);
		return itemInfo;
	}
}
