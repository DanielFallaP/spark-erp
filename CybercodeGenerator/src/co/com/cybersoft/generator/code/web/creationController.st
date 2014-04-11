package co.com.cybersoft.web.controller.$tableName$;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import co.com.cybersoft.core.domain.$entityName$Details;
import co.com.cybersoft.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.events.$tableName$.Create$entityName$Event;
import co.com.cybersoft.web.domain.$tableName$.$entityName$Info;

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
	
	@RequestMapping(method=RequestMethod.GET)
	public String $tableName$Creation(){
		return "/configuration/$tableName$/create$entityName$";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create$entityName$(@Valid @ModelAttribute("$tableName$Info") $entityName$Info $tableName$Info, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of an $tableName$!!!");
		$tableName$Info.setCreated(false);
		$entityName$Details $tableName$Details = create$entityName$Details($tableName$Info);
		$tableName$Details.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		request.getSession().setAttribute("$tableName$Info", $tableName$Info);
		$tableName$Service.create$entityName$(new Create$entityName$Event($tableName$Details));
		String calledFrom = $tableName$Info.getCalledFrom();
		$tableName$Info=new $entityName$Info();
		$tableName$Info.setCreated(true);
		$tableName$Info.setCalledFrom(calledFrom);
		model.addAttribute("$tableName$Info", $tableName$Info);
		return "/configuration/$tableName$/create$entityName$";
	}
	
	private $entityName$Details create$entityName$Details($entityName$Info $tableName$Info){
		$entityName$Details $tableName$Details = new $entityName$Details();
		LOG.debug($tableName$Info.getCode());
		BeanUtils.copyProperties($tableName$Info, $tableName$Details);
		return $tableName$Details;
	}
	
	@ModelAttribute("$tableName$Info")
	private $entityName$Info get$entityName$Info(@PathVariable("from") String calledFrom){
		$entityName$Info $tableName$Info = new $entityName$Info();
		$tableName$Info.setCalledFrom(calledFrom);
		return $tableName$Info;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("$tableName$Info", req.getSession().getAttribute("$tableName$Info"));
		modelAndView.addObject("$tableName$CreateException",true);
		modelAndView.setViewName("/configuration/$tableName$/create$entityName$");
		exception.printStackTrace();
		return modelAndView;
	}
	
}
