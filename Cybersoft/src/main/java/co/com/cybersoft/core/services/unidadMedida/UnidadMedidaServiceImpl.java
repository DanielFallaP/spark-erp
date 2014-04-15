package co.com.cybersoft.core.services.unidadMedida;

import java.util.Date;

import co.com.cybersoft.events.unidadMedida.CreateUnidadMedidaEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaPageEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaModificationEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaPageEvent;
import co.com.cybersoft.persistence.services.unidadMedida.UnidadMedidaPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UnidadMedidaServiceImpl implements UnidadMedidaService{

	private final UnidadMedidaPersistenceService unidadMedidaPersistenceService;
	
	public UnidadMedidaServiceImpl(final UnidadMedidaPersistenceService unidadMedidaPersistenceService){
		this.unidadMedidaPersistenceService=unidadMedidaPersistenceService;
	}
	
	/**
	 */
	@Override
	public UnidadMedidaDetailsEvent createUnidadMedida(CreateUnidadMedidaEvent event ) throws Exception{
		return unidadMedidaPersistenceService.createUnidadMedida(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public UnidadMedidaPageEvent requestUnidadMedidaPage(RequestUnidadMedidaPageEvent event) throws Exception{
		return unidadMedidaPersistenceService.requestUnidadMedidaPage(event);
	}

	@Override
	public UnidadMedidaDetailsEvent requestUnidadMedidaDetails(RequestUnidadMedidaDetailsEvent event ) throws Exception{
		return unidadMedidaPersistenceService.requestUnidadMedidaDetails(event);
	}

	@Override
	public UnidadMedidaDetailsEvent modifyUnidadMedida(UnidadMedidaModificationEvent event) throws Exception {
		return unidadMedidaPersistenceService.modifyUnidadMedida(event);
	}
	
	@Override
	public UnidadMedidaPageEvent requestAll() throws Exception {
		return unidadMedidaPersistenceService.requestAll();
	}

}