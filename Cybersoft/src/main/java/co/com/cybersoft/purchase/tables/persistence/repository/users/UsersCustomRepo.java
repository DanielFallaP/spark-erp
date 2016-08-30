package co.com.cybersoft.purchase.tables.persistence.repository.users;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface UsersCustomRepo {

	Long getTotalCount() throws Exception;


	List<Users> findAllActiveByLogin(EmbeddedField ...fields) throws Exception;
	List<Users> findAllActiveByPassword(EmbeddedField ...fields) throws Exception;
	List<Users> findAllActiveByRole(EmbeddedField ...fields) throws Exception;
	List<Users> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;

	
	Page<Users> findAll(Pageable pageable, UsersFilterInfo filter)throws Exception;

	List<Users> findAllNoPage(Pageable pageable, UsersFilterInfo filter)throws Exception;
	
}