package co.com.cybersoft.web.controller.operationType;

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

import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.events.operationType.OperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.OperationTypeModificationEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypeDetailsEvent;
import co.com.cybersoft.web.domain.operationType.OperationTypeInfo;

/**
 * Controller class for OperationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/operationType/modifyOperationType/{code}")
public class OperationTypeModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(OperationTypeModificationController.class);
	
	@Autowired
	private OperationTypeService operationTypeService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/operationType/modifyOperationType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyOperationType(@Valid @ModelAttribute("operationTypeInfo") OperationTypeInfo operationTypeInfo, HttpServletRequest request) throws Exception {
		OperationTypeDetails operationTypeDetails = createOperationTypeDetails(operationTypeInfo);
		operationTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		operationTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("operationTypeInfo", operationTypeInfo);
		operationTypeService.modifyOperationType(new OperationTypeModificationEvent(operationTypeDetails));
		return "redirect:/configuration/operationType/searchOperationType";
	}
	
	private OperationTypeDetails createOperationTypeDetails(OperationTypeInfo operationTypeInfo){
		OperationTypeDetails operationTypeDetails = new OperationTypeDetails();
		BeanUtils.copyProperties(operationTypeInfo, operationTypeDetails);
		return operationTypeDetails;
	}

	@ModelAttribute("operationTypeInfo")
	private OperationTypeInfo getOperationTypeInfo(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the operationType "+code);
		OperationTypeInfo operationTypeInfo = new OperationTypeInfo();
		
		OperationTypeDetailsEvent requestOperationTypeDetails = operationTypeService.requestOperationTypeDetails(new RequestOperationTypeDetailsEvent(code));
		request.getSession().setAttribute("operationTypeInfo", operationTypeInfo);
		
		BeanUtils.copyProperties(requestOperationTypeDetails.getOperationTypeDetails(), operationTypeInfo);
		return operationTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("operationTypeInfo", req.getSession().getAttribute("operationTypeInfo"));
			modelAndView.addObject("operationTypeValidationException",true);
			modelAndView.setViewName("/configuration/operationType/modifyOperationType");
		}
		else{
			modelAndView.addObject("operationTypeInfo", req.getSession().getAttribute("operationTypeInfo"));
			modelAndView.addObject("operationTypeModificationException",true);
			modelAndView.setViewName("/configuration/operationType/modifyOperationType");
		}
		return modelAndView;
	}
}