package co.com.cybersoft.persistence.services.afe;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.events.afe.CreateAfeEvent;
import co.com.cybersoft.events.afe.AfeDetailsEvent;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.events.afe.AfeModificationEvent;
import co.com.cybersoft.events.afe.RequestAfeDetailsEvent;
import co.com.cybersoft.events.afe.RequestAfePageEvent;
import co.com.cybersoft.persistence.domain.Afe;
import co.com.cybersoft.persistence.repository.AfeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfePersistenceServiceImpl implements AfePersistenceService{

	private final AfeRepository afeRepository;
	
	public AfePersistenceServiceImpl(final AfeRepository afeRepository) {
		this.afeRepository=afeRepository;
	}
	
	@Override
	public AfeDetailsEvent createAfe(CreateAfeEvent event) throws Exception{
		Afe afe = Afe.fromAfeDetails(event.getAfeDetails());
		afe = afeRepository.save(afe);
		return new AfeDetailsEvent(afe.toAfeDetails());
	}

	@Override
	public AfePageEvent requestAfePage(RequestAfePageEvent event) throws Exception {
		Page<Afe> afes = afeRepository.findAll(event.getPageable());
		return new AfePageEvent(afes);
	}

	@Override
	public AfeDetailsEvent requestAfeDetails(RequestAfeDetailsEvent event) throws Exception {
		Afe afe = afeRepository.findByCode(event.getId());
		AfeDetails afeDetails = afe.toAfeDetails();
		return new AfeDetailsEvent(afeDetails);
	}

	@Override
	public AfeDetailsEvent modifyAfe(AfeModificationEvent event) throws Exception {
		Afe afe = Afe.fromAfeDetails(event.getAfeDetails());
		afe = afeRepository.save(afe);
		return new AfeDetailsEvent(afe.toAfeDetails());
	}
	
	@Override
	public AfePageEvent requestAll() throws Exception {
		List<Afe> all = afeRepository.findAll();
		List<AfeDetails> list = new ArrayList<AfeDetails>();
		for (Afe afe : all) {
			list.add(afe.toAfeDetails());
		}
		return new AfePageEvent(list);
	}

}