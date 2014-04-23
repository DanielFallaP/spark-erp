package co.com.cybersoft.web.controller.jointVenture;

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

import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.events.jointVenture.JointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.JointVentureModificationEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVentureDetailsEvent;
import co.com.cybersoft.web.domain.jointVenture.JointVentureInfo;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/jointVenture/modifyJointVenture/{code}")
public class JointVentureModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(JointVentureModificationController.class);
	
	@Autowired
	private JointVentureService jointVentureService;
	
	@Autowired
		private BillService billService;

	@Autowired
		private PartnerService partnerService;

	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/jointVenture/modifyJointVenture";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyJointVenture(@Valid @ModelAttribute("jointVentureInfo") JointVentureInfo jointVentureInfo, HttpServletRequest request) throws Exception {
		JointVentureDetails jointVentureDetails = createJointVentureDetails(jointVentureInfo);
		jointVentureDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		jointVentureDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("jointVentureInfo", jointVentureInfo);
		jointVentureService.modifyJointVenture(new JointVentureModificationEvent(jointVentureDetails));
		return "redirect:/configuration/jointVenture/searchJointVenture";
	}
	
	private JointVentureDetails createJointVentureDetails(JointVentureInfo jointVentureInfo){
		JointVentureDetails jointVentureDetails = new JointVentureDetails();
		BeanUtils.copyProperties(jointVentureInfo, jointVentureDetails);
		return jointVentureDetails;
	}

	@ModelAttribute("jointVentureInfo")
	private JointVentureInfo getJointVentureInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the jointVenture "+code);
		JointVentureInfo jointVentureInfo = new JointVentureInfo();
		
		JointVentureDetailsEvent requestJointVentureDetails = jointVentureService.requestJointVentureDetails(new RequestJointVentureDetailsEvent(code));
		BillPageEvent allBillEvent = billService.requestAll();
		jointVentureInfo.setBillList(allBillEvent.getBillList());
		jointVentureInfo.rearrangeBillList(jointVentureInfo.getBill());
		PartnerPageEvent allPartnerEvent = partnerService.requestAll();
		jointVentureInfo.setPartnerList(allPartnerEvent.getPartnerList());
		jointVentureInfo.rearrangePartnerList(jointVentureInfo.getPartner());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		jointVentureInfo.setActiveList(allActiveEvent.getActiveList());
		jointVentureInfo.rearrangeActiveList(jointVentureInfo.getActive());

		request.getSession().setAttribute("jointVentureInfo", jointVentureInfo);
		
		BeanUtils.copyProperties(requestJointVentureDetails.getJointVentureDetails(), jointVentureInfo);
		return jointVentureInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("jointVentureInfo", req.getSession().getAttribute("jointVentureInfo"));
			modelAndView.addObject("jointVentureValidationException",true);
			modelAndView.setViewName("/configuration/jointVenture/modifyJointVenture");
		}
		else{
			modelAndView.addObject("jointVentureInfo", req.getSession().getAttribute("jointVentureInfo"));
			modelAndView.addObject("jointVentureModificationException",true);
			modelAndView.setViewName("/configuration/jointVenture/modifyJointVenture");
		}
		return modelAndView;
	}
}