package co.com.cybersoft.maintenance.tables.web.controller.characteristic;

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

import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.characteristic.CharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.events.characteristic.RequestCharacteristicPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicFilterInfo;

/**
 * Search controller class for characteristic
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/characteristic/searchCharacteristic")
public class CharacteristicSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CharacteristicSearchController.class);

	@Autowired
	private CharacteristicService characteristicService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		CharacteristicFilterInfo f=(CharacteristicFilterInfo) request.getSession().getAttribute("characteristicFilter");
		if (f!=null){
			f.getCharacteristicFilterList().clear();
		}
		
		LOG.debug("Retrieving  characteristic ");
		CharacteristicPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("characteristicAscending");
			String oldField=(String)request.getSession().getAttribute("characteristicField");
			if (oldField!=null && request.getSession().getAttribute("characteristicAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("characteristicAscending", direction);
			}
			else
				request.getSession().setAttribute("characteristicAscending", true);
			request.getSession().setAttribute("characteristicField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("characteristicField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = characteristicService.requestCharacteristicPage(new RequestCharacteristicPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("characteristicAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("characteristicAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("characteristicField"):field));
			}
			details = characteristicService.requestCharacteristicPage(new RequestCharacteristicPageEvent(pageRequest));
		}
		
		PageWrapper<Characteristic> page=new PageWrapper<Characteristic>(details.getCharacteristicPage(),"/maintenance/characteristic/searchCharacteristic");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("characteristicField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/characteristic/searchCharacteristic";
	}
	
	private Boolean hasActions(CharacteristicFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("characteristicFilterInfo")CharacteristicFilterInfo filter, HttpServletRequest request) throws Exception{
		CharacteristicFilterInfo f=(CharacteristicFilterInfo) request.getSession().getAttribute("characteristicFilter");
		if (f!=null && f.getCharacteristicFilterList().size()!=0)
			filter.getCharacteristicFilterList().addAll(f.getCharacteristicFilterList());
		if (filter.getAaddFilter())
			filter.getCharacteristicFilterList().add((CharacteristicFilterInfo) (request.getSession().getAttribute("characteristicFilterCopy")!=null?request.getSession().getAttribute("characteristicFilterCopy"):new CharacteristicFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("characteristicAscending");
			String oldField=(String)request.getSession().getAttribute("characteristicField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("characteristicAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("characteristicAscending", direction);
			}
			else if (request.getSession().getAttribute("characteristicAscending")==null)
				request.getSession().setAttribute("characteristicAscending", true);
			request.getSession().setAttribute("characteristicField", filter.getSelectedFilterField());
		}
		
		RequestCharacteristicPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("characteristicField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestCharacteristicPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("characteristicAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("characteristicAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("characteristicField"):filter.getSelectedFilterField()));
				pageEvent = new RequestCharacteristicPageEvent(pageRequest,filter);			}
		}
		
		CharacteristicPageEvent details = characteristicService.requestCharacteristicFilterPage(pageEvent);
		PageWrapper<Characteristic> page=new PageWrapper<Characteristic>(details.getCharacteristicPage(),"/maintenance/characteristic/searchCharacteristic");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("characteristicField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("characteristicFilter", filter);
    	request.getSession().setAttribute("characteristicFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/characteristic/searchCharacteristic";
	}
	
	@ModelAttribute("characteristicFilterInfo")
	private CharacteristicFilterInfo getCharacteristicFilterInfo(){
		return new CharacteristicFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}