package co.com.cybersoft.man.services.quotation;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.docs.web.domain.quotation.QuotationBodyInfo;

public class RequiredItem {
	private String itemCode;
	private String itemDescription;
	private String unit;
	private Double acceptedQuantity;
	private Double requiredQuantity;
	private List<QuotationBodyInfo> providers=new ArrayList<>();
	
	public RequiredItem(){
		
	}
	
	public RequiredItem(String itemCode, String itemDescription, String unit, Double acceptedQuantity, Double requiredQuantity){
		this.itemCode=itemCode;
		this.itemDescription=itemDescription;
		this.unit=unit;
		this.acceptedQuantity=acceptedQuantity;
		this.requiredQuantity=requiredQuantity;
	}
	
	
	
	public List<QuotationBodyInfo> getProviders() {
		return providers;
	}

	public void setProviders(List<QuotationBodyInfo> providers) {
		this.providers = providers;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setRequiredQuantity(Double requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public String getUnit() {
		return unit;
	}

	public String getItemCode() {
		return itemCode;
	}
	
	public Double getAcceptedQuantity() {
		return acceptedQuantity;
	}
	
	
	
	public Double getRequiredQuantity() {
		return requiredQuantity;
	}

	
	
	public void setAcceptedQuantity(Double acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	@Override
	public boolean equals(Object object){
		if (object instanceof RequiredItem){
			if (((RequiredItem) object).getItemCode().equals(itemCode))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
}
