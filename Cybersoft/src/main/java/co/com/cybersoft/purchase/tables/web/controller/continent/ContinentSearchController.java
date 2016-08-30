package co.com.cybersoft.purchase.tables.web.controller.continent;

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

import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;

/**
 * Search controller class for continent
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/continent/searchContinent")
public class ContinentSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContinentSearchController.class);

	@Autowired
	private ContinentService continentService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ContinentFilterInfo f=(ContinentFilterInfo) request.getSession().getAttribute("continentFilter");
		if (f!=null){
			f.getContinentFilterList().clear();
		}
		
		LOG.debug("Retrieving  continent ");
		ContinentPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("continentAscending");
			String oldField=(String)request.getSession().getAttribute("continentField");
			if (oldField!=null && request.getSession().getAttribute("continentAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("continentAscending", direction);
			}
			else
				request.getSession().setAttribute("continentAscending", true);
			request.getSession().setAttribute("continentField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("continentField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = continentService.requestContinentPage(new RequestContinentPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("continentAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("continentAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("continentField"):field));
			}
			details = continentService.requestContinentPage(new RequestContinentPageEvent(pageRequest));
		}
		
		PageWrapper<Continent> page=new PageWrapper<Continent>(details.getContinentPage(),"/purchase/continent/searchContinent");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("continentField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		
		return "/purchase/continent/searchContinent";
	}
	
	private Boolean hasActions(ContinentFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("continentFilterInfo")ContinentFilterInfo filter, HttpServletRequest request) throws Exception{
		ContinentFilterInfo f=(ContinentFilterInfo) request.getSession().getAttribute("continentFilter");
		if (f!=null && f.getContinentFilterList().size()!=0)
			filter.getContinentFilterList().addAll(f.getContinentFilterList());
		if (filter.getAaddFilter())
			filter.getContinentFilterList().add((ContinentFilterInfo) (request.getSession().getAttribute("continentFilterCopy")!=null?request.getSession().getAttribute("continentFilterCopy"):new ContinentFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("continentAscending");
			String oldField=(String)request.getSession().getAttribute("continentField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("continentAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("continentAscending", direction);
			}
			else if (request.getSession().getAttribute("continentAscending")==null)
				request.getSession().setAttribute("continentAscending", true);
			request.getSession().setAttribute("continentField", filter.getSelectedFilterField());
		}
		
		RequestContinentPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("continentField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestContinentPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("continentAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("continentAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("continentField"):filter.getSelectedFilterField()));
				pageEvent = new RequestContinentPageEvent(pageRequest,filter);			}
		}
		
		ContinentPageEvent details = continentService.requestContinentFilterPage(pageEvent);
		PageWrapper<Continent> page=new PageWrapper<Continent>(details.getContinentPage(),"/purchase/continent/searchContinent");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("continentField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("continentFilter", filter);
    	request.getSession().setAttribute("continentFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/continent/searchContinent";
	}
	
	@ModelAttribute("continentFilterInfo")
	private ContinentFilterInfo getContinentFilterInfo(){
		return new ContinentFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}