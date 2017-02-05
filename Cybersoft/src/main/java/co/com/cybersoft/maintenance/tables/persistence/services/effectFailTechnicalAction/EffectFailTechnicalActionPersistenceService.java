package co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction;

import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.CreateEffectFailTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface EffectFailTechnicalActionPersistenceService {

	EffectFailTechnicalActionDetailsEvent createEffectFailTechnicalAction(CreateEffectFailTechnicalActionEvent event) throws Exception;

	EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception;

	EffectFailTechnicalActionDetailsEvent requestEffectFailTechnicalActionDetails(RequestEffectFailTechnicalActionDetailsEvent event) throws Exception;
	
	EffectFailTechnicalActionDetailsEvent modifyEffectFailTechnicalAction(EffectFailTechnicalActionModificationEvent event) throws Exception;
	EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilterPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception;
	EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilter(RequestEffectFailTechnicalActionPageEvent event) throws Exception;
	
	EffectFailTechnicalActionPageEvent requestAllByEffectFail(EmbeddedField... fields) throws Exception;
	EffectFailTechnicalActionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception;

	
	
	EffectFailTechnicalActionDetailsEvent getOnlyRecord() throws Exception;
	
}