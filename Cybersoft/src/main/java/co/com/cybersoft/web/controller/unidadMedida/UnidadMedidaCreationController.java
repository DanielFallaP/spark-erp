package co.com.cybersoft.web.controller.unidadMedida;

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

import co.com.cybersoft.core.domain.UnidadMedidaDetails;
import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaService;
import co.com.cybersoft.events.unidadMedida.CreateUnidadMedidaEvent;
import co.com.cybersoft.web.domain.unidadMedida.UnidadMedidaInfo;

/**
 * Controller for unidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/unidadMedida/createUnidadMedida/{from}")
public class UnidadMedidaCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(UnidadMedidaCreationController.class);
	
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String unidadMedidaCreation() throws Exception {
		return "/configuration/unidadMedida/createUnidadMedida";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createUnidadMedida(@Valid @ModelAttribute("unidadMedidaInfo") UnidadMedidaInfo unidadMedidaInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of unidadMedida!!!");
		unidadMedidaInfo.setCreated(false);
		UnidadMedidaDetails unidadMedidaDetails = createUnidadMedidaDetails(unidadMedidaInfo);
		unidadMedidaDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		unidadMedidaDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		unidadMedidaDetails.setDateOfCreation(new Date());
		unidadMedidaDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("unidadMedidaInfo", unidadMedidaInfo);
		unidadMedidaService.createUnidadMedida(new CreateUnidadMedidaEvent(unidadMedidaDetails));
		String calledFrom = unidadMedidaInfo.getCalledFrom();
		unidadMedidaInfo=new UnidadMedidaInfo();
		unidadMedidaInfo.setCreated(true);
		unidadMedidaInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("unidadMedidaInfo", unidadMedidaInfo);
		return "/configuration/unidadMedida/createUnidadMedida";
	}
	
	private UnidadMedidaDetails createUnidadMedidaDetails(UnidadMedidaInfo unidadMedidaInfo){
		UnidadMedidaDetails unidadMedidaDetails = new UnidadMedidaDetails();
		LOG.debug(unidadMedidaInfo.getCode());
		BeanUtils.copyProperties(unidadMedidaInfo, unidadMedidaDetails);
		return unidadMedidaDetails;
	}
	
	@ModelAttribute("unidadMedidaInfo")
	private UnidadMedidaInfo getUnidadMedidaInfo(@PathVariable("from") String calledFrom)  throws Exception {
		UnidadMedidaInfo unidadMedidaInfo = new UnidadMedidaInfo();
		
		
		unidadMedidaInfo.setCalledFrom(calledFrom);
		return unidadMedidaInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("unidadMedidaInfo", req.getSession().getAttribute("unidadMedidaInfo"));
		modelAndView.addObject("unidadMedidaCreateException",true);
		modelAndView.setViewName("/configuration/unidadMedida/createUnidadMedida");
		exception.printStackTrace();
		return modelAndView;
	}
	
}