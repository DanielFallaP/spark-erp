package co.com.cybersoft.events.unidadMedida;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.UnidadMedida;
import co.com.cybersoft.core.domain.UnidadMedidaDetails;
import co.com.cybersoft.persistence.domain.UnidadMedida;
import java.util.List;

/**
 * Event class for UnidadMedida
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UnidadMedidaPageEvent {
	private Page<UnidadMedida> unidadMedidaPage;
	
	private List<UnidadMedidaDetails> unidadMedidaList;



	
	public UnidadMedidaPageEvent(List<UnidadMedidaDetails>  unidadMedidaList){
			this.unidadMedidaList= unidadMedidaList;
	}



	
	public List<UnidadMedidaDetails> getUnidadMedidaList() {
	return unidadMedidaList;
	}



	
	public UnidadMedidaPageEvent(Page<UnidadMedida>  unidadMedidaPage){
		this.unidadMedidaPage= unidadMedidaPage;
	}

	public Page<UnidadMedida> getUnidadMedidaPage() {
		return unidadMedidaPage;
	}
	
}