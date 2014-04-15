package co.com.cybersoft.persistence.services.unidadMedida;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.UnidadMedidaDetails;
import co.com.cybersoft.events.unidadMedida.CreateUnidadMedidaEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaPageEvent;
import co.com.cybersoft.events.unidadMedida.UnidadMedidaModificationEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaDetailsEvent;
import co.com.cybersoft.events.unidadMedida.RequestUnidadMedidaPageEvent;
import co.com.cybersoft.persistence.domain.UnidadMedida;
import co.com.cybersoft.persistence.repository.UnidadMedidaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UnidadMedidaPersistenceServiceImpl implements UnidadMedidaPersistenceService{

	private final UnidadMedidaRepository unidadMedidaRepository;
	
	public UnidadMedidaPersistenceServiceImpl(final UnidadMedidaRepository unidadMedidaRepository) {
		this.unidadMedidaRepository=unidadMedidaRepository;
	}
	
	@Override
	public UnidadMedidaDetailsEvent createUnidadMedida(CreateUnidadMedidaEvent event) throws Exception{
		UnidadMedida unidadMedida = UnidadMedida.fromUnidadMedidaDetails(event.getUnidadMedidaDetails());
		unidadMedida = unidadMedidaRepository.save(unidadMedida);
		return new UnidadMedidaDetailsEvent(unidadMedida.toUnidadMedidaDetails());
	}

	@Override
	public UnidadMedidaPageEvent requestUnidadMedidaPage(RequestUnidadMedidaPageEvent event) throws Exception {
		Page<UnidadMedida> unidadMedidas = unidadMedidaRepository.findAll(event.getPageable());
		return new UnidadMedidaPageEvent(unidadMedidas);
	}

	@Override
	public UnidadMedidaDetailsEvent requestUnidadMedidaDetails(RequestUnidadMedidaDetailsEvent event) throws Exception {
		UnidadMedida unidadMedida = unidadMedidaRepository.findByCode(event.getId());
		UnidadMedidaDetails unidadMedidaDetails = unidadMedida.toUnidadMedidaDetails();
		return new UnidadMedidaDetailsEvent(unidadMedidaDetails);
	}

	@Override
	public UnidadMedidaDetailsEvent modifyUnidadMedida(UnidadMedidaModificationEvent event) throws Exception {
		UnidadMedida unidadMedida = UnidadMedida.fromUnidadMedidaDetails(event.getUnidadMedidaDetails());
		unidadMedida = unidadMedidaRepository.save(unidadMedida);
		return new UnidadMedidaDetailsEvent(unidadMedida.toUnidadMedidaDetails());
	}
	
	@Override
	public UnidadMedidaPageEvent requestAll() throws Exception {
		List<UnidadMedida> all = unidadMedidaRepository.findAll();
		List<UnidadMedidaDetails> list = new ArrayList<UnidadMedidaDetails>();
		for (UnidadMedida unidadMedida : all) {
			list.add(unidadMedida.toUnidadMedidaDetails());
		}
		return new UnidadMedidaPageEvent(list);
	}

}