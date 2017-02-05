package co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CostCentersCustomersRepository extends PagingAndSortingRepository<CostCentersCustomers, Long>{
	CostCentersCustomers findByCompany(String value);

	CostCentersCustomers findByDescription(String value);

	CostCentersCustomers findByNameCostCenter(String value);

	CostCentersCustomers findBySubCostCentersCustomers(String value);

	CostCentersCustomers findBySubDescription(String value);

	CostCentersCustomers findByContact(String value);

	CostCentersCustomers findByAreaDepartment(String value);

	CostCentersCustomers findByAddress(String value);

	CostCentersCustomers findByCity(String value);

	CostCentersCustomers findByPhone(String value);

	CostCentersCustomers findByClassis(Integer value);

	CostCentersCustomers findByState(String value);

	CostCentersCustomers findByComments(String value);

	CostCentersCustomers findByStateCostCenter(Boolean value);

	CostCentersCustomers findByActive(Boolean value);


}