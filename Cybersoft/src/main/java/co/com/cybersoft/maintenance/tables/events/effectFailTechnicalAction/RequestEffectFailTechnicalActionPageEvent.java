package co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;

/**
 * Event class for EffectFailTechnicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestEffectFailTechnicalActionPageEvent {

	private Pageable pageable;
	private EffectFailTechnicalActionFilterInfo filter;
	
	public RequestEffectFailTechnicalActionPageEvent(Pageable pageable, EffectFailTechnicalActionFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestEffectFailTechnicalActionPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public EffectFailTechnicalActionFilterInfo getFilter() {
		return filter;
	}
}