package co.com.cybersoft.web.controller.afe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.afe.AFEService;
import co.com.cybersoft.events.afe.AFEDetails;
import co.com.cybersoft.events.afe.CreateAFEEvent;
import co.com.cybersoft.web.controller.items.ItemCreationController;
import co.com.cybersoft.web.domain.AFEInfo;

@Controller
@RequestMapping("/configuration/afe/createAFE")
public class AFECreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);

	@Autowired
	private AFEService afeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String afeCreation(Model model){
		return "/configuration/afe/createAFE";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createAFE(@ModelAttribute("afeInfo") AFEInfo afeInfo){
		LOG.debug("Creating AFE!!!");
		AFEDetails afeDetails = createAFEDetails(afeInfo);
		afeService.createAFE(new CreateAFEEvent(afeDetails));
		return "/configuration/afe/createAFE";
	}
	
	private AFEDetails createAFEDetails(AFEInfo afeInfo){
		AFEDetails afeDetails = new AFEDetails();
		BeanUtils.copyProperties(afeInfo, afeDetails);
		return afeDetails;
	}
	
	@ModelAttribute("afeInfo")
	private AFEInfo getAFEInfo(){
		return new AFEInfo();
	}
	
}
