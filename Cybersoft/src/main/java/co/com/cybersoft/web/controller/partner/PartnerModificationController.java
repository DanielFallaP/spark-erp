package co.com.cybersoft.web.controller.partner;

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

import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.events.partner.PartnerDetailsEvent;
import co.com.cybersoft.events.partner.PartnerModificationEvent;
import co.com.cybersoft.events.partner.RequestPartnerDetailsEvent;
import co.com.cybersoft.web.domain.partner.PartnerInfo;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/partner/modifyPartner/{code}")
public class PartnerModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(PartnerModificationController.class);
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/partner/modifyPartner";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyPartner(@Valid @ModelAttribute("partnerInfo") PartnerInfo partnerInfo, HttpServletRequest request) throws Exception {
		PartnerDetails partnerDetails = createPartnerDetails(partnerInfo);
		partnerDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		partnerDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("partnerInfo", partnerInfo);
		partnerService.modifyPartner(new PartnerModificationEvent(partnerDetails));
		return "redirect:/configuration/partner/searchPartner";
	}
	
	private PartnerDetails createPartnerDetails(PartnerInfo partnerInfo){
		PartnerDetails partnerDetails = new PartnerDetails();
		BeanUtils.copyProperties(partnerInfo, partnerDetails);
		return partnerDetails;
	}

	@ModelAttribute("partnerInfo")
	private PartnerInfo getPartnerInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the partner "+code);
		PartnerInfo partnerInfo = new PartnerInfo();
		
		PartnerDetailsEvent requestPartnerDetails = partnerService.requestPartnerDetails(new RequestPartnerDetailsEvent(code));
		ActivePageEvent allActiveEvent = activeService.requestAll();
		partnerInfo.setActiveList(allActiveEvent.getActiveList());
		partnerInfo.rearrangeActiveList(partnerInfo.getActive());

		request.getSession().setAttribute("partnerInfo", partnerInfo);
		
		BeanUtils.copyProperties(requestPartnerDetails.getPartnerDetails(), partnerInfo);
		return partnerInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("partnerInfo", req.getSession().getAttribute("partnerInfo"));
			modelAndView.addObject("partnerValidationException",true);
			modelAndView.setViewName("/configuration/partner/modifyPartner");
		}
		else{
			modelAndView.addObject("partnerInfo", req.getSession().getAttribute("partnerInfo"));
			modelAndView.addObject("partnerModificationException",true);
			modelAndView.setViewName("/configuration/partner/modifyPartner");
		}
		return modelAndView;
	}
}