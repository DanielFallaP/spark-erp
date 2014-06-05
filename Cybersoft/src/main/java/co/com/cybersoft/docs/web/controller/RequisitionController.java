package co.com.cybersoft.docs.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.country.CountryService;
import co.com.cybersoft.core.services.department.DepartmentService;
import co.com.cybersoft.core.services.expenseType.ExpenseTypeService;
import co.com.cybersoft.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.core.services.priority.PriorityService;
import co.com.cybersoft.core.services.requisition.RequisitionService;
import co.com.cybersoft.core.services.state.StateService;
import co.com.cybersoft.core.services.transportationType.TransportationTypeService;
import co.com.cybersoft.core.services.warehouse.WarehouseService;
import co.com.cybersoft.events.country.CountryPageEvent;
import co.com.cybersoft.events.department.DepartmentPageEvent;
import co.com.cybersoft.events.expenseType.ExpenseTypePageEvent;
import co.com.cybersoft.events.populatedPlace.PopulatedPlacePageEvent;
import co.com.cybersoft.events.priority.PriorityPageEvent;
import co.com.cybersoft.events.requisition.RequestRequisitionDetailsEvent;
import co.com.cybersoft.events.requisition.RequisitionDetailsEvent;
import co.com.cybersoft.events.state.StatePageEvent;
import co.com.cybersoft.events.transportationType.TransportationTypePageEvent;
import co.com.cybersoft.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.web.domain.requisition.RequisitionInfo;

@Controller
@RequestMapping("/requisition")
public class RequisitionController {
	
	@Autowired
	private RequisitionService requisitionHeadService;
	
	@Autowired
		private PriorityService priorityService;

	@Autowired
		private DepartmentService departmentService;

	@Autowired
		private ExpenseTypeService expenseTypeService;

	@Autowired
		private TransportationTypeService transportationTypeService;

	@Autowired
		private WarehouseService warehouseService;

	@Autowired
		private CountryService countryService;

	@Autowired
		private StateService stateService;

	@Autowired
		private PopulatedPlaceService populatedPlaceService;

	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docs/requisition/createRequisition";
	}
	
	@ModelAttribute("requisitionHeadInfo")
	private RequisitionInfo getRequisitionHeadInfo(HttpServletRequest request)  throws Exception {
		RequisitionInfo requisitionHeadInfo = new RequisitionInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
		requisitionHeadInfo.setPriorityList(allPriorityEvent.getPriorityList());
		DepartmentPageEvent allRequestingDepartmentEvent = departmentService.requestAllByDepartment();
		requisitionHeadInfo.setRequestingDepartmentList(allRequestingDepartmentEvent.getDepartmentList());
		ExpenseTypePageEvent allExpenseTypeEvent = expenseTypeService.requestAllByExpenseType();
		requisitionHeadInfo.setExpenseTypeList(allExpenseTypeEvent.getExpenseTypeList());
		TransportationTypePageEvent allTransportationTypeEvent = transportationTypeService.requestAllByTransportationType();
		requisitionHeadInfo.setTransportationTypeList(allTransportationTypeEvent.getTransportationTypeList());
		WarehousePageEvent allReceivingWarehouseEvent = warehouseService.requestAllByWarehouse();
		requisitionHeadInfo.setReceivingWarehouseList(allReceivingWarehouseEvent.getWarehouseList());

		
		CountryPageEvent allCountryPageEvent = null;
		StatePageEvent allStatePageEvent = null;
		PopulatedPlacePageEvent allPopulatedPlacePageEvent = null;

			
			allCountryPageEvent = countryService.requestAllByCountry();
			requisitionHeadInfo.setCountryList(allCountryPageEvent.getCountryList());

			if (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty()){	
						allStatePageEvent = stateService.requestAllByCountryName(allCountryPageEvent.getCountryList().get(0).getCountry());
						requisitionHeadInfo.setStateList(allStatePageEvent.getStateList());
			}
			if (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty()){	
						allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName(allStatePageEvent.getStateList().get(0).getState());
						requisitionHeadInfo.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
			}


		
		if (value!=null){
			RequestRequisitionDetailsEvent event = new RequestRequisitionDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			RequisitionDetailsEvent responseEvent = requisitionHeadService.requestRequisitionDetails(event);
			if (responseEvent.getRequisitionDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getRequisitionDetails(), requisitionHeadInfo);
		}
		
		
		requisitionHeadInfo.setId(null);
		requisitionHeadInfo.setDate(new Date());
		requisitionHeadInfo.setStock(true);
		requisitionHeadInfo.setRequiredOnDate(new Date());
		requisitionHeadInfo.setActive(true);

		
		request.getSession().setAttribute("requisitionHeadInfo", requisitionHeadInfo);
		
		return requisitionHeadInfo;
	}

}
