package co.com.cybersoft.maintenance.tables.events.characteristic;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import java.util.List;

/**
 * Event class for Characteristic
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CharacteristicPageEvent {
	private Page<Characteristic> characteristicPage;
	
	private List<Characteristic> allList;
	
	private Long totalCount;
	
	private List<CharacteristicDetails> characteristicList;



	
	public CharacteristicPageEvent(){
    }
	public CharacteristicPageEvent(List<CharacteristicDetails>  characteristicList){
			this.characteristicList= characteristicList;
	}



	
	public List<CharacteristicDetails> getCharacteristicList() {
	return characteristicList;
	}



	
	public List<Characteristic> getAllList() {
		return allList;
	}

	public void setAllList(List<Characteristic> allList) {
		this.allList = allList;
	}
	
	public CharacteristicPageEvent(Page<Characteristic>  characteristicPage){
		this.characteristicPage= characteristicPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CharacteristicPageEvent(Page<Characteristic>  characteristicPage, Long totalCount){
		this.characteristicPage= characteristicPage;
		this.totalCount=totalCount;
	}

	public Page<Characteristic> getCharacteristicPage() {
		return characteristicPage;
	}
	
	
}