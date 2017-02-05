package co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CostCentersCustomersCustomRepo {

	Long getTotalCount() throws Exception;


	List<CostCentersCustomers> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByDescription(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByNameCostCenter(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveBySubCostCentersCustomers(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveBySubDescription(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByContact(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByAreaDepartment(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByAddress(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByCity(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByPhone(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByState(EmbeddedField ...fields) throws Exception;
	List<CostCentersCustomers> findAllActiveByComments(EmbeddedField ...fields) throws Exception;

	
	Page<CostCentersCustomers> findAll(Pageable pageable, CostCentersCustomersFilterInfo filter)throws Exception;

	List<CostCentersCustomers> findAllNoPage(Pageable pageable, CostCentersCustomersFilterInfo filter)throws Exception;
	
}