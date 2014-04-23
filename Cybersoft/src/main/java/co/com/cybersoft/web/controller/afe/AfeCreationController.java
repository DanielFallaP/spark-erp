package co.com.cybersoft.web.controller.afe;

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

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.CreateAfeEvent;
import co.com.cybersoft.web.domain.afe.AfeInfo;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.events.bill.BillPageEvent;


/**
 * Controller for afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afe/createAfe/{from}")
public class AfeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(AfeCreationController.class);
	
	@Autowired
	private AfeService afeService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private AfeTypeService afeTypeService;

	@Autowired
		private ContractService contractService;

	@Autowired
		private BillService billService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String afeCreation() throws Exception {
		return "/configuration/afe/createAfe";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createAfe(@Valid @ModelAttribute("afeInfo") AfeInfo afeInfo, Model model, HttpServletRequest request) throws Exception{
		afeInfo.setCreated(false);
		AfeDetails afeDetails = createAfeDetails(afeInfo);
		afeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		afeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		afeDetails.setDateOfCreation(new Date());
		afeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("afeInfo", afeInfo);
		afeService.createAfe(new CreateAfeEvent(afeDetails));
		String calledFrom = afeInfo.getCalledFrom();
		afeInfo=new AfeInfo();
		afeInfo.setCreated(true);
		afeInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		afeInfo.setCompanyList(allCompanyEvent.getCompanyList());
		AfeTypePageEvent allAfeTypeEvent = afeTypeService.requestAll();
		afeInfo.setAfeTypeList(allAfeTypeEvent.getAfeTypeList());
		ContractPageEvent allContractEvent = contractService.requestAll();
		afeInfo.setContractList(allContractEvent.getContractList());
		BillPageEvent allBillEvent = billService.requestAll();
		afeInfo.setBillList(allBillEvent.getBillList());

		
		model.addAttribute("afeInfo", afeInfo);
		return "/configuration/afe/createAfe";
	}
	
	private AfeDetails createAfeDetails(AfeInfo afeInfo){
		AfeDetails afeDetails = new AfeDetails();
		BeanUtils.copyProperties(afeInfo, afeDetails);
		return afeDetails;
	}
	
	@ModelAttribute("afeInfo")
	private AfeInfo getAfeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		AfeInfo afeInfo = new AfeInfo();
		
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		afeInfo.setCompanyList(allCompanyEvent.getCompanyList());
		AfeTypePageEvent allAfeTypeEvent = afeTypeService.requestAll();
		afeInfo.setAfeTypeList(allAfeTypeEvent.getAfeTypeList());
		ContractPageEvent allContractEvent = contractService.requestAll();
		afeInfo.setContractList(allContractEvent.getContractList());
		BillPageEvent allBillEvent = billService.requestAll();
		afeInfo.setBillList(allBillEvent.getBillList());

		
		afeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("afeInfo", afeInfo);
		
		return afeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("afeInfo", req.getSession().getAttribute("afeInfo"));
			modelAndView.addObject("afeValidationException",true);
			modelAndView.setViewName("/configuration/afe/createAfe");
		}
		else{
			modelAndView.addObject("afeInfo", req.getSession().getAttribute("afeInfo"));
			modelAndView.addObject("afeCreateException",true);
			modelAndView.setViewName("/configuration/afe/createAfe");
		}
		return modelAndView;
	}
	
}