package co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AuthorizerCostCenterRepository extends PagingAndSortingRepository<AuthorizerCostCenter, Long>{
	AuthorizerCostCenter findByCostCenter(String value);

	AuthorizerCostCenter findByResponsible(String value);

	AuthorizerCostCenter findByStateAuthorizerCostCenter(Boolean value);

	AuthorizerCostCenter findByActive(Boolean value);


}