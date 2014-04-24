package co.com.cybersoft.web.controller.active;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/active/getCodeList/{code}")
public class ActiveSearchByCodeController {

	@Autowired
	private ActiveService activeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getActiveCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		ActivePageEvent codesEvent = activeService.requestByCodePrefix(code);
		
		List<ActiveDetails> codes = codesEvent.getActiveList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (ActiveDetails activeDetails : codes) {
			responseBuff.append(activeDetails.getCode());
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