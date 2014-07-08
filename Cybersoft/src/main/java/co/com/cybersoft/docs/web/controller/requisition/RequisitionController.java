package co.com.cybersoft.docs.web.controller.requisition;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.docs.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionEvent;
import co.com.cybersoft.docs.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionItemInfo;
import co.com.cybersoft.tables.core.services.country.CountryService;
import co.com.cybersoft.tables.core.services.department.DepartmentService;
import co.com.cybersoft.tables.core.services.expenseType.ExpenseTypeService;
import co.com.cybersoft.tables.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.tables.core.services.priority.PriorityService;
import co.com.cybersoft.tables.core.services.state.StateService;
import co.com.cybersoft.tables.core.services.transportationType.TransportationTypeService;
import co.com.cybersoft.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.tables.events.country.CountryPageEvent;
import co.com.cybersoft.tables.events.department.DepartmentPageEvent;
import co.com.cybersoft.tables.events.expenseType.ExpenseTypePageEvent;
import co.com.cybersoft.tables.events.populatedPlace.PopulatedPlacePageEvent;
import co.com.cybersoft.tables.events.priority.PriorityPageEvent;
import co.com.cybersoft.tables.events.state.StatePageEvent;
import co.com.cybersoft.tables.events.transportationType.TransportationTypePageEvent;
import co.com.cybersoft.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.util.CyberUtils;


@Controller
@RequestMapping("/docs/requisition")
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
	
	private List<String> additionFormNames=Arrays.asList("item");
	
	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	
		
	@RequestMapping(method=RequestMethod.POST)
	public String saveRequisition(@ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, @ModelAttribute("requisitionItemInfo") RequisitionItemInfo current, @ModelAttribute("requisitionItemModificationInfo") RequisitionItemInfo modified,Model model, HttpServletRequest request) throws Exception{
		RequisitionEvent requisitionEvent=null;
		if (modified.getRequisitionItemModificationInfo().getSubmit().equals("modification")){
			
			requisitionInfo.setRequisitionItemModificationInfo(modified.getRequisitionItemModificationInfo());
			requisitionEvent = requisitionService.updateRequisitionBody(new SaveRequisitionEvent(requisitionInfo));
		}
		else if (modified.getRequisitionItemModificationInfo().getSubmit().equals("creation")){
			Set<ConstraintViolation<RequisitionItemInfo>> validate = javax.validation.Validation.buildDefaultValidatorFactory().getValidator().validate(current);
			requisitionInfo.setCurrentRequisitionItemInfo(current);
			requisitionEvent = requisitionService.saveRequisitionBody(new SaveRequisitionEvent(requisitionInfo));
			RequisitionItemInfo requisitionItemInfo = getRequisitionItemInfo(request);
			model.addAttribute("requisitionItemInfo", requisitionItemInfo);
		}
		else if (modified.getRequisitionItemModificationInfo().getSubmit().endsWith("deletion")){
			List<String> toDelete = Arrays.asList(requisitionInfo.getDeletion().split(","));
			requisitionEvent=requisitionService.deleteRequisition(new SaveRequisitionEvent(requisitionInfo), toDelete);			
		}
		else{
			requisitionEvent = requisitionService.saveRequisition(new SaveRequisitionEvent(requisitionInfo));
		}
		
		requisitionInfo.setCreated(true);
		requisitionInfo.setShowBody(true);
		requisitionInfo.set_toSave(true);
		requisitionInfo.setId(requisitionEvent.getRequisition().getId());
		requisitionInfo.setRequisitionItemList(requisitionEvent.getRequisition().getRequisitionItemList());
		requisitionInfo.setNumericId(requisitionEvent.getRequisition().getNumericId());
		requisitionInfo.setDateOfCreation(requisitionEvent.getRequisition().getDateOfCreation());
		requisitionInfo.setCreatedBy(requisitionEvent.getRequisition().getCreatedBy());
//		requisitionInfo.getRequisitionItemModificationInfo().getRequisitionItemModificationInfo().setSubmit("");
		
		CountryPageEvent allCountryPageEvent = null;
		StatePageEvent allStatePageEvent = null;
		PopulatedPlacePageEvent allPopulatedPlacePageEvent = null;

			
			allCountryPageEvent = countryService.requestAllByCountry();
			requisitionInfo.setCountryList(allCountryPageEvent.getCountryList());

			if (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty()){	
					allStatePageEvent = stateService.requestAllByCountryName(requisitionEvent.getRequisition().getCountry());
					requisitionInfo.setStateList(allStatePageEvent.getStateList());
			}
			if (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty()){	
					allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName(requisitionEvent.getRequisition().getState());
					requisitionInfo.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
			}

		
		model.addAttribute("requisitionInfo",requisitionInfo);
		model.addAttribute("requisitionItemInfoList", requisitionInfo.getRequisitionItemList());
		return "/docs/requisition/saveRequisition";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docs/requisition/saveRequisition";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		RequisitionInfo requisitionInfo=(RequisitionInfo) req.getSession().getAttribute("requisitionInfo");
		if (exception instanceof BindException){
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, requisitionInfo);
			modelAndView.setViewName("/docs/requisition/saveRequisition");
		}
		else{
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionCreateException",true);
			modelAndView.setViewName("/docs/requisition/saveRequisition");
		}
		
		try {
			requisitionInfo.set_toSave(false);
			RequisitionItemInfo requisitionItemInfo = getRequisitionItemInfo(req);	
			requisitionInfo.setCurrentRequisitionItemInfo(requisitionItemInfo);
			RequisitionItemInfo requisitionItemModificationInfo = getRequisitionItemModificationInfo(req);
			requisitionInfo.setRequisitionItemModificationInfo(requisitionItemModificationInfo);
			requisitionItemInfo.setSubmit("");
			requisitionItemInfo.setRequisitionItemModificationInfo(new RequisitionItemInfo());
			RequisitionItemInfo submitModification = new RequisitionItemInfo();
			submitModification.setSubmit("");
			requisitionItemModificationInfo.setRequisitionItemModificationInfo(submitModification);
		
			modelAndView.addObject("requisitionInfo",requisitionInfo);
			modelAndView.addObject("requisitionItemInfoList", requisitionInfo.getRequisitionItemList());
			modelAndView.addObject("requisitionItemInfo",requisitionItemInfo);
			modelAndView.addObject("requisitionItemModificationInfo",requisitionItemModificationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, RequisitionInfo requisitionInfo){
		List<ObjectError> errors = exception.getAllErrors();
		additionFormNames=Arrays.asList("item");
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (isAdditionForm(fieldError.getField())){
						model.addObject("_AdditionFormException", true);
					}
					if (fieldError.getCodes()[0].startsWith(CyberUtils.lengthValidation)
							||fieldError.getCodes()[0].startsWith(CyberUtils.rangeValidation)){
						
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"outOfRangeErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.notEmptyValidation)  
							|| fieldError.getCodes()[0].startsWith(CyberUtils.notNullValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"requiredFieldErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.typeValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"fieldTypeErrorValidationMessage");
						
					}
					else{
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"genericErrorValidationMessage");
						
					}
				}
			}
		}
		return model;
	}
	
	private boolean isAdditionForm(String fieldName){
		return additionFormNames.contains(fieldName);
	}
	
	@ModelAttribute("requisitionInfo")
	private RequisitionInfo getRequisitionInfo(String id, @ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, Model model, HttpServletRequest request)  throws Exception {
		RequisitionInfo requisitionInfoSession = (RequisitionInfo) request.getSession().getAttribute("requisitionInfo");

		if (requisitionInfo.getCreated()!=null && requisitionInfo.getCreated()){
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
			
			if (id==null)
				requisitionInfo = new RequisitionInfo();
			else{
				requisitionInfo = requisitionService.requestRequisitionDetails(new RequestRequisitionEvent(id)).getRequisition();
				requisitionInfo.setShowBody(true);
			}
			requisitionInfo.setCreated(true);
			
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
			
			
			
			if (id==null){
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
			}
			else{
				allCountryPageEvent = countryService.requestAllByCountry();
				requisitionInfo.setCountryList(allCountryPageEvent.getCountryList());

				if (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty()){	
						allStatePageEvent = stateService.requestAllByCountryName(requisitionInfo.getCountry());
						requisitionInfo.setStateList(allStatePageEvent.getStateList());
				}
				if (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty()){	
						allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName(requisitionInfo.getState());
						requisitionInfo.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
				}
			}
			
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
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemInfo.setPriorityList(allPriorityEvent.getPriorityList());
			
			requisitionItemInfo.setId(null);
			requisitionItemInfo.setBodyRequiredOnDate(new Date());

			request.getSession().setAttribute("requisitionItemInfo", requisitionItemInfo);
			
			return requisitionItemInfo;
		}
		

		private RequisitionItemInfo getRequisitionItemModificationInfo(HttpServletRequest request) throws Exception{
			RequisitionItemInfo requisitionItemModificationInfo = new RequisitionItemInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemModificationInfo.setPriorityList(allPriorityEvent.getPriorityList());

			request.getSession().setAttribute("requisitionItemModificationInfo", requisitionItemModificationInfo);
			
			return requisitionItemModificationInfo;
		}
}
