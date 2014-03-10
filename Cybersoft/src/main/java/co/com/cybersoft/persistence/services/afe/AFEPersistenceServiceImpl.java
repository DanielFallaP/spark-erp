package co.com.cybersoft.persistence.services.afe;

import co.com.cybersoft.events.afe.AFECreatedEvent;
import co.com.cybersoft.events.afe.CreateAFEEvent;
import co.com.cybersoft.persistence.domain.AFE;
import co.com.cybersoft.persistence.repository.AFERepository;

public class AFEPersistenceServiceImpl implements AFEPersistenceService {

	private final AFERepository afeRepository;
	
	public AFEPersistenceServiceImpl(final AFERepository afeRepository){
		this.afeRepository=afeRepository;
	}
	
	@Override
	public AFECreatedEvent createAFE(CreateAFEEvent event) {
		AFE afe = AFE.fromAFEDetails(event.getAfeDetails());
		AFE save = afeRepository.save(afe);
		return new AFECreatedEvent(save.toAFEDetails());
	}

}
