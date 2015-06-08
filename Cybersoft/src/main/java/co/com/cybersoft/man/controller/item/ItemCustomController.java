package co.com.cybersoft.man.controller.item;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.cybersoft.man.services.item.ItemManService;
import co.com.cybersoft.purchase.tables.web.domain.itemPurchaseHistory.ItemPurchaseHistoryInfo;

@Controller
public class ItemCustomController {
	
	@Autowired 
	ItemManService itemService;

	@RequestMapping(value="/custom/itemHistory/lastPurchase", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemPurchaseHistoryInfo getLastPurchasePrices(HttpServletRequest request) throws Exception{
		if (request.getParameter("item")!=null)
			return itemService.getItemLastPurchasePriceByItemCode(request.getParameter("item"));
		else
			return itemService.getItemLastPurchasePriceByDescription(request.getParameter("description"));
	}
}
