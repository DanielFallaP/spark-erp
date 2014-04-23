package co.com.cybersoft.web.controller.company;

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

import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CreateCompanyEvent;
import co.com.cybersoft.web.domain.company.CompanyInfo;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/company/createCompany/{from}")
public class CompanyCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyCreationController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
		private CorporationService corporationService;

	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String companyCreation() throws Exception {
		return "/configuration/company/createCompany";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCompany(@Valid @ModelAttribute("companyInfo") CompanyInfo companyInfo, Model model, HttpServletRequest request) throws Exception{
		companyInfo.setCreated(false);
		CompanyDetails companyDetails = createCompanyDetails(companyInfo);
		companyDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setDateOfCreation(new Date());
		companyDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("companyInfo", companyInfo);
		companyService.createCompany(new CreateCompanyEvent(companyDetails));
		String calledFrom = companyInfo.getCalledFrom();
		companyInfo=new CompanyInfo();
		companyInfo.setCreated(true);
		companyInfo.setCalledFrom(calledFrom);
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		companyInfo.setCorporationList(allCorporationEvent.getCorporationList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		companyInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("companyInfo", companyInfo);
		return "/configuration/company/createCompany";
	}
	
	private CompanyDetails createCompanyDetails(CompanyInfo companyInfo){
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyInfo, companyDetails);
		return companyDetails;
	}
	
	@ModelAttribute("companyInfo")
	private CompanyInfo getCompanyInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CompanyInfo companyInfo = new CompanyInfo();
		
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		companyInfo.setCorporationList(allCorporationEvent.getCorporationList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		companyInfo.setActiveList(allActiveEvent.getActiveList());

		
		companyInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("companyInfo", companyInfo);
		
		return companyInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyValidationException",true);
			modelAndView.setViewName("/configuration/company/createCompany");
		}
		else{
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyCreateException",true);
			modelAndView.setViewName("/configuration/company/createCompany");
		}
		return modelAndView;
	}
	
}