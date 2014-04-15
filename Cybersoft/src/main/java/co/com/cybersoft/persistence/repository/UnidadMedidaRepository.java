package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.UnidadMedida;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface UnidadMedidaRepository extends MongoRepository<UnidadMedida, String>{
	UnidadMedida findByCode(String code);
}