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

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/branch/getCodeList/{code}")
public class BranchSearchByCodeController {

	@Autowired
	private BranchService branchService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getBranchCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		BranchPageEvent codesEvent = branchService.requestByCodePrefix(code);
		
		List<BranchDetails> codes = codesEvent.getBranchList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (BranchDetails branchDetails : codes) {
			responseBuff.append(branchDetails.getCode());
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