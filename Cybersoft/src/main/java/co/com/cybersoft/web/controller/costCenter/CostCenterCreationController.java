package co.com.cybersoft.web.controller.costCenter;

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

import co.com.cybersoft.core.domain.CostCenterDetails;
import co.com.cybersoft.core.services.costCenter.CostCenterService;
import co.com.cybersoft.events.costCenter.CreateCostCenterEvent;
import co.com.cybersoft.web.domain.costCenter.CostCenterInfo;

/**
 * Controller for costCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/costCenter/createCostCenter/{from}")
public class CostCenterCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CostCenterCreationController.class);
	
	@Autowired
	private CostCenterService costCenterService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String costCenterCreation() throws Exception {
		return "/configuration/costCenter/createCostCenter";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCostCenter(@Valid @ModelAttribute("costCenterInfo") CostCenterInfo costCenterInfo, Model model, HttpServletRequest request) throws Exception{
		costCenterInfo.setCreated(false);
		CostCenterDetails costCenterDetails = createCostCenterDetails(costCenterInfo);
		costCenterDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		costCenterDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		costCenterDetails.setDateOfCreation(new Date());
		costCenterDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("costCenterInfo", costCenterInfo);
		costCenterService.createCostCenter(new CreateCostCenterEvent(costCenterDetails));
		String calledFrom = costCenterInfo.getCalledFrom();
		costCenterInfo=new CostCenterInfo();
		costCenterInfo.setCreated(true);
		costCenterInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("costCenterInfo", costCenterInfo);
		return "/configuration/costCenter/createCostCenter";
	}
	
	private CostCenterDetails createCostCenterDetails(CostCenterInfo costCenterInfo){
		CostCenterDetails costCenterDetails = new CostCenterDetails();
		BeanUtils.copyProperties(costCenterInfo, costCenterDetails);
		return costCenterDetails;
	}
	
	@ModelAttribute("costCenterInfo")
	private CostCenterInfo getCostCenterInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CostCenterInfo costCenterInfo = new CostCenterInfo();
		
		
		costCenterInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("costCenterInfo", costCenterInfo);
		
		return costCenterInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("costCenterInfo", req.getSession().getAttribute("costCenterInfo"));
			modelAndView.addObject("costCenterValidationException",true);
			modelAndView.setViewName("/configuration/costCenter/createCostCenter");
		}
		else{
			modelAndView.addObject("costCenterInfo", req.getSession().getAttribute("costCenterInfo"));
			modelAndView.addObject("costCenterCreateException",true);
			modelAndView.setViewName("/configuration/costCenter/createCostCenter");
		}
		return modelAndView;
	}
	
}