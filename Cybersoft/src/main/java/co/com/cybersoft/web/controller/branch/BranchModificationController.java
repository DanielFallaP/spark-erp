package co.com.cybersoft.web.controller.branch;

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

import co.com.cybersoft.core.domain.BranchDetails;
import co.com.cybersoft.core.services.branch.BranchService;
import co.com.cybersoft.events.branch.BranchDetailsEvent;
import co.com.cybersoft.events.branch.BranchModificationEvent;
import co.com.cybersoft.events.branch.RequestBranchDetailsEvent;
import co.com.cybersoft.web.domain.branch.BranchInfo;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/branch/modifyBranch/{code}")
public class BranchModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(BranchModificationController.class);
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
		private CorporationService corporationService;

	@Autowired
		private CompanyService companyService;

	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/branch/modifyBranch";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyBranch(@Valid @ModelAttribute("branchInfo") BranchInfo branchInfo, HttpServletRequest request) throws Exception {
		BranchDetails branchDetails = createBranchDetails(branchInfo);
		branchDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		branchDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("branchInfo", branchInfo);
		branchService.modifyBranch(new BranchModificationEvent(branchDetails));
		return "redirect:/configuration/branch/searchBranch";
	}
	
	private BranchDetails createBranchDetails(BranchInfo branchInfo){
		BranchDetails branchDetails = new BranchDetails();
		BeanUtils.copyProperties(branchInfo, branchDetails);
		return branchDetails;
	}

	@ModelAttribute("branchInfo")
	private BranchInfo getBranchInfo(@PathVariable("code") Integer code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the branch "+code);
		BranchInfo branchInfo = new BranchInfo();
		
		BranchDetailsEvent requestBranchDetails = branchService.requestBranchDetails(new RequestBranchDetailsEvent(code));
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		branchInfo.setCorporationList(allCorporationEvent.getCorporationList());
		branchInfo.rearrangeCorporationList(branchInfo.getCorporation());
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		branchInfo.setCompanyList(allCompanyEvent.getCompanyList());
		branchInfo.rearrangeCompanyList(branchInfo.getCompany());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		branchInfo.setActiveList(allActiveEvent.getActiveList());
		branchInfo.rearrangeActiveList(branchInfo.getActive());

		request.getSession().setAttribute("branchInfo", branchInfo);
		
		BeanUtils.copyProperties(requestBranchDetails.getBranchDetails(), branchInfo);
		return branchInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("branchInfo", req.getSession().getAttribute("branchInfo"));
			modelAndView.addObject("branchValidationException",true);
			modelAndView.setViewName("/configuration/branch/modifyBranch");
		}
		else{
			modelAndView.addObject("branchInfo", req.getSession().getAttribute("branchInfo"));
			modelAndView.addObject("branchModificationException",true);
			modelAndView.setViewName("/configuration/branch/modifyBranch");
		}
		return modelAndView;
	}
}