package co.com.cybersoft.core.services.articulo;

import java.util.Date;

import co.com.cybersoft.events.articulo.CreateArticuloEvent;
import co.com.cybersoft.events.articulo.ArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.ArticuloPageEvent;
import co.com.cybersoft.events.articulo.ArticuloModificationEvent;
import co.com.cybersoft.events.articulo.RequestArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.RequestArticuloPageEvent;
import co.com.cybersoft.persistence.services.articulo.ArticuloPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ArticuloServiceImpl implements ArticuloService{

	private final ArticuloPersistenceService articuloPersistenceService;
	
	public ArticuloServiceImpl(final ArticuloPersistenceService articuloPersistenceService){
		this.articuloPersistenceService=articuloPersistenceService;
	}
	
	/**
	 */
	@Override
	public ArticuloDetailsEvent createArticulo(CreateArticuloEvent event ) throws Exception{
		return articuloPersistenceService.createArticulo(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public ArticuloPageEvent requestArticuloPage(RequestArticuloPageEvent event) throws Exception{
		return articuloPersistenceService.requestArticuloPage(event);
	}

	@Override
	public ArticuloDetailsEvent requestArticuloDetails(RequestArticuloDetailsEvent event ) throws Exception{
		return articuloPersistenceService.requestArticuloDetails(event);
	}

	@Override
	public ArticuloDetailsEvent modifyArticulo(ArticuloModificationEvent event) throws Exception {
		return articuloPersistenceService.modifyArticulo(event);
	}
	
	@Override
	public ArticuloPageEvent requestAll() throws Exception {
		return articuloPersistenceService.requestAll();
	}

}