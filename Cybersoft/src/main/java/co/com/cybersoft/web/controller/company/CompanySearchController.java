package co.com.cybersoft.web.controller.company;

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

import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.company.CompanyPageEvent;
import co.com.cybersoft.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.persistence.domain.Company;

/**
 * Search controller class for company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/company/searchCompany")
public class CompanySearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompanySearchController.class);

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  company ");
		CompanyPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("companyField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("companyAscending");
			if (request.getSession().getAttribute("companyAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("companyAscending", direction);
			}
			else
				request.getSession().setAttribute("companyAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("companyField")==null){
			details = companyService.requestCompanyPage(new RequestCompanyPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("companyAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("companyAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("companyField"):field));
			}
			details = companyService.requestCompanyPage(new RequestCompanyPageEvent(pageRequest));
		}
		
		PageWrapper<Company> page=new PageWrapper<Company>(details.getCompanyPage(),"/configuration/company/searchCompany");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/company/searchCompany";
	}
}