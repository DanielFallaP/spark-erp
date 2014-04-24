package co.com.cybersoft.web.controller.contract;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/contract/getDescriptionList/{description}")
public class ContractSearchByDescriptionController {

	@Autowired
	private ContractService contractService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getContractDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		ContractPageEvent descriptionEvent = contractService.requestByContainingDescription(description);
		
		List<ContractDetails> descriptions = descriptionEvent.getContractList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ContractDetails contractDetails : descriptions) {
			responseBuff.append(contractDetails.getDescription());
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