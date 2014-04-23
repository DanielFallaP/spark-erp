package co.com.cybersoft.web.controller.branch;

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

import co.com.cybersoft.core.domain.BranchDetails;
import co.com.cybersoft.core.services.branch.BranchService;
import co.com.cybersoft.events.branch.CreateBranchEvent;
import co.com.cybersoft.web.domain.branch.BranchInfo;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/branch/createBranch/{from}")
public class BranchCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(BranchCreationController.class);
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
		private CorporationService corporationService;

	@Autowired
		private CompanyService companyService;

	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String branchCreation() throws Exception {
		return "/configuration/branch/createBranch";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createBranch(@Valid @ModelAttribute("branchInfo") BranchInfo branchInfo, Model model, HttpServletRequest request) throws Exception{
		branchInfo.setCreated(false);
		BranchDetails branchDetails = createBranchDetails(branchInfo);
		branchDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		branchDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		branchDetails.setDateOfCreation(new Date());
		branchDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("branchInfo", branchInfo);
		branchService.createBranch(new CreateBranchEvent(branchDetails));
		String calledFrom = branchInfo.getCalledFrom();
		branchInfo=new BranchInfo();
		branchInfo.setCreated(true);
		branchInfo.setCalledFrom(calledFrom);
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		branchInfo.setCorporationList(allCorporationEvent.getCorporationList());
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		branchInfo.setCompanyList(allCompanyEvent.getCompanyList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		branchInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("branchInfo", branchInfo);
		return "/configuration/branch/createBranch";
	}
	
	private BranchDetails createBranchDetails(BranchInfo branchInfo){
		BranchDetails branchDetails = new BranchDetails();
		BeanUtils.copyProperties(branchInfo, branchDetails);
		return branchDetails;
	}
	
	@ModelAttribute("branchInfo")
	private BranchInfo getBranchInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		BranchInfo branchInfo = new BranchInfo();
		
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		branchInfo.setCorporationList(allCorporationEvent.getCorporationList());
		CompanyPageEvent allCompanyEvent = companyService.requestAll();
		branchInfo.setCompanyList(allCompanyEvent.getCompanyList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		branchInfo.setActiveList(allActiveEvent.getActiveList());

		
		branchInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("branchInfo", branchInfo);
		
		return branchInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("branchInfo", req.getSession().getAttribute("branchInfo"));
			modelAndView.addObject("branchValidationException",true);
			modelAndView.setViewName("/configuration/branch/createBranch");
		}
		else{
			modelAndView.addObject("branchInfo", req.getSession().getAttribute("branchInfo"));
			modelAndView.addObject("branchCreateException",true);
			modelAndView.setViewName("/configuration/branch/createBranch");
		}
		return modelAndView;
	}
	
}