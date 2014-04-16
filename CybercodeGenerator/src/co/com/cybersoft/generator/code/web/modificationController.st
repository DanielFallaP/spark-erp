package co.com.cybersoft.web.controller.$tableName$;

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
@RequestMapping("/configuration/$tableName$/modify$entityName$/{code}")
public class $entityName$ModificationController {
	private static final Logger LOG=LoggerFactory.getLogger($entityName$ModificationController.class);
	
	@Autowired
	private $entityName$Service $tableName$Service;
	
	$referenceServicesDeclarations$
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/$tableName$/modify$entityName$";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modify$entityName$(@Valid @ModelAttribute("$tableName$Info") $entityName$Info $tableName$Info, HttpServletRequest request) throws Exception {
		$entityName$Details $tableName$Details = create$entityName$Details($tableName$Info);
		$tableName$Details.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$tableName$Details.setDateOfModification(new Date());
		
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		$tableName$Service.modify$entityName$(new $entityName$ModificationEvent($tableName$Details));
		return "redirect:/configuration/$tableName$/search$entityName$";
	}
	
	private $entityName$Details create$entityName$Details($entityName$Info $tableName$Info){
		$entityName$Details $tableName$Details = new $entityName$Details();
		BeanUtils.copyProperties($tableName$Info, $tableName$Details);
		return $tableName$Details;
	}

	@ModelAttribute("$tableName$Info")
	private $entityName$Info get$entityName$Info(@PathVariable("code") String code, HttpServletRequest request) throws Exception {
		LOG.debug("Retrieving the $tableName$ "+code);
		$entityName$Info $tableName$Info = new $entityName$Info();
		
		$entityName$DetailsEvent request$entityName$Details = $tableName$Service.request$entityName$Details(new Request$entityName$DetailsEvent(code));
		$setReferencesLists$
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		
		BeanUtils.copyProperties(request$entityName$Details.get$entityName$Details(), $tableName$Info);
		return $tableName$Info;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
			modelAndView.addObject("$tableName$ValidationException",true);
			modelAndView.setViewName("/configuration/$tableName$/modify$entityName$");
		}
		else{
			modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
			modelAndView.addObject("$tableName$ModificationException",true);
			modelAndView.setViewName("/configuration/$tableName$/modify$entityName$");
		}
		return modelAndView;
	}
}