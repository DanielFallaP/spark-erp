package co.com.cybersoft.web.controller.partner;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/partner/getDescriptionList/{description}")
public class PartnerSearchByDescriptionController {

	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getPartnerDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		PartnerPageEvent descriptionEvent = partnerService.requestByContainingDescription(description);
		
		List<PartnerDetails> descriptions = descriptionEvent.getPartnerList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (PartnerDetails partnerDetails : descriptions) {
			responseBuff.append(partnerDetails.getDescription());
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