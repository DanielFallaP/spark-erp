package co.com.cybersoft.maintenance.tables.events.effectFail;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailFilterInfo;

/**
 * Event class for EffectFail
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestEffectFailPageEvent {

	private Pageable pageable;
	private EffectFailFilterInfo filter;
	
	public RequestEffectFailPageEvent(Pageable pageable, EffectFailFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestEffectFailPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public EffectFailFilterInfo getFilter() {
		return filter;
	}
}