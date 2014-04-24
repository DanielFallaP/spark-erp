package co.com.cybersoft.web.controller.costCenter;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CostCenterDetails;
import co.com.cybersoft.core.services.costCenter.CostCenterService;
import co.com.cybersoft.events.costCenter.CostCenterPageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/costCenter/getCodeList/{code}")
public class CostCenterSearchByCodeController {

	@Autowired
	private CostCenterService costCenterService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCostCenterCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		CostCenterPageEvent codesEvent = costCenterService.requestByCodePrefix(code);
		
		List<CostCenterDetails> codes = codesEvent.getCostCenterList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CostCenterDetails costCenterDetails : codes) {
			responseBuff.append(costCenterDetails.getCode());
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