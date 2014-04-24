package co.com.cybersoft.web.controller.bill;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/bill/getCodeList/{code}")
public class BillSearchByCodeController {

	@Autowired
	private BillService billService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getBillCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		BillPageEvent codesEvent = billService.requestByCodePrefix(code);
		
		List<BillDetails> codes = codesEvent.getBillList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (BillDetails billDetails : codes) {
			responseBuff.append(billDetails.getCode());
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