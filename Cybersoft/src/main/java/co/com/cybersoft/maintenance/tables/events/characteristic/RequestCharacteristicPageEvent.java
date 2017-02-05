package co.com.cybersoft.maintenance.tables.events.characteristic;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicFilterInfo;

/**
 * Event class for Characteristic
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestCharacteristicPageEvent {

	private Pageable pageable;
	private CharacteristicFilterInfo filter;
	
	public RequestCharacteristicPageEvent(Pageable pageable, CharacteristicFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestCharacteristicPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public CharacteristicFilterInfo getFilter() {
		return filter;
	}
}