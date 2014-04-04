package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.afe.AFEService;
import co.com.cybersoft.core.services.afe.AFEServiceImpl;
import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.services.items.ItemServiceImpl;
import co.com.cybersoft.core.services.units.UnitService;
import co.com.cybersoft.core.services.units.UnitServiceImpl;
import co.com.cybersoft.persistence.services.afe.AFEPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.persistence.services.units.UnitPersistenceService;

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
	
	@Bean
	public UnitService unitService(UnitPersistenceService unitPersistenceService){
		return new UnitServiceImpl(unitPersistenceService);
	}
}
