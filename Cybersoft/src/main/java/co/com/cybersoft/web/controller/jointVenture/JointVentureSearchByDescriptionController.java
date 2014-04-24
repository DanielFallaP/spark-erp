package co.com.cybersoft.web.controller.jointVenture;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/jointVenture/getDescriptionList/{description}")
public class JointVentureSearchByDescriptionController {

	@Autowired
	private JointVentureService jointVentureService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getJointVentureDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		JointVenturePageEvent descriptionEvent = jointVentureService.requestByContainingDescription(description);
		
		List<JointVentureDetails> descriptions = descriptionEvent.getJointVentureList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (JointVentureDetails jointVentureDetails : descriptions) {
			responseBuff.append(jointVentureDetails.getCode());
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