package co.com.cybersoft.web.controller.items;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.ItemsDetails;
import co.com.cybersoft.core.services.items.ItemsService;
import co.com.cybersoft.events.items.ItemsPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/items/getDescriptionList/{description}")
public class ItemsSearchByDescriptionController {

	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getItemsDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		ItemsPageEvent descriptionEvent = itemsService.requestByContainingDescription(description);
		
		List<ItemsDetails> descriptions = descriptionEvent.getItemsList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ItemsDetails itemsDetails : descriptions) {
			responseBuff.append(itemsDetails.getDescription());
			if (i!=descriptions.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}