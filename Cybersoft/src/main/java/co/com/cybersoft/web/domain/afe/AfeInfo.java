package co.com.cybersoft.web.domain.afe;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.core.domain.CompanyDetails;
import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.core.domain.BillDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AfeInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String code;


	@NotEmpty
	private String description;


	@NotNull
	private Integer number;


	private Boolean active;


	@NotEmpty
	private String company;


	private List<CompanyDetails> companyList;
	@NotEmpty
	private String afeType;


	private List<AfeTypeDetails> afeTypeList;
	@NotEmpty
	private String contract;


	private List<ContractDetails> contractList;
	@NotEmpty
	private String bill;


	private List<BillDetails> billList;
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	public Integer getNumber() {
		return number;	
	}
		
	public void setNumber(Integer number) {
		this.number = number;	
	}

	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	public List<CompanyDetails> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(
				List<CompanyDetails> companyList) {
			this.companyList = companyList;
	}

	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	public List<AfeTypeDetails> getAfeTypeList() {
		return afeTypeList;
	}

	public void setAfeTypeList(
				List<AfeTypeDetails> afeTypeList) {
			this.afeTypeList = afeTypeList;
	}

	public String getAfeType() {
		return afeType;	
	}
		
	public void setAfeType(String afeType) {
		this.afeType = afeType;	
	}

	public List<ContractDetails> getContractList() {
		return contractList;
	}

	public void setContractList(
				List<ContractDetails> contractList) {
			this.contractList = contractList;
	}

	public String getContract() {
		return contract;	
	}
		
	public void setContract(String contract) {
		this.contract = contract;	
	}

	public List<BillDetails> getBillList() {
		return billList;
	}

	public void setBillList(
				List<BillDetails> billList) {
			this.billList = billList;
	}

	public String getBill() {
		return bill;	
	}
		
	public void setBill(String bill) {
		this.bill = bill;	
	}

	public void rearrangeCompanyList(String selected){
			CompanyDetails selectedCompany=null;
			ArrayList<CompanyDetails> newList = new ArrayList<CompanyDetails>();
			for(CompanyDetails company:companyList){
				if (company.getDescription().equals(selected)){
					selectedCompany=company;
					newList.add(0, selectedCompany);
				}
				else{
					newList.add(company);
				}
			}
			companyList=newList;
		
		}
	public void rearrangeAfeTypeList(String selected){
			AfeTypeDetails selectedAfeType=null;
			ArrayList<AfeTypeDetails> newList = new ArrayList<AfeTypeDetails>();
			for(AfeTypeDetails afeType:afeTypeList){
				if (afeType.getDescription().equals(selected)){
					selectedAfeType=afeType;
					newList.add(0, selectedAfeType);
				}
				else{
					newList.add(afeType);
				}
			}
			afeTypeList=newList;
		
		}
	public void rearrangeContractList(String selected){
			ContractDetails selectedContract=null;
			ArrayList<ContractDetails> newList = new ArrayList<ContractDetails>();
			for(ContractDetails contract:contractList){
				if (contract.getDescription().equals(selected)){
					selectedContract=contract;
					newList.add(0, selectedContract);
				}
				else{
					newList.add(contract);
				}
			}
			contractList=newList;
		
		}
	public void rearrangeBillList(String selected){
			BillDetails selectedBill=null;
			ArrayList<BillDetails> newList = new ArrayList<BillDetails>();
			for(BillDetails bill:billList){
				if (bill.getDescription().equals(selected)){
					selectedBill=bill;
					newList.add(0, selectedBill);
				}
				else{
					newList.add(bill);
				}
			}
			billList=newList;
		
		}

	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}