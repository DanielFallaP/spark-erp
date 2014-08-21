package co.com.cybersoft.man.services.quotation;

import java.util.ArrayList;
import java.util.List;

public class AcceptedSupplier {

	private String supplier;
	private List<RequiredItem> items=new ArrayList<>();
	
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public List<RequiredItem> getItems() {
		return items;
	}
	public void setItems(List<RequiredItem> items) {
		this.items = items;
	}
	
	
	
	@Override
	public boolean equals(Object object){
		if (object instanceof AcceptedSupplier){
			if (((AcceptedSupplier) object).getSupplier().equals(supplier))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
}
