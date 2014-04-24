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

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/contract/getCodeList/{code}")
public class ContractSearchByCodeController {

	@Autowired
	private ContractService contractService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getContractCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		ContractPageEvent codesEvent = contractService.requestByCodePrefix(code);
		
		List<ContractDetails> codes = codesEvent.getContractList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ContractDetails contractDetails : codes) {
			responseBuff.append(contractDetails.getCode());
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