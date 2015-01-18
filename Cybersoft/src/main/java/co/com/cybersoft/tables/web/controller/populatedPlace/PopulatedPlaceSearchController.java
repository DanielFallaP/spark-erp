package co.com.cybersoft.tables.web.controller.populatedPlace;

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

import co.com.cybersoft.tables.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.tables.events.populatedPlace.PopulatedPlacePageEvent;
import co.com.cybersoft.tables.events.populatedPlace.RequestPopulatedPlacePageEvent;
import co.com.cybersoft.tables.persistence.domain.PopulatedPlace;
import co.com.cybersoft.tables.web.domain.populatedPlace.PopulatedPlaceFilterInfo;

/**
 * Search controller class for populatedPlace
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/populatedPlace/searchPopulatedPlace")
public class PopulatedPlaceSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PopulatedPlaceSearchController.class);

	@Autowired
	private PopulatedPlaceService populatedPlaceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  populatedPlace ");
		PopulatedPlacePageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("populatedPlaceAscending");
			String oldField=(String)request.getSession().getAttribute("populatedPlaceField");
			if (oldField!=null && request.getSession().getAttribute("populatedPlaceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("populatedPlaceAscending", direction);
			}
			else
				request.getSession().setAttribute("populatedPlaceAscending", true);
			request.getSession().setAttribute("populatedPlaceField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("populatedPlaceField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			details = populatedPlaceService.requestPopulatedPlacePage(new RequestPopulatedPlacePageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("populatedPlaceAscending")!=null){
								
				direction=(boolean) request.getSession().getAttribute("populatedPlaceAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("populatedPlaceField"):field));
			}
			details = populatedPlaceService.requestPopulatedPlacePage(new RequestPopulatedPlacePageEvent(pageRequest));
		}
		
		PageWrapper<PopulatedPlace> page=new PageWrapper<PopulatedPlace>(details.getPopulatedPlacePage(),"/configuration/populatedPlace/searchPopulatedPlace");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("populatedPlaceField"));
		model.addAttribute("_direction", direction);
		return "/configuration/populatedPlace/searchPopulatedPlace";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, Pageable pageable, String field, @ModelAttribute("populatedPlaceFilterInfo")PopulatedPlaceFilterInfo filter, HttpServletRequest request) throws Exception{
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("populatedPlaceAscending");
			String oldField=(String)request.getSession().getAttribute("populatedPlaceField");
			if (oldField!=null && request.getSession().getAttribute("populatedPlaceAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("populatedPlaceAscending", direction);
			}
			else
				request.getSession().setAttribute("populatedPlaceAscending", true);
			request.getSession().setAttribute("populatedPlaceField", field);
		}
		
		RequestPopulatedPlacePageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("populatedPlaceField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"_id");
			pageEvent = new RequestPopulatedPlacePageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("populatedPlaceAscending")!=null){
				direction=(boolean) request.getSession().getAttribute("populatedPlaceAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("populatedPlaceField"):field));
			}
		}
		PopulatedPlacePageEvent details = populatedPlaceService.requestPopulatedPlaceFilterPage(pageEvent);
		PageWrapper<PopulatedPlace> page=new PageWrapper<PopulatedPlace>(details.getPopulatedPlacePage(),"/configuration/populatedPlace/searchPopulatedPlace");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("populatedPlaceField"));
		model.addAttribute("_direction", direction);
		return "/configuration/populatedPlace/searchPopulatedPlace";
	}
	
	@ModelAttribute("populatedPlaceFilterInfo")
	private PopulatedPlaceFilterInfo getPopulatedPlaceFilterInfo(){
		return new PopulatedPlaceFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}