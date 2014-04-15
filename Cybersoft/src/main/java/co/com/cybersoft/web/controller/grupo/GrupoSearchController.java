package co.com.cybersoft.web.controller.grupo;

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

import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.grupo.GrupoPageEvent;
import co.com.cybersoft.events.grupo.RequestGrupoPageEvent;
import co.com.cybersoft.persistence.domain.Grupo;

/**
 * Search controller class for grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/grupo/searchGrupo")
public class GrupoSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(GrupoSearchController.class);

	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  grupo ");
		GrupoPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("grupoField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("grupoAscending");
			if (request.getSession().getAttribute("grupoAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("grupoAscending", direction);
			}
			else
				request.getSession().setAttribute("grupoAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("grupoField")==null){
			details = grupoService.requestGrupoPage(new RequestGrupoPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("grupoAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("grupoAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("grupoField"):field));
			}
			details = grupoService.requestGrupoPage(new RequestGrupoPageEvent(pageRequest));
		}
		
		PageWrapper<Grupo> page=new PageWrapper<Grupo>(details.getGrupoPage(),"/configuration/grupo/searchGrupo");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/grupo/searchGrupo";
	}
}