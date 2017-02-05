package co.com.cybersoft.maintenance.tables.web.controller.contract;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.Model;
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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;
import co.com.cybersoft.maintenance.tables.core.services.contract.ContractService;
import co.com.cybersoft.maintenance.tables.events.contract.ContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractModificationEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractInfo;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.maintenance.tables.events.responsible.ResponsiblePageEvent;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;


/**
 * Controller class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/contract/modifyContract/{id}")
public class ContractModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ContractModificationController.class);
	
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
		return "/maintenance/contract/modifyContract";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modifyContract(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("contractInfo") ContractInfo contractInfo, HttpServletRequest request) throws Exception {
		
		ContractDetails contractDetails = createContractDetails(contractInfo);
		contractDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		contractDetails.setDateOfModification(new Date());
		//contractDetails.set_companyId(((ContractDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("contractInfo", contractInfo);
		contractService.modifyContract(new ContractModificationEvent(contractDetails));
		return "redirect:/maintenance/contract/searchContract";
	}
	
	private ContractDetails createContractDetails(ContractInfo contractInfo){
		ContractDetails contractDetails = new ContractDetails();
		BeanUtils.copyProperties(contractInfo, contractDetails);
		return contractDetails;
	}

	@ModelAttribute("contractInfo")
	private ContractInfo getContractInfo(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the contract "+id);
		
		ContractInfo contractInfo = new ContractInfo();
			
		ContractDetailsEvent requestContractDetails = contractService.requestContractDetails(new RequestContractDetailsEvent(id));
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		contractInfo.setCompanyList(allCompanyEvent.getCompanyList());
		ResponsiblePageEvent allResponsibleEvent = responsibleService.requestAllByThird();
		contractInfo.setResponsibleList(allResponsibleEvent.getResponsibleList());
		CostCentersCustomersPageEvent allCostCenterEvent = costCentersCustomersService.requestAllByNameCostCenter();
		contractInfo.setCostCenterList(allCostCenterEvent.getCostCentersCustomersList());

		
		BeanUtils.copyProperties(requestContractDetails.getContractDetails(), contractInfo);
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
			modelAndView.setViewName("/maintenance/contract/modifyContract");
		}
		else{
			modelAndView.addObject("contractInfo", req.getSession().getAttribute("contractInfo"));
			modelAndView.addObject("contractModificationException",true);
			modelAndView.setViewName("/maintenance/contract/modifyContract");
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