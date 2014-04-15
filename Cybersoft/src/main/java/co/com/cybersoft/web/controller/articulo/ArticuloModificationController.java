package co.com.cybersoft.web.controller.articulo;

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

import co.com.cybersoft.core.domain.ArticuloDetails;
import co.com.cybersoft.core.services.articulo.ArticuloService;
import co.com.cybersoft.events.articulo.ArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.ArticuloModificationEvent;
import co.com.cybersoft.events.articulo.RequestArticuloDetailsEvent;
import co.com.cybersoft.web.domain.articulo.ArticuloInfo;
import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaService;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaPageEvent;
import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.events.grupo.GrupoPageEvent;


/**
 * Controller class for Articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/articulo/modifyArticulo/{code}")
public class ArticuloModificationController {
	private static final Logger LOG=LoggerFactory.getLogger(ArticuloModificationController.class);
	
	@Autowired
	private ArticuloService articuloService;
	
	@Autowired
		private UnidadMedidaService unidadMedidaService;

	@Autowired
		private GrupoService grupoService;


	
	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(){
		return "/configuration/articulo/modifyArticulo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyArticulo(@Valid @ModelAttribute("articuloInfo") ArticuloInfo articuloInfo) throws Exception {
		LOG.debug("Modification of articulo "+articuloInfo.getCode());
		ArticuloDetails articuloDetails = createArticuloDetails(articuloInfo);
		articuloDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		articuloDetails.setDateOfModification(new Date());
		
		articuloService.modifyArticulo(new ArticuloModificationEvent(articuloDetails));
		return "redirect:/configuration/articulo/searchArticulo";
	}
	
	private ArticuloDetails createArticuloDetails(ArticuloInfo articuloInfo){
		ArticuloDetails articuloDetails = new ArticuloDetails();
		LOG.debug(articuloInfo.getCode());
		BeanUtils.copyProperties(articuloInfo, articuloDetails);
		return articuloDetails;
	}

	@ModelAttribute("articuloInfo")
	private ArticuloInfo getArticuloInfo(@PathVariable("code") String code) throws Exception {
		LOG.debug("Retrieving the articulo "+code);
		ArticuloInfo articuloInfo = new ArticuloInfo();
		
		ArticuloDetailsEvent requestArticuloDetails = articuloService.requestArticuloDetails(new RequestArticuloDetailsEvent(code));
		UnidadMedidaPageEvent allUnidadMedidaEvent = unidadMedidaService.requestAll();
		articuloInfo.setUnidadMedidaList(allUnidadMedidaEvent.getUnidadMedidaList());
		articuloInfo.rearrangeUnidadMedidaList(articuloInfo.getUnidadMedida());
		GrupoPageEvent allGrupoEvent = grupoService.requestAll();
		articuloInfo.setGrupoList(allGrupoEvent.getGrupoList());
		articuloInfo.rearrangeGrupoList(articuloInfo.getGrupo());

		
		BeanUtils.copyProperties(requestArticuloDetails.getArticuloDetails(), articuloInfo);
		return articuloInfo;
	}
}