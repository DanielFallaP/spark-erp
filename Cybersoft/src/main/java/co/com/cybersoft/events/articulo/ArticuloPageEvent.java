package co.com.cybersoft.events.articulo;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Articulo;
import co.com.cybersoft.core.domain.ArticuloDetails;
import co.com.cybersoft.persistence.domain.Articulo;
import java.util.List;

/**
 * Event class for Articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ArticuloPageEvent {
	private Page<Articulo> articuloPage;
	
	private List<ArticuloDetails> articuloList;



	
	public ArticuloPageEvent(List<ArticuloDetails>  articuloList){
			this.articuloList= articuloList;
	}



	
	public List<ArticuloDetails> getArticuloList() {
	return articuloList;
	}



	
	public ArticuloPageEvent(Page<Articulo>  articuloPage){
		this.articuloPage= articuloPage;
	}

	public Page<Articulo> getArticuloPage() {
		return articuloPage;
	}
	
}