package co.com.cybersoft.tables.web.domain.populatedPlace;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PopulatedPlaceFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	private String country;

	private Integer selectedFilterPage;
	
	private String field;

	
	
	public Integer getSelectedFilterPage() {
		return selectedFilterPage;
	}

	public void setSelectedFilterPage(Integer selectedFilterPage) {
		this.selectedFilterPage = selectedFilterPage;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}

	private String state;


	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
	}

	private String populatedPlace;


	public String getPopulatedPlace() {
		return populatedPlace;	
	}
		
	public void setPopulatedPlace(String populatedPlace) {
		this.populatedPlace = populatedPlace;	
	}

	private String area;


	public String getArea() {
		return area;	
	}
		
	public void setArea(String area) {
		this.area = area;	
	}

	private String population;


	public String getPopulation() {
		return population;	
	}
		
	public void setPopulation(String population) {
		this.population = population;	
	}

	private String latitude;


	public String getLatitude() {
		return latitude;	
	}
		
	public void setLatitude(String latitude) {
		this.latitude = latitude;	
	}

	private String longitude;


	public String getLongitude() {
		return longitude;	
	}
		
	public void setLongitude(String longitude) {
		this.longitude = longitude;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
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