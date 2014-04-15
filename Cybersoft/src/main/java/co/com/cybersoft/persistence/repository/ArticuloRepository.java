package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Articulo;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ArticuloRepository extends MongoRepository<Articulo, String>{
	Articulo findByCode(String code);
}