package co.com.cybersoft.maintenance.tables.events.typeCauseClose;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import java.util.List;

/**
 * Event class for TypeCauseClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TypeCauseClosePageEvent {
	private Page<TypeCauseClose> typeCauseClosePage;
	
	private List<TypeCauseClose> allList;
	
	private Long totalCount;
	
	private List<TypeCauseCloseDetails> typeCauseCloseList;



	
	public TypeCauseClosePageEvent(){
    }
	public TypeCauseClosePageEvent(List<TypeCauseCloseDetails>  typeCauseCloseList){
			this.typeCauseCloseList= typeCauseCloseList;
	}



	
	public List<TypeCauseCloseDetails> getTypeCauseCloseList() {
	return typeCauseCloseList;
	}



	
	public List<TypeCauseClose> getAllList() {
		return allList;
	}

	public void setAllList(List<TypeCauseClose> allList) {
		this.allList = allList;
	}
	
	public TypeCauseClosePageEvent(Page<TypeCauseClose>  typeCauseClosePage){
		this.typeCauseClosePage= typeCauseClosePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TypeCauseClosePageEvent(Page<TypeCauseClose>  typeCauseClosePage, Long totalCount){
		this.typeCauseClosePage= typeCauseClosePage;
		this.totalCount=totalCount;
	}

	public Page<TypeCauseClose> getTypeCauseClosePage() {
		return typeCauseClosePage;
	}
	
	
}