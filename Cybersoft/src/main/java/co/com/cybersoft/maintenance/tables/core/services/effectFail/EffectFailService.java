package co.com.cybersoft.maintenance.tables.core.services.effectFail;

import co.com.cybersoft.maintenance.tables.events.effectFail.CreateEffectFailEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface EffectFailService {
	EffectFailDetailsEvent requestOnlyRecord() throws Exception;

	EffectFailDetailsEvent createEffectFail(CreateEffectFailEvent event ) throws Exception;
	
	EffectFailPageEvent requestEffectFailPage(RequestEffectFailPageEvent event) throws Exception;

	EffectFailDetailsEvent requestEffectFailDetails(RequestEffectFailDetailsEvent event ) throws Exception;

	EffectFailDetailsEvent modifyEffectFail(EffectFailModificationEvent event) throws Exception;
	
	EffectFailPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	EffectFailPageEvent requestAllByEffectFailName(EmbeddedField... fields) throws Exception;

	
	
	EffectFailPageEvent requestEffectFailFilterPage(RequestEffectFailPageEvent event) throws Exception;

	EffectFailPageEvent requestEffectFailFilter(RequestEffectFailPageEvent event) throws Exception;
	
}