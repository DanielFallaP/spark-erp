package co.com.cybersoft.docs.web.controller.requisition;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.services.country.CountryService;
import co.com.cybersoft.core.services.department.DepartmentService;
import co.com.cybersoft.core.services.expenseType.ExpenseTypeService;
import co.com.cybersoft.core.services.item.ItemService;
import co.com.cybersoft.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.core.services.priority.PriorityService;
import co.com.cybersoft.core.services.state.StateService;
import co.com.cybersoft.core.services.transportationType.TransportationTypeService;
import co.com.cybersoft.core.services.warehouse.WarehouseService;
import co.com.cybersoft.docs.events.requisition.RequisitionEvent;
import co.com.cybersoft.docs.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionItemInfo;
import co.com.cybersoft.events.country.CountryPageEvent;
import co.com.cybersoft.events.department.DepartmentPageEvent;
import co.com.cybersoft.events.expenseType.ExpenseTypePageEvent;
import co.com.cybersoft.events.item.ItemPageEvent;
import co.com.cybersoft.events.populatedPlace.PopulatedPlacePageEvent;
import co.com.cybersoft.events.priority.PriorityPageEvent;
import co.com.cybersoft.events.state.StatePageEvent;
import co.com.cybersoft.events.transportationType.TransportationTypePageEvent;
import co.com.cybersoft.events.warehouse.WarehousePageEvent;


@Controller
@RequestMapping("/requisition")
public class RequisitionController {
	
	@Autowired
	private RequisitionPersistenceService requisitionService;
	
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
	
	@Autowired
	private ItemService itemService;

	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveRequisition(@ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, @ModelAttribute("requisitionItemInfo") RequisitionItemInfo current, @ModelAttribute("requisitionItemModificationInfo") RequisitionItemInfo modified,Model model, HttpServletRequest request) throws Exception{
		RequisitionEvent requisitionEvent=null;
		if (modified.getRequisitionItemModificationInfo().getSubmit().equals("modification")){
			requisitionInfo.setRequisitionItemModificationInfo(modified.getRequisitionItemModificationInfo());
			requisitionEvent = requisitionService.updateRequisitionBody(new SaveRequisitionEvent(requisitionInfo));
		}
		else if (modified.getRequisitionItemModificationInfo().getSubmit().equals("creation")){
			requisitionInfo.setCurrentRequisitionItemInfo(current);
			requisitionEvent = requisitionService.saveRequisitionBody(new SaveRequisitionEvent(requisitionInfo));
		}
		else if (modified.getRequisitionItemModificationInfo().getSubmit().equals("deletion")){
			List<String> toDelete = Arrays.asList(requisitionInfo.getDeletion().split(","));
			requisitionEvent=requisitionService.deleteRequisition(new SaveRequisitionEvent(requisitionInfo), toDelete);			
		}
		else{
			requisitionEvent = requisitionService.saveRequisition(new SaveRequisitionEvent(requisitionInfo));
		}
		requisitionInfo.setCreated(true);
		requisitionInfo.setId(requisitionEvent.getRequisition().getId());
		requisitionInfo.setRequisitionItemList(requisitionEvent.getRequisition().getRequisitionItemList());
		requisitionInfo.setNumericId(requisitionEvent.getRequisition().getNumericId());
		
		model.addAttribute("requisitionInfo",requisitionInfo);
		model.addAttribute("requisitionItemInfoList", requisitionInfo.getRequisitionItemList());

		return "/docs/requisition/createRequisition";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docs/requisition/createRequisition";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		//TODO
		exception.printStackTrace();
		LOG.debug(exception.getMessage());
		return null;
	}
	
	@ModelAttribute("requisitionInfo")
	private RequisitionInfo getRequisitionInfo(@ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, Model model, HttpServletRequest request)  throws Exception {
		RequisitionInfo requisitionInfoSession = (RequisitionInfo) request.getSession().getAttribute("requisitionInfo");

		if (requisitionInfoSession!=null && requisitionInfoSession.getId()!=null){
			RequisitionItemInfo requisitionItemInfo = getRequisitionItemInfo(request);	
			RequisitionItemInfo requisitionItemModificationInfo = getRequisitionItemModificationInfo(request);
			requisitionInfo.setCurrentRequisitionItemInfo(requisitionItemInfo);
			requisitionInfo.setRequisitionItemModificationInfo(requisitionItemModificationInfo);
			requisitionItemInfo.setSubmit("");
			RequisitionItemInfo submitModification = new RequisitionItemInfo();
			submitModification.setSubmit("");
			requisitionItemModificationInfo.setRequisitionItemModificationInfo(submitModification);
			
			model.addAttribute("requisitionInfo",requisitionInfoSession);
			model.addAttribute("requisitionItemInfoList", requisitionInfo.getRequisitionItemList());
			model.addAttribute("requisitionItemInfo", requisitionItemInfo);
			model.addAttribute("requisitionItemModificationInfo",requisitionItemModificationInfo);
			
			return requisitionInfo;
		}
		else{
			
			requisitionInfo = new RequisitionInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionInfo.setPriorityList(allPriorityEvent.getPriorityList());
			DepartmentPageEvent allRequestingDepartmentEvent = departmentService.requestAllByDepartment();
			requisitionInfo.setRequestingDepartmentList(allRequestingDepartmentEvent.getDepartmentList());
			ExpenseTypePageEvent allExpenseTypeEvent = expenseTypeService.requestAllByExpenseType();
			requisitionInfo.setExpenseTypeList(allExpenseTypeEvent.getExpenseTypeList());
			TransportationTypePageEvent allTransportationTypeEvent = transportationTypeService.requestAllByTransportationType();
			requisitionInfo.setTransportationTypeList(allTransportationTypeEvent.getTransportationTypeList());
			WarehousePageEvent allReceivingWarehouseEvent = warehouseService.requestAllByWarehouse();
			requisitionInfo.setReceivingWarehouseList(allReceivingWarehouseEvent.getWarehouseList());
			
			
			CountryPageEvent allCountryPageEvent = null;
			StatePageEvent allStatePageEvent = null;
			PopulatedPlacePageEvent allPopulatedPlacePageEvent = null;
			
			
			allCountryPageEvent = countryService.requestAllByCountry();
			requisitionInfo.setCountryList(allCountryPageEvent.getCountryList());
			
			if (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty()){	
				allStatePageEvent = stateService.requestAllByCountryName(allCountryPageEvent.getCountryList().get(0).getCountry());
				requisitionInfo.setStateList(allStatePageEvent.getStateList());
			}
			if (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty()){	
				allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName(allStatePageEvent.getStateList().get(0).getState());
				requisitionInfo.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
			}
			
			requisitionInfo.setId(null);
			requisitionInfo.setDate(new Date());
			requisitionInfo.setStock(true);
			requisitionInfo.setRequiredOnDate(new Date());
			requisitionInfo.setActive(true);
			
			RequisitionItemInfo requisitionItemInfo = getRequisitionItemInfo(request);	
			requisitionInfo.setCurrentRequisitionItemInfo(requisitionItemInfo);
			RequisitionItemInfo requisitionItemModificationInfo = getRequisitionItemModificationInfo(request);
			requisitionInfo.setRequisitionItemModificationInfo(requisitionItemModificationInfo);
			requisitionItemInfo.setSubmit("");
			RequisitionItemInfo submitModification = new RequisitionItemInfo();
			submitModification.setSubmit("");
			requisitionItemModificationInfo.setRequisitionItemModificationInfo(submitModification);
		
			request.getSession().setAttribute("requisitionInfo", requisitionInfo);
			model.addAttribute("requisitionInfo",requisitionInfo);
			model.addAttribute("requisitionItemInfoList", requisitionInfo.getRequisitionItemList());
			model.addAttribute("requisitionItemInfo", requisitionItemInfo);
			model.addAttribute("requisitionItemModificationInfo",requisitionItemModificationInfo);
			
			return requisitionInfo;
		}
		
	}
		
		private RequisitionItemInfo getRequisitionItemInfo(HttpServletRequest request) throws Exception {
			RequisitionItemInfo requisitionItemInfo = new RequisitionItemInfo();
			
			ItemPageEvent allItemEvent = itemService.requestAllByCode();
			requisitionItemInfo.setItemList(allItemEvent.getItemList());
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemInfo.setPriorityList(allPriorityEvent.getPriorityList());
			
			requisitionItemInfo.setId(null);
			requisitionItemInfo.setBodyRequiredOnDate(new Date());
			requisitionItemInfo.setActive(true);

			request.getSession().setAttribute("requisitionItemInfo", requisitionItemInfo);
			
			return requisitionItemInfo;
		}
		

		private RequisitionItemInfo getRequisitionItemModificationInfo(HttpServletRequest request) throws Exception{
			RequisitionItemInfo requisitionItemModificationInfo = new RequisitionItemInfo();
			
			ItemPageEvent allItemEvent = itemService.requestAllByCode();
			requisitionItemModificationInfo.setItemList(allItemEvent.getItemList());
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemModificationInfo.setPriorityList(allPriorityEvent.getPriorityList());

			request.getSession().setAttribute("requisitionItemModificationInfo", requisitionItemModificationInfo);
			
			return requisitionItemModificationInfo;
		}
}
