package co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface AuthorizerCostCenterCustomRepo {

	Long getTotalCount() throws Exception;


	List<AuthorizerCostCenter> findAllActiveByCostCenter(EmbeddedField ...fields) throws Exception;
	List<AuthorizerCostCenter> findAllActiveByResponsible(EmbeddedField ...fields) throws Exception;

	
	Page<AuthorizerCostCenter> findAll(Pageable pageable, AuthorizerCostCenterFilterInfo filter)throws Exception;

	List<AuthorizerCostCenter> findAllNoPage(Pageable pageable, AuthorizerCostCenterFilterInfo filter)throws Exception;
	
}