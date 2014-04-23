package co.com.cybersoft.web.controller.afe;

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

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.AfeDetailsEvent;
import co.com.cybersoft.events.afe.AfeModificationEvent;
import co.com.cybersoft.events.afe.RequestAfeDetailsEvent;
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
 * Controller class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/afe/modifyAfe/{code}")
public class AfeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(AfeModificationController.class);
	
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
	public String modification(){
		return "/configuration/afe/modifyAfe";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyAfe(@Valid @ModelAttribute("afeInfo") AfeInfo afeInfo, HttpServletRequest request) throws Exception {
		AfeDetails afeDetails = createAfeDetails(afeInfo);
		afeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		afeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("afeInfo", afeInfo);
		afeService.modifyAfe(new AfeModificationEvent(afeDetails));
		return "redirect:/configuration/afe/searchAfe";
	}
	
	private AfeDetails createAfeDetails(AfeInfo afeInfo){
		AfeDetails afeDetails = new AfeDetails();
		BeanUtils.copyProperties(afeInfo, afeDetails);
		return afeDetails;
	}

	@ModelAttribute("afeInfo")
	private AfeInfo getAfeInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the afe "+code);
		AfeInfo afeInfo = new AfeInfo();
		
		AfeDetailsEvent requestAfeDetails = afeService.requestAfeDetails(new RequestAfeDetailsEvent(code));
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		afeInfo.setCompanyList(allCompanyEvent.getCompanyList());
		afeInfo.rearrangeCompanyList(afeInfo.getCompany());
		AfeTypePageEvent allAfeTypeEvent = afeTypeService.requestAll();
		afeInfo.setAfeTypeList(allAfeTypeEvent.getAfeTypeList());
		afeInfo.rearrangeAfeTypeList(afeInfo.getAfeType());
		ContractPageEvent allContractEvent = contractService.requestAll();
		afeInfo.setContractList(allContractEvent.getContractList());
		afeInfo.rearrangeContractList(afeInfo.getContract());
		BillPageEvent allBillEvent = billService.requestAll();
		afeInfo.setBillList(allBillEvent.getBillList());
		afeInfo.rearrangeBillList(afeInfo.getBill());

		request.getSession().setAttribute("afeInfo", afeInfo);
		
		BeanUtils.copyProperties(requestAfeDetails.getAfeDetails(), afeInfo);
		return afeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("afeInfo", req.getSession().getAttribute("afeInfo"));
			modelAndView.addObject("afeValidationException",true);
			modelAndView.setViewName("/configuration/afe/modifyAfe");
		}
		else{
			modelAndView.addObject("afeInfo", req.getSession().getAttribute("afeInfo"));
			modelAndView.addObject("afeModificationException",true);
			modelAndView.setViewName("/configuration/afe/modifyAfe");
		}
		return modelAndView;
	}
}