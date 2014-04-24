package co.com.cybersoft.web.controller.bill;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.CreateBillEvent;
import co.com.cybersoft.web.domain.bill.BillInfo;
import co.com.cybersoft.events.bill.BillDetailsEvent;
import co.com.cybersoft.events.bill.RequestBillDetailsEvent;


import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/bill/createBill/{from}")
public class BillCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(BillCreationController.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String billCreation() throws Exception {
		return "/configuration/bill/createBill";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createBill(@Valid @ModelAttribute("billInfo") BillInfo billInfo, Model model, HttpServletRequest request) throws Exception{
		billInfo.setCreated(false);
		BillDetails billDetails = createBillDetails(billInfo);
		billDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		billDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		billDetails.setDateOfCreation(new Date());
		billDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("billInfo", billInfo);
		billService.createBill(new CreateBillEvent(billDetails));
		String calledFrom = billInfo.getCalledFrom();
		billInfo=new BillInfo();
		billInfo.setCreated(true);
		billInfo.setCalledFrom(calledFrom);
		ActivePageEvent allActiveEvent = activeService.requestAll();
		billInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("billInfo", billInfo);
		return "/configuration/bill/createBill";
	}
	
	private BillDetails createBillDetails(BillInfo billInfo){
		BillDetails billDetails = new BillDetails();
		BeanUtils.copyProperties(billInfo, billDetails);
		return billDetails;
	}
	
	@ModelAttribute("billInfo")
	private BillInfo getBillInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		BillInfo billInfo = new BillInfo();
		
		String code = request.getParameter("id");
		String description = request.getParameter("desc");
		if (code!=null){
			BillDetailsEvent responseEvent = billService.requestBillDetails(new RequestBillDetailsEvent(code));
			if (responseEvent.getBillDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getBillDetails(), billInfo);
		}
		
		if (description!=null){
			RequestBillDetailsEvent event = new RequestBillDetailsEvent(null);
			event.setDescription(description);
			BillDetailsEvent responseEvent = billService.requestBillDetails(event);
			if (responseEvent.getBillDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getBillDetails(), billInfo);
		}
		
		billInfo.setId(null);
		
		ActivePageEvent allActiveEvent = activeService.requestAll();
		billInfo.setActiveList(allActiveEvent.getActiveList());

		
		billInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("billInfo", billInfo);
		
		return billInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("billInfo", req.getSession().getAttribute("billInfo"));
			modelAndView.addObject("billValidationException",true);
			modelAndView.setViewName("/configuration/bill/createBill");
		}
		else{
			modelAndView.addObject("billInfo", req.getSession().getAttribute("billInfo"));
			modelAndView.addObject("billCreateException",true);
			modelAndView.setViewName("/configuration/bill/createBill");
		}
		return modelAndView;
	}
	
}