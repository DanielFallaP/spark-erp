package co.com.cybersoft.web.controller.$tableName$;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

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

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.core.domain.$entityName$Details;
import co.com.cybersoft.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.events.$tableName$.Create$entityName$Event;
import co.com.cybersoft.web.domain.$tableName$.$entityName$Info;
import co.com.cybersoft.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.events.$tableName$.Request$entityName$DetailsEvent;


$referenceServicesImports$

/**
 * Controller for $tableName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/$tableName$/create$entityName$/{from}")
public class $entityName$CreationController {

	private static final Logger LOG = LoggerFactory.getLogger($entityName$CreationController.class);
	
	@Autowired
	private $entityName$Service $tableName$Service;
	
	$referenceServicesDeclarations$
	
	@RequestMapping(method=RequestMethod.GET)
	public String $tableName$Creation() throws Exception {
		return "/configuration/$tableName$/create$entityName$";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create$entityName$(@Valid @ModelAttribute("$tableName$Info") $entityName$Info $tableName$Info, Model model, HttpServletRequest request) throws Exception{
		$compoundReferences$
		$tableName$Info.setCreated(false);
		$entityName$Details $tableName$Details = create$entityName$Details($tableName$Info);
		$tableName$Details.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		$tableName$Details.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$tableName$Details.setDateOfCreation(new Date());
		$tableName$Details.setDateOfModification(new Date());
		
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		$tableName$Service.create$entityName$(new Create$entityName$Event($tableName$Details));
		String calledFrom = $tableName$Info.getCalledFrom();
		$tableName$Info=new $entityName$Info();
		$tableName$Info.setCreated(true);
		$tableName$Info.setCalledFrom(calledFrom);
		$setReferencesLists$
		$setCompoundLists$
		
		$setDefaults$
		
		model.addAttribute("$tableName$Info", $tableName$Info);
		return "/configuration/$tableName$/create$entityName$";
	}
	
	private $entityName$Details create$entityName$Details($entityName$Info $tableName$Info){
		$entityName$Details $tableName$Details = new $entityName$Details();
		BeanUtils.copyProperties($tableName$Info, $tableName$Details);
		return $tableName$Details;
	}
	
	@ModelAttribute("$tableName$Info")
	private $entityName$Info get$entityName$Info(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		$entityName$Info $tableName$Info = new $entityName$Info();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		$compoundReferencesParameters$
		
		$setReferencesLists$
		$setCompoundLists$
		
		if (value!=null){
			Request$entityName$DetailsEvent event = new Request$entityName$DetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			$entityName$DetailsEvent responseEvent = $tableName$Service.request$entityName$Details(event);
			if (responseEvent.get$entityName$Details()!=null)
				BeanUtils.copyProperties(responseEvent.get$entityName$Details(), $tableName$Info);
		}
		
		
		$tableName$Info.setId(null);
		$setDefaults$
		
		$tableName$Info.setCalledFrom(calledFrom);
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		
		return $tableName$Info;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			$entityName$Info $tableName$Info=($entityName$Info) req.getSession().getAttribute("$tableName$Info");
			modelAndView.addObject("$tableName$Info", $tableName$Info);
			modelAndView.addObject("$tableName$ValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, $tableName$Info);
			modelAndView.setViewName("/configuration/$tableName$/create$entityName$");
		}
		else{
			modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
			modelAndView.addObject("$tableName$CreateException",true);
			modelAndView.setViewName("/configuration/$tableName$/create$entityName$");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, $entityName$Info $tableName$Info){
		List<ObjectError> errors = exception.getAllErrors();
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (fieldError.getCodes()[0].startsWith(CyberUtils.lenghtValidation)
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
