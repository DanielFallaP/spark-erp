package co.com.cybersoft.web.controller.operationType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;

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

import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.events.operationType.CreateOperationTypeEvent;
import co.com.cybersoft.web.domain.operationType.OperationTypeInfo;

/**
 * Controller for operationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/operationType/createOperationType/{from}")
public class OperationTypeCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(OperationTypeCreationController.class);
	
	@Autowired
	private OperationTypeService operationTypeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String operationTypeCreation() throws Exception {
		return "/configuration/operationType/createOperationType";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createOperationType(@Valid @ModelAttribute("operationTypeInfo") OperationTypeInfo operationTypeInfo, Model model, HttpServletRequest request) throws Exception{
		operationTypeInfo.setCreated(false);
		OperationTypeDetails operationTypeDetails = createOperationTypeDetails(operationTypeInfo);
		operationTypeDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		operationTypeDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		operationTypeDetails.setDateOfCreation(new Date());
		operationTypeDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("operationTypeInfo", operationTypeInfo);
		operationTypeService.createOperationType(new CreateOperationTypeEvent(operationTypeDetails));
		String calledFrom = operationTypeInfo.getCalledFrom();
		operationTypeInfo=new OperationTypeInfo();
		operationTypeInfo.setCreated(true);
		operationTypeInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("operationTypeInfo", operationTypeInfo);
		return "/configuration/operationType/createOperationType";
	}
	
	private OperationTypeDetails createOperationTypeDetails(OperationTypeInfo operationTypeInfo){
		OperationTypeDetails operationTypeDetails = new OperationTypeDetails();
		BeanUtils.copyProperties(operationTypeInfo, operationTypeDetails);
		return operationTypeDetails;
	}
	
	@ModelAttribute("operationTypeInfo")
	private OperationTypeInfo getOperationTypeInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		OperationTypeInfo operationTypeInfo = new OperationTypeInfo();
		
		
		operationTypeInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("operationTypeInfo", operationTypeInfo);
		
		return operationTypeInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("operationTypeInfo", req.getSession().getAttribute("operationTypeInfo"));
			modelAndView.addObject("operationTypeValidationException",true);
			modelAndView.setViewName("/configuration/operationType/createOperationType");
		}
		else{
			modelAndView.addObject("operationTypeInfo", req.getSession().getAttribute("operationTypeInfo"));
			modelAndView.addObject("operationTypeCreateException",true);
			modelAndView.setViewName("/configuration/operationType/createOperationType");
		}
		return modelAndView;
	}
	
}