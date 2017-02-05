package co.com.cybersoft.maintenance.tables.events.typeSorter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import java.util.List;

/**
 * Event class for TypeSorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TypeSorterPageEvent {
	private Page<TypeSorter> typeSorterPage;
	
	private List<TypeSorter> allList;
	
	private Long totalCount;
	
	private List<TypeSorterDetails> typeSorterList;



	
	public TypeSorterPageEvent(){
    }
	public TypeSorterPageEvent(List<TypeSorterDetails>  typeSorterList){
			this.typeSorterList= typeSorterList;
	}



	
	public List<TypeSorterDetails> getTypeSorterList() {
	return typeSorterList;
	}



	
	public List<TypeSorter> getAllList() {
		return allList;
	}

	public void setAllList(List<TypeSorter> allList) {
		this.allList = allList;
	}
	
	public TypeSorterPageEvent(Page<TypeSorter>  typeSorterPage){
		this.typeSorterPage= typeSorterPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TypeSorterPageEvent(Page<TypeSorter>  typeSorterPage, Long totalCount){
		this.typeSorterPage= typeSorterPage;
		this.totalCount=totalCount;
	}

	public Page<TypeSorter> getTypeSorterPage() {
		return typeSorterPage;
	}
	
	
}