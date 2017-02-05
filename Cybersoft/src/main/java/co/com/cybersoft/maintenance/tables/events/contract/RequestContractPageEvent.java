package co.com.cybersoft.maintenance.tables.events.contract;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;

/**
 * Event class for Contract
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestContractPageEvent {

	private Pageable pageable;
	private ContractFilterInfo filter;
	
	public RequestContractPageEvent(Pageable pageable, ContractFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestContractPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public ContractFilterInfo getFilter() {
		return filter;
	}
}