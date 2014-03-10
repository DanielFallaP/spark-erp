package co.com.cybersoft.persistence.services.afe;

import co.com.cybersoft.events.afe.AFECreatedEvent;
import co.com.cybersoft.events.afe.CreateAFEEvent;

public interface AFEPersistenceService {
	AFECreatedEvent createAFE(CreateAFEEvent event);
}
