package co.com.cybersoft.persistence.services.unidadMedida;

import co.com.cybersoft.events.unidadMedida.CreateUnidadMedidaEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaPageEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaModificationEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface UnidadMedidaPersistenceService {

	UnidadMedidaDetailsEvent createUnidadMedida(CreateUnidadMedidaEvent event) throws Exception;

	UnidadMedidaPageEvent requestUnidadMedidaPage(RequestUnidadMedidaPageEvent event) throws Exception;

	UnidadMedidaDetailsEvent requestUnidadMedidaDetails(RequestUnidadMedidaDetailsEvent event) throws Exception;
	
	UnidadMedidaDetailsEvent modifyUnidadMedida(UnidadMedidaModificationEvent event) throws Exception;
	
	UnidadMedidaPageEvent requestAll() throws Exception;
	
}