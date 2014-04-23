package co.com.cybersoft.web.controller.partner;

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

import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.CreatePartnerEvent;
import co.com.cybersoft.web.domain.partner.PartnerInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/partner/createPartner/{from}")
public class PartnerCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(PartnerCreationController.class);
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String partnerCreation() throws Exception {
		return "/configuration/partner/createPartner";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createPartner(@Valid @ModelAttribute("partnerInfo") PartnerInfo partnerInfo, Model model, HttpServletRequest request) throws Exception{
		partnerInfo.setCreated(false);
		PartnerDetails partnerDetails = createPartnerDetails(partnerInfo);
		partnerDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		partnerDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		partnerDetails.setDateOfCreation(new Date());
		partnerDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("partnerInfo", partnerInfo);
		partnerService.createPartner(new CreatePartnerEvent(partnerDetails));
		String calledFrom = partnerInfo.getCalledFrom();
		partnerInfo=new PartnerInfo();
		partnerInfo.setCreated(true);
		partnerInfo.setCalledFrom(calledFrom);
		ActivePageEvent allActiveEvent = activeService.requestAll();
		partnerInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("partnerInfo", partnerInfo);
		return "/configuration/partner/createPartner";
	}
	
	private PartnerDetails createPartnerDetails(PartnerInfo partnerInfo){
		PartnerDetails partnerDetails = new PartnerDetails();
		BeanUtils.copyProperties(partnerInfo, partnerDetails);
		return partnerDetails;
	}
	
	@ModelAttribute("partnerInfo")
	private PartnerInfo getPartnerInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		PartnerInfo partnerInfo = new PartnerInfo();
		
		ActivePageEvent allActiveEvent = activeService.requestAll();
		partnerInfo.setActiveList(allActiveEvent.getActiveList());

		
		partnerInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("partnerInfo", partnerInfo);
		
		return partnerInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("partnerInfo", req.getSession().getAttribute("partnerInfo"));
			modelAndView.addObject("partnerValidationException",true);
			modelAndView.setViewName("/configuration/partner/createPartner");
		}
		else{
			modelAndView.addObject("partnerInfo", req.getSession().getAttribute("partnerInfo"));
			modelAndView.addObject("partnerCreateException",true);
			modelAndView.setViewName("/configuration/partner/createPartner");
		}
		return modelAndView;
	}
	
}