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
import co.com.cybersoft.persistence.repository.afe.AfeRepository;
import co.com.cybersoft.persistence.repository.afe.AfeCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfePersistenceServiceImpl implements AfePersistenceService{

	private final AfeRepository afeRepository;
	
	private final AfeCustomRepo afeCustomRepo;
	
	public AfePersistenceServiceImpl(final AfeRepository afeRepository, final AfeCustomRepo afeCustomRepo) {
		this.afeRepository=afeRepository;
		this.afeCustomRepo=afeCustomRepo;
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
		AfeDetails afeDetails=null;
		if (event.getId()!=null){
			Afe afe = afeRepository.findByCode(event.getId());
			if (afe!=null)
				afeDetails = afe.toAfeDetails();
		}
		else{
					Afe afe = afeRepository.findByDescription(event.getDescription());
					if (afe!=null)
						afeDetails = afe.toAfeDetails();
				}
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
	
	@Override
	public AfePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Afe> codes = afeCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<AfeDetails> list = new ArrayList<AfeDetails>();
		for (Afe afe : codes) {
			list.add(afe.toAfeDetails());
		}
		return new AfePageEvent(list);
	}

	@Override
	public AfePageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<AfeDetails> list = new ArrayList<AfeDetails>();
		List<Afe> descriptions = afeCustomRepo.findByContainingDescription(description);
		for (Afe afe : descriptions) {
			list.add(afe.toAfeDetails());
		}
		return new AfePageEvent(list);
	}

}