package co.com.cybersoft.web.controller.items;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

import co.com.cybersoft.core.domain.ItemsDetails;
import co.com.cybersoft.core.services.items.ItemsService;
import co.com.cybersoft.events.items.CreateItemsEvent;
import co.com.cybersoft.web.domain.items.ItemsInfo;
import co.com.cybersoft.events.items.ItemsDetailsEvent;
import co.com.cybersoft.events.items.RequestItemsDetailsEvent;


import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/items/createItems/{from}")
public class ItemsCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ItemsCreationController.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
		private MeasurementUnitService measurementUnitService;

	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String itemsCreation() throws Exception {
		return "/configuration/items/createItems";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createItems(@Valid @ModelAttribute("itemsInfo") ItemsInfo itemsInfo, Model model, HttpServletRequest request) throws Exception{
		itemsInfo.setCreated(false);
		ItemsDetails itemsDetails = createItemsDetails(itemsInfo);
		itemsDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		itemsDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		itemsDetails.setDateOfCreation(new Date());
		itemsDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("itemsInfo", itemsInfo);
		itemsService.createItems(new CreateItemsEvent(itemsDetails));
		String calledFrom = itemsInfo.getCalledFrom();
		itemsInfo=new ItemsInfo();
		itemsInfo.setCreated(true);
		itemsInfo.setCalledFrom(calledFrom);
		MeasurementUnitPageEvent allMeasurementUnitEvent = measurementUnitService.requestAll();
		itemsInfo.setMeasurementUnitList(allMeasurementUnitEvent.getMeasurementUnitList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		itemsInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("itemsInfo", itemsInfo);
		return "/configuration/items/createItems";
	}
	
	private ItemsDetails createItemsDetails(ItemsInfo itemsInfo){
		ItemsDetails itemsDetails = new ItemsDetails();
		BeanUtils.copyProperties(itemsInfo, itemsDetails);
		return itemsDetails;
	}
	
	@ModelAttribute("itemsInfo")
	private ItemsInfo getItemsInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ItemsInfo itemsInfo = new ItemsInfo();
		
		String code = request.getParameter("id");
		String description = request.getParameter("desc");
		if (code!=null){
			ItemsDetailsEvent responseEvent = itemsService.requestItemsDetails(new RequestItemsDetailsEvent(code));
			if (responseEvent.getItemsDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getItemsDetails(), itemsInfo);
		}
		
		if (description!=null){
			RequestItemsDetailsEvent event = new RequestItemsDetailsEvent(null);
			event.setDescription(description);
			ItemsDetailsEvent responseEvent = itemsService.requestItemsDetails(event);
			if (responseEvent.getItemsDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getItemsDetails(), itemsInfo);
		}
		
		itemsInfo.setId(null);
		
		MeasurementUnitPageEvent allMeasurementUnitEvent = measurementUnitService.requestAll();
		itemsInfo.setMeasurementUnitList(allMeasurementUnitEvent.getMeasurementUnitList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		itemsInfo.setActiveList(allActiveEvent.getActiveList());

		
		itemsInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("itemsInfo", itemsInfo);
		
		return itemsInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("itemsInfo", req.getSession().getAttribute("itemsInfo"));
			modelAndView.addObject("itemsValidationException",true);
			modelAndView.setViewName("/configuration/items/createItems");
		}
		else{
			modelAndView.addObject("itemsInfo", req.getSession().getAttribute("itemsInfo"));
			modelAndView.addObject("itemsCreateException",true);
			modelAndView.setViewName("/configuration/items/createItems");
		}
		return modelAndView;
	}
	
}