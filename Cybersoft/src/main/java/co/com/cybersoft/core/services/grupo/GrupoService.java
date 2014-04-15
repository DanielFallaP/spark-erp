package co.com.cybersoft.core.services.grupo;

import co.com.cybersoft.events.grupo.CreateGrupoEvent;
import co.com.cybersoft.events.grupo.GrupoDetailsEvent;
import co.com.cybersoft.events.grupo.GrupoPageEvent;
import co.com.cybersoft.events.grupo.GrupoModificationEvent;
import co.com.cybersoft.events.grupo.RequestGrupoDetailsEvent;
import co.com.cybersoft.events.grupo.RequestGrupoPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface GrupoService {
	GrupoDetailsEvent createGrupo(CreateGrupoEvent event ) throws Exception;
	
	GrupoPageEvent requestGrupoPage(RequestGrupoPageEvent event) throws Exception;

	GrupoDetailsEvent requestGrupoDetails(RequestGrupoDetailsEvent event ) throws Exception;

	GrupoDetailsEvent modifyGrupo(GrupoModificationEvent event) throws Exception;
	
	GrupoPageEvent requestAll() throws Exception;
	
}