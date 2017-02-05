package co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StateCostCentersCustomRepo {

	Long getTotalCount() throws Exception;


	List<StateCostCenters> findAllActiveByStateCostCenters(EmbeddedField ...fields) throws Exception;

	
	Page<StateCostCenters> findAll(Pageable pageable, StateCostCentersFilterInfo filter)throws Exception;

	List<StateCostCenters> findAllNoPage(Pageable pageable, StateCostCentersFilterInfo filter)throws Exception;
	
}