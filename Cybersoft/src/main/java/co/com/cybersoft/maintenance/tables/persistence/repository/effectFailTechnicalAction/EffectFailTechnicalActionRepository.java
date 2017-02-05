package co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface EffectFailTechnicalActionRepository extends PagingAndSortingRepository<EffectFailTechnicalAction, Long>{
	EffectFailTechnicalAction findByEffectFail(String value);

	EffectFailTechnicalAction findByTechnicalAction(String value);

	EffectFailTechnicalAction findByOrderEffectTechnicalActionFails(Integer value);

	EffectFailTechnicalAction findByActive(Boolean value);


}