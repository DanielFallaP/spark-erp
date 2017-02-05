package co.com.cybersoft.maintenance.tables.persistence.repository.costingType;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CostingTypeCustomRepo {

	Long getTotalCount() throws Exception;


	List<CostingType> findAllActiveByCostingType(EmbeddedField ...fields) throws Exception;

	
	Page<CostingType> findAll(Pageable pageable, CostingTypeFilterInfo filter)throws Exception;

	List<CostingType> findAllNoPage(Pageable pageable, CostingTypeFilterInfo filter)throws Exception;
	
}