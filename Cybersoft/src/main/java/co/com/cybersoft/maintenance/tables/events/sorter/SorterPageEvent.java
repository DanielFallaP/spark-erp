package co.com.cybersoft.maintenance.tables.events.sorter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import java.util.List;

/**
 * Event class for Sorter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class SorterPageEvent {
	private Page<Sorter> sorterPage;
	
	private List<Sorter> allList;
	
	private Long totalCount;
	
	private List<SorterDetails> sorterList;



	
	public SorterPageEvent(){
    }
	public SorterPageEvent(List<SorterDetails>  sorterList){
			this.sorterList= sorterList;
	}



	
	public List<SorterDetails> getSorterList() {
	return sorterList;
	}



	
	public List<Sorter> getAllList() {
		return allList;
	}

	public void setAllList(List<Sorter> allList) {
		this.allList = allList;
	}
	
	public SorterPageEvent(Page<Sorter>  sorterPage){
		this.sorterPage= sorterPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public SorterPageEvent(Page<Sorter>  sorterPage, Long totalCount){
		this.sorterPage= sorterPage;
		this.totalCount=totalCount;
	}

	public Page<Sorter> getSorterPage() {
		return sorterPage;
	}
	
	
}