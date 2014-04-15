package co.com.cybersoft.web.controller.grupo;

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
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.core.domain.GrupoDetails;
import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.events.grupo.CreateGrupoEvent;
import co.com.cybersoft.web.domain.grupo.GrupoInfo;

/**
 * Controller for grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/grupo/createGrupo/{from}")
public class GrupoCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(GrupoCreationController.class);
	
	@Autowired
	private GrupoService grupoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String grupoCreation() throws Exception {
		return "/configuration/grupo/createGrupo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createGrupo(@Valid @ModelAttribute("grupoInfo") GrupoInfo grupoInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of grupo!!!");
		grupoInfo.setCreated(false);
		GrupoDetails grupoDetails = createGrupoDetails(grupoInfo);
		grupoDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		grupoDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		grupoDetails.setDateOfCreation(new Date());
		grupoDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("grupoInfo", grupoInfo);
		grupoService.createGrupo(new CreateGrupoEvent(grupoDetails));
		String calledFrom = grupoInfo.getCalledFrom();
		grupoInfo=new GrupoInfo();
		grupoInfo.setCreated(true);
		grupoInfo.setCalledFrom(calledFrom);
		
		model.addAttribute("grupoInfo", grupoInfo);
		return "/configuration/grupo/createGrupo";
	}
	
	private GrupoDetails createGrupoDetails(GrupoInfo grupoInfo){
		GrupoDetails grupoDetails = new GrupoDetails();
		LOG.debug(grupoInfo.getCode());
		BeanUtils.copyProperties(grupoInfo, grupoDetails);
		return grupoDetails;
	}
	
	@ModelAttribute("grupoInfo")
	private GrupoInfo getGrupoInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		GrupoInfo grupoInfo = new GrupoInfo();
		grupoInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("grupoInfo", grupoInfo);
		return grupoInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			modelAndView.addObject("grupoInfo", req.getSession().getAttribute("grupoInfo"));
			modelAndView.addObject("grupoValidationException",true);
			modelAndView.setViewName("/configuration/grupo/createGrupo");
		}
		else{
			modelAndView.addObject("grupoInfo", req.getSession().getAttribute("grupoInfo"));
			modelAndView.addObject("grupoCreateException",true);
			modelAndView.setViewName("/configuration/grupo/createGrupo");
		}
		return modelAndView;
	}
	
}