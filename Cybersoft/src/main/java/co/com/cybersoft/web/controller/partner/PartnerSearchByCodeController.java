package co.com.cybersoft.web.controller.partner;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/partner/getCodeList/{code}")
public class PartnerSearchByCodeController {

	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getPartnerCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		PartnerPageEvent codesEvent = partnerService.requestByCodePrefix(code);
		
		List<PartnerDetails> codes = codesEvent.getPartnerList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (PartnerDetails partnerDetails : codes) {
			responseBuff.append(partnerDetails.getCode());
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