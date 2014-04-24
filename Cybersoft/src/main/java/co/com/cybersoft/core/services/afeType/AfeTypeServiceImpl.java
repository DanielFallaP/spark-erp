package co.com.cybersoft.core.services.afeType;

import co.com.cybersoft.events.afeType.CreateAfeTypeEvent;
import co.com.cybersoft.events.afeType.AfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.events.afeType.AfeTypeModificationEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypePageEvent;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeTypeServiceImpl implements AfeTypeService{

	private final AfeTypePersistenceService afeTypePersistenceService;
	
	public AfeTypeServiceImpl(final AfeTypePersistenceService afeTypePersistenceService){
		this.afeTypePersistenceService=afeTypePersistenceService;
	}
	
	/**
	 */
	@Override
	public AfeTypeDetailsEvent createAfeType(CreateAfeTypeEvent event ) throws Exception{
		return afeTypePersistenceService.createAfeType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public AfeTypePageEvent requestAfeTypePage(RequestAfeTypePageEvent event) throws Exception{
		return afeTypePersistenceService.requestAfeTypePage(event);
	}

	@Override
	public AfeTypeDetailsEvent requestAfeTypeDetails(RequestAfeTypeDetailsEvent event ) throws Exception{
		return afeTypePersistenceService.requestAfeTypeDetails(event);
	}

	@Override
	public AfeTypeDetailsEvent modifyAfeType(AfeTypeModificationEvent event) throws Exception {
		return afeTypePersistenceService.modifyAfeType(event);
	}
	
	@Override
	public AfeTypePageEvent requestAll() throws Exception {
		return afeTypePersistenceService.requestAll();
	}
	
	@Override
	public AfeTypePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return afeTypePersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public AfeTypePageEvent requestByContainingDescription(String description) throws Exception {
		return afeTypePersistenceService.requestByContainingDescription(description);
	}
	
}