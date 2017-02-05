package co.com.cybersoft.maintenance.tables.persistence.repository.third;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ThirdCustomRepo {

	Long getTotalCount() throws Exception;


	List<Third> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByCostCenter(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByCode(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByNameThird(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByContact(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByAddress(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByCountry(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByPhoneOne(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByPhoneTwo(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByEmail(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByComment(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByTypeRegime(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByExternalAccess(EmbeddedField ...fields) throws Exception;
	List<Third> findAllActiveByKeyExternalAccess(EmbeddedField ...fields) throws Exception;

	
	Page<Third> findAll(Pageable pageable, ThirdFilterInfo filter)throws Exception;

	List<Third> findAllNoPage(Pageable pageable, ThirdFilterInfo filter)throws Exception;
	
}