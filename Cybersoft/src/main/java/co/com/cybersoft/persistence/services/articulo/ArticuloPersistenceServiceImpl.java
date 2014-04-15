package co.com.cybersoft.persistence.services.articulo;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.ArticuloDetails;
import co.com.cybersoft.events.articulo.CreateArticuloEvent;
import co.com.cybersoft.events.articulo.ArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.ArticuloPageEvent;
import co.com.cybersoft.events.articulo.ArticuloModificationEvent;
import co.com.cybersoft.events.articulo.RequestArticuloDetailsEvent;
import co.com.cybersoft.events.articulo.RequestArticuloPageEvent;
import co.com.cybersoft.persistence.domain.Articulo;
import co.com.cybersoft.persistence.repository.ArticuloRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ArticuloPersistenceServiceImpl implements ArticuloPersistenceService{

	private final ArticuloRepository articuloRepository;
	
	public ArticuloPersistenceServiceImpl(final ArticuloRepository articuloRepository) {
		this.articuloRepository=articuloRepository;
	}
	
	@Override
	public ArticuloDetailsEvent createArticulo(CreateArticuloEvent event) throws Exception{
		Articulo articulo = Articulo.fromArticuloDetails(event.getArticuloDetails());
		articulo = articuloRepository.save(articulo);
		return new ArticuloDetailsEvent(articulo.toArticuloDetails());
	}

	@Override
	public ArticuloPageEvent requestArticuloPage(RequestArticuloPageEvent event) throws Exception {
		Page<Articulo> articulos = articuloRepository.findAll(event.getPageable());
		return new ArticuloPageEvent(articulos);
	}

	@Override
	public ArticuloDetailsEvent requestArticuloDetails(RequestArticuloDetailsEvent event) throws Exception {
		Articulo articulo = articuloRepository.findByCode(event.getId());
		ArticuloDetails articuloDetails = articulo.toArticuloDetails();
		return new ArticuloDetailsEvent(articuloDetails);
	}

	@Override
	public ArticuloDetailsEvent modifyArticulo(ArticuloModificationEvent event) throws Exception {
		Articulo articulo = Articulo.fromArticuloDetails(event.getArticuloDetails());
		articulo = articuloRepository.save(articulo);
		return new ArticuloDetailsEvent(articulo.toArticuloDetails());
	}
	
	@Override
	public ArticuloPageEvent requestAll() throws Exception {
		List<Articulo> all = articuloRepository.findAll();
		List<ArticuloDetails> list = new ArrayList<ArticuloDetails>();
		for (Articulo articulo : all) {
			list.add(articulo.toArticuloDetails());
		}
		return new ArticuloPageEvent(list);
	}

}