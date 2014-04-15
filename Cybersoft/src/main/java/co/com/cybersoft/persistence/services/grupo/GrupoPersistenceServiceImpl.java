package co.com.cybersoft.persistence.services.grupo;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.GrupoDetails;
import co.com.cybersoft.events.grupo.CreateGrupoEvent;
import co.com.cybersoft.events.grupo.GrupoDetailsEvent;
import co.com.cybersoft.events.grupo.GrupoPageEvent;
import co.com.cybersoft.events.grupo.GrupoModificationEvent;
import co.com.cybersoft.events.grupo.RequestGrupoDetailsEvent;
import co.com.cybersoft.events.grupo.RequestGrupoPageEvent;
import co.com.cybersoft.persistence.domain.Grupo;
import co.com.cybersoft.persistence.repository.GrupoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class GrupoPersistenceServiceImpl implements GrupoPersistenceService{

	private final GrupoRepository grupoRepository;
	
	public GrupoPersistenceServiceImpl(final GrupoRepository grupoRepository) {
		this.grupoRepository=grupoRepository;
	}
	
	@Override
	public GrupoDetailsEvent createGrupo(CreateGrupoEvent event) throws Exception{
		Grupo grupo = Grupo.fromGrupoDetails(event.getGrupoDetails());
		grupo = grupoRepository.save(grupo);
		return new GrupoDetailsEvent(grupo.toGrupoDetails());
	}

	@Override
	public GrupoPageEvent requestGrupoPage(RequestGrupoPageEvent event) throws Exception {
		Page<Grupo> grupos = grupoRepository.findAll(event.getPageable());
		return new GrupoPageEvent(grupos);
	}

	@Override
	public GrupoDetailsEvent requestGrupoDetails(RequestGrupoDetailsEvent event) throws Exception {
		Grupo grupo = grupoRepository.findByCode(event.getId());
		GrupoDetails grupoDetails = grupo.toGrupoDetails();
		return new GrupoDetailsEvent(grupoDetails);
	}

	@Override
	public GrupoDetailsEvent modifyGrupo(GrupoModificationEvent event) throws Exception {
		Grupo grupo = Grupo.fromGrupoDetails(event.getGrupoDetails());
		grupo = grupoRepository.save(grupo);
		return new GrupoDetailsEvent(grupo.toGrupoDetails());
	}
	
	@Override
	public GrupoPageEvent requestAll() throws Exception {
		List<Grupo> all = grupoRepository.findAll();
		List<GrupoDetails> list = new ArrayList<GrupoDetails>();
		for (Grupo grupo : all) {
			list.add(grupo.toGrupoDetails());
		}
		return new GrupoPageEvent(list);
	}

}