package co.com.cybersoft.web.domain.wareHouse;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.core.domain.ActiveDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for wareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WareHouseInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String code;


	@NotEmpty
	private String name;


	@NotEmpty
	private String accountCode;


	@NotEmpty
	private String accountConceptCode;


	private List<CalculusTypeDetails> calculusTypeList;
	private String calculusType;


	private List<OperationTypeDetails> operationTypeList;
	private String operationType;


	private List<AfeDetails> afeList;
	private String afe;


	private List<ContractDetails> contractList;
	private String contract;


	private List<ActiveDetails> activeList;
	private String active;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	public String getName() {
		return name;	
	}
		
	public void setName(String name) {
		this.name = name;	
	}

	public String getAccountCode() {
		return accountCode;	
	}
		
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;	
	}

	public String getAccountConceptCode() {
		return accountConceptCode;	
	}
		
	public void setAccountConceptCode(String accountConceptCode) {
		this.accountConceptCode = accountConceptCode;	
	}

	public List<CalculusTypeDetails> getCalculusTypeList() {
		return calculusTypeList;
	}

	public void setCalculusTypeList(
				List<CalculusTypeDetails> calculusTypeList) {
			this.calculusTypeList = calculusTypeList;
	}

	public String getCalculusType() {
		return calculusType;	
	}
		
	public void setCalculusType(String calculusType) {
		this.calculusType = calculusType;	
	}

	public List<OperationTypeDetails> getOperationTypeList() {
		return operationTypeList;
	}

	public void setOperationTypeList(
				List<OperationTypeDetails> operationTypeList) {
			this.operationTypeList = operationTypeList;
	}

	public String getOperationType() {
		return operationType;	
	}
		
	public void setOperationType(String operationType) {
		this.operationType = operationType;	
	}

	public List<AfeDetails> getAfeList() {
		return afeList;
	}

	public void setAfeList(
				List<AfeDetails> afeList) {
			this.afeList = afeList;
	}

	public String getAfe() {
		return afe;	
	}
		
	public void setAfe(String afe) {
		this.afe = afe;	
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

	public List<ActiveDetails> getActiveList() {
		return activeList;
	}

	public void setActiveList(
				List<ActiveDetails> activeList) {
			this.activeList = activeList;
	}

	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	public void rearrangeCalculusTypeList(String selected){
			CalculusTypeDetails selectedCalculusType=null;
			ArrayList<CalculusTypeDetails> newList = new ArrayList<CalculusTypeDetails>();
			for(CalculusTypeDetails calculusType:calculusTypeList){
				if (calculusType.getName().equals(selected)){
					selectedCalculusType=calculusType;
					newList.add(0, selectedCalculusType);
				}
				else{
					newList.add(calculusType);
				}
			}
			calculusTypeList=newList;
		
		}
	public void rearrangeOperationTypeList(String selected){
			OperationTypeDetails selectedOperationType=null;
			ArrayList<OperationTypeDetails> newList = new ArrayList<OperationTypeDetails>();
			for(OperationTypeDetails operationType:operationTypeList){
				if (operationType.getName().equals(selected)){
					selectedOperationType=operationType;
					newList.add(0, selectedOperationType);
				}
				else{
					newList.add(operationType);
				}
			}
			operationTypeList=newList;
		
		}
	public void rearrangeAfeList(String selected){
			AfeDetails selectedAfe=null;
			ArrayList<AfeDetails> newList = new ArrayList<AfeDetails>();
			for(AfeDetails afe:afeList){
				if (afe.getName().equals(selected)){
					selectedAfe=afe;
					newList.add(0, selectedAfe);
				}
				else{
					newList.add(afe);
				}
			}
			afeList=newList;
		
		}
	public void rearrangeContractList(String selected){
			ContractDetails selectedContract=null;
			ArrayList<ContractDetails> newList = new ArrayList<ContractDetails>();
			for(ContractDetails contract:contractList){
				if (contract.getName().equals(selected)){
					selectedContract=contract;
					newList.add(0, selectedContract);
				}
				else{
					newList.add(contract);
				}
			}
			contractList=newList;
		
		}
	public void rearrangeActiveList(String selected){
			ActiveDetails selectedActive=null;
			ArrayList<ActiveDetails> newList = new ArrayList<ActiveDetails>();
			for(ActiveDetails active:activeList){
				if (active.getName().equals(selected)){
					selectedActive=active;
					newList.add(0, selectedActive);
				}
				else{
					newList.add(active);
				}
			}
			activeList=newList;
		
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