package co.com.cybersoft.web.controller.bill;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.BillDetailsEvent;
import co.com.cybersoft.events.bill.BillModificationEvent;
import co.com.cybersoft.events.bill.RequestBillDetailsEvent;
import co.com.cybersoft.web.domain.bill.BillInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/bill/modifyBill/{code}")
public class BillModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(BillModificationController.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/bill/modifyBill";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyBill(@Valid @ModelAttribute("billInfo") BillInfo billInfo, HttpServletRequest request) throws Exception {
		BillDetails billDetails = createBillDetails(billInfo);
		billDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		billDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("billInfo", billInfo);
		billService.modifyBill(new BillModificationEvent(billDetails));
		return "redirect:/configuration/bill/searchBill";
	}
	
	private BillDetails createBillDetails(BillInfo billInfo){
		BillDetails billDetails = new BillDetails();
		BeanUtils.copyProperties(billInfo, billDetails);
		return billDetails;
	}

	@ModelAttribute("billInfo")
	private BillInfo getBillInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the bill "+code);
		BillInfo billInfo = new BillInfo();
		
		BillDetailsEvent requestBillDetails = billService.requestBillDetails(new RequestBillDetailsEvent(code));
		ActivePageEvent allActiveEvent = activeService.requestAll();
		billInfo.setActiveList(allActiveEvent.getActiveList());
		billInfo.rearrangeActiveList(billInfo.getActive());

		request.getSession().setAttribute("billInfo", billInfo);
		
		BeanUtils.copyProperties(requestBillDetails.getBillDetails(), billInfo);
		return billInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("billInfo", req.getSession().getAttribute("billInfo"));
			modelAndView.addObject("billValidationException",true);
			modelAndView.setViewName("/configuration/bill/modifyBill");
		}
		else{
			modelAndView.addObject("billInfo", req.getSession().getAttribute("billInfo"));
			modelAndView.addObject("billModificationException",true);
			modelAndView.setViewName("/configuration/bill/modifyBill");
		}
		return modelAndView;
	}
}