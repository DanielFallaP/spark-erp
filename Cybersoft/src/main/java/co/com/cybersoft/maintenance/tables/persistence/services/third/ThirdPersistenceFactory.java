package co.com.cybersoft.maintenance.tables.persistence.services.third;

import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ThirdPersistenceFactory {

	ThirdRepository thirdRepository;
	
	public ThirdPersistenceFactory(ThirdRepository thirdRepository){
		this.thirdRepository=thirdRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Third getThirdByField(String field, String value){
		if (field.equals("code"))
					return thirdRepository.findByCode(value);if (field.equals("nameThird"))
					return thirdRepository.findByNameThird(value);if (field.equals("contact"))
					return thirdRepository.findByContact(value);if (field.equals("address"))
					return thirdRepository.findByAddress(value);if (field.equals("country"))
					return thirdRepository.findByCountry(value);if (field.equals("phoneOne"))
					return thirdRepository.findByPhoneOne(value);if (field.equals("phoneTwo"))
					return thirdRepository.findByPhoneTwo(value);if (field.equals("email"))
					return thirdRepository.findByEmail(value);if (field.equals("comment"))
					return thirdRepository.findByComment(value);if (field.equals("typeRegime"))
					return thirdRepository.findByTypeRegime(value);if (field.equals("externalAccess"))
					return thirdRepository.findByExternalAccess(value);if (field.equals("keyExternalAccess"))
					return thirdRepository.findByKeyExternalAccess(value);		
		return null;
	}
}