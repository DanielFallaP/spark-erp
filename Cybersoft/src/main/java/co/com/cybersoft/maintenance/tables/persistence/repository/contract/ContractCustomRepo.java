package co.com.cybersoft.maintenance.tables.persistence.repository.contract;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ContractCustomRepo {

	Long getTotalCount() throws Exception;


	List<Contract> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Contract> findAllActiveByDescription(EmbeddedField ...fields) throws Exception;
	List<Contract> findAllActiveByResponsible(EmbeddedField ...fields) throws Exception;
	List<Contract> findAllActiveByCostCenter(EmbeddedField ...fields) throws Exception;

	
	Page<Contract> findAll(Pageable pageable, ContractFilterInfo filter)throws Exception;

	List<Contract> findAllNoPage(Pageable pageable, ContractFilterInfo filter)throws Exception;
	
}