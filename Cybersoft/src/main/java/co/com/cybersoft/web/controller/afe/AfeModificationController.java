package co.com.cybersoft.web.controller.afe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.AfeDetailsEvent;
import co.com.cybersoft.events.afe.AfeModificationEvent;
import co.com.cybersoft.events.afe.RequestAfeDetailsEvent;
import co.com.cybersoft.web.domain.afe.AfeInfo;


/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afe/modifyAfe/{code}")
public class AfeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(AfeModificationController.class);
	
	@Autowired
	private AfeService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/afe/modifyAfe";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyAfe(@ModelAttribute("afeInfo") AfeInfo afeInfo) throws Exception {
		LOG.debug("Modification of afe "+afeInfo.getCode());
		AfeDetails afeDetails = createAfeDetails(afeInfo);
		afeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		afeService.modifyAfe(new AfeModificationEvent(afeDetails));
		return "redirect:/configuration/afe/searchAfe";
	}
	
	private AfeDetails createAfeDetails(AfeInfo afeInfo){
		AfeDetails afeDetails = new AfeDetails();
		LOG.debug(afeInfo.getCode());
		BeanUtils.copyProperties(afeInfo, afeDetails);
		return afeDetails;
	}

	@ModelAttribute("afeInfo")
	private AfeInfo getAfeInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the afe "+code);
		AfeDetailsEvent requestAfeDetails = afeService.requestAfeDetails(new RequestAfeDetailsEvent(code));
		AfeInfo afeInfo = new AfeInfo();
		BeanUtils.copyProperties(requestAfeDetails.getAfeDetails(), afeInfo);
		return afeInfo;
	}
}