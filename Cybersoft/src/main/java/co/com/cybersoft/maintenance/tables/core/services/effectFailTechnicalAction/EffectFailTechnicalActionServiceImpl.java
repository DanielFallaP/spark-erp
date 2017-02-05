package co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction;

import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.CreateEffectFailTechnicalActionEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.EffectFailTechnicalActionModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction.RequestEffectFailTechnicalActionPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction.EffectFailTechnicalActionPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailTechnicalActionServiceImpl implements EffectFailTechnicalActionService{

	private final EffectFailTechnicalActionPersistenceService effectFailTechnicalActionPersistenceService;
	
	public EffectFailTechnicalActionServiceImpl(final EffectFailTechnicalActionPersistenceService effectFailTechnicalActionPersistenceService){
		this.effectFailTechnicalActionPersistenceService=effectFailTechnicalActionPersistenceService;
	}
	
	/**
	 */
	public EffectFailTechnicalActionDetailsEvent createEffectFailTechnicalAction(CreateEffectFailTechnicalActionEvent event ) throws Exception{
		return effectFailTechnicalActionPersistenceService.createEffectFailTechnicalAction(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception{
		return effectFailTechnicalActionPersistenceService.requestEffectFailTechnicalActionPage(event);
	}

	public EffectFailTechnicalActionDetailsEvent requestEffectFailTechnicalActionDetails(RequestEffectFailTechnicalActionDetailsEvent event ) throws Exception{
		return effectFailTechnicalActionPersistenceService.requestEffectFailTechnicalActionDetails(event);
	}

	public EffectFailTechnicalActionDetailsEvent modifyEffectFailTechnicalAction(EffectFailTechnicalActionModificationEvent event) throws Exception {
		return effectFailTechnicalActionPersistenceService.modifyEffectFailTechnicalAction(event);
	}
	
	public EffectFailTechnicalActionDetailsEvent requestOnlyRecord() throws Exception {
		return effectFailTechnicalActionPersistenceService.getOnlyRecord();
	}
	
	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilterPage(RequestEffectFailTechnicalActionPageEvent event) throws Exception {
		return effectFailTechnicalActionPersistenceService.requestEffectFailTechnicalActionFilterPage(event);
	}
	
	public EffectFailTechnicalActionPageEvent requestEffectFailTechnicalActionFilter(RequestEffectFailTechnicalActionPageEvent event) throws Exception {
		return effectFailTechnicalActionPersistenceService.requestEffectFailTechnicalActionFilter(event);
	}
	

	public EffectFailTechnicalActionPageEvent requestAllByEffectFail(EmbeddedField... fields) throws Exception {
		return effectFailTechnicalActionPersistenceService.requestAllByEffectFail(fields);
	}public EffectFailTechnicalActionPageEvent requestAllByTechnicalAction(EmbeddedField... fields) throws Exception {
		return effectFailTechnicalActionPersistenceService.requestAllByTechnicalAction(fields);
	}
	
	
	
}