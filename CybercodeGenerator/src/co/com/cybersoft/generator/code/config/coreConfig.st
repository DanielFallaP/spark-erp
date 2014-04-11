package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.services.items.ItemServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
$imports$

@Configuration
public class CoreConfig {

	@Bean 
	public ItemService itemService(ItemPersistenceService itemPersistenceService){
		return new ItemServiceImpl(itemPersistenceService);
	}
	
	$beanDeclarations$
}