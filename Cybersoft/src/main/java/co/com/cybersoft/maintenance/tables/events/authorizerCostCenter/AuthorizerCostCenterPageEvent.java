package co.com.cybersoft.maintenance.tables.events.authorizerCostCenter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import java.util.List;

/**
 * Event class for AuthorizerCostCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class AuthorizerCostCenterPageEvent {
	private Page<AuthorizerCostCenter> authorizerCostCenterPage;
	
	private List<AuthorizerCostCenter> allList;
	
	private Long totalCount;
	
	private List<AuthorizerCostCenterDetails> authorizerCostCenterList;



	
	public AuthorizerCostCenterPageEvent(){
    }
	public AuthorizerCostCenterPageEvent(List<AuthorizerCostCenterDetails>  authorizerCostCenterList){
			this.authorizerCostCenterList= authorizerCostCenterList;
	}



	
	public List<AuthorizerCostCenterDetails> getAuthorizerCostCenterList() {
	return authorizerCostCenterList;
	}



	
	public List<AuthorizerCostCenter> getAllList() {
		return allList;
	}

	public void setAllList(List<AuthorizerCostCenter> allList) {
		this.allList = allList;
	}
	
	public AuthorizerCostCenterPageEvent(Page<AuthorizerCostCenter>  authorizerCostCenterPage){
		this.authorizerCostCenterPage= authorizerCostCenterPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public AuthorizerCostCenterPageEvent(Page<AuthorizerCostCenter>  authorizerCostCenterPage, Long totalCount){
		this.authorizerCostCenterPage= authorizerCostCenterPage;
		this.totalCount=totalCount;
	}

	public Page<AuthorizerCostCenter> getAuthorizerCostCenterPage() {
		return authorizerCostCenterPage;
	}
	
	
}