package co.com.cybersoft.purchase.tables.persistence.repository.users;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface UsersRepository extends PagingAndSortingRepository<Users, Long>{
	Users findByLogin(String value);

	Users findByPassword(String value);

	Users findByRole(String value);

	Users findByCompany(String value);

	Users findByCurrencyRead(Boolean value);

	Users findByCurrencyCreate(Boolean value);

	Users findByCurrencyUpdate(Boolean value);

	Users findByCurrencyExport(Boolean value);

	Users findByActive(Boolean value);


}