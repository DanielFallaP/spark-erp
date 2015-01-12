package co.com.cybersoft.tables.web.controller.exchangeRate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionBodyInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.util.SparkBindingException;
import co.com.cybersoft.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepo;
import co.com.cybersoft.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

/**
 * Search controller class for exchangeRate
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/exchangeRate/searchExchangeRate")
public class ExchangeRateSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateSearchController.class);

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, Pageable pageable, @ModelAttribute("exchangeRateFilterInfo")ExchangeRateFilterInfo filter, HttpServletRequest request) throws Exception{
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.ASC,"_id");
		RequestExchangeRatePageEvent pageEvent = new RequestExchangeRatePageEvent(pageRequest,filter);
		ExchangeRatePageEvent details = exchangeRateService.requestExchangeRateFilterPage(pageEvent);
		PageWrapper<ExchangeRate> page=new PageWrapper<ExchangeRate>(details.getExchangeRatePage(),"/configuration/exchangeRate/searchExchangeRate");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("exchangeRateField"));
		return "/configuration/exchangeRate/searchExchangeRate";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
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
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			details = exchangeRateService.requestExchangeRatePage(new RequestExchangeRatePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("exchangeRateAscending")!=null){
								
				direction=(boolean) request.getSession().getAttribute("exchangeRateAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("exchangeRateField"):field));
			}
			details = exchangeRateService.requestExchangeRatePage(new RequestExchangeRatePageEvent(pageRequest));
		}
		
		PageWrapper<ExchangeRate> page=new PageWrapper<ExchangeRate>(details.getExchangeRatePage(),"/configuration/exchangeRate/searchExchangeRate");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("exchangeRateField"));
		model.addAttribute("_direction", direction);
		return "/configuration/exchangeRate/searchExchangeRate";
	}
	
	@ModelAttribute("exchangeRateFilterInfo")
	private ExchangeRateFilterInfo getExchangeRateFilterInfo(){
		return new ExchangeRateFilterInfo();
	}
}