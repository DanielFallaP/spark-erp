package co.com.cybersoft.web.controller.corporation;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/corporation/getDescriptionList/{description}")
public class CorporationSearchByDescriptionController {

	@Autowired
	private CorporationService corporationService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCorporationDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		CorporationPageEvent descriptionEvent = corporationService.requestByContainingDescription(description);
		
		List<CorporationDetails> descriptions = descriptionEvent.getCorporationList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CorporationDetails corporationDetails : descriptions) {
			responseBuff.append(corporationDetails.getDescription());
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