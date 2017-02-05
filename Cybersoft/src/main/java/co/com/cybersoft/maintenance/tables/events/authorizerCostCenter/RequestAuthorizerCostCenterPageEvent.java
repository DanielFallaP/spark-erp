package co.com.cybersoft.maintenance.tables.events.authorizerCostCenter;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterFilterInfo;

/**
 * Event class for AuthorizerCostCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestAuthorizerCostCenterPageEvent {

	private Pageable pageable;
	private AuthorizerCostCenterFilterInfo filter;
	
	public RequestAuthorizerCostCenterPageEvent(Pageable pageable, AuthorizerCostCenterFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestAuthorizerCostCenterPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public AuthorizerCostCenterFilterInfo getFilter() {
		return filter;
	}
}