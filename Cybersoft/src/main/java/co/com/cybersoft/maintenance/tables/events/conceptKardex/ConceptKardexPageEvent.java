package co.com.cybersoft.maintenance.tables.events.conceptKardex;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import java.util.List;

/**
 * Event class for ConceptKardex
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ConceptKardexPageEvent {
	private Page<ConceptKardex> conceptKardexPage;
	
	private List<ConceptKardex> allList;
	
	private Long totalCount;
	
	private List<ConceptKardexDetails> conceptKardexList;



	
	public ConceptKardexPageEvent(){
    }
	public ConceptKardexPageEvent(List<ConceptKardexDetails>  conceptKardexList){
			this.conceptKardexList= conceptKardexList;
	}



	
	public List<ConceptKardexDetails> getConceptKardexList() {
	return conceptKardexList;
	}



	
	public List<ConceptKardex> getAllList() {
		return allList;
	}

	public void setAllList(List<ConceptKardex> allList) {
		this.allList = allList;
	}
	
	public ConceptKardexPageEvent(Page<ConceptKardex>  conceptKardexPage){
		this.conceptKardexPage= conceptKardexPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ConceptKardexPageEvent(Page<ConceptKardex>  conceptKardexPage, Long totalCount){
		this.conceptKardexPage= conceptKardexPage;
		this.totalCount=totalCount;
	}

	public Page<ConceptKardex> getConceptKardexPage() {
		return conceptKardexPage;
	}
	
	
}