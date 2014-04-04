package co.com.cybersoft.web.controller.items;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.events.items.CreateItemEvent;
import co.com.cybersoft.events.items.ItemDetails;
import co.com.cybersoft.web.domain.items.ItemInfo;

@Controller
@RequestMapping("/configuration/items/createItem/{from}")
public class ItemCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String itemCreation(){
		return "/configuration/items/createItem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createItem(@Valid @ModelAttribute("itemInfo") ItemInfo itemInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of an item!!!");
		itemInfo.setItemCreated(false);
		ItemDetails itemDetails = createItemDetails(itemInfo);
		request.getSession().setAttribute("itemInfo", itemInfo);
		itemService.createItem(new CreateItemEvent(itemDetails));
		String calledFrom = itemInfo.getCalledFrom();
		itemInfo=new ItemInfo();
		itemInfo.setItemCreated(true);
		itemInfo.setCalledFrom(calledFrom);
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
	private ItemInfo getItemInfo(@PathVariable("from") String calledFrom){
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setCalledFrom(calledFrom);
		return itemInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemInfo", req.getSession().getAttribute("itemInfo"));
		modelAndView.addObject("itemCreateException",true);
		modelAndView.setViewName("/configuration/items/createItem");
		exception.printStackTrace();
		return modelAndView;
	}
	
}
