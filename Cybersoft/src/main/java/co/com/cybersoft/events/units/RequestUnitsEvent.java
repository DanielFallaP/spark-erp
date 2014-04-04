package co.com.cybersoft.events.units;

import org.springframework.data.domain.Pageable;

public class RequestUnitsEvent {

	private Pageable pageable;
	
	public RequestUnitsEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
}
