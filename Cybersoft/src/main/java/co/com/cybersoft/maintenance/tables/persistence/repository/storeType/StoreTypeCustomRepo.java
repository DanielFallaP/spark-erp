package co.com.cybersoft.maintenance.tables.persistence.repository.storeType;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StoreTypeCustomRepo {

	Long getTotalCount() throws Exception;


	List<StoreType> findAllActiveByStoreName(EmbeddedField ...fields) throws Exception;

	
	Page<StoreType> findAll(Pageable pageable, StoreTypeFilterInfo filter)throws Exception;

	List<StoreType> findAllNoPage(Pageable pageable, StoreTypeFilterInfo filter)throws Exception;
	
}