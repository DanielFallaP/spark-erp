package co.com.cybersoft.purchase.tables.web.controller.currency;

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

import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;

/**
 * Search controller class for currency
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currency/searchCurrency")
public class CurrencySearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CurrencySearchController.class);

	@Autowired
	private CurrencyService currencyService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CurrencyFilterInfo f=(CurrencyFilterInfo) request.getSession().getAttribute("currencyFilter");
		if (f!=null){
			f.getCurrencyFilterList().clear();
		}
		
		LOG.debug("Retrieving  currency ");
		CurrencyPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("currencyAscending");
			String oldField=(String)request.getSession().getAttribute("currencyField");
			if (oldField!=null && request.getSession().getAttribute("currencyAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("currencyAscending", direction);
			}
			else
				request.getSession().setAttribute("currencyAscending", true);
			request.getSession().setAttribute("currencyField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("currencyField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = currencyService.requestCurrencyPage(new RequestCurrencyPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("currencyAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("currencyAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("currencyField"):field));
			}
			details = currencyService.requestCurrencyPage(new RequestCurrencyPageEvent(pageRequest));
		}
		
		PageWrapper<Currency> page=new PageWrapper<Currency>(details.getCurrencyPage(),"/purchase/currency/searchCurrency");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("currencyField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/purchase/currency/searchCurrency";
	}
	
	private Boolean hasActions(CurrencyFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("currencyFilterInfo")CurrencyFilterInfo filter, HttpServletRequest request) throws Exception{
		CurrencyFilterInfo f=(CurrencyFilterInfo) request.getSession().getAttribute("currencyFilter");
		if (f!=null && f.getCurrencyFilterList().size()!=0)
			filter.getCurrencyFilterList().addAll(f.getCurrencyFilterList());
		if (filter.getAaddFilter())
			filter.getCurrencyFilterList().add((CurrencyFilterInfo) (request.getSession().getAttribute("currencyFilterCopy")!=null?request.getSession().getAttribute("currencyFilterCopy"):new CurrencyFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("currencyAscending");
			String oldField=(String)request.getSession().getAttribute("currencyField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("currencyAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("currencyAscending", direction);
			}
			else if (request.getSession().getAttribute("currencyAscending")==null)
				request.getSession().setAttribute("currencyAscending", true);
			request.getSession().setAttribute("currencyField", filter.getSelectedFilterField());
		}
		
		RequestCurrencyPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("currencyField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCurrencyPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("currencyAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("currencyAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("currencyField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCurrencyPageEvent(pageRequest,filter);			}
		}
		
		CurrencyPageEvent details = currencyService.requestCurrencyFilterPage(pageEvent);
		PageWrapper<Currency> page=new PageWrapper<Currency>(details.getCurrencyPage(),"/purchase/currency/searchCurrency");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("currencyField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("currencyFilter", filter);
    	request.getSession().setAttribute("currencyFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/currency/searchCurrency";
	}
	
	@ModelAttribute("currencyFilterInfo")
	private CurrencyFilterInfo getCurrencyFilterInfo(){
		return new CurrencyFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}