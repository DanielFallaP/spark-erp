package co.com.cybersoft.web.controller.grupo;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.core.domain.GrupoDetails;
import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.events.grupo.GrupoDetailsEvent;
import co.com.cybersoft.events.grupo.GrupoModificationEvent;
import co.com.cybersoft.events.grupo.RequestGrupoDetailsEvent;
import co.com.cybersoft.web.domain.grupo.GrupoInfo;

/**
 * Controller class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/grupo/modifyGrupo/{code}")
public class GrupoModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(GrupoModificationController.class);
	
	@Autowired
	private GrupoService grupoService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/grupo/modifyGrupo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyGrupo(@Valid @ModelAttribute("grupoInfo") GrupoInfo grupoInfo) throws Exception {
		LOG.debug("Modification of grupo "+grupoInfo.getCode());
		GrupoDetails grupoDetails = createGrupoDetails(grupoInfo);
		grupoDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		grupoDetails.setDateOfModification(new Date());
		
		grupoService.modifyGrupo(new GrupoModificationEvent(grupoDetails));
		return "redirect:/configuration/grupo/searchGrupo";
	}
	
	private GrupoDetails createGrupoDetails(GrupoInfo grupoInfo){
		GrupoDetails grupoDetails = new GrupoDetails();
		LOG.debug(grupoInfo.getCode());
		BeanUtils.copyProperties(grupoInfo, grupoDetails);
		return grupoDetails;
	}

	@ModelAttribute("grupoInfo")
	private GrupoInfo getGrupoInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the grupo "+code);
		GrupoInfo grupoInfo = new GrupoInfo();
		
		GrupoDetailsEvent requestGrupoDetails = grupoService.requestGrupoDetails(new RequestGrupoDetailsEvent(code));
		
		BeanUtils.copyProperties(requestGrupoDetails.getGrupoDetails(), grupoInfo);
		return grupoInfo;
	}
}