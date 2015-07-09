package co.com.cybersoft.purchase.tables.persistence.services.continent;

import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContinentPersistenceFactory {

	ContinentRepository continentRepository;
	
	public ContinentPersistenceFactory(ContinentRepository continentRepository){
		this.continentRepository=continentRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.Continent getContinentByField(String field, String value){
		if (field.equals("continent"))
					return continentRepository.findByContinent(value);		
		return null;
	}
}