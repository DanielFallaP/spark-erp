package co.com.cybersoft.purchase.tables.web.controller.region;

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

import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;

/**
 * Search controller class for region
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/region/searchRegion")
public class RegionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegionSearchController.class);

	@Autowired
	private RegionService regionService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		RegionFilterInfo f=(RegionFilterInfo) request.getSession().getAttribute("regionFilter");
		if (f!=null){
			f.getRegionFilterList().clear();
		}
		
		LOG.debug("Retrieving  region ");
		RegionPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("regionAscending");
			String oldField=(String)request.getSession().getAttribute("regionField");
			if (oldField!=null && request.getSession().getAttribute("regionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("regionAscending", direction);
			}
			else
				request.getSession().setAttribute("regionAscending", true);
			request.getSession().setAttribute("regionField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("regionField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = regionService.requestRegionPage(new RequestRegionPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("regionAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("regionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("regionField"):field));
			}
			details = regionService.requestRegionPage(new RequestRegionPageEvent(pageRequest));
		}
		
		PageWrapper<Region> page=new PageWrapper<Region>(details.getRegionPage(),"/purchase/region/searchRegion");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("regionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		
		return "/purchase/region/searchRegion";
	}
	
	private Boolean hasActions(RegionFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("regionFilterInfo")RegionFilterInfo filter, HttpServletRequest request) throws Exception{
		RegionFilterInfo f=(RegionFilterInfo) request.getSession().getAttribute("regionFilter");
		if (f!=null && f.getRegionFilterList().size()!=0)
			filter.getRegionFilterList().addAll(f.getRegionFilterList());
		if (filter.getAaddFilter())
			filter.getRegionFilterList().add((RegionFilterInfo) (request.getSession().getAttribute("regionFilterCopy")!=null?request.getSession().getAttribute("regionFilterCopy"):new RegionFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("regionAscending");
			String oldField=(String)request.getSession().getAttribute("regionField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("regionAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("regionAscending", direction);
			}
			else if (request.getSession().getAttribute("regionAscending")==null)
				request.getSession().setAttribute("regionAscending", true);
			request.getSession().setAttribute("regionField", filter.getSelectedFilterField());
		}
		
		RequestRegionPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("regionField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestRegionPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("regionAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("regionAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("regionField"):filter.getSelectedFilterField()));
				pageEvent = new RequestRegionPageEvent(pageRequest,filter);			}
		}
		
		RegionPageEvent details = regionService.requestRegionFilterPage(pageEvent);
		PageWrapper<Region> page=new PageWrapper<Region>(details.getRegionPage(),"/purchase/region/searchRegion");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("regionField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("regionFilter", filter);
    	request.getSession().setAttribute("regionFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/purchase/region/searchRegion";
	}
	
	@ModelAttribute("regionFilterInfo")
	private RegionFilterInfo getRegionFilterInfo(){
		return new RegionFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}