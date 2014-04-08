package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.$entityName$;

public interface $entityName$Repository extends MongoRepository<$entityName$, String>{
	$entityName$ findByCode(String code);
}
