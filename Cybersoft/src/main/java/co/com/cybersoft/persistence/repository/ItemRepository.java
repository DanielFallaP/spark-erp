package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Item;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ItemRepository extends MongoRepository<Item, String>{
	Item findByCode(String code);
}