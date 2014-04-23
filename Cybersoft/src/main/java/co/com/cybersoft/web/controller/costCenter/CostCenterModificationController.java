package co.com.cybersoft.web.controller.costCenter;

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

import co.com.cybersoft.core.domain.CostCenterDetails;
import co.com.cybersoft.core.services.costCenter.CostCenterService;
import co.com.cybersoft.events.costCenter.CostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.CostCenterModificationEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterDetailsEvent;
import co.com.cybersoft.web.domain.costCenter.CostCenterInfo;

/**
 * Controller class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/costCenter/modifyCostCenter/{code}")
public class CostCenterModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CostCenterModificationController.class);
	
	@Autowired
	private CostCenterService costCenterService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/costCenter/modifyCostCenter";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyCostCenter(@Valid @ModelAttribute("costCenterInfo") CostCenterInfo costCenterInfo, HttpServletRequest request) throws Exception {
		CostCenterDetails costCenterDetails = createCostCenterDetails(costCenterInfo);
		costCenterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		costCenterDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("costCenterInfo", costCenterInfo);
		costCenterService.modifyCostCenter(new CostCenterModificationEvent(costCenterDetails));
		return "redirect:/configuration/costCenter/searchCostCenter";
	}
	
	private CostCenterDetails createCostCenterDetails(CostCenterInfo costCenterInfo){
		CostCenterDetails costCenterDetails = new CostCenterDetails();
		BeanUtils.copyProperties(costCenterInfo, costCenterDetails);
		return costCenterDetails;
	}

	@ModelAttribute("costCenterInfo")
	private CostCenterInfo getCostCenterInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the costCenter "+code);
		CostCenterInfo costCenterInfo = new CostCenterInfo();
		
		CostCenterDetailsEvent requestCostCenterDetails = costCenterService.requestCostCenterDetails(new RequestCostCenterDetailsEvent(code));
		request.getSession().setAttribute("costCenterInfo", costCenterInfo);
		
		BeanUtils.copyProperties(requestCostCenterDetails.getCostCenterDetails(), costCenterInfo);
		return costCenterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("costCenterInfo", req.getSession().getAttribute("costCenterInfo"));
			modelAndView.addObject("costCenterValidationException",true);
			modelAndView.setViewName("/configuration/costCenter/modifyCostCenter");
		}
		else{
			modelAndView.addObject("costCenterInfo", req.getSession().getAttribute("costCenterInfo"));
			modelAndView.addObject("costCenterModificationException",true);
			modelAndView.setViewName("/configuration/costCenter/modifyCostCenter");
		}
		return modelAndView;
	}
}