package co.com.cybersoft.maintenance.tables.web.controller.contract;

import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;

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
import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;
import co.com.cybersoft.maintenance.tables.core.services.contract.ContractService;
import co.com.cybersoft.maintenance.tables.events.contract.CreateContractEvent;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractInfo;
import co.com.cybersoft.maintenance.tables.events.contract.ContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;


/**
 * Controller for contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/contract/createContract/{from}")
public class ContractCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ContractCreationController.class);
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
		private CompanyService companyService;

	@Autowired
		private ResponsibleService responsibleService;

	@Autowired
		private CostCentersCustomersService costCentersCustomersService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/contract/createContract";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createContract(@Valid @ModelAttribute("contractInfo") ContractInfo contractInfo, Model model, HttpServletRequest request) throws Exception{
		contractInfo.setCreated(false);
		ContractDetails contractDetails = createContractDetails(contractInfo);
		contractDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setDateOfCreation(new Date());
		contractDetails.setDateOfModification(new Date());
		//contractDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("contractInfo", contractInfo);
		contractService.createContract(new CreateContractEvent(contractDetails));
		String calledFrom = contractInfo.getCalledFrom();
		contractInfo=new ContractInfo();
		contractInfo.setCreated(true);
		contractInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		contractInfo.setCompanyList(allCompanyEvent.getCompanyList());
		ResponsiblePageEvent allResponsibleEvent = responsibleService.requestAllByThird();
		contractInfo.setResponsibleList(allResponsibleEvent.getResponsibleList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		contractInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		contractInfo.setActive(true);

		
		model.addAttribute("contractInfo", contractInfo);
		return "/maintenance/contract/createContract";
	}
	
	private ContractDetails createContractDetails(ContractInfo contractInfo){
		ContractDetails contractDetails = new ContractDetails();
		BeanUtils.copyProperties(contractInfo, contractDetails);
		return contractDetails;
	}
	
	@ModelAttribute("contractInfo")
	private ContractInfo getContractInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		ContractInfo contractInfo = new ContractInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		contractInfo.setCompanyList(allCompanyEvent.getCompanyList());
		ResponsiblePageEvent allResponsibleEvent = responsibleService.requestAllByThird();
		contractInfo.setResponsibleList(allResponsibleEvent.getResponsibleList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		contractInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		
		if (value!=null){
			RequestContractDetailsEvent event = new RequestContractDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			ContractDetailsEvent responseEvent = contractService.requestContractDetails(event);
			if (responseEvent.getContractDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getContractDetails(), contractInfo);
		}
		
		
		contractInfo.setId(null);
		contractInfo.setActive(true);

		
		contractInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("contractInfo", contractInfo);
		
		return contractInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			ContractInfo contractInfo=(ContractInfo) req.getSession().getAttribute("contractInfo");
			modelAndView.addObject("contractInfo", contractInfo);
			modelAndView.addObject("contractValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, contractInfo);
			modelAndView.setViewName("/maintenance/contract/createContract");
		}
		else{
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractCreateException",true);
			modelAndView.setViewName("/maintenance/contract/createContract");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, ContractInfo contractInfo){
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