package co.com.cybersoft.web.controller.jointVenture;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/jointVenture/getCodeList/{code}")
public class JointVentureSearchByCodeController {

	@Autowired
	private JointVentureService jointVentureService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getJointVentureCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		JointVenturePageEvent codesEvent = jointVentureService.requestByCodePrefix(code);
		
		List<JointVentureDetails> codes = codesEvent.getJointVentureList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (JointVentureDetails jointVentureDetails : codes) {
			responseBuff.append(jointVentureDetails.getCode());
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