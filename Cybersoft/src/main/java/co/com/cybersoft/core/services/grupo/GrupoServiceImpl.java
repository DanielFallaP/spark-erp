package co.com.cybersoft.core.services.grupo;

import java.util.Date;

import co.com.cybersoft.events.grupo.CreateGrupoEvent;
import co.com.cybersoft.events.grupo.GrupoDetailsEvent;
import co.com.cybersoft.events.grupo.GrupoPageEvent;
import co.com.cybersoft.events.grupo.GrupoModificationEvent;
import co.com.cybersoft.events.grupo.RequestGrupoDetailsEvent;
import co.com.cybersoft.events.grupo.RequestGrupoPageEvent;
import co.com.cybersoft.persistence.services.grupo.GrupoPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class GrupoServiceImpl implements GrupoService{

	private final GrupoPersistenceService grupoPersistenceService;
	
	public GrupoServiceImpl(final GrupoPersistenceService grupoPersistenceService){
		this.grupoPersistenceService=grupoPersistenceService;
	}
	
	/**
	 */
	@Override
	public GrupoDetailsEvent createGrupo(CreateGrupoEvent event ) throws Exception{
		return grupoPersistenceService.createGrupo(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public GrupoPageEvent requestGrupoPage(RequestGrupoPageEvent event) throws Exception{
		return grupoPersistenceService.requestGrupoPage(event);
	}

	@Override
	public GrupoDetailsEvent requestGrupoDetails(RequestGrupoDetailsEvent event ) throws Exception{
		return grupoPersistenceService.requestGrupoDetails(event);
	}

	@Override
	public GrupoDetailsEvent modifyGrupo(GrupoModificationEvent event) throws Exception {
		return grupoPersistenceService.modifyGrupo(event);
	}
	
	@Override
	public GrupoPageEvent requestAll() throws Exception {
		return grupoPersistenceService.requestAll();
	}

}