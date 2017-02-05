package co.com.cybersoft.maintenance.tables.persistence.repository.effectFail;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface EffectFailCustomRepo {

	Long getTotalCount() throws Exception;


	List<EffectFail> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<EffectFail> findAllActiveByEffectFailName(EmbeddedField ...fields) throws Exception;

	
	Page<EffectFail> findAll(Pageable pageable, EffectFailFilterInfo filter)throws Exception;

	List<EffectFail> findAllNoPage(Pageable pageable, EffectFailFilterInfo filter)throws Exception;
	
}