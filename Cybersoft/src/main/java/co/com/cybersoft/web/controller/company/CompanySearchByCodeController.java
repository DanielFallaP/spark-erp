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

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/company/getCodeList/{code}")
public class CompanySearchByCodeController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCompanyCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		CompanyPageEvent codesEvent = companyService.requestByCodePrefix(code);
		
		List<CompanyDetails> codes = codesEvent.getCompanyList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CompanyDetails companyDetails : codes) {
			responseBuff.append(companyDetails.getCode());
			if (i!=codes.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}