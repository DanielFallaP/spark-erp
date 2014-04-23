package co.com.cybersoft.web.controller.wareHouse;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/wareHouse/getDescriptionList/{description}")
public class WareHouseSearchByDescriptionController {

	@Autowired
	private WareHouseService wareHouseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getWareHouseDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		WareHousePageEvent descriptionEvent = wareHouseService.requestByContainingDescription(description);
		
		List<WareHouseDetails> descriptions = descriptionEvent.getWareHouseList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (WareHouseDetails wareHouseDetails : descriptions) {
			responseBuff.append(wareHouseDetails.getCode());
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
