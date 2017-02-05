package co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeCauseClosePersistenceFactory {

	TypeCauseCloseRepository typeCauseCloseRepository;
	
	public TypeCauseClosePersistenceFactory(TypeCauseCloseRepository typeCauseCloseRepository){
		this.typeCauseCloseRepository=typeCauseCloseRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose getTypeCauseCloseByField(String field, String value){
		if (field.equals("typeCauseClose"))
					return typeCauseCloseRepository.findByTypeCauseClose(value);		
		return null;
	}
}