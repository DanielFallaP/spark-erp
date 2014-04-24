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

@Controller
@RequestMapping("/configuration/bill/getDescriptionList/{description}")
public class BillSearchByDescriptionController {

	@Autowired
	private BillService billService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getBillDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		BillPageEvent descriptionEvent = billService.requestByContainingDescription(description);
		
		List<BillDetails> descriptions = descriptionEvent.getBillList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (BillDetails billDetails : descriptions) {
			responseBuff.append(billDetails.getDescription());
			if (i!=descriptions.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}