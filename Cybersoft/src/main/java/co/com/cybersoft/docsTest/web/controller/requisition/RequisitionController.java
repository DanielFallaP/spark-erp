package co.com.cybersoft.docsTest.web.controller.requisition;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.docsTest.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docsTest.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docsTest.web.domain.requisition.RequisitionBodyInfo;
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
import co.com.cybersoft.util.SparkBindingException;


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
	
	
	@Autowired
	@Qualifier("jsr303Validator") Validator validator;
	
	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	
		
	@RequestMapping(method=RequestMethod.POST)
	public String saveRequisition(@ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, @ModelAttribute("requisitionBodyInfo") RequisitionBodyInfo current, @ModelAttribute("requisitionBodyModificationInfo") RequisitionBodyInfo modified,Model model, HttpServletRequest request) throws Exception{
		RequisitionEvent requisitionEvent=null;
		if (modified.getRequisitionBodyModificationInfo().getSubmit().equals("modification")){
			manualValidation(modified.getRequisitionBodyModificationInfo(), "requisitionBodyModificationInfo", CyberUtils.modificationForm);
			
			requisitionInfo.setRequisitionBodyModificationInfo(modified.getRequisitionBodyModificationInfo());
			requisitionEvent = requisitionService.updateRequisitionBody(new SaveRequisitionEvent(requisitionInfo));
		}
		else if (modified.getRequisitionBodyModificationInfo().getSubmit().equals("creation")){
			
			manualValidation(current, "requisitionBodyInfo", CyberUtils.additionForm);
			
			requisitionInfo.setCurrentRequisitionBodyInfo(current);
			requisitionEvent = requisitionService.createRequisitionBodyRecord(new SaveRequisitionEvent(requisitionInfo));
			RequisitionBodyInfo requisitionBodyInfo = getRequisitionBodyInfo(request);
			model.addAttribute("requisitionBodyInfo", requisitionBodyInfo);
		}
		else if (modified.getRequisitionBodyModificationInfo().getSubmit().endsWith("deletion")){
			List<String> toDelete = Arrays.asList(requisitionInfo.getDeletion().split(","));
			requisitionEvent=requisitionService.deleteRequisitionBodyRecords(new SaveRequisitionEvent(requisitionInfo), toDelete);			
		}
		else{
			requisitionEvent = requisitionService.saveRequisition(new SaveRequisitionEvent(requisitionInfo));
		}
		
		if (requisitionInfo.getCreated()!=null && !requisitionInfo.getCreated()){
			return "redirect:/docs/requisition?id="+requisitionEvent.getRequisition().getId();
		}
		else{
			
//		requisitionInfo.setCreated(true);
			requisitionInfo.setShowBody(true);
			requisitionInfo.set_toSave(true);
			requisitionInfo.setId(requisitionEvent.getRequisition().getId());
			requisitionInfo.setRequisitionBodyList(requisitionEvent.getRequisition().getRequisitionBodyList());
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
			model.addAttribute("requisitionBodyList", requisitionInfo.getRequisitionBodyList());
			return "/docsTest/requisition/saveRequisition";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docsTest/requisition/saveRequisition";
	}
	
	private void manualValidation(Object object, String objectName, String origin) throws Exception{
		HashMap<Object, Object> errorMap = new HashMap<>();
		MapBindingResult mapBindingResult = new MapBindingResult(errorMap, objectName);
		validator.validate(object, mapBindingResult);
					
		if (!mapBindingResult.getAllErrors().isEmpty()){
			throw new SparkBindingException(mapBindingResult.getAllErrors(), origin,"");
		}
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		RequisitionInfo requisitionInfo=(RequisitionInfo) req.getSession().getAttribute("requisitionInfo");
		if (exception instanceof BindException){
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionValidationException",true);
			modelAndView = setErrors(modelAndView, ((BindException) exception).getAllErrors(), requisitionInfo, "");
			modelAndView.setViewName("/docsTest/requisition/saveRequisition");
		}
		else if (exception instanceof SparkBindingException){
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionValidationException",true);
			modelAndView = setErrors(modelAndView, ((SparkBindingException) exception).getErrors(), requisitionInfo, CyberUtils.additionForm);
			modelAndView.setViewName("/docsTest/requisition/saveRequisition");
		}
		else {
			modelAndView.addObject("requisitionInfo", requisitionInfo);
			modelAndView.addObject("requisitionCreateException",true);
			modelAndView.setViewName("/docsTest/requisition/saveRequisition");
		}
		
		try {
			requisitionInfo.set_toSave(false);
			RequisitionBodyInfo requisitionBodyInfo = getRequisitionBodyInfo(req);	
			requisitionInfo.setCurrentRequisitionBodyInfo(requisitionBodyInfo);
			RequisitionBodyInfo requisitionBodyModificationInfo = getRequisitionBodyModificationInfo(req);
			requisitionInfo.setRequisitionBodyModificationInfo(requisitionBodyModificationInfo);
			requisitionBodyInfo.setSubmit("");
			requisitionBodyInfo.setRequisitionBodyModificationInfo(new RequisitionBodyInfo());
			RequisitionBodyInfo submitModification = new RequisitionBodyInfo();
			submitModification.setSubmit("");
			requisitionBodyModificationInfo.setRequisitionBodyModificationInfo(submitModification);
		
			modelAndView.addObject("requisitionInfo",requisitionInfo);
			modelAndView.addObject("requisitionBodyList", requisitionInfo.getRequisitionBodyList());
			modelAndView.addObject("requisitionBodyInfo",requisitionBodyInfo);
			modelAndView.addObject("requisitionBodyModificationInfo",requisitionBodyModificationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, List<ObjectError> errors, RequisitionInfo requisitionInfo, String origin){
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (origin.equals(CyberUtils.additionForm)){
						model.addObject("_AdditionFormException", true);
					}
					
					if (origin.equals(CyberUtils.modificationForm)){
						model.addObject("_ModificationFormException", true);
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
	
	@ModelAttribute("requisitionInfo")
	private RequisitionInfo getRequisitionInfo(String id, @ModelAttribute("requisitionInfo") RequisitionInfo requisitionInfo, Model model, HttpServletRequest request)  throws Exception {
		RequisitionInfo requisitionInfoSession = (RequisitionInfo) request.getSession().getAttribute("requisitionInfo");

			if (id!=null || (requisitionInfo.getCreated()!=null && requisitionInfo.getCreated() && requisitionInfoSession!=null && requisitionInfoSession.getId()!=null)){
				requisitionInfo = requisitionService.requestRequisitionDetails(id==null?new RequestRequisitionEvent(requisitionInfoSession.getId()):new RequestRequisitionEvent(id)).getRequisition();
				requisitionInfo.setShowBody(true);
				requisitionInfo.setCreated(true);
			}
			else{
				requisitionInfo = new RequisitionInfo();
			}
			
			
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
			
			
			
			if (id!=null || (requisitionInfo.getCreated()!=null && requisitionInfo.getCreated() && requisitionInfoSession!=null && requisitionInfoSession.getId()!=null)){
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
			else {
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
			
			RequisitionBodyInfo requisitionBodyInfo = getRequisitionBodyInfo(request);	
			requisitionInfo.setCurrentRequisitionBodyInfo(requisitionBodyInfo);
			RequisitionBodyInfo requisitionBodyModificationInfo = getRequisitionBodyModificationInfo(request);
			requisitionInfo.setRequisitionBodyModificationInfo(requisitionBodyModificationInfo);
			requisitionBodyInfo.setSubmit("");
			RequisitionBodyInfo submitModification = new RequisitionBodyInfo();
			submitModification.setSubmit("");
			requisitionBodyModificationInfo.setRequisitionBodyModificationInfo(submitModification);
		
			request.getSession().setAttribute("requisitionInfo", requisitionInfo);
			model.addAttribute("requisitionInfo",requisitionInfo);
			model.addAttribute("requisitionBodyList", requisitionInfo.getRequisitionBodyList());
			model.addAttribute("requisitionBodyInfo", requisitionBodyInfo);
			model.addAttribute("requisitionBodyModificationInfo",requisitionBodyModificationInfo);
			
			return requisitionInfo;
	}
		
		private RequisitionBodyInfo getRequisitionBodyInfo(HttpServletRequest request) throws Exception {
			RequisitionBodyInfo requisitionBodyInfo = new RequisitionBodyInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionBodyInfo.setPriorityList(allPriorityEvent.getPriorityList());
			
			requisitionBodyInfo.setId(null);
			requisitionBodyInfo.setBodyRequiredOnDate(new Date());

			request.getSession().setAttribute("requisitionBodyInfo", requisitionBodyInfo);
			
			return requisitionBodyInfo;
		}
		

		private RequisitionBodyInfo getRequisitionBodyModificationInfo(HttpServletRequest request) throws Exception{
			RequisitionBodyInfo requisitionBodyModificationInfo = new RequisitionBodyInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionBodyModificationInfo.setPriorityList(allPriorityEvent.getPriorityList());

			request.getSession().setAttribute("requisitionBodyModificationInfo", requisitionBodyModificationInfo);
			
			return requisitionBodyModificationInfo;
		}
}
