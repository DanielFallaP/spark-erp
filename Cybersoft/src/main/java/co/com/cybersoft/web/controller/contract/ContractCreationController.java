package co.com.cybersoft.web.controller.contract;

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

import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.events.contract.CreateContractEvent;
import co.com.cybersoft.web.domain.contract.ContractInfo;

/**
 * Controller for contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/contract/createContract/{from}")
public class ContractCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ContractCreationController.class);
	
	@Autowired
	private ContractService contractService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String contractCreation() throws Exception {
		return "/configuration/contract/createContract";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createContract(@Valid @ModelAttribute("contractInfo") ContractInfo contractInfo, Model model, HttpServletRequest request) throws Exception{
		contractInfo.setCreated(false);
		ContractDetails contractDetails = createContractDetails(contractInfo);
		contractDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setDateOfCreation(new Date());
		contractDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("contractInfo", contractInfo);
		contractService.createContract(new CreateContractEvent(contractDetails));
		String calledFrom = contractInfo.getCalledFrom();
		contractInfo=new ContractInfo();
		contractInfo.setCreated(true);
		contractInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("contractInfo", contractInfo);
		return "/configuration/contract/createContract";
	}
	
	private ContractDetails createContractDetails(ContractInfo contractInfo){
		ContractDetails contractDetails = new ContractDetails();
		BeanUtils.copyProperties(contractInfo, contractDetails);
		return contractDetails;
	}
	
	@ModelAttribute("contractInfo")
	private ContractInfo getContractInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ContractInfo contractInfo = new ContractInfo();
		
		
		contractInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("contractInfo", contractInfo);
		
		return contractInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractValidationException",true);
			modelAndView.setViewName("/configuration/contract/createContract");
		}
		else{
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractCreateException",true);
			modelAndView.setViewName("/configuration/contract/createContract");
		}
		return modelAndView;
	}
	
}