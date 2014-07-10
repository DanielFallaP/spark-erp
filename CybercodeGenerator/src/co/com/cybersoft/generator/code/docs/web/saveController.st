package co.com.cybersoft.docs.web.controller.$docName$;

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

import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.Save$upperDocName$Event;
import co.com.cybersoft.docs.persistence.services.$docName$.$upperDocName$PersistenceService;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$Info;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$ItemInfo;
import co.com.cybersoft.tables.core.services.country.CountryService;
import co.com.cybersoft.tables.core.services.department.DepartmentService;
import co.com.cybersoft.tables.core.services.expenseType.ExpenseTypeService;
import co.com.cybersoft.tables.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.tables.core.services.priority.PriorityService;
import co.com.cybersoft.tables.core.services.state.StateService;
import co.com.cybersoft.tables.core.services.transportationType.TransportationTypeService;
import co.com.cybersoft.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.SparkBindingException;
$imports$
import co.com.cybersoft.tables.events.country.CountryPageEvent;
import co.com.cybersoft.tables.events.department.DepartmentPageEvent;
import co.com.cybersoft.tables.events.expenseType.ExpenseTypePageEvent;
import co.com.cybersoft.tables.events.populatedPlace.PopulatedPlacePageEvent;
import co.com.cybersoft.tables.events.priority.PriorityPageEvent;
import co.com.cybersoft.tables.events.state.StatePageEvent;
import co.com.cybersoft.tables.events.transportationType.TransportationTypePageEvent;
import co.com.cybersoft.tables.events.warehouse.WarehousePageEvent;


@Controller
@RequestMapping("/docs/$docName$")
public class $upperDocName$Controller {
	
	@Autowired
	@Qualifier("jsr303Validator") Validator validator;

	@Autowired
	private $upperDocName$PersistenceService $docName$Service;
	
	$fields$
	
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
	
	
	
	private static final Logger LOG = LoggerFactory.getLogger($upperDocName$Controller.class);
	
	
		
	@RequestMapping(method=RequestMethod.POST)
	public String save$upperDocName$(@ModelAttribute("$docName$Info") $upperDocName$Info $docName$Info, @ModelAttribute("$docName$ItemInfo") $upperDocName$ItemInfo current, @ModelAttribute("$docName$ItemModificationInfo") $upperDocName$ItemInfo modified,Model model, HttpServletRequest request) throws Exception{
		$upperDocName$Event $docName$Event=null;
		if (modified.get$upperDocName$ItemModificationInfo().getSubmit().equals("modification")){
			manualValidation(modified, "$docName$ItemModificationInfo", CyberUtils.modificationForm);
			
			$docName$Info.set$upperDocName$ItemModificationInfo(modified.get$upperDocName$ItemModificationInfo());
			$docName$Event = $docName$Service.update$upperDocName$Body(new Save$upperDocName$Event($docName$Info));
		}
		else if (modified.get$upperDocName$ItemModificationInfo().getSubmit().equals("creation")){
			
			manualValidation(current, "$docName$ItemInfo", CyberUtils.additionForm);
			
			$docName$Info.setCurrent$upperDocName$ItemInfo(current);
			$docName$Event = $docName$Service.save$upperDocName$Body(new Save$upperDocName$Event($docName$Info));
			$upperDocName$ItemInfo $docName$ItemInfo = get$upperDocName$ItemInfo(request);
			model.addAttribute("$docName$ItemInfo", $docName$ItemInfo);
		}
		else if (modified.get$upperDocName$ItemModificationInfo().getSubmit().endsWith("deletion")){
			List<String> toDelete = Arrays.asList($docName$Info.getDeletion().split(","));
			$docName$Event=$docName$Service.delete$upperDocName$(new Save$upperDocName$Event($docName$Info), toDelete);			
		}
		else{
			$docName$Event = $docName$Service.save$upperDocName$(new Save$upperDocName$Event($docName$Info));
		}
		
		$docName$Info.setCreated(true);
		$docName$Info.setShowBody(true);
		$docName$Info.set_toSave(true);
		$docName$Info.setId($docName$Event.get$upperDocName$().getId());
		$docName$Info.set$upperDocName$ItemList($docName$Event.get$upperDocName$().get$upperDocName$ItemList());
		$docName$Info.setNumericId($docName$Event.get$upperDocName$().getNumericId());
		$docName$Info.setDateOfCreation($docName$Event.get$upperDocName$().getDateOfCreation());
		$docName$Info.setCreatedBy($docName$Event.get$upperDocName$().getCreatedBy());
//		$docName$Info.get$upperDocName$ItemModificationInfo().get$upperDocName$ItemModificationInfo().setSubmit("");
		
		CountryPageEvent allCountryPageEvent = null;
		StatePageEvent allStatePageEvent = null;
		PopulatedPlacePageEvent allPopulatedPlacePageEvent = null;

			
			allCountryPageEvent = countryService.requestAllByCountry();
			$docName$Info.setCountryList(allCountryPageEvent.getCountryList());

			if (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty()){	
					allStatePageEvent = stateService.requestAllByCountryName($docName$Event.get$upperDocName$().getCountry());
					$docName$Info.setStateList(allStatePageEvent.getStateList());
			}
			if (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty()){	
					allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName($docName$Event.get$upperDocName$().getState());
					$docName$Info.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
			}

		
		model.addAttribute("$docName$Info",$docName$Info);
		model.addAttribute("$docName$ItemInfoList", $docName$Info.get$upperDocName$ItemList());
		return "/docs/$docName$/save$upperDocName$";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docs/$docName$/save$upperDocName$";
	}
	
	private void manualValidation(Object object, String objectName, String origin) throws Exception{
		HashMap<Object, Object> errorMap = new HashMap<>();
		MapBindingResult mapBindingResult = new MapBindingResult(errorMap, objectName);
		validator.validate(object, mapBindingResult);
					
		if (!mapBindingResult.getAllErrors().isEmpty()){
			throw new SparkBindingException(mapBindingResult.getAllErrors(), origin);
		}
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		$upperDocName$Info $docName$Info=($upperDocName$Info) req.getSession().getAttribute("$docName$Info");
		if (exception instanceof BindException){
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$ValidationException",true);
			modelAndView = setErrors(modelAndView, ((BindException) exception).getAllErrors(), $docName$Info, "");
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		else if (exception instanceof SparkBindingException){
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$ValidationException",true);
			modelAndView = setErrors(modelAndView, ((SparkBindingException) exception).getErrors(), $docName$Info, CyberUtils.additionForm);
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		else {
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$CreateException",true);
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		
		try {
			$docName$Info.set_toSave(false);
			$upperDocName$ItemInfo $docName$ItemInfo = get$upperDocName$ItemInfo(req);	
			$docName$Info.setCurrent$upperDocName$ItemInfo($docName$ItemInfo);
			$upperDocName$ItemInfo $docName$ItemModificationInfo = get$upperDocName$ItemModificationInfo(req);
			$docName$Info.set$upperDocName$ItemModificationInfo($docName$ItemModificationInfo);
			$docName$ItemInfo.setSubmit("");
			$docName$ItemInfo.set$upperDocName$ItemModificationInfo(new $upperDocName$ItemInfo());
			$upperDocName$ItemInfo submitModification = new $upperDocName$ItemInfo();
			submitModification.setSubmit("");
			$docName$ItemModificationInfo.set$upperDocName$ItemModificationInfo(submitModification);
		
			modelAndView.addObject("$docName$Info",$docName$Info);
			modelAndView.addObject("$docName$ItemInfoList", $docName$Info.get$upperDocName$ItemList());
			modelAndView.addObject("$docName$ItemInfo",$docName$ItemInfo);
			modelAndView.addObject("$docName$ItemModificationInfo",$docName$ItemModificationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, List<ObjectError> errors, $upperDocName$Info $docName$Info, String origin){
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
	
	@ModelAttribute("$docName$Info")
	private $upperDocName$Info get$upperDocName$Info(String id, @ModelAttribute("$docName$Info") $upperDocName$Info $docName$Info, Model model, HttpServletRequest request)  throws Exception {
		$upperDocName$Info $docName$InfoSession = ($upperDocName$Info) request.getSession().getAttribute("$docName$Info");

		if ($docName$Info.getCreated()!=null && $docName$Info.getCreated()){
			$upperDocName$ItemInfo $docName$ItemInfo = get$upperDocName$ItemInfo(request);	
			$upperDocName$ItemInfo $docName$ItemModificationInfo = get$upperDocName$ItemModificationInfo(request);
			$docName$Info.setCurrent$upperDocName$ItemInfo($docName$ItemInfo);
			$docName$Info.set$upperDocName$ItemModificationInfo($docName$ItemModificationInfo);
			$docName$ItemInfo.setSubmit("");
			$upperDocName$ItemInfo submitModification = new $upperDocName$ItemInfo();
			submitModification.setSubmit("");
			$docName$ItemModificationInfo.set$upperDocName$ItemModificationInfo(submitModification);
			
			model.addAttribute("$docName$Info",$docName$InfoSession);
			model.addAttribute("$docName$ItemInfoList", $docName$Info.get$upperDocName$ItemList());
			model.addAttribute("$docName$ItemInfo", $docName$ItemInfo);
			model.addAttribute("$docName$ItemModificationInfo",requisitionItemModificationInfo);
			
			return requisitionInfo;
		}
		else{
			
			if (id==null)
				requisitionInfo = new $upperDocName$Info();
			else{
				requisitionInfo = requisitionService.request$upperDocName$Details(new Request$upperDocName$Event(id)).get$upperDocName$();
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
			
			$upperDocName$ItemInfo requisitionItemInfo = get$upperDocName$ItemInfo(request);	
			requisitionInfo.setCurrent$upperDocName$ItemInfo(requisitionItemInfo);
			$upperDocName$ItemInfo requisitionItemModificationInfo = get$upperDocName$ItemModificationInfo(request);
			requisitionInfo.set$upperDocName$ItemModificationInfo(requisitionItemModificationInfo);
			requisitionItemInfo.setSubmit("");
			$upperDocName$ItemInfo submitModification = new $upperDocName$ItemInfo();
			submitModification.setSubmit("");
			requisitionItemModificationInfo.set$upperDocName$ItemModificationInfo(submitModification);
		
			request.getSession().setAttribute("requisitionInfo", requisitionInfo);
			model.addAttribute("requisitionInfo",requisitionInfo);
			model.addAttribute("requisitionItemInfoList", requisitionInfo.get$upperDocName$ItemList());
			model.addAttribute("requisitionItemInfo", requisitionItemInfo);
			model.addAttribute("requisitionItemModificationInfo",requisitionItemModificationInfo);
			
			return requisitionInfo;
		}
		
	}
		
		private $upperDocName$ItemInfo get$upperDocName$ItemInfo(HttpServletRequest request) throws Exception {
			$upperDocName$ItemInfo requisitionItemInfo = new $upperDocName$ItemInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemInfo.setPriorityList(allPriorityEvent.getPriorityList());
			
			requisitionItemInfo.setId(null);
			requisitionItemInfo.setBodyRequiredOnDate(new Date());

			request.getSession().setAttribute("requisitionItemInfo", requisitionItemInfo);
			
			return requisitionItemInfo;
		}
		

		private $upperDocName$ItemInfo get$upperDocName$ItemModificationInfo(HttpServletRequest request) throws Exception{
			$upperDocName$ItemInfo requisitionItemModificationInfo = new $upperDocName$ItemInfo();
			
			PriorityPageEvent allPriorityEvent = priorityService.requestAllByPriority();
			requisitionItemModificationInfo.setPriorityList(allPriorityEvent.getPriorityList());

			request.getSession().setAttribute("requisitionItemModificationInfo", requisitionItemModificationInfo);
			
			return requisitionItemModificationInfo;
		}
}
