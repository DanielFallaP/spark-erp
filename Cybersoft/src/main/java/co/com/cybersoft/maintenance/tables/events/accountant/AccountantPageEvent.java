package co.com.cybersoft.maintenance.tables.events.accountant;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import java.util.List;

/**
 * Event class for Accountant
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class AccountantPageEvent {
	private Page<Accountant> accountantPage;
	
	private List<Accountant> allList;
	
	private Long totalCount;
	
	private List<AccountantDetails> accountantList;



	
	public AccountantPageEvent(){
    }
	public AccountantPageEvent(List<AccountantDetails>  accountantList){
			this.accountantList= accountantList;
	}



	
	public List<AccountantDetails> getAccountantList() {
	return accountantList;
	}



	
	public List<Accountant> getAllList() {
		return allList;
	}

	public void setAllList(List<Accountant> allList) {
		this.allList = allList;
	}
	
	public AccountantPageEvent(Page<Accountant>  accountantPage){
		this.accountantPage= accountantPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public AccountantPageEvent(Page<Accountant>  accountantPage, Long totalCount){
		this.accountantPage= accountantPage;
		this.totalCount=totalCount;
	}

	public Page<Accountant> getAccountantPage() {
		return accountantPage;
	}
	
	
}