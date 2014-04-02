package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.ItemService;
import co.com.cybersoft.core.services.ItemServiceImpl;
import co.com.cybersoft.core.services.afe.AFEService;
import co.com.cybersoft.core.services.afe.AFEServiceImpl;
import co.com.cybersoft.persistence.services.afe.AFEPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;

@Configuration
public class CoreConfig {

	@Bean 
	public ItemService itemService(ItemPersistenceService itemPersistenceService){
		return new ItemServiceImpl(itemPersistenceService);
	}
	
	@Bean
	public AFEService afeService(AFEPersistenceService afePersistenceService){
		return new AFEServiceImpl(afePersistenceService);
	}
}
