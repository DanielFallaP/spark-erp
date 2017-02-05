package co.com.cybersoft.maintenance.tables.persistence.repository.third;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ThirdRepository extends PagingAndSortingRepository<Third, Long>{
	Third findByCompany(String value);

	Third findByCostCenter(String value);

	Third findByCode(String value);

	Third findByNameThird(String value);

	Third findByTypeThird(Integer value);

	Third findByContact(String value);

	Third findByAddress(String value);

	Third findByCountry(String value);

	Third findByPhoneOne(String value);

	Third findByPhoneTwo(String value);

	Third findByEmail(String value);

	Third findByDateEntry(Date value);

	Third findByDateRetirement(Date value);

	Third findByComment(String value);

	Third findByBigContributor(Integer value);

	Third findByAutorretenedor(Integer value);

	Third findByTypeRegime(String value);

	Third findByExternalAccess(String value);

	Third findByKeyExternalAccess(String value);

	Third findByStateThird(Boolean value);

	Third findByActive(Boolean value);


}