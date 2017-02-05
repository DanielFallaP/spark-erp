package co.com.cybersoft.maintenance.tables.persistence.repository.characteristic;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CharacteristicCustomRepo {

	Long getTotalCount() throws Exception;


	List<Characteristic> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Characteristic> findAllActiveByNameCharacteristic(EmbeddedField ...fields) throws Exception;

	
	Page<Characteristic> findAll(Pageable pageable, CharacteristicFilterInfo filter)throws Exception;

	List<Characteristic> findAllNoPage(Pageable pageable, CharacteristicFilterInfo filter)throws Exception;
	
}