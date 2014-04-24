package co.com.cybersoft.web.controller.afeType;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/afeType/getCodeList/{code}")
public class AfeTypeSearchByCodeController {

	@Autowired
	private AfeTypeService afeTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getAfeTypeCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		AfeTypePageEvent codesEvent = afeTypeService.requestByCodePrefix(code);
		
		List<AfeTypeDetails> codes = codesEvent.getAfeTypeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (AfeTypeDetails afeTypeDetails : codes) {
			responseBuff.append(afeTypeDetails.getCode());
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