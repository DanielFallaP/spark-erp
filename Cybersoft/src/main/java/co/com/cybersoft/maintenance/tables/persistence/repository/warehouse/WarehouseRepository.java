package co.com.cybersoft.maintenance.tables.persistence.repository.warehouse;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface WarehouseRepository extends PagingAndSortingRepository<Warehouse, Long>{
	Warehouse findByCompany(String value);

	Warehouse findByCode(Integer value);

	Warehouse findByStoreName(String value);

	Warehouse findByPhysicalLocation(String value);

	Warehouse findByCostingType(String value);

	Warehouse findByComment(String value);

	Warehouse findByStoreType(String value);

	Warehouse findByActive(Boolean value);


}