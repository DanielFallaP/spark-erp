package co.com.cybersoft.web.controller.contract;

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

import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.events.contract.ContractDetailsEvent;
import co.com.cybersoft.events.contract.ContractModificationEvent;
import co.com.cybersoft.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.web.domain.contract.ContractInfo;

/**
 * Controller class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/contract/modifyContract/{code}")
public class ContractModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ContractModificationController.class);
	
	@Autowired
	private ContractService contractService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/contract/modifyContract";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyContract(@Valid @ModelAttribute("contractInfo") ContractInfo contractInfo, HttpServletRequest request) throws Exception {
		ContractDetails contractDetails = createContractDetails(contractInfo);
		contractDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("contractInfo", contractInfo);
		contractService.modifyContract(new ContractModificationEvent(contractDetails));
		return "redirect:/configuration/contract/searchContract";
	}
	
	private ContractDetails createContractDetails(ContractInfo contractInfo){
		ContractDetails contractDetails = new ContractDetails();
		BeanUtils.copyProperties(contractInfo, contractDetails);
		return contractDetails;
	}

	@ModelAttribute("contractInfo")
	private ContractInfo getContractInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the contract "+code);
		ContractInfo contractInfo = new ContractInfo();
		
		ContractDetailsEvent requestContractDetails = contractService.requestContractDetails(new RequestContractDetailsEvent(code));
		request.getSession().setAttribute("contractInfo", contractInfo);
		
		BeanUtils.copyProperties(requestContractDetails.getContractDetails(), contractInfo);
		return contractInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractValidationException",true);
			modelAndView.setViewName("/configuration/contract/modifyContract");
		}
		else{
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractModificationException",true);
			modelAndView.setViewName("/configuration/contract/modifyContract");
		}
		return modelAndView;
	}
}