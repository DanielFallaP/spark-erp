package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.WareHouse;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface WareHouseRepository extends MongoRepository<WareHouse, String>{
	WareHouse findByCode(String code);
	WareHouse findByName(String name);
}