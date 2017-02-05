package co.com.cybersoft.maintenance.tables.events.typeConceptKardex;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import java.util.List;

/**
 * Event class for TypeConceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TypeConceptKardexPageEvent {
	private Page<TypeConceptKardex> typeConceptKardexPage;
	
	private List<TypeConceptKardex> allList;
	
	private Long totalCount;
	
	private List<TypeConceptKardexDetails> typeConceptKardexList;



	
	public TypeConceptKardexPageEvent(){
    }
	public TypeConceptKardexPageEvent(List<TypeConceptKardexDetails>  typeConceptKardexList){
			this.typeConceptKardexList= typeConceptKardexList;
	}



	
	public List<TypeConceptKardexDetails> getTypeConceptKardexList() {
	return typeConceptKardexList;
	}



	
	public List<TypeConceptKardex> getAllList() {
		return allList;
	}

	public void setAllList(List<TypeConceptKardex> allList) {
		this.allList = allList;
	}
	
	public TypeConceptKardexPageEvent(Page<TypeConceptKardex>  typeConceptKardexPage){
		this.typeConceptKardexPage= typeConceptKardexPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TypeConceptKardexPageEvent(Page<TypeConceptKardex>  typeConceptKardexPage, Long totalCount){
		this.typeConceptKardexPage= typeConceptKardexPage;
		this.totalCount=totalCount;
	}

	public Page<TypeConceptKardex> getTypeConceptKardexPage() {
		return typeConceptKardexPage;
	}
	
	
}