package co.com.cybersoft.purchase.tables.events.customerTenancy;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.purchase.tables.core.domain.CustomerTenancyDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import java.util.List;

/**
 * Event class for CustomerTenancy
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CustomerTenancyPageEvent {
	private Page<CustomerTenancy> customerTenancyPage;
	
	private List<CustomerTenancy> allList;
	
	private Long totalCount;
	
	private List<CustomerTenancyDetails> customerTenancyList;



	
	public CustomerTenancyPageEvent(){
    }
	public CustomerTenancyPageEvent(List<CustomerTenancyDetails>  customerTenancyList){
			this.customerTenancyList= customerTenancyList;
	}



	
	public List<CustomerTenancyDetails> getCustomerTenancyList() {
	return customerTenancyList;
	}



	
	public List<CustomerTenancy> getAllList() {
		return allList;
	}

	public void setAllList(List<CustomerTenancy> allList) {
		this.allList = allList;
	}
	
	public CustomerTenancyPageEvent(Page<CustomerTenancy>  customerTenancyPage){
		this.customerTenancyPage= customerTenancyPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CustomerTenancyPageEvent(Page<CustomerTenancy>  customerTenancyPage, Long totalCount){
		this.customerTenancyPage= customerTenancyPage;
		this.totalCount=totalCount;
	}

	public Page<CustomerTenancy> getCustomerTenancyPage() {
		return customerTenancyPage;
	}
	
	
}