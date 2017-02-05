package co.com.cybersoft.maintenance.tables.persistence.repository.characteristic;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CharacteristicRepository extends PagingAndSortingRepository<Characteristic, Long>{
	Characteristic findByCompany(String value);

	Characteristic findByNameCharacteristic(String value);

	Characteristic findByActive(Boolean value);


}