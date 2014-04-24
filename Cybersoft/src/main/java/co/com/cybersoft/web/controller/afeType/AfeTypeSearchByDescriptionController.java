package co.com.cybersoft.web.controller.afeType;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/afeType/getDescriptionList/{description}")
public class AfeTypeSearchByDescriptionController {

	@Autowired
	private AfeTypeService afeTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getAfeTypeDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		AfeTypePageEvent descriptionEvent = afeTypeService.requestByContainingDescription(description);
		
		List<AfeTypeDetails> descriptions = descriptionEvent.getAfeTypeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (AfeTypeDetails afeTypeDetails : descriptions) {
			responseBuff.append(afeTypeDetails.getDescription());
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