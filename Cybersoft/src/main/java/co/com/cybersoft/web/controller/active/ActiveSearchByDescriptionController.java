package co.com.cybersoft.web.controller.active;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/active/getDescriptionList/{description}")
public class ActiveSearchByDescriptionController {

	@Autowired
	private ActiveService activeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getActiveDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		ActivePageEvent descriptionEvent = activeService.requestByContainingDescription(description);
		
		List<ActiveDetails> descriptions = descriptionEvent.getActiveList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ActiveDetails activeDetails : descriptions) {
			responseBuff.append(activeDetails.getCode());
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