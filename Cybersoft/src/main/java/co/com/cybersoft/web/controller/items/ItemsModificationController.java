package co.com.cybersoft.web.controller.items;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.domain.ItemsDetails;
import co.com.cybersoft.core.services.items.ItemsService;
import co.com.cybersoft.events.items.ItemsDetailsEvent;
import co.com.cybersoft.events.items.ItemsModificationEvent;
import co.com.cybersoft.events.items.RequestItemsDetailsEvent;
import co.com.cybersoft.web.domain.items.ItemsInfo;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller class for Items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/items/modifyItems/{code}")
public class ItemsModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ItemsModificationController.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
		private MeasurementUnitService measurementUnitService;

	@Autowired
		private ActiveService activeService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/items/modifyItems";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyItems(@Valid @ModelAttribute("itemsInfo") ItemsInfo itemsInfo, HttpServletRequest request) throws Exception {
		ItemsDetails itemsDetails = createItemsDetails(itemsInfo);
		itemsDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		itemsDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("itemsInfo", itemsInfo);
		itemsService.modifyItems(new ItemsModificationEvent(itemsDetails));
		return "redirect:/configuration/items/searchItems";
	}
	
	private ItemsDetails createItemsDetails(ItemsInfo itemsInfo){
		ItemsDetails itemsDetails = new ItemsDetails();
		BeanUtils.copyProperties(itemsInfo, itemsDetails);
		return itemsDetails;
	}

	@ModelAttribute("itemsInfo")
	private ItemsInfo getItemsInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the items "+code);
		ItemsInfo itemsInfo = new ItemsInfo();
		
		ItemsDetailsEvent requestItemsDetails = itemsService.requestItemsDetails(new RequestItemsDetailsEvent(code));
		MeasurementUnitPageEvent allMeasurementUnitEvent = measurementUnitService.requestAll();
		itemsInfo.setMeasurementUnitList(allMeasurementUnitEvent.getMeasurementUnitList());
		itemsInfo.rearrangeMeasurementUnitList(itemsInfo.getMeasurementUnit());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		itemsInfo.setActiveList(allActiveEvent.getActiveList());
		itemsInfo.rearrangeActiveList(itemsInfo.getActive());

		request.getSession().setAttribute("itemsInfo", itemsInfo);
		
		BeanUtils.copyProperties(requestItemsDetails.getItemsDetails(), itemsInfo);
		return itemsInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("itemsInfo", req.getSession().getAttribute("itemsInfo"));
			modelAndView.addObject("itemsValidationException",true);
			modelAndView.setViewName("/configuration/items/modifyItems");
		}
		else{
			modelAndView.addObject("itemsInfo", req.getSession().getAttribute("itemsInfo"));
			modelAndView.addObject("itemsModificationException",true);
			modelAndView.setViewName("/configuration/items/modifyItems");
		}
		return modelAndView;
	}
}