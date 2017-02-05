package co.com.cybersoft.maintenance.tables.web.controller.company;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;

/**
 * Search controller class for company
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/company/searchCompany")
public class CompanySearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompanySearchController.class);

	@Autowired
	private CompanyService companyService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CompanyFilterInfo f=(CompanyFilterInfo) request.getSession().getAttribute("companyFilter");
		if (f!=null){
			f.getCompanyFilterList().clear();
		}
		
		LOG.debug("Retrieving  company ");
		CompanyPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("companyAscending");
			String oldField=(String)request.getSession().getAttribute("companyField");
			if (oldField!=null && request.getSession().getAttribute("companyAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("companyAscending", direction);
			}
			else
				request.getSession().setAttribute("companyAscending", true);
			request.getSession().setAttribute("companyField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("companyField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = companyService.requestCompanyPage(new RequestCompanyPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("companyAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("companyAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("companyField"):field));
			}
			details = companyService.requestCompanyPage(new RequestCompanyPageEvent(pageRequest));
		}
		
		PageWrapper<Company> page=new PageWrapper<Company>(details.getCompanyPage(),"/maintenance/company/searchCompany");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("companyField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/company/searchCompany";
	}
	
	private Boolean hasActions(CompanyFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("companyFilterInfo")CompanyFilterInfo filter, HttpServletRequest request) throws Exception{
		CompanyFilterInfo f=(CompanyFilterInfo) request.getSession().getAttribute("companyFilter");
		if (f!=null && f.getCompanyFilterList().size()!=0)
			filter.getCompanyFilterList().addAll(f.getCompanyFilterList());
		if (filter.getAaddFilter())
			filter.getCompanyFilterList().add((CompanyFilterInfo) (request.getSession().getAttribute("companyFilterCopy")!=null?request.getSession().getAttribute("companyFilterCopy"):new CompanyFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("companyAscending");
			String oldField=(String)request.getSession().getAttribute("companyField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("companyAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("companyAscending", direction);
			}
			else if (request.getSession().getAttribute("companyAscending")==null)
				request.getSession().setAttribute("companyAscending", true);
			request.getSession().setAttribute("companyField", filter.getSelectedFilterField());
		}
		
		RequestCompanyPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("companyField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCompanyPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("companyAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("companyAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("companyField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCompanyPageEvent(pageRequest,filter);			}
		}
		
		CompanyPageEvent details = companyService.requestCompanyFilterPage(pageEvent);
		PageWrapper<Company> page=new PageWrapper<Company>(details.getCompanyPage(),"/maintenance/company/searchCompany");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("companyField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("companyFilter", filter);
    	request.getSession().setAttribute("companyFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/company/searchCompany";
	}
	
	@ModelAttribute("companyFilterInfo")
	private CompanyFilterInfo getCompanyFilterInfo(){
		return new CompanyFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}