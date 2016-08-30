package co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CustomerTenancyRepository extends PagingAndSortingRepository<CustomerTenancy, Long>{
	CustomerTenancy findBySoftwareTradeMark(String value);

	CustomerTenancy findBySoftwareVersion(String value);

	CustomerTenancy findBySoftwareSerialNo(String value);

	CustomerTenancy findByDoubleCurrency(Boolean value);

	CustomerTenancy findByLocalCurrency(String value);

	CustomerTenancy findByForeignCurrency(String value);

	CustomerTenancy findByActive(Boolean value);


}