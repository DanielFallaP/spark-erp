package co.com.cybersoft.web.controller.$tableName$;

import java.util.Date;
import java.util.List;

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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.core.domain.$entityName$Details;
import co.com.cybersoft.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.events.$tableName$.$entityName$ModificationEvent;
import co.com.cybersoft.events.$tableName$.Request$entityName$DetailsEvent;
import co.com.cybersoft.web.domain.$tableName$.$entityName$Info;
$referenceServicesImports$

/**
 * Controller class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/businessRules/set$entityName$")
public class Set$entityName$Controller {
	private static final Logger LOG=LoggerFactory.getLogger(Set$entityName$Controller.class);
	
	@Autowired
	private $entityName$Service $tableName$Service;
	
	$referenceServicesDeclarations$
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/$tableName$/set$entityName$";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modify$entityName$(@Valid @ModelAttribute("$tableName$Info") $entityName$Info $tableName$Info, HttpServletRequest request) throws Exception {
		$entityName$Details $tableName$Details = create$entityName$Details($tableName$Info);
		$tableName$Details.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$tableName$Details.setDateOfModification(new Date());
		
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		$tableName$Service.modify$entityName$(new $entityName$ModificationEvent($tableName$Details));
		return "redirect:/settings";
	}
	
	private $entityName$Details create$entityName$Details($entityName$Info $tableName$Info){
		$entityName$Details $tableName$Details = new $entityName$Details();
		BeanUtils.copyProperties($tableName$Info, $tableName$Details);
		return $tableName$Details;
	}

	@ModelAttribute("$tableName$Info")
	private $entityName$Info get$entityName$Info(HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the $tableName$");
		$entityName$Info $tableName$Info = new $entityName$Info();
		
		$entityName$DetailsEvent request$entityName$Details = $tableName$Service.requestOnlyRecord();
		$setReferencesLists$
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		
		BeanUtils.copyProperties(request$entityName$Details.get$entityName$Details(), $tableName$Info);
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
			modelAndView.setViewName("/configuration/$tableName$/set$entityName$");
		}
		else{
			modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
			modelAndView.addObject("$tableName$ModificationException",true);
			modelAndView.setViewName("/configuration/$tableName$/set$entityName$");
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
