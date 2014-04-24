package co.com.cybersoft.web.controller.afe;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/afe/getDescriptionList/{description}")
public class AfeSearchByDescriptionController {

	@Autowired
	private AfeService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getAfeDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		AfePageEvent descriptionEvent = afeService.requestByContainingDescription(description);
		
		List<AfeDetails> descriptions = descriptionEvent.getAfeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (AfeDetails afeDetails : descriptions) {
			responseBuff.append(afeDetails.getDescription());
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