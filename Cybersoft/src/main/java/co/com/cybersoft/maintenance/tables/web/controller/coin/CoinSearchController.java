package co.com.cybersoft.maintenance.tables.web.controller.coin;

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

import co.com.cybersoft.maintenance.tables.core.services.coin.CoinService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.coin.CoinPageEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinFilterInfo;

/**
 * Search controller class for coin
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/coin/searchCoin")
public class CoinSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CoinSearchController.class);

	@Autowired
	private CoinService coinService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CoinFilterInfo f=(CoinFilterInfo) request.getSession().getAttribute("coinFilter");
		if (f!=null){
			f.getCoinFilterList().clear();
		}
		
		LOG.debug("Retrieving  coin ");
		CoinPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("coinAscending");
			String oldField=(String)request.getSession().getAttribute("coinField");
			if (oldField!=null && request.getSession().getAttribute("coinAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("coinAscending", direction);
			}
			else
				request.getSession().setAttribute("coinAscending", true);
			request.getSession().setAttribute("coinField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("coinField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = coinService.requestCoinPage(new RequestCoinPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("coinAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("coinAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("coinField"):field));
			}
			details = coinService.requestCoinPage(new RequestCoinPageEvent(pageRequest));
		}
		
		PageWrapper<Coin> page=new PageWrapper<Coin>(details.getCoinPage(),"/maintenance/coin/searchCoin");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("coinField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/coin/searchCoin";
	}
	
	private Boolean hasActions(CoinFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("coinFilterInfo")CoinFilterInfo filter, HttpServletRequest request) throws Exception{
		CoinFilterInfo f=(CoinFilterInfo) request.getSession().getAttribute("coinFilter");
		if (f!=null && f.getCoinFilterList().size()!=0)
			filter.getCoinFilterList().addAll(f.getCoinFilterList());
		if (filter.getAaddFilter())
			filter.getCoinFilterList().add((CoinFilterInfo) (request.getSession().getAttribute("coinFilterCopy")!=null?request.getSession().getAttribute("coinFilterCopy"):new CoinFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("coinAscending");
			String oldField=(String)request.getSession().getAttribute("coinField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("coinAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("coinAscending", direction);
			}
			else if (request.getSession().getAttribute("coinAscending")==null)
				request.getSession().setAttribute("coinAscending", true);
			request.getSession().setAttribute("coinField", filter.getSelectedFilterField());
		}
		
		RequestCoinPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("coinField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCoinPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("coinAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("coinAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("coinField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCoinPageEvent(pageRequest,filter);			}
		}
		
		CoinPageEvent details = coinService.requestCoinFilterPage(pageEvent);
		PageWrapper<Coin> page=new PageWrapper<Coin>(details.getCoinPage(),"/maintenance/coin/searchCoin");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("coinField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("coinFilter", filter);
    	request.getSession().setAttribute("coinFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/coin/searchCoin";
	}
	
	@ModelAttribute("coinFilterInfo")
	private CoinFilterInfo getCoinFilterInfo(){
		return new CoinFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}