package co.com.cybersoft.web.controller.wareHouse;

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

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.events.wareHouse.WareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.WareHouseModificationEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHouseDetailsEvent;
import co.com.cybersoft.web.domain.wareHouse.WareHouseInfo;
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
 * Controller class for WareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/wareHouse/modifyWareHouse/{code}")
public class WareHouseModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(WareHouseModificationController.class);
	
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
	public String modification(){
		return "/configuration/wareHouse/modifyWareHouse";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyWareHouse(@Valid @ModelAttribute("wareHouseInfo") WareHouseInfo wareHouseInfo, HttpServletRequest request) throws Exception {
		WareHouseDetails wareHouseDetails = createWareHouseDetails(wareHouseInfo);
		wareHouseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		wareHouseDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("wareHouseInfo", wareHouseInfo);
		wareHouseService.modifyWareHouse(new WareHouseModificationEvent(wareHouseDetails));
		return "redirect:/configuration/wareHouse/searchWareHouse";
	}
	
	private WareHouseDetails createWareHouseDetails(WareHouseInfo wareHouseInfo){
		WareHouseDetails wareHouseDetails = new WareHouseDetails();
		BeanUtils.copyProperties(wareHouseInfo, wareHouseDetails);
		return wareHouseDetails;
	}

	@ModelAttribute("wareHouseInfo")
	private WareHouseInfo getWareHouseInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the wareHouse "+code);
		WareHouseInfo wareHouseInfo = new WareHouseInfo();
		
		WareHouseDetailsEvent requestWareHouseDetails = wareHouseService.requestWareHouseDetails(new RequestWareHouseDetailsEvent(code));
		CalculusTypePageEvent allCalculusTypeEvent = calculusTypeService.requestAll();
		wareHouseInfo.setCalculusTypeList(allCalculusTypeEvent.getCalculusTypeList());
		wareHouseInfo.rearrangeCalculusTypeList(wareHouseInfo.getCalculusType());
		OperationTypePageEvent allOperationTypeEvent = operationTypeService.requestAll();
		wareHouseInfo.setOperationTypeList(allOperationTypeEvent.getOperationTypeList());
		wareHouseInfo.rearrangeOperationTypeList(wareHouseInfo.getOperationType());
		AfePageEvent allAfeEvent = afeService.requestAll();
		wareHouseInfo.setAfeList(allAfeEvent.getAfeList());
		wareHouseInfo.rearrangeAfeList(wareHouseInfo.getAfe());
		ContractPageEvent allContractEvent = contractService.requestAll();
		wareHouseInfo.setContractList(allContractEvent.getContractList());
		wareHouseInfo.rearrangeContractList(wareHouseInfo.getContract());
		ActivePageEvent allActiveEvent = activeService.requestAll();
		wareHouseInfo.setActiveList(allActiveEvent.getActiveList());
		wareHouseInfo.rearrangeActiveList(wareHouseInfo.getActive());

		request.getSession().setAttribute("wareHouseInfo", wareHouseInfo);
		
		BeanUtils.copyProperties(requestWareHouseDetails.getWareHouseDetails(), wareHouseInfo);
		return wareHouseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("wareHouseInfo", req.getSession().getAttribute("wareHouseInfo"));
			modelAndView.addObject("wareHouseValidationException",true);
			modelAndView.setViewName("/configuration/wareHouse/modifyWareHouse");
		}
		else{
			modelAndView.addObject("wareHouseInfo", req.getSession().getAttribute("wareHouseInfo"));
			modelAndView.addObject("wareHouseModificationException",true);
			modelAndView.setViewName("/configuration/wareHouse/modifyWareHouse");
		}
		return modelAndView;
	}
}