package co.com.cybersoft.man.services.quotation;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.docs.web.domain.quotation.QuotationBodyInfo;

public class RequiredItem {
	private String itemCode;
	private Double acceptedQuantity;
	private Double requiredQuantity;
	private List<QuotationBodyInfo> providers=new ArrayList<>();
	
	public RequiredItem(){
		
	}
	
	public RequiredItem(String itemCode, Double acceptedQuantity, Double requiredQuantity){
		this.itemCode=itemCode;
		this.acceptedQuantity=acceptedQuantity;
		this.requiredQuantity=requiredQuantity;
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
