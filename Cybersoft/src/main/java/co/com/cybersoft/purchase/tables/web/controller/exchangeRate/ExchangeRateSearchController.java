package co.com.cybersoft.purchase.tables.web.controller.exchangeRate;

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

import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

/**
 * Search controller class for exchangeRate
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/exchangeRate/searchExchangeRate")
public class ExchangeRateSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateSearchController.class);

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
	 private co.com.cybersoft.man.maintenance.TechnicalActions technicalActionsAPI;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ExchangeRateFilterInfo f=(ExchangeRateFilterInfo) request.getSession().getAttribute("exchangeRateFilter");
		if (f!=null){
			f.getExchangeRateFilterList().clear();
		}
		
		LOG.debug("Retrieving  exchangeRate ");
		ExchangeRatePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("exchangeRateAscending");
			String oldField=(String)request.getSession().getAttribute("exchangeRateField");
			if (oldField!=null && request.getSession().getAttribute("exchangeRateAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("exchangeRateAscending", direction);
			}
			else
				request.getSession().setAttribute("exchangeRateAscending", true);
			request.getSession().setAttribute("exchangeRateField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("exchangeRateField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = exchangeRateService.requestExchangeRatePage(new RequestExchangeRatePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("exchangeRateAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("exchangeRateAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("exchangeRateField"):field));
			}
			details = exchangeRateService.requestExchangeRatePage(new RequestExchangeRatePageEvent(pageRequest));
		}
		
		PageWrapper<ExchangeRate> page=new PageWrapper<ExchangeRate>(details.getExchangeRatePage(),"/purchase/exchangeRate/searchExchangeRate");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("exchangeRateField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		
		return "/purchase/exchangeRate/searchExchangeRate";
	}
	
	private Boolean hasActions(ExchangeRateFilterInfo filter){
		if (filter.getAaaaction()!=null && filter.getAaaaction()!="")return true;

		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("exchangeRateFilterInfo")ExchangeRateFilterInfo filter, HttpServletRequest request) throws Exception{
		ExchangeRateFilterInfo f=(ExchangeRateFilterInfo) request.getSession().getAttribute("exchangeRateFilter");
		if (f!=null && f.getExchangeRateFilterList().size()!=0)
			filter.getExchangeRateFilterList().addAll(f.getExchangeRateFilterList());
		if (filter.getAaddFilter())
			filter.getExchangeRateFilterList().add((ExchangeRateFilterInfo) (request.getSession().getAttribute("exchangeRateFilterCopy")!=null?request.getSession().getAttribute("exchangeRateFilterCopy"):new ExchangeRateFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("exchangeRateAscending");
			String oldField=(String)request.getSession().getAttribute("exchangeRateField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("exchangeRateAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("exchangeRateAscending", direction);
			}
			else if (request.getSession().getAttribute("exchangeRateAscending")==null)
				request.getSession().setAttribute("exchangeRateAscending", true);
			request.getSession().setAttribute("exchangeRateField", filter.getSelectedFilterField());
		}
		
		RequestExchangeRatePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("exchangeRateField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestExchangeRatePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("exchangeRateAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("exchangeRateAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("exchangeRateField"):filter.getSelectedFilterField()));
				pageEvent = new RequestExchangeRatePageEvent(pageRequest,filter);			}
		}
		
		ExchangeRatePageEvent details = exchangeRateService.requestExchangeRateFilterPage(pageEvent);
		PageWrapper<ExchangeRate> page=new PageWrapper<ExchangeRate>(details.getExchangeRatePage(),"/purchase/exchangeRate/searchExchangeRate");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("exchangeRateField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		
		if (hasActions(filter)){
			if (filter.getAaaaction()!=null && filter.getAaaaction()!="" && filter.getAaaaction().equals("Transaction A"))technicalActionsAPI.transactionA(filter);
			if (filter.getAaaaction()!=null && filter.getAaaaction()!="" && filter.getAaaaction().equals("Transaction B"))technicalActionsAPI.transactionB(filter);
			if (filter.getAaaaction()!=null && filter.getAaaaction()!="" && filter.getAaaaction().equals("Transaction C"))technicalActionsAPI.transactionC(filter);
			if (filter.getAaaaction()!=null && filter.getAaaaction()!="" && filter.getAaaaction().equals("Transaction D"))technicalActionsAPI.transactionD(filter);

			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("exchangeRateFilter", filter);
    	request.getSession().setAttribute("exchangeRateFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/exchangeRate/searchExchangeRate";
	}
	
	@ModelAttribute("exchangeRateFilterInfo")
	private ExchangeRateFilterInfo getExchangeRateFilterInfo(){
		return new ExchangeRateFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}