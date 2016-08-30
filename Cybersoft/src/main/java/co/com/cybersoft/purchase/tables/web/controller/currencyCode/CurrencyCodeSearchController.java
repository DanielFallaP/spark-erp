package co.com.cybersoft.purchase.tables.web.controller.currencyCode;

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

import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;

/**
 * Search controller class for currencyCode
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/currencyCode/searchCurrencyCode")
public class CurrencyCodeSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CurrencyCodeSearchController.class);

	@Autowired
	private CurrencyCodeService currencyCodeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CurrencyCodeFilterInfo f=(CurrencyCodeFilterInfo) request.getSession().getAttribute("currencyCodeFilter");
		if (f!=null){
			f.getCurrencyCodeFilterList().clear();
		}
		
		LOG.debug("Retrieving  currencyCode ");
		CurrencyCodePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("currencyCodeAscending");
			String oldField=(String)request.getSession().getAttribute("currencyCodeField");
			if (oldField!=null && request.getSession().getAttribute("currencyCodeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("currencyCodeAscending", direction);
			}
			else
				request.getSession().setAttribute("currencyCodeAscending", true);
			request.getSession().setAttribute("currencyCodeField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("currencyCodeField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = currencyCodeService.requestCurrencyCodePage(new RequestCurrencyCodePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("currencyCodeAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("currencyCodeAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("currencyCodeField"):field));
			}
			details = currencyCodeService.requestCurrencyCodePage(new RequestCurrencyCodePageEvent(pageRequest));
		}
		
		PageWrapper<CurrencyCode> page=new PageWrapper<CurrencyCode>(details.getCurrencyCodePage(),"/purchase/currencyCode/searchCurrencyCode");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("currencyCodeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		
		return "/purchase/currencyCode/searchCurrencyCode";
	}
	
	private Boolean hasActions(CurrencyCodeFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("currencyCodeFilterInfo")CurrencyCodeFilterInfo filter, HttpServletRequest request) throws Exception{
		CurrencyCodeFilterInfo f=(CurrencyCodeFilterInfo) request.getSession().getAttribute("currencyCodeFilter");
		if (f!=null && f.getCurrencyCodeFilterList().size()!=0)
			filter.getCurrencyCodeFilterList().addAll(f.getCurrencyCodeFilterList());
		if (filter.getAaddFilter())
			filter.getCurrencyCodeFilterList().add((CurrencyCodeFilterInfo) (request.getSession().getAttribute("currencyCodeFilterCopy")!=null?request.getSession().getAttribute("currencyCodeFilterCopy"):new CurrencyCodeFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("currencyCodeAscending");
			String oldField=(String)request.getSession().getAttribute("currencyCodeField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("currencyCodeAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("currencyCodeAscending", direction);
			}
			else if (request.getSession().getAttribute("currencyCodeAscending")==null)
				request.getSession().setAttribute("currencyCodeAscending", true);
			request.getSession().setAttribute("currencyCodeField", filter.getSelectedFilterField());
		}
		
		RequestCurrencyCodePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("currencyCodeField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCurrencyCodePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("currencyCodeAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("currencyCodeAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("currencyCodeField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCurrencyCodePageEvent(pageRequest,filter);			}
		}
		
		CurrencyCodePageEvent details = currencyCodeService.requestCurrencyCodeFilterPage(pageEvent);
		PageWrapper<CurrencyCode> page=new PageWrapper<CurrencyCode>(details.getCurrencyCodePage(),"/purchase/currencyCode/searchCurrencyCode");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("currencyCodeField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("currencyCodeFilter", filter);
    	request.getSession().setAttribute("currencyCodeFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/currencyCode/searchCurrencyCode";
	}
	
	@ModelAttribute("currencyCodeFilterInfo")
	private CurrencyCodeFilterInfo getCurrencyCodeFilterInfo(){
		return new CurrencyCodeFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}