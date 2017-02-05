package co.com.cybersoft.maintenance.tables.persistence.repository.contract;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>{
	Contract findByCompany(String value);

	Contract findByDescription(String value);

	Contract findByContractStartDate(Date value);

	Contract findByContractEndDate(Date value);

	Contract findByYearContractValue(Double value);

	Contract findByResponsible(String value);

	Contract findByCostCenter(String value);

	Contract findByStateContract(Boolean value);

	Contract findByActive(Boolean value);


}