package co.com.cybersoft.maintenance.tables.persistence.repository.effectFail;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface EffectFailRepository extends PagingAndSortingRepository<EffectFail, Long>{
	EffectFail findByCompany(String value);

	EffectFail findByEffectFailName(String value);

	EffectFail findByActive(Boolean value);


}