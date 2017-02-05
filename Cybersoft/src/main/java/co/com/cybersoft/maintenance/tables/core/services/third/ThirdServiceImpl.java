package co.com.cybersoft.maintenance.tables.core.services.third;

import co.com.cybersoft.maintenance.tables.events.third.CreateThirdEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.third.ThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.third.RequestThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.third.ThirdPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ThirdServiceImpl implements ThirdService{

	private final ThirdPersistenceService thirdPersistenceService;
	
	public ThirdServiceImpl(final ThirdPersistenceService thirdPersistenceService){
		this.thirdPersistenceService=thirdPersistenceService;
	}
	
	/**
	 */
	public ThirdDetailsEvent createThird(CreateThirdEvent event ) throws Exception{
		return thirdPersistenceService.createThird(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ThirdPageEvent requestThirdPage(RequestThirdPageEvent event) throws Exception{
		return thirdPersistenceService.requestThirdPage(event);
	}

	public ThirdDetailsEvent requestThirdDetails(RequestThirdDetailsEvent event ) throws Exception{
		return thirdPersistenceService.requestThirdDetails(event);
	}

	public ThirdDetailsEvent modifyThird(ThirdModificationEvent event) throws Exception {
		return thirdPersistenceService.modifyThird(event);
	}
	
	public ThirdDetailsEvent requestOnlyRecord() throws Exception {
		return thirdPersistenceService.getOnlyRecord();
	}
	
	public ThirdPageEvent requestThirdFilterPage(RequestThirdPageEvent event) throws Exception {
		return thirdPersistenceService.requestThirdFilterPage(event);
	}
	
	public ThirdPageEvent requestThirdFilter(RequestThirdPageEvent event) throws Exception {
		return thirdPersistenceService.requestThirdFilter(event);
	}
	

	public ThirdPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByCompany(fields);
	}public ThirdPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByCostCenter(fields);
	}public ThirdPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByCode(fields);
	}public ThirdPageEvent requestAllByNameThird(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByNameThird(fields);
	}public ThirdPageEvent requestAllByContact(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByContact(fields);
	}public ThirdPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByAddress(fields);
	}public ThirdPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByCountry(fields);
	}public ThirdPageEvent requestAllByPhoneOne(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByPhoneOne(fields);
	}public ThirdPageEvent requestAllByPhoneTwo(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByPhoneTwo(fields);
	}public ThirdPageEvent requestAllByEmail(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByEmail(fields);
	}public ThirdPageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByComment(fields);
	}public ThirdPageEvent requestAllByTypeRegime(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByTypeRegime(fields);
	}public ThirdPageEvent requestAllByExternalAccess(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByExternalAccess(fields);
	}public ThirdPageEvent requestAllByKeyExternalAccess(EmbeddedField... fields) throws Exception {
		return thirdPersistenceService.requestAllByKeyExternalAccess(fields);
	}
	
	
	
}