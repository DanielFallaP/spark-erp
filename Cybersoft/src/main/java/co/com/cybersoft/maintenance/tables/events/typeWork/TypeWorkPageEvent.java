package co.com.cybersoft.maintenance.tables.events.typeWork;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import java.util.List;

/**
 * Event class for TypeWork
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TypeWorkPageEvent {
	private Page<TypeWork> typeWorkPage;
	
	private List<TypeWork> allList;
	
	private Long totalCount;
	
	private List<TypeWorkDetails> typeWorkList;



	
	public TypeWorkPageEvent(){
    }
	public TypeWorkPageEvent(List<TypeWorkDetails>  typeWorkList){
			this.typeWorkList= typeWorkList;
	}



	
	public List<TypeWorkDetails> getTypeWorkList() {
	return typeWorkList;
	}



	
	public List<TypeWork> getAllList() {
		return allList;
	}

	public void setAllList(List<TypeWork> allList) {
		this.allList = allList;
	}
	
	public TypeWorkPageEvent(Page<TypeWork>  typeWorkPage){
		this.typeWorkPage= typeWorkPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TypeWorkPageEvent(Page<TypeWork>  typeWorkPage, Long totalCount){
		this.typeWorkPage= typeWorkPage;
		this.totalCount=totalCount;
	}

	public Page<TypeWork> getTypeWorkPage() {
		return typeWorkPage;
	}
	
	
}