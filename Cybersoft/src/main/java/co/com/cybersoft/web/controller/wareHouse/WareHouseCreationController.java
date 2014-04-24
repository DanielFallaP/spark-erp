package co.com.cybersoft.web.controller.wareHouse;

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

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.events.wareHouse.CreateWareHouseEvent;
import co.com.cybersoft.web.domain.wareHouse.WareHouseInfo;
import co.com.cybersoft.events.wareHouse.WareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHouseDetailsEvent;


import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.events.active.ActivePageEvent;


/**
 * Controller for wareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/wareHouse/createWareHouse/{from}")
public class WareHouseCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(WareHouseCreationController.class);
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@Autowired
		private CalculusTypeService calculusTypeService;

	@Autowired
		private OperationTypeService operationTypeService;

	@Autowired
		private AfeService afeService;

	@Autowired
		private ContractService contractService;

	@Autowired
		private ActiveService activeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String wareHouseCreation() throws Exception {
		return "/configuration/wareHouse/createWareHouse";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createWareHouse(@Valid @ModelAttribute("wareHouseInfo") WareHouseInfo wareHouseInfo, Model model, HttpServletRequest request) throws Exception{
		wareHouseInfo.setCreated(false);
		WareHouseDetails wareHouseDetails = createWareHouseDetails(wareHouseInfo);
		wareHouseDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		wareHouseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		wareHouseDetails.setDateOfCreation(new Date());
		wareHouseDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("wareHouseInfo", wareHouseInfo);
		wareHouseService.createWareHouse(new CreateWareHouseEvent(wareHouseDetails));
		String calledFrom = wareHouseInfo.getCalledFrom();
		wareHouseInfo=new WareHouseInfo();
		wareHouseInfo.setCreated(true);
		wareHouseInfo.setCalledFrom(calledFrom);
		CalculusTypePageEvent allCalculusTypeEvent = calculusTypeService.requestAll();
		wareHouseInfo.setCalculusTypeList(allCalculusTypeEvent.getCalculusTypeList());
		OperationTypePageEvent allOperationTypeEvent = operationTypeService.requestAll();
		wareHouseInfo.setOperationTypeList(allOperationTypeEvent.getOperationTypeList());
		AfePageEvent allAfeEvent = afeService.requestAll();
		wareHouseInfo.setAfeList(allAfeEvent.getAfeList());
		ContractPageEvent allContractEvent = contractService.requestAll();
		wareHouseInfo.setContractList(allContractEvent.getContractList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		wareHouseInfo.setActiveList(allActiveEvent.getActiveList());

		
		model.addAttribute("wareHouseInfo", wareHouseInfo);
		return "/configuration/wareHouse/createWareHouse";
	}
	
	private WareHouseDetails createWareHouseDetails(WareHouseInfo wareHouseInfo){
		WareHouseDetails wareHouseDetails = new WareHouseDetails();
		BeanUtils.copyProperties(wareHouseInfo, wareHouseDetails);
		return wareHouseDetails;
	}
	
	@ModelAttribute("wareHouseInfo")
	private WareHouseInfo getWareHouseInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		WareHouseInfo wareHouseInfo = new WareHouseInfo();
		
		String code = request.getParameter("id");
		String description = request.getParameter("desc");
		if (code!=null){
			WareHouseDetailsEvent responseEvent = wareHouseService.requestWareHouseDetails(new RequestWareHouseDetailsEvent(code));
			if (responseEvent.getWareHouseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getWareHouseDetails(), wareHouseInfo);
		}
		
		if (description!=null){
			RequestWareHouseDetailsEvent event = new RequestWareHouseDetailsEvent(null);
			event.setDescription(description);
			WareHouseDetailsEvent responseEvent = wareHouseService.requestWareHouseDetails(event);
			if (responseEvent.getWareHouseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getWareHouseDetails(), wareHouseInfo);
		}
		
		wareHouseInfo.setId(null);
		
		CalculusTypePageEvent allCalculusTypeEvent = calculusTypeService.requestAll();
		wareHouseInfo.setCalculusTypeList(allCalculusTypeEvent.getCalculusTypeList());
		OperationTypePageEvent allOperationTypeEvent = operationTypeService.requestAll();
		wareHouseInfo.setOperationTypeList(allOperationTypeEvent.getOperationTypeList());
		AfePageEvent allAfeEvent = afeService.requestAll();
		wareHouseInfo.setAfeList(allAfeEvent.getAfeList());
		ContractPageEvent allContractEvent = contractService.requestAll();
		wareHouseInfo.setContractList(allContractEvent.getContractList());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		wareHouseInfo.setActiveList(allActiveEvent.getActiveList());

		
		wareHouseInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("wareHouseInfo", wareHouseInfo);
		
		return wareHouseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("wareHouseInfo", req.getSession().getAttribute("wareHouseInfo"));
			modelAndView.addObject("wareHouseValidationException",true);
			modelAndView.setViewName("/configuration/wareHouse/createWareHouse");
		}
		else{
			modelAndView.addObject("wareHouseInfo", req.getSession().getAttribute("wareHouseInfo"));
			modelAndView.addObject("wareHouseCreateException",true);
			modelAndView.setViewName("/configuration/wareHouse/createWareHouse");
		}
		return modelAndView;
	}
	
}