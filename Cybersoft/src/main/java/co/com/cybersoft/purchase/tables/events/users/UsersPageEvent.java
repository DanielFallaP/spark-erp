package co.com.cybersoft.purchase.tables.events.users;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import java.util.List;

/**
 * Event class for Users
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class UsersPageEvent {
	private Page<Users> usersPage;
	
	private List<Users> allList;
	
	private Long totalCount;
	
	private List<UsersDetails> usersList;



	
	public UsersPageEvent(){
    }
	public UsersPageEvent(List<UsersDetails>  usersList){
			this.usersList= usersList;
	}



	
	public List<UsersDetails> getUsersList() {
	return usersList;
	}



	
	public List<Users> getAllList() {
		return allList;
	}

	public void setAllList(List<Users> allList) {
		this.allList = allList;
	}
	
	public UsersPageEvent(Page<Users>  usersPage){
		this.usersPage= usersPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public UsersPageEvent(Page<Users>  usersPage, Long totalCount){
		this.usersPage= usersPage;
		this.totalCount=totalCount;
	}

	public Page<Users> getUsersPage() {
		return usersPage;
	}
	
	
}