package co.com.cybersoft.web.controller.paymentType;

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

import co.com.cybersoft.core.services.paymentType.PaymentTypeService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.paymentType.PaymentTypePageEvent;
import co.com.cybersoft.events.paymentType.RequestPaymentTypePageEvent;
import co.com.cybersoft.persistence.domain.PaymentType;

/**
 * Search controller class for paymentType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/paymentType/searchPaymentType")
public class PaymentTypeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentTypeSearchController.class);

	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  paymentType ");
		PaymentTypePageEvent details;
		if (field!=null){
			request.getSession().setAttribute("paymentTypeField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("paymentTypeAscending");
			if (request.getSession().getAttribute("paymentTypeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("paymentTypeAscending", direction);
			}
			else
				request.getSession().setAttribute("paymentTypeAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("paymentTypeField")==null){
			details = paymentTypeService.requestPaymentTypePage(new RequestPaymentTypePageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("paymentTypeAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("paymentTypeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("paymentTypeField"):field));
			}
			details = paymentTypeService.requestPaymentTypePage(new RequestPaymentTypePageEvent(pageRequest));
		}
		
		PageWrapper<PaymentType> page=new PageWrapper<PaymentType>(details.getPaymentTypePage(),"/configuration/paymentType/searchPaymentType");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/paymentType/searchPaymentType";
	}
}