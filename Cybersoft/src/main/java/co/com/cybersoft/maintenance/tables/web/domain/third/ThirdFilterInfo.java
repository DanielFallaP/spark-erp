package co.com.cybersoft.maintenance.tables.web.domain.third;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ThirdFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String company;


	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	private String costCenter;


	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}

	private String code;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	private String nameThird;


	public String getNameThird() {
		return nameThird;	
	}
		
	public void setNameThird(String nameThird) {
		this.nameThird = nameThird;	
	}

	private String typeThird;


	public String getTypeThird() {
		return typeThird;	
	}
		
	public void setTypeThird(String typeThird) {
		this.typeThird = typeThird;	
	}

	private String contact;


	public String getContact() {
		return contact;	
	}
		
	public void setContact(String contact) {
		this.contact = contact;	
	}

	private String address;


	public String getAddress() {
		return address;	
	}
		
	public void setAddress(String address) {
		this.address = address;	
	}

	private String country;


	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	private String phoneOne;


	public String getPhoneOne() {
		return phoneOne;	
	}
		
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;	
	}

	private String phoneTwo;


	public String getPhoneTwo() {
		return phoneTwo;	
	}
		
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;	
	}

	private String email;


	public String getEmail() {
		return email;	
	}
		
	public void setEmail(String email) {
		this.email = email;	
	}

	private String dateEntry;


	public String getDateEntry() {
		return dateEntry;	
	}
		
	public void setDateEntry(String dateEntry) {
		this.dateEntry = dateEntry;	
	}

	private String dateRetirement;


	public String getDateRetirement() {
		return dateRetirement;	
	}
		
	public void setDateRetirement(String dateRetirement) {
		this.dateRetirement = dateRetirement;	
	}

	private String comment;


	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}

	private String bigContributor;


	public String getBigContributor() {
		return bigContributor;	
	}
		
	public void setBigContributor(String bigContributor) {
		this.bigContributor = bigContributor;	
	}

	private String autorretenedor;


	public String getAutorretenedor() {
		return autorretenedor;	
	}
		
	public void setAutorretenedor(String autorretenedor) {
		this.autorretenedor = autorretenedor;	
	}

	private String typeRegime;


	public String getTypeRegime() {
		return typeRegime;	
	}
		
	public void setTypeRegime(String typeRegime) {
		this.typeRegime = typeRegime;	
	}

	private String externalAccess;


	public String getExternalAccess() {
		return externalAccess;	
	}
		
	public void setExternalAccess(String externalAccess) {
		this.externalAccess = externalAccess;	
	}

	private String keyExternalAccess;


	public String getKeyExternalAccess() {
		return keyExternalAccess;	
	}
		
	public void setKeyExternalAccess(String keyExternalAccess) {
		this.keyExternalAccess = keyExternalAccess;	
	}

	private String stateThird;


	public String getStateThird() {
		return stateThird;	
	}
		
	public void setStateThird(String stateThird) {
		this.stateThird = stateThird;	
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
	
	private List<ThirdFilterInfo> thirdFilterList=new ArrayList<ThirdFilterInfo>();
	
	public List<ThirdFilterInfo> getThirdFilterList() {
		return thirdFilterList;
	}

	public void setThirdFilterList(
			List<ThirdFilterInfo> thirdFilterList) {
		this.thirdFilterList = thirdFilterList;
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
		for (ThirdFilterInfo fil : thirdFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getCostCenter()!=null && !fil.getCostCenter().equals(""))ffilterAsText+=fil.getCostCenter()+" ";
			if (fil.getCode()!=null && !fil.getCode().equals(""))ffilterAsText+=fil.getCode()+" ";
			if (fil.getNameThird()!=null && !fil.getNameThird().equals(""))ffilterAsText+=fil.getNameThird()+" ";
			if (fil.getTypeThird()!=null && !fil.getTypeThird().equals(""))ffilterAsText+=fil.getTypeThird()+" ";
			if (fil.getContact()!=null && !fil.getContact().equals(""))ffilterAsText+=fil.getContact()+" ";
			if (fil.getAddress()!=null && !fil.getAddress().equals(""))ffilterAsText+=fil.getAddress()+" ";
			if (fil.getCountry()!=null && !fil.getCountry().equals(""))ffilterAsText+=fil.getCountry()+" ";
			if (fil.getPhoneOne()!=null && !fil.getPhoneOne().equals(""))ffilterAsText+=fil.getPhoneOne()+" ";
			if (fil.getPhoneTwo()!=null && !fil.getPhoneTwo().equals(""))ffilterAsText+=fil.getPhoneTwo()+" ";
			if (fil.getEmail()!=null && !fil.getEmail().equals(""))ffilterAsText+=fil.getEmail()+" ";
			if (fil.getDateEntry()!=null && !fil.getDateEntry().equals(""))ffilterAsText+=fil.getDateEntry()+" ";
			if (fil.getDateRetirement()!=null && !fil.getDateRetirement().equals(""))ffilterAsText+=fil.getDateRetirement()+" ";
			if (fil.getComment()!=null && !fil.getComment().equals(""))ffilterAsText+=fil.getComment()+" ";
			if (fil.getBigContributor()!=null && !fil.getBigContributor().equals(""))ffilterAsText+=fil.getBigContributor()+" ";
			if (fil.getAutorretenedor()!=null && !fil.getAutorretenedor().equals(""))ffilterAsText+=fil.getAutorretenedor()+" ";
			if (fil.getTypeRegime()!=null && !fil.getTypeRegime().equals(""))ffilterAsText+=fil.getTypeRegime()+" ";
			if (fil.getExternalAccess()!=null && !fil.getExternalAccess().equals(""))ffilterAsText+=fil.getExternalAccess()+" ";
			if (fil.getKeyExternalAccess()!=null && !fil.getKeyExternalAccess().equals(""))ffilterAsText+=fil.getKeyExternalAccess()+" ";
			if (fil.getStateThird()!=null && !fil.getStateThird().equals(""))ffilterAsText+=fil.getStateThird()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (costCenter!=null && !costCenter.equals(""))ffilterAsText+=costCenter+" ";if (code!=null && !code.equals(""))ffilterAsText+=code+" ";if (nameThird!=null && !nameThird.equals(""))ffilterAsText+=nameThird+" ";if (typeThird!=null && !typeThird.equals(""))ffilterAsText+=typeThird+" ";if (contact!=null && !contact.equals(""))ffilterAsText+=contact+" ";if (address!=null && !address.equals(""))ffilterAsText+=address+" ";if (country!=null && !country.equals(""))ffilterAsText+=country+" ";if (phoneOne!=null && !phoneOne.equals(""))ffilterAsText+=phoneOne+" ";if (phoneTwo!=null && !phoneTwo.equals(""))ffilterAsText+=phoneTwo+" ";if (email!=null && !email.equals(""))ffilterAsText+=email+" ";if (dateEntry!=null && !dateEntry.equals(""))ffilterAsText+=dateEntry+" ";if (dateRetirement!=null && !dateRetirement.equals(""))ffilterAsText+=dateRetirement+" ";if (comment!=null && !comment.equals(""))ffilterAsText+=comment+" ";if (bigContributor!=null && !bigContributor.equals(""))ffilterAsText+=bigContributor+" ";if (autorretenedor!=null && !autorretenedor.equals(""))ffilterAsText+=autorretenedor+" ";if (typeRegime!=null && !typeRegime.equals(""))ffilterAsText+=typeRegime+" ";if (externalAccess!=null && !externalAccess.equals(""))ffilterAsText+=externalAccess+" ";if (keyExternalAccess!=null && !keyExternalAccess.equals(""))ffilterAsText+=keyExternalAccess+" ";if (stateThird!=null && !stateThird.equals(""))ffilterAsText+=stateThird+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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