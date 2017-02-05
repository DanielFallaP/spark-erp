package co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface EffectFailTechnicalActionCustomRepo {

	Long getTotalCount() throws Exception;


	List<EffectFailTechnicalAction> findAllActiveByEffectFail(EmbeddedField ...fields) throws Exception;
	List<EffectFailTechnicalAction> findAllActiveByTechnicalAction(EmbeddedField ...fields) throws Exception;

	
	Page<EffectFailTechnicalAction> findAll(Pageable pageable, EffectFailTechnicalActionFilterInfo filter)throws Exception;

	List<EffectFailTechnicalAction> findAllNoPage(Pageable pageable, EffectFailTechnicalActionFilterInfo filter)throws Exception;
	
}