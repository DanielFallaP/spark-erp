package co.com.cybersoft.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ItemInfo implements Serializable{

	@NotNull
	@NotEmpty
	private String code;

	private String shortDescription;

	@NotNull
	@NotEmpty
	private String purchaseUnitOfMeasurement;
//	
//	private String partNumber;
//	
//	private String array;
//	
//	private String groupOfItems;
//	
//	private Boolean active;
//	
//	private Boolean blocked;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getPurchaseUnitOfMeasurement() {
		return purchaseUnitOfMeasurement;
	}

	public void setPurchaseUnitOfMeasurement(String purchaseUnitOfMeasurement) {
		this.purchaseUnitOfMeasurement = purchaseUnitOfMeasurement;
	}

	
}
