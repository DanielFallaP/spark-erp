package co.com.cybersoft.purchase.tables.web.domain.users;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UsersFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String login;


	public String getLogin() {
		return login;	
	}
		
	public void setLogin(String login) {
		this.login = login;	
	}

	private String password;


	public String getPassword() {
		return password;	
	}
		
	public void setPassword(String password) {
		this.password = password;	
	}

	private String role;


	public String getRole() {
		return role;	
	}
		
	public void setRole(String role) {
		this.role = role;	
	}

	private String company;


	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	private String currencyRead;


	public String getCurrencyRead() {
		return currencyRead;	
	}
		
	public void setCurrencyRead(String currencyRead) {
		this.currencyRead = currencyRead;	
	}

	private String currencyCreate;


	public String getCurrencyCreate() {
		return currencyCreate;	
	}
		
	public void setCurrencyCreate(String currencyCreate) {
		this.currencyCreate = currencyCreate;	
	}

	private String currencyUpdate;


	public String getCurrencyUpdate() {
		return currencyUpdate;	
	}
		
	public void setCurrencyUpdate(String currencyUpdate) {
		this.currencyUpdate = currencyUpdate;	
	}

	private String currencyExport;


	public String getCurrencyExport() {
		return currencyExport;	
	}
		
	public void setCurrencyExport(String currencyExport) {
		this.currencyExport = currencyExport;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}


	
	private String fffilterNature;
	
	public String getFffilterNature() {
		return fffilterNature;
	}

	public void setFffilterNature(String fffilterNature) {
		this.fffilterNature = fffilterNature;
	}
		
	private String ffilterAsText="All";
	
	private Boolean aaddFilter=Boolean.FALSE;
	
	private List<UsersFilterInfo> usersFilterList=new ArrayList<UsersFilterInfo>();
	
	public List<UsersFilterInfo> getUsersFilterList() {
		return usersFilterList;
	}

	public void setUsersFilterList(
			List<UsersFilterInfo> usersFilterList) {
		this.usersFilterList = usersFilterList;
	}

	public Boolean getAaddFilter() {
		return aaddFilter;
	}

	public void setAaddFilter(Boolean aaddFilter) {
		this.aaddFilter = aaddFilter;
	}

	public String getFfilterAsText() {
		ffilterAsText="";
		int index=0;
		for (UsersFilterInfo fil : usersFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getLogin()!=null && !fil.getLogin().equals(""))ffilterAsText+=fil.getLogin()+" ";
			if (fil.getPassword()!=null && !fil.getPassword().equals(""))ffilterAsText+=fil.getPassword()+" ";
			if (fil.getRole()!=null && !fil.getRole().equals(""))ffilterAsText+=fil.getRole()+" ";
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getCurrencyRead()!=null && !fil.getCurrencyRead().equals(""))ffilterAsText+=fil.getCurrencyRead()+" ";
			if (fil.getCurrencyCreate()!=null && !fil.getCurrencyCreate().equals(""))ffilterAsText+=fil.getCurrencyCreate()+" ";
			if (fil.getCurrencyUpdate()!=null && !fil.getCurrencyUpdate().equals(""))ffilterAsText+=fil.getCurrencyUpdate()+" ";
			if (fil.getCurrencyExport()!=null && !fil.getCurrencyExport().equals(""))ffilterAsText+=fil.getCurrencyExport()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (login!=null && !login.equals(""))ffilterAsText+=login+" ";if (password!=null && !password.equals(""))ffilterAsText+=password+" ";if (role!=null && !role.equals(""))ffilterAsText+=role+" ";if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (currencyRead!=null && !currencyRead.equals(""))ffilterAsText+=currencyRead+" ";if (currencyCreate!=null && !currencyCreate.equals(""))ffilterAsText+=currencyCreate+" ";if (currencyUpdate!=null && !currencyUpdate.equals(""))ffilterAsText+=currencyUpdate+" ";if (currencyExport!=null && !currencyExport.equals(""))ffilterAsText+=currencyExport+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
		if (this.getDateOfModification()!=null && !this.getDateOfModification().equals(""))ffilterAsText+=this.getDateOfModification()+" ";
		if (this.getUserName()!=null && !this.getUserName().equals(""))ffilterAsText+=this.getUserName()+" ";
		if (this.getDateOfCreation()!=null && !this.getDateOfCreation().equals(""))ffilterAsText+=this.getDateOfCreation()+" ";
		if (this.getCreatedBy()!=null && !this.getCreatedBy().equals(""))ffilterAsText+=this.getCreatedBy()+" ";
		
		return ffilterAsText;
	}

	public void setFfilterAsText(String ffilterAsText) {
		this.ffilterAsText = ffilterAsText;
	}
	
	public String getAaaaction() {
		return aaaaction;
	}

	public void setAaaaction(String aaaaction) {
		this.aaaaction = aaaaction;
	}
	
	public Boolean getChangeSortingFieldDirection() {
		return changeSortingFieldDirection;
	}

	public void setChangeSortingFieldDirection(Boolean changeSortingFieldDirection) {
		this.changeSortingFieldDirection = changeSortingFieldDirection;
	}
	
	public Integer getSelectedFilterPage() {
		return selectedFilterPage;
	}

	public void setSelectedFilterPage(Integer selectedFilterPage) {
		this.selectedFilterPage = selectedFilterPage;
	}

	public String getSelectedFilterField() {
		return selectedFilterField;
	}

	public void setSelectedFilterField(String selectedFilterField) {
		this.selectedFilterField = selectedFilterField;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public String getDateOfModification() {
		return dateOfModification;
	}
	
	public void setDateOfModification(String dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}