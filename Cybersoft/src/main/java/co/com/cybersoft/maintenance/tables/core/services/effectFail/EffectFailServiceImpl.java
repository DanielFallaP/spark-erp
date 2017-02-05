package co.com.cybersoft.maintenance.tables.core.services.effectFail;

import co.com.cybersoft.maintenance.tables.events.effectFail.CreateEffectFailEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.EffectFailModificationEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.effectFail.RequestEffectFailPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFail.EffectFailPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailServiceImpl implements EffectFailService{

	private final EffectFailPersistenceService effectFailPersistenceService;
	
	public EffectFailServiceImpl(final EffectFailPersistenceService effectFailPersistenceService){
		this.effectFailPersistenceService=effectFailPersistenceService;
	}
	
	/**
	 */
	public EffectFailDetailsEvent createEffectFail(CreateEffectFailEvent event ) throws Exception{
		return effectFailPersistenceService.createEffectFail(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public EffectFailPageEvent requestEffectFailPage(RequestEffectFailPageEvent event) throws Exception{
		return effectFailPersistenceService.requestEffectFailPage(event);
	}

	public EffectFailDetailsEvent requestEffectFailDetails(RequestEffectFailDetailsEvent event ) throws Exception{
		return effectFailPersistenceService.requestEffectFailDetails(event);
	}

	public EffectFailDetailsEvent modifyEffectFail(EffectFailModificationEvent event) throws Exception {
		return effectFailPersistenceService.modifyEffectFail(event);
	}
	
	public EffectFailDetailsEvent requestOnlyRecord() throws Exception {
		return effectFailPersistenceService.getOnlyRecord();
	}
	
	public EffectFailPageEvent requestEffectFailFilterPage(RequestEffectFailPageEvent event) throws Exception {
		return effectFailPersistenceService.requestEffectFailFilterPage(event);
	}
	
	public EffectFailPageEvent requestEffectFailFilter(RequestEffectFailPageEvent event) throws Exception {
		return effectFailPersistenceService.requestEffectFailFilter(event);
	}
	

	public EffectFailPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return effectFailPersistenceService.requestAllByCompany(fields);
	}public EffectFailPageEvent requestAllByEffectFailName(EmbeddedField... fields) throws Exception {
		return effectFailPersistenceService.requestAllByEffectFailName(fields);
	}
	
	
	
}