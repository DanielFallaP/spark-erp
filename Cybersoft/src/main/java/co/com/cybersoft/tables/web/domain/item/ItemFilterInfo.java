package co.com.cybersoft.tables.web.domain.item;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ItemFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private Integer selectedFilterPage;
	
	private Boolean changeSortingFieldDirection;

	public Boolean getChangeSortingFieldDirection() {
		return changeSortingFieldDirection;
	}

	public void setChangeSortingFieldDirection(Boolean changeSortingFieldDirection) {
		this.changeSortingFieldDirection = changeSortingFieldDirection;
	}

	private String selectedFilterField;
	private String code;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	private String description;


	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	private String largeName;


	public String getLargeName() {
		return largeName;	
	}
		
	public void setLargeName(String largeName) {
		this.largeName = largeName;	
	}

	private String stockSubGroup;


	public String getStockSubGroup() {
		return stockSubGroup;	
	}
		
	public void setStockSubGroup(String stockSubGroup) {
		this.stockSubGroup = stockSubGroup;	
	}

	private String division;


	public String getDivision() {
		return division;	
	}
		
	public void setDivision(String division) {
		this.division = division;	
	}

	private String inputMeasurementUnit;


	public String getInputMeasurementUnit() {
		return inputMeasurementUnit;	
	}
		
	public void setInputMeasurementUnit(String inputMeasurementUnit) {
		this.inputMeasurementUnit = inputMeasurementUnit;	
	}

	private String packingUnits;


	public String getPackingUnits() {
		return packingUnits;	
	}
		
	public void setPackingUnits(String packingUnits) {
		this.packingUnits = packingUnits;	
	}

	private String conversionFactor;


	public String getConversionFactor() {
		return conversionFactor;	
	}
		
	public void setConversionFactor(String conversionFactor) {
		this.conversionFactor = conversionFactor;	
	}

	private String outputMeasurementUnit;


	public String getOutputMeasurementUnit() {
		return outputMeasurementUnit;	
	}
		
	public void setOutputMeasurementUnit(String outputMeasurementUnit) {
		this.outputMeasurementUnit = outputMeasurementUnit;	
	}

	private String useMiniMax;


	public String getUseMiniMax() {
		return useMiniMax;	
	}
		
	public void setUseMiniMax(String useMiniMax) {
		this.useMiniMax = useMiniMax;	
	}

	private String useMiniMaxByItem;


	public String getUseMiniMaxByItem() {
		return useMiniMaxByItem;	
	}
		
	public void setUseMiniMaxByItem(String useMiniMaxByItem) {
		this.useMiniMaxByItem = useMiniMaxByItem;	
	}

	private String tax;


	public String getTax() {
		return tax;	
	}
		
	public void setTax(String tax) {
		this.tax = tax;	
	}

	private String itemType;


	public String getItemType() {
		return itemType;	
	}
		
	public void setItemType(String itemType) {
		this.itemType = itemType;	
	}

	private String partNumber;


	public String getPartNumber() {
		return partNumber;	
	}
		
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;	
	}

	private String commodity;


	public String getCommodity() {
		return commodity;	
	}
		
	public void setCommodity(String commodity) {
		this.commodity = commodity;	
	}

	private String lastItemUsedDate;


	public String getLastItemUsedDate() {
		return lastItemUsedDate;	
	}
		
	public void setLastItemUsedDate(String lastItemUsedDate) {
		this.lastItemUsedDate = lastItemUsedDate;	
	}

	private String oldItem;


	public String getOldItem() {
		return oldItem;	
	}
		
	public void setOldItem(String oldItem) {
		this.oldItem = oldItem;	
	}

	private String array;


	public String getArray() {
		return array;	
	}
		
	public void setArray(String array) {
		this.array = array;	
	}

	private String set;


	public String getSet() {
		return set;	
	}
		
	public void setSet(String set) {
		this.set = set;	
	}

	private String permitsLargerQuantityIssuingItemReception;


	public String getPermitsLargerQuantityIssuingItemReception() {
		return permitsLargerQuantityIssuingItemReception;	
	}
		
	public void setPermitsLargerQuantityIssuingItemReception(String permitsLargerQuantityIssuingItemReception) {
		this.permitsLargerQuantityIssuingItemReception = permitsLargerQuantityIssuingItemReception;	
	}

	private String blocked;


	public String getBlocked() {
		return blocked;	
	}
		
	public void setBlocked(String blocked) {
		this.blocked = blocked;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
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