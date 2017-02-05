package co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TechnicalActionCustomRepo {

	Long getTotalCount() throws Exception;


	List<TechnicalAction> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<TechnicalAction> findAllActiveByCode(EmbeddedField ...fields) throws Exception;
	List<TechnicalAction> findAllActiveByTechnicalActionName(EmbeddedField ...fields) throws Exception;

	
	Page<TechnicalAction> findAll(Pageable pageable, TechnicalActionFilterInfo filter)throws Exception;

	List<TechnicalAction> findAllNoPage(Pageable pageable, TechnicalActionFilterInfo filter)throws Exception;
	
}