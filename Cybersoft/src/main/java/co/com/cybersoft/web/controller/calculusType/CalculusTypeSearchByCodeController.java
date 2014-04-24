package co.com.cybersoft.web.controller.calculusType;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/calculusType/getCodeList/{code}")
public class CalculusTypeSearchByCodeController {

	@Autowired
	private CalculusTypeService calculusTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getCalculusTypeCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		CalculusTypePageEvent codesEvent = calculusTypeService.requestByCodePrefix(code);
		
		List<CalculusTypeDetails> codes = codesEvent.getCalculusTypeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (CalculusTypeDetails calculusTypeDetails : codes) {
			responseBuff.append(calculusTypeDetails.getCode());
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