package co.com.cybersoft.web.controller.corporation;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/corporation/getCodeList/{code}")
public class CorporationSearchByCodeController {

	@Autowired
	private CorporationService corporationService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCorporationCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		CorporationPageEvent codesEvent = corporationService.requestByCodePrefix(code);
		
		List<CorporationDetails> codes = codesEvent.getCorporationList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CorporationDetails corporationDetails : codes) {
			responseBuff.append(corporationDetails.getCode());
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