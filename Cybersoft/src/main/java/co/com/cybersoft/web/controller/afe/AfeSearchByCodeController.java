package co.com.cybersoft.web.controller.afe;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/afe/getCodeList/{code}")
public class AfeSearchByCodeController {

	@Autowired
	private AfeService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getAfeCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		AfePageEvent codesEvent = afeService.requestByCodePrefix(code);
		
		List<AfeDetails> codes = codesEvent.getAfeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (AfeDetails afeDetails : codes) {
			responseBuff.append(afeDetails.getCode());
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