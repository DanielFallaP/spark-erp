package co.com.cybersoft.web.domain.jointVenture;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.core.domain.ActiveDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for jointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class JointVentureInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@Length(max=4)
	@NotEmpty
	private String code;


	private Date fromDate;


	private Date toDate;


	private List<BillDetails> billList;
	private String bill;


	private List<PartnerDetails> partnerList;
	private String partner;


	private List<ActiveDetails> activeList;
	private String active;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	public Date getFromDate() {
		return fromDate;	
	}
		
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;	
	}

	public Date getToDate() {
		return toDate;	
	}
		
	public void setToDate(Date toDate) {
		this.toDate = toDate;	
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

	public List<PartnerDetails> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(
				List<PartnerDetails> partnerList) {
			this.partnerList = partnerList;
	}

	public String getPartner() {
		return partner;	
	}
		
	public void setPartner(String partner) {
		this.partner = partner;	
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
	public void rearrangePartnerList(String selected){
			PartnerDetails selectedPartner=null;
			ArrayList<PartnerDetails> newList = new ArrayList<PartnerDetails>();
			for(PartnerDetails partner:partnerList){
				if (partner.getDescription().equals(selected)){
					selectedPartner=partner;
					newList.add(0, selectedPartner);
				}
				else{
					newList.add(partner);
				}
			}
			partnerList=newList;
		
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