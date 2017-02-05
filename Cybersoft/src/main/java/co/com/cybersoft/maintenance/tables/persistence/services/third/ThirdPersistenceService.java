package co.com.cybersoft.maintenance.tables.persistence.services.third;

import co.com.cybersoft.maintenance.tables.events.third.CreateThirdEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ThirdPersistenceService {

	ThirdDetailsEvent createThird(CreateThirdEvent event) throws Exception;

	ThirdPageEvent requestThirdPage(RequestThirdPageEvent event) throws Exception;

	ThirdDetailsEvent requestThirdDetails(RequestThirdDetailsEvent event) throws Exception;
	
	ThirdDetailsEvent modifyThird(ThirdModificationEvent event) throws Exception;
	ThirdPageEvent requestThirdFilterPage(RequestThirdPageEvent event) throws Exception;
	ThirdPageEvent requestThirdFilter(RequestThirdPageEvent event) throws Exception;
	
	ThirdPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByCode(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByNameThird(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByContact(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByPhoneOne(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByPhoneTwo(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByEmail(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByComment(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByTypeRegime(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByExternalAccess(EmbeddedField... fields) throws Exception;
	ThirdPageEvent requestAllByKeyExternalAccess(EmbeddedField... fields) throws Exception;

	
	
	ThirdDetailsEvent getOnlyRecord() throws Exception;
	
}