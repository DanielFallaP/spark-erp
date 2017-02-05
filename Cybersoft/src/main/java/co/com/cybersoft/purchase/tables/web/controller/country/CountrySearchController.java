package co.com.cybersoft.purchase.tables.web.controller.country;

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

import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;

/**
 * Search controller class for country
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/country/searchCountry")
public class CountrySearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CountrySearchController.class);

	@Autowired
	private CountryService countryService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CountryFilterInfo f=(CountryFilterInfo) request.getSession().getAttribute("countryFilter");
		if (f!=null){
			f.getCountryFilterList().clear();
		}
		
		LOG.debug("Retrieving  country ");
		CountryPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("countryAscending");
			String oldField=(String)request.getSession().getAttribute("countryField");
			if (oldField!=null && request.getSession().getAttribute("countryAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("countryAscending", direction);
			}
			else
				request.getSession().setAttribute("countryAscending", true);
			request.getSession().setAttribute("countryField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("countryField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = countryService.requestCountryPage(new RequestCountryPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("countryAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("countryAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("countryField"):field));
			}
			details = countryService.requestCountryPage(new RequestCountryPageEvent(pageRequest));
		}
		
		PageWrapper<Country> page=new PageWrapper<Country>(details.getCountryPage(),"/purchase/country/searchCountry");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("countryField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/purchase/country/searchCountry";
	}
	
	private Boolean hasActions(CountryFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("countryFilterInfo")CountryFilterInfo filter, HttpServletRequest request) throws Exception{
		CountryFilterInfo f=(CountryFilterInfo) request.getSession().getAttribute("countryFilter");
		if (f!=null && f.getCountryFilterList().size()!=0)
			filter.getCountryFilterList().addAll(f.getCountryFilterList());
		if (filter.getAaddFilter())
			filter.getCountryFilterList().add((CountryFilterInfo) (request.getSession().getAttribute("countryFilterCopy")!=null?request.getSession().getAttribute("countryFilterCopy"):new CountryFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("countryAscending");
			String oldField=(String)request.getSession().getAttribute("countryField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("countryAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("countryAscending", direction);
			}
			else if (request.getSession().getAttribute("countryAscending")==null)
				request.getSession().setAttribute("countryAscending", true);
			request.getSession().setAttribute("countryField", filter.getSelectedFilterField());
		}
		
		RequestCountryPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("countryField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCountryPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("countryAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("countryAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("countryField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCountryPageEvent(pageRequest,filter);			}
		}
		
		CountryPageEvent details = countryService.requestCountryFilterPage(pageEvent);
		PageWrapper<Country> page=new PageWrapper<Country>(details.getCountryPage(),"/purchase/country/searchCountry");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("countryField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("countryFilter", filter);
    	request.getSession().setAttribute("countryFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/country/searchCountry";
	}
	
	@ModelAttribute("countryFilterInfo")
	private CountryFilterInfo getCountryFilterInfo(){
		return new CountryFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}