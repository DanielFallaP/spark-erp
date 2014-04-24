package co.com.cybersoft.web.controller.calculusType;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/calculusType/getDescriptionList/{description}")
public class CalculusTypeSearchByDescriptionController {

	@Autowired
	private CalculusTypeService calculusTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCalculusTypeDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		CalculusTypePageEvent descriptionEvent = calculusTypeService.requestByContainingDescription(description);
		
		List<CalculusTypeDetails> descriptions = descriptionEvent.getCalculusTypeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CalculusTypeDetails calculusTypeDetails : descriptions) {
			responseBuff.append(calculusTypeDetails.getDescription());
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