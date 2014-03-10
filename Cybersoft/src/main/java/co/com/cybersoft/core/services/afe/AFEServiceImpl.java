package co.com.cybersoft.core.services.afe;

import co.com.cybersoft.events.afe.AFECreatedEvent;
import co.com.cybersoft.events.afe.CreateAFEEvent;
import co.com.cybersoft.persistence.services.afe.AFEPersistenceService;

public class AFEServiceImpl implements AFEService{

	private final AFEPersistenceService afePersistenceService;
	
	public AFEServiceImpl(final AFEPersistenceService afePersistenceService){
		this.afePersistenceService=afePersistenceService;
	}
	
	@Override
	public AFECreatedEvent createAFE(CreateAFEEvent event) {
		return afePersistenceService.createAFE(event);
	}

}
