package co.com.cybersoft.web.controller.articulo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.articulo.ArticuloService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.articulo.ArticuloPageEvent;
import co.com.cybersoft.events.articulo.RequestArticuloPageEvent;
import co.com.cybersoft.persistence.domain.Articulo;

/**
 * Search controller class for articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/articulo/searchArticulo")
public class ArticuloSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ArticuloSearchController.class);

	@Autowired
	private ArticuloService articuloService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  articulo ");
		ArticuloPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("articuloField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("articuloAscending");
			if (request.getSession().getAttribute("articuloAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("articuloAscending", direction);
			}
			else
				request.getSession().setAttribute("articuloAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("articuloField")==null){
			details = articuloService.requestArticuloPage(new RequestArticuloPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("articuloAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("articuloAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("articuloField"):field));
			}
			details = articuloService.requestArticuloPage(new RequestArticuloPageEvent(pageRequest));
		}
		
		PageWrapper<Articulo> page=new PageWrapper<Articulo>(details.getArticuloPage(),"/configuration/articulo/searchArticulo");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/articulo/searchArticulo";
	}
}