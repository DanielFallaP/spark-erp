package co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.customerTenancy.CustomerTenancyFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CustomerTenancyCustomRepo {

	Long getTotalCount() throws Exception;


	List<CustomerTenancy> findAllActiveBySoftwareTradeMark(EmbeddedField ...fields) throws Exception;
	List<CustomerTenancy> findAllActiveBySoftwareVersion(EmbeddedField ...fields) throws Exception;
	List<CustomerTenancy> findAllActiveBySoftwareSerialNo(EmbeddedField ...fields) throws Exception;
	List<CustomerTenancy> findAllActiveByLocalCurrency(EmbeddedField ...fields) throws Exception;
	List<CustomerTenancy> findAllActiveByForeignCurrency(EmbeddedField ...fields) throws Exception;

	
	Page<CustomerTenancy> findAll(Pageable pageable, CustomerTenancyFilterInfo filter)throws Exception;

	List<CustomerTenancy> findAllNoPage(Pageable pageable, CustomerTenancyFilterInfo filter)throws Exception;
	
}