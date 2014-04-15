package co.com.cybersoft.core.services.articulo;

import co.com.cybersoft.events.articulo.CreateArticuloEvent;
import co.com.cybersoft.events.articulo.ArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.ArticuloPageEvent;
import co.com.cybersoft.events.articulo.ArticuloModificationEvent;
import co.com.cybersoft.events.articulo.RequestArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.RequestArticuloPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ArticuloService {
	ArticuloDetailsEvent createArticulo(CreateArticuloEvent event ) throws Exception;
	
	ArticuloPageEvent requestArticuloPage(RequestArticuloPageEvent event) throws Exception;

	ArticuloDetailsEvent requestArticuloDetails(RequestArticuloDetailsEvent event ) throws Exception;

	ArticuloDetailsEvent modifyArticulo(ArticuloModificationEvent event) throws Exception;
	
	ArticuloPageEvent requestAll() throws Exception;
	
}