package co.com.cybersoft.purchase.tables.web.controller.region;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
 * @author Cybersystems 2015. All rights reserved.
 *
 */
@Controller
@RequestMapping("/purchase/region/searchRegion")
public class RegionSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegionSearchController.class);

	@Autowired
	private RegionService regionService;
	
	@Transactional
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
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
								
				direction=(boolean) request.getSession().getAttribute("regionAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("regionField"):field));
			}
			details = regionService.requestRegionPage(new RequestRegionPageEvent(pageRequest));
		}
		
		PageWrapper<Region> page=new PageWrapper<Region>(details.getRegionPage(),"/purchase/region/searchRegion");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("regionField"));
		model.addAttribute("_direction", direction);
		return "/purchase/region/searchRegion";
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("regionFilterInfo")RegionFilterInfo filter, HttpServletRequest request) throws Exception{
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
				direction=(boolean) request.getSession().getAttribute("regionAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("regionField"):filter.getSelectedFilterField()));
				pageEvent = new RequestRegionPageEvent(pageRequest,filter);			}
		}
		RegionPageEvent details = regionService.requestRegionFilterPage(pageEvent);
		PageWrapper<Region> page=new PageWrapper<Region>(details.getRegionPage(),"/purchase/region/searchRegion");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("regionField"));
		model.addAttribute("_direction", direction);
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