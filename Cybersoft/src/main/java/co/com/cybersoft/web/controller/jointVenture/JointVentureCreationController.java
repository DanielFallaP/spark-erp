package co.com.cybersoft.web.controller.jointVenture;

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

import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.events.jointVenture.CreateJointVentureEvent;
import co.com.cybersoft.web.domain.jointVenture.JointVentureInfo;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for jointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/jointVenture/createJointVenture/{from}")
public class JointVentureCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(JointVentureCreationController.class);
	
	@Autowired
	private JointVentureService jointVentureService;
	
	@Autowired
		private BillService billService;

	@Autowired
		private PartnerService partnerService;

	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String jointVentureCreation() throws Exception {
		return "/configuration/jointVenture/createJointVenture";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createJointVenture(@Valid @ModelAttribute("jointVentureInfo") JointVentureInfo jointVentureInfo, Model model, HttpServletRequest request) throws Exception{
		jointVentureInfo.setCreated(false);
		JointVentureDetails jointVentureDetails = createJointVentureDetails(jointVentureInfo);
		jointVentureDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		jointVentureDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		jointVentureDetails.setDateOfCreation(new Date());
		jointVentureDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("jointVentureInfo", jointVentureInfo);
		jointVentureService.createJointVenture(new CreateJointVentureEvent(jointVentureDetails));
		String calledFrom = jointVentureInfo.getCalledFrom();
		jointVentureInfo=new JointVentureInfo();
		jointVentureInfo.setCreated(true);
		jointVentureInfo.setCalledFrom(calledFrom);
		BillPageEvent allBillEvent = billService.requestAll();
		jointVentureInfo.setBillList(allBillEvent.getBillList());
		PartnerPageEvent allPartnerEvent = partnerService.requestAll();
		jointVentureInfo.setPartnerList(allPartnerEvent.getPartnerList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		jointVentureInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("jointVentureInfo", jointVentureInfo);
		return "/configuration/jointVenture/createJointVenture";
	}
	
	private JointVentureDetails createJointVentureDetails(JointVentureInfo jointVentureInfo){
		JointVentureDetails jointVentureDetails = new JointVentureDetails();
		BeanUtils.copyProperties(jointVentureInfo, jointVentureDetails);
		return jointVentureDetails;
	}
	
	@ModelAttribute("jointVentureInfo")
	private JointVentureInfo getJointVentureInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		JointVentureInfo jointVentureInfo = new JointVentureInfo();
		
		BillPageEvent allBillEvent = billService.requestAll();
		jointVentureInfo.setBillList(allBillEvent.getBillList());
		PartnerPageEvent allPartnerEvent = partnerService.requestAll();
		jointVentureInfo.setPartnerList(allPartnerEvent.getPartnerList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		jointVentureInfo.setActiveList(allActiveEvent.getActiveList());

		
		jointVentureInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("jointVentureInfo", jointVentureInfo);
		
		return jointVentureInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("jointVentureInfo", req.getSession().getAttribute("jointVentureInfo"));
			modelAndView.addObject("jointVentureValidationException",true);
			modelAndView.setViewName("/configuration/jointVenture/createJointVenture");
		}
		else{
			modelAndView.addObject("jointVentureInfo", req.getSession().getAttribute("jointVentureInfo"));
			modelAndView.addObject("jointVentureCreateException",true);
			modelAndView.setViewName("/configuration/jointVenture/createJointVenture");
		}
		return modelAndView;
	}
	
}