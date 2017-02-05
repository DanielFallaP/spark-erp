package co.com.cybersoft.maintenance.tables.persistence.repository.accountant;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface AccountantCustomRepo {

	Long getTotalCount() throws Exception;


	List<Accountant> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Accountant> findAllActiveByNameAccountant(EmbeddedField ...fields) throws Exception;

	
	Page<Accountant> findAll(Pageable pageable, AccountantFilterInfo filter)throws Exception;

	List<Accountant> findAllNoPage(Pageable pageable, AccountantFilterInfo filter)throws Exception;
	
}