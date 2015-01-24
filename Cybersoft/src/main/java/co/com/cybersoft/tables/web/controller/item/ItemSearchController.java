package co.com.cybersoft.tables.web.controller.item;

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

import co.com.cybersoft.tables.core.services.item.ItemService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.tables.events.item.ItemPageEvent;
import co.com.cybersoft.tables.events.item.RequestItemPageEvent;
import co.com.cybersoft.tables.persistence.domain.Item;
import co.com.cybersoft.tables.web.domain.item.ItemFilterInfo;

/**
 * Search controller class for item
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/item/searchItem")
public class ItemSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemSearchController.class);

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  item ");
		ItemPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("itemAscending");
			String oldField=(String)request.getSession().getAttribute("itemField");
			if (oldField!=null && request.getSession().getAttribute("itemAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("itemAscending", direction);
			}
			else
				request.getSession().setAttribute("itemAscending", true);
			request.getSession().setAttribute("itemField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("itemField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "_id");
			details = itemService.requestItemPage(new RequestItemPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("itemAscending")!=null){
								
				direction=(boolean) request.getSession().getAttribute("itemAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("itemField"):field));
			}
			details = itemService.requestItemPage(new RequestItemPageEvent(pageRequest));
		}
		
		PageWrapper<Item> page=new PageWrapper<Item>(details.getItemPage(),"/configuration/item/searchItem");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("itemField"));
		model.addAttribute("_direction", direction);
		return "/configuration/item/searchItem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("itemFilterInfo")ItemFilterInfo filter, HttpServletRequest request) throws Exception{
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("itemAscending");
			String oldField=(String)request.getSession().getAttribute("itemField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("itemAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("itemAscending", direction);
			}
			else if (request.getSession().getAttribute("itemAscending")==null)
				request.getSession().setAttribute("itemAscending", true);
			request.getSession().setAttribute("itemField", filter.getSelectedFilterField());
		}
		
		RequestItemPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("itemField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"_id");
			pageEvent = new RequestItemPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("itemAscending")!=null){
				direction=(boolean) request.getSession().getAttribute("itemAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("itemField"):filter.getSelectedFilterField()));
				pageEvent = new RequestItemPageEvent(pageRequest,filter);
			}
		}
		ItemPageEvent details = itemService.requestItemFilterPage(pageEvent);
		PageWrapper<Item> page=new PageWrapper<Item>(details.getItemPage(),"/configuration/item/searchItem");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("itemField"));
		model.addAttribute("_direction", direction);
		return "/configuration/item/searchItem";
	}
	
	@ModelAttribute("itemFilterInfo")
	private ItemFilterInfo getItemFilterInfo(){
		return new ItemFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}