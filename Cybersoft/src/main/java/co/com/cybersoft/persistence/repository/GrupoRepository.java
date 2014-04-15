package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Grupo;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface GrupoRepository extends MongoRepository<Grupo, String>{
	Grupo findByCode(String code);
}