package co.com.cybersoft.web.controller.company;

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

import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.events.company.CompanyDetailsEvent;
import co.com.cybersoft.events.company.CompanyModificationEvent;
import co.com.cybersoft.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.web.domain.company.CompanyInfo;
import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/company/modifyCompany/{code}")
public class CompanyModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(CompanyModificationController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
		private CorporationService corporationService;

	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/company/modifyCompany";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyCompany(@Valid @ModelAttribute("companyInfo") CompanyInfo companyInfo, HttpServletRequest request) throws Exception {
		CompanyDetails companyDetails = createCompanyDetails(companyInfo);
		companyDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		companyDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("companyInfo", companyInfo);
		companyService.modifyCompany(new CompanyModificationEvent(companyDetails));
		return "redirect:/configuration/company/searchCompany";
	}
	
	private CompanyDetails createCompanyDetails(CompanyInfo companyInfo){
		CompanyDetails companyDetails = new CompanyDetails();
		BeanUtils.copyProperties(companyInfo, companyDetails);
		return companyDetails;
	}

	@ModelAttribute("companyInfo")
	private CompanyInfo getCompanyInfo(@PathVariable("code") Integer code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the company "+code);
		CompanyInfo companyInfo = new CompanyInfo();
		
		CompanyDetailsEvent requestCompanyDetails = companyService.requestCompanyDetails(new RequestCompanyDetailsEvent(code));
		CorporationPageEvent allCorporationEvent = corporationService.requestAll();
		companyInfo.setCorporationList(allCorporationEvent.getCorporationList());
		companyInfo.rearrangeCorporationList(companyInfo.getCorporation());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		companyInfo.setActiveList(allActiveEvent.getActiveList());
		companyInfo.rearrangeActiveList(companyInfo.getActive());

		request.getSession().setAttribute("companyInfo", companyInfo);
		
		BeanUtils.copyProperties(requestCompanyDetails.getCompanyDetails(), companyInfo);
		return companyInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyValidationException",true);
			modelAndView.setViewName("/configuration/company/modifyCompany");
		}
		else{
			modelAndView.addObject("companyInfo", req.getSession().getAttribute("companyInfo"));
			modelAndView.addObject("companyModificationException",true);
			modelAndView.setViewName("/configuration/company/modifyCompany");
		}
		return modelAndView;
	}
}