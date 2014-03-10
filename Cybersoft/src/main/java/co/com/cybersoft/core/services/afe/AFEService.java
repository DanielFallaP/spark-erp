package co.com.cybersoft.core.services.afe;

import co.com.cybersoft.events.afe.AFECreatedEvent;
import co.com.cybersoft.events.afe.CreateAFEEvent;

public interface AFEService {
	AFECreatedEvent createAFE(CreateAFEEvent event);
}
