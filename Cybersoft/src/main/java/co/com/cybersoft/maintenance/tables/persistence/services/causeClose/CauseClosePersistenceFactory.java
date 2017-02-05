package co.com.cybersoft.maintenance.tables.persistence.services.causeClose;

import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CauseClosePersistenceFactory {

	CauseCloseRepository causeCloseRepository;
	
	public CauseClosePersistenceFactory(CauseCloseRepository causeCloseRepository){
		this.causeCloseRepository=causeCloseRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose getCauseCloseByField(String field, String value){
		if (field.equals("nameCauseClose"))
					return causeCloseRepository.findByNameCauseClose(value);		
		return null;
	}
}