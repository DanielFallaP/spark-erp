package co.com.cybersoft.web.controller.articulo;

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

import co.com.cybersoft.core.domain.ArticuloDetails;
import co.com.cybersoft.core.services.articulo.ArticuloService;
import co.com.cybersoft.events.articulo.CreateArticuloEvent;
import co.com.cybersoft.web.domain.articulo.ArticuloInfo;
import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaService;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaPageEvent;
import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.events.grupo.GrupoPageEvent;


/**
 * Controller for articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/articulo/createArticulo/{from}")
public class ArticuloCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ArticuloCreationController.class);
	
	@Autowired
	private ArticuloService articuloService;
	
	@Autowired
		private UnidadMedidaService unidadMedidaService;

	@Autowired
		private GrupoService grupoService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String articuloCreation() throws Exception {
		return "/configuration/articulo/createArticulo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createArticulo(@Valid @ModelAttribute("articuloInfo") ArticuloInfo articuloInfo, Model model, HttpServletRequest request) throws Exception{
		LOG.debug("Creation of articulo!!!");
		articuloInfo.setCreated(false);
		ArticuloDetails articuloDetails = createArticuloDetails(articuloInfo);
		articuloDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		articuloDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		articuloDetails.setDateOfCreation(new Date());
		articuloDetails.setDateOfModification(new Date());
		
		request.getSession().setAttribute("articuloInfo", articuloInfo);
		articuloService.createArticulo(new CreateArticuloEvent(articuloDetails));
		String calledFrom = articuloInfo.getCalledFrom();
		articuloInfo=new ArticuloInfo();
		articuloInfo.setCreated(true);
		articuloInfo.setCalledFrom(calledFrom);
		UnidadMedidaPageEvent allUnidadMedidaEvent = unidadMedidaService.requestAll();
		articuloInfo.setUnidadMedidaList(allUnidadMedidaEvent.getUnidadMedidaList());
		GrupoPageEvent allGrupoEvent = grupoService.requestAll();
		articuloInfo.setGrupoList(allGrupoEvent.getGrupoList());

		
		model.addAttribute("articuloInfo", articuloInfo);
		return "/configuration/articulo/createArticulo";
	}
	
	private ArticuloDetails createArticuloDetails(ArticuloInfo articuloInfo){
		ArticuloDetails articuloDetails = new ArticuloDetails();
		LOG.debug(articuloInfo.getCode());
		BeanUtils.copyProperties(articuloInfo, articuloDetails);
		return articuloDetails;
	}
	
	@ModelAttribute("articuloInfo")
	private ArticuloInfo getArticuloInfo(@PathVariable("from") String calledFrom)  throws Exception {
		ArticuloInfo articuloInfo = new ArticuloInfo();
		
		UnidadMedidaPageEvent allUnidadMedidaEvent = unidadMedidaService.requestAll();
		articuloInfo.setUnidadMedidaList(allUnidadMedidaEvent.getUnidadMedidaList());
		GrupoPageEvent allGrupoEvent = grupoService.requestAll();
		articuloInfo.setGrupoList(allGrupoEvent.getGrupoList());

		
		articuloInfo.setCalledFrom(calledFrom);
		return articuloInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("articuloInfo", req.getSession().getAttribute("articuloInfo"));
		modelAndView.addObject("articuloCreateException",true);
		modelAndView.setViewName("/configuration/articulo/createArticulo");
		exception.printStackTrace();
		return modelAndView;
	}
	
}