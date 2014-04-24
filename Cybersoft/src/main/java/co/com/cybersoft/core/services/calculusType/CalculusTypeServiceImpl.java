package co.com.cybersoft.core.services.calculusType;

import co.com.cybersoft.events.calculusType.CreateCalculusTypeEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeModificationEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypePageEvent;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CalculusTypeServiceImpl implements CalculusTypeService{

	private final CalculusTypePersistenceService calculusTypePersistenceService;
	
	public CalculusTypeServiceImpl(final CalculusTypePersistenceService calculusTypePersistenceService){
		this.calculusTypePersistenceService=calculusTypePersistenceService;
	}
	
	/**
	 */
	@Override
	public CalculusTypeDetailsEvent createCalculusType(CreateCalculusTypeEvent event ) throws Exception{
		return calculusTypePersistenceService.createCalculusType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public CalculusTypePageEvent requestCalculusTypePage(RequestCalculusTypePageEvent event) throws Exception{
		return calculusTypePersistenceService.requestCalculusTypePage(event);
	}

	@Override
	public CalculusTypeDetailsEvent requestCalculusTypeDetails(RequestCalculusTypeDetailsEvent event ) throws Exception{
		return calculusTypePersistenceService.requestCalculusTypeDetails(event);
	}

	@Override
	public CalculusTypeDetailsEvent modifyCalculusType(CalculusTypeModificationEvent event) throws Exception {
		return calculusTypePersistenceService.modifyCalculusType(event);
	}
	
	@Override
	public CalculusTypePageEvent requestAll() throws Exception {
		return calculusTypePersistenceService.requestAll();
	}
	
	@Override
	public CalculusTypePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return calculusTypePersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public CalculusTypePageEvent requestByContainingDescription(String description) throws Exception {
		return calculusTypePersistenceService.requestByContainingDescription(description);
	}
	
}