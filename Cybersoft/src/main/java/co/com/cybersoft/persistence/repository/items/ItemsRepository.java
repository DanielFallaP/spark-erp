package co.com.cybersoft.persistence.repository.items;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Items;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ItemsRepository extends MongoRepository<Items, String>{
	Items findByCode(String code);
	Items findByDescription(String description);
	
}