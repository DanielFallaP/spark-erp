package co.com.cybersoft.persistence.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.MeasurementUnit;


public interface MeasurementUnitRepository extends MongoRepository<MeasurementUnit, String>{

}
