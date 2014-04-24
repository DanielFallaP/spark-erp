package co.com.cybersoft.web.controller.operationType;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/operationType/getCodeList/{code}")
public class OperationTypeSearchByCodeController {

	@Autowired
	private OperationTypeService operationTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getOperationTypeCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		OperationTypePageEvent codesEvent = operationTypeService.requestByCodePrefix(code);
		
		List<OperationTypeDetails> codes = codesEvent.getOperationTypeList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (OperationTypeDetails operationTypeDetails : codes) {
			responseBuff.append(operationTypeDetails.getCode());
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