package co.com.cybersoft.web.controller.branch;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.BranchDetails;
import co.com.cybersoft.core.services.branch.BranchService;
import co.com.cybersoft.events.branch.BranchPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/branch/getDescriptionList/{description}")
public class BranchSearchByDescriptionController {

	@Autowired
	private BranchService branchService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getBranchDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		BranchPageEvent descriptionEvent = branchService.requestByContainingDescription(description);
		
		List<BranchDetails> descriptions = descriptionEvent.getBranchList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (BranchDetails branchDetails : descriptions) {
			responseBuff.append(branchDetails.getDescription());
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