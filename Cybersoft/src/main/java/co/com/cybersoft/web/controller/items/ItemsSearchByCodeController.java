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

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/items/getCodeList/{code}")
public class ItemsSearchByCodeController {

	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getItemsCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		ItemsPageEvent codesEvent = itemsService.requestByCodePrefix(code);
		
		List<ItemsDetails> codes = codesEvent.getItemsList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ItemsDetails itemsDetails : codes) {
			responseBuff.append(itemsDetails.getCode());
			if (i!=codes.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}