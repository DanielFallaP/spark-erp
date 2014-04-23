package co.com.cybersoft.web.controller.bill;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.events.bill.RequestBillPageEvent;
import co.com.cybersoft.persistence.domain.Bill;

/**
 * Search controller class for bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/bill/searchBill")
public class BillSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BillSearchController.class);

	@Autowired
	private BillService billService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  bill ");
		BillPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("billField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("billAscending");
			if (request.getSession().getAttribute("billAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("billAscending", direction);
			}
			else
				request.getSession().setAttribute("billAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("billField")==null){
			details = billService.requestBillPage(new RequestBillPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("billAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("billAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("billField"):field));
			}
			details = billService.requestBillPage(new RequestBillPageEvent(pageRequest));
		}
		
		PageWrapper<Bill> page=new PageWrapper<Bill>(details.getBillPage(),"/configuration/bill/searchBill");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/bill/searchBill";
	}
}