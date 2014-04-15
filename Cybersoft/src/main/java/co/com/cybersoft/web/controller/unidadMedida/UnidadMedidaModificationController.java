package co.com.cybersoft.web.controller.unidadMedida;

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

import co.com.cybersoft.core.domain.UnidadMedidaDetails;
import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaService;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaModificationEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaDetailsEvent;
import co.com.cybersoft.web.domain.unidadMedida.UnidadMedidaInfo;

/**
 * Controller class for UnidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/unidadMedida/modifyUnidadMedida/{code}")
public class UnidadMedidaModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(UnidadMedidaModificationController.class);
	
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/unidadMedida/modifyUnidadMedida";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyUnidadMedida(@Valid @ModelAttribute("unidadMedidaInfo") UnidadMedidaInfo unidadMedidaInfo) throws Exception {
		LOG.debug("Modification of unidadMedida "+unidadMedidaInfo.getCode());
		UnidadMedidaDetails unidadMedidaDetails = createUnidadMedidaDetails(unidadMedidaInfo);
		unidadMedidaDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		unidadMedidaDetails.setDateOfModification(new Date());
		
		unidadMedidaService.modifyUnidadMedida(new UnidadMedidaModificationEvent(unidadMedidaDetails));
		return "redirect:/configuration/unidadMedida/searchUnidadMedida";
	}
	
	private UnidadMedidaDetails createUnidadMedidaDetails(UnidadMedidaInfo unidadMedidaInfo){
		UnidadMedidaDetails unidadMedidaDetails = new UnidadMedidaDetails();
		LOG.debug(unidadMedidaInfo.getCode());
		BeanUtils.copyProperties(unidadMedidaInfo, unidadMedidaDetails);
		return unidadMedidaDetails;
	}

	@ModelAttribute("unidadMedidaInfo")
	private UnidadMedidaInfo getUnidadMedidaInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the unidadMedida "+code);
		UnidadMedidaInfo unidadMedidaInfo = new UnidadMedidaInfo();
		
		UnidadMedidaDetailsEvent requestUnidadMedidaDetails = unidadMedidaService.requestUnidadMedidaDetails(new RequestUnidadMedidaDetailsEvent(code));
		
		BeanUtils.copyProperties(requestUnidadMedidaDetails.getUnidadMedidaDetails(), unidadMedidaInfo);
		return unidadMedidaInfo;
	}
}