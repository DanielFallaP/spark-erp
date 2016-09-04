package co.com.cybersoft.$module$.tables.web.controller.$tableName$;

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
import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;
import co.com.cybersoft.$module$.tables.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$ModificationEvent;
import co.com.cybersoft.$module$.tables.events.$tableName$.Request$entityName$DetailsEvent;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$Info;
$referenceServicesImports$

/**
 * Controller class for $entityName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/$module$/$tableName$/modify$entityName$/{id}")
public class $entityName$ModificationController {
	private static final Logger LOG=LoggerFactory.getLogger($entityName$ModificationController.class);
	
	@Autowired
	private $entityName$Service $tableName$Service;
	
	$referenceServicesDeclarations$
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/$module$/$tableName$/modify$entityName$";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String modify$entityName$(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("$tableName$Info") $entityName$Info $tableName$Info, HttpServletRequest request) throws Exception {
		$compoundReferences$
		
		$entityName$Details $tableName$Details = create$entityName$Details($tableName$Info);
		$tableName$Details.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$tableName$Details.setDateOfModification(new Date());
		//$tableName$Details.set_companyId((($entityName$Details)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		$tableName$Service.modify$entityName$(new $entityName$ModificationEvent($tableName$Details));
		return "redirect:/$module$/$tableName$/search$entityName$";
	}
	
	private $entityName$Details create$entityName$Details($entityName$Info $tableName$Info){
		$entityName$Details $tableName$Details = new $entityName$Details();
		BeanUtils.copyProperties($tableName$Info, $tableName$Details);
		return $tableName$Details;
	}

	@ModelAttribute("$tableName$Info")
	private $entityName$Info get$entityName$Info(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the $tableName$ "+id);
		
		$entityName$Info $tableName$Info = new $entityName$Info();
			
		$entityName$DetailsEvent request$entityName$Details = $tableName$Service.request$entityName$Details(new Request$entityName$DetailsEvent(id));
		
		$additionalFields$
		$setReferencesLists$
		$setCompoundLists$
		
		BeanUtils.copyProperties(request$entityName$Details.get$entityName$Details(), $tableName$Info);
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
			modelAndView.setViewName("/$module$/$tableName$/modify$entityName$");
		}
		else{
			modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
			modelAndView.addObject("$tableName$ModificationException",true);
			modelAndView.setViewName("/$module$/$tableName$/modify$entityName$");
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, $entityName$Info $tableName$Info){
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
