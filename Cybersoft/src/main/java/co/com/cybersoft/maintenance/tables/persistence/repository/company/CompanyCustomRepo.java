package co.com.cybersoft.maintenance.tables.persistence.repository.company;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CompanyCustomRepo {

	Long getTotalCount() throws Exception;


	List<Company> findAllActiveByCodeNit(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByCompanyName(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByCompanyLicense(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByComment(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByUserLoginCompany(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByUserSendHistory(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByStateShippingHistory(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByStateCompany(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByCodeOfWIN(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByInventoryConditionPermanent(EmbeddedField ...fields) throws Exception;
	List<Company> findAllActiveByPermanentInventoryWorkOrder(EmbeddedField ...fields) throws Exception;

	
	Page<Company> findAll(Pageable pageable, CompanyFilterInfo filter)throws Exception;

	List<Company> findAllNoPage(Pageable pageable, CompanyFilterInfo filter)throws Exception;
	
}