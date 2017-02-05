package co.com.cybersoft.maintenance.tables.persistence.repository.warehouse;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface WarehouseCustomRepo {

	Long getTotalCount() throws Exception;


	List<Warehouse> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Warehouse> findAllActiveByStoreName(EmbeddedField ...fields) throws Exception;
	List<Warehouse> findAllActiveByPhysicalLocation(EmbeddedField ...fields) throws Exception;
	List<Warehouse> findAllActiveByCostingType(EmbeddedField ...fields) throws Exception;
	List<Warehouse> findAllActiveByComment(EmbeddedField ...fields) throws Exception;
	List<Warehouse> findAllActiveByStoreType(EmbeddedField ...fields) throws Exception;

	
	Page<Warehouse> findAll(Pageable pageable, WarehouseFilterInfo filter)throws Exception;

	List<Warehouse> findAllNoPage(Pageable pageable, WarehouseFilterInfo filter)throws Exception;
	
}