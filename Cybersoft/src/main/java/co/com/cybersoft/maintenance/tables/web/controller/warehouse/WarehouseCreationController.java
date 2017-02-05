package co.com.cybersoft.maintenance.tables.web.controller.warehouse;

import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import org.springframework.ui.Model;

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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.maintenance.tables.events.warehouse.CreateWarehouseEvent;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseInfo;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehouseDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationService;
import co.com.cybersoft.maintenance.tables.events.physicalLocation.PhysicalLocationPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeService;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeService;
import co.com.cybersoft.maintenance.tables.events.storeType.StoreTypePageEvent;


/**
 * Controller for warehouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/warehouse/createWarehouse/{from}")
public class WarehouseCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(WarehouseCreationController.class);
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private PhysicalLocationService physicalLocationService;

	@Autowired
		private CostingTypeService costingTypeService;

	@Autowired
		private StoreTypeService storeTypeService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/warehouse/createWarehouse";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createWarehouse(@Valid @ModelAttribute("warehouseInfo") WarehouseInfo warehouseInfo, Model model, HttpServletRequest request) throws Exception{
		warehouseInfo.setCreated(false);
		WarehouseDetails warehouseDetails = createWarehouseDetails(warehouseInfo);
		warehouseDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		warehouseDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		warehouseDetails.setDateOfCreation(new Date());
		warehouseDetails.setDateOfModification(new Date());
		//warehouseDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("warehouseInfo", warehouseInfo);
		warehouseService.createWarehouse(new CreateWarehouseEvent(warehouseDetails));
		String calledFrom = warehouseInfo.getCalledFrom();
		warehouseInfo=new WarehouseInfo();
		warehouseInfo.setCreated(true);
		warehouseInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		warehouseInfo.setCompanyList(allCompanyEvent.getCompanyList());
		PhysicalLocationPageEvent allPhysicalLocationEvent = physicalLocationService.requestAllByNamePhysicalLocation();
		warehouseInfo.setPhysicalLocationList(allPhysicalLocationEvent.getPhysicalLocationList());
		CostingTypePageEvent allCostingTypeEvent = costingTypeService.requestAllByCostingType();
		warehouseInfo.setCostingTypeList(allCostingTypeEvent.getCostingTypeList());
		StoreTypePageEvent allStoreTypeEvent = storeTypeService.requestAllByStoreName();
		warehouseInfo.setStoreTypeList(allStoreTypeEvent.getStoreTypeList());

		
		warehouseInfo.setActive(true);

		
		model.addAttribute("warehouseInfo", warehouseInfo);
		return "/maintenance/warehouse/createWarehouse";
	}
	
	private WarehouseDetails createWarehouseDetails(WarehouseInfo warehouseInfo){
		WarehouseDetails warehouseDetails = new WarehouseDetails();
		BeanUtils.copyProperties(warehouseInfo, warehouseDetails);
		return warehouseDetails;
	}
	
	@ModelAttribute("warehouseInfo")
	private WarehouseInfo getWarehouseInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		warehouseInfo.setCompanyList(allCompanyEvent.getCompanyList());
		PhysicalLocationPageEvent allPhysicalLocationEvent = physicalLocationService.requestAllByNamePhysicalLocation();
		warehouseInfo.setPhysicalLocationList(allPhysicalLocationEvent.getPhysicalLocationList());
		CostingTypePageEvent allCostingTypeEvent = costingTypeService.requestAllByCostingType();
		warehouseInfo.setCostingTypeList(allCostingTypeEvent.getCostingTypeList());
		StoreTypePageEvent allStoreTypeEvent = storeTypeService.requestAllByStoreName();
		warehouseInfo.setStoreTypeList(allStoreTypeEvent.getStoreTypeList());

		
		
		if (value!=null){
			RequestWarehouseDetailsEvent event = new RequestWarehouseDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			WarehouseDetailsEvent responseEvent = warehouseService.requestWarehouseDetails(event);
			if (responseEvent.getWarehouseDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getWarehouseDetails(), warehouseInfo);
		}
		
		
		warehouseInfo.setId(null);
		warehouseInfo.setActive(true);

		
		warehouseInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("warehouseInfo", warehouseInfo);
		
		return warehouseInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			WarehouseInfo warehouseInfo=(WarehouseInfo) req.getSession().getAttribute("warehouseInfo");
			modelAndView.addObject("warehouseInfo", warehouseInfo);
			modelAndView.addObject("warehouseValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, warehouseInfo);
			modelAndView.setViewName("/maintenance/warehouse/createWarehouse");
		}
		else{
			modelAndView.addObject("warehouseInfo", req.getSession().getAttribute("warehouseInfo"));
			modelAndView.addObject("warehouseCreateException",true);
			modelAndView.setViewName("/maintenance/warehouse/createWarehouse");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, WarehouseInfo warehouseInfo){
		List<ObjectError> errors = exception.getAllErrors();
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
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
}