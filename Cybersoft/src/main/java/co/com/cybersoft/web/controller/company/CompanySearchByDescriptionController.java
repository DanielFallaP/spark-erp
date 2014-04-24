package co.com.cybersoft.web.controller.company;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/company/getDescriptionList/{description}")
public class CompanySearchByDescriptionController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCompanyDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		CompanyPageEvent descriptionEvent = companyService.requestByContainingDescription(description);
		
		List<CompanyDetails> descriptions = descriptionEvent.getCompanyList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CompanyDetails companyDetails : descriptions) {
			responseBuff.append(companyDetails.getDescription());
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