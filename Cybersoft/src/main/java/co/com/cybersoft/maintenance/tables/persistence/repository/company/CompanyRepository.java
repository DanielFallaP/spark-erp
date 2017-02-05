package co.com.cybersoft.maintenance.tables.persistence.repository.company;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>{
	Company findByCode(Integer value);

	Company findByCodeNit(String value);

	Company findByCompanyName(String value);

	Company findByCompanyLicense(String value);

	Company findByComment(String value);

	Company findByHomeRangesOfDatesOfTheLastGeneration(Date value);

	Company findByEndDateRangesOfTheLastGeneration(Date value);

	Company findByUserLoginCompany(String value);

	Company findByStateAnalysisCompany(Boolean value);

	Company findByStateGenerationCompany(Boolean value);

	Company findByStartDateCompanyProcess(Date value);

	Company findByEndDateProcessCompany(Date value);

	Company findByNumberPartialWorkOrderCompany(Integer value);

	Company findByTotalNumberofWorkOrderintheCompany(Integer value);

	Company findByCancelProcessCompany(Boolean value);

	Company findByUserSendHistory(String value);

	Company findByStateShippingHistory(String value);

	Company findByStartDateSendingHistory(Date value);

	Company findByEndingDatelSendingHistory(Date value);

	Company findByPartialNumberOfSendingHistory(Integer value);

	Company findByTotalNumberOfSendingHistory(Integer value);

	Company findByCancelSendingHistory(Boolean value);

	Company findByStateCompany(String value);

	Company findByCodeOfWIN(String value);

	Company findByInventoryConditionPermanent(String value);

	Company findByPermanentInventoryWorkOrder(String value);

	Company findByLanguage(Integer value);

	Company findByActiveCompany(Integer value);

	Company findByActive(Boolean value);


}