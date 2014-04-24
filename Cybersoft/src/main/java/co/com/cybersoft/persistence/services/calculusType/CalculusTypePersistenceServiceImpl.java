package co.com.cybersoft.persistence.services.calculusType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.events.calculusType.CreateCalculusTypeEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeModificationEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypePageEvent;
import co.com.cybersoft.persistence.domain.CalculusType;
import co.com.cybersoft.persistence.repository.calculusType.CalculusTypeRepository;
import co.com.cybersoft.persistence.repository.calculusType.CalculusTypeCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CalculusTypePersistenceServiceImpl implements CalculusTypePersistenceService{

	private final CalculusTypeRepository calculusTypeRepository;
	
	private final CalculusTypeCustomRepo calculusTypeCustomRepo;
	
	public CalculusTypePersistenceServiceImpl(final CalculusTypeRepository calculusTypeRepository, final CalculusTypeCustomRepo calculusTypeCustomRepo) {
		this.calculusTypeRepository=calculusTypeRepository;
		this.calculusTypeCustomRepo=calculusTypeCustomRepo;
	}
	
	@Override
	public CalculusTypeDetailsEvent createCalculusType(CreateCalculusTypeEvent event) throws Exception{
		CalculusType calculusType = CalculusType.fromCalculusTypeDetails(event.getCalculusTypeDetails());
		calculusType = calculusTypeRepository.save(calculusType);
		return new CalculusTypeDetailsEvent(calculusType.toCalculusTypeDetails());
	}

	@Override
	public CalculusTypePageEvent requestCalculusTypePage(RequestCalculusTypePageEvent event) throws Exception {
		Page<CalculusType> calculusTypes = calculusTypeRepository.findAll(event.getPageable());
		return new CalculusTypePageEvent(calculusTypes);
	}

	@Override
	public CalculusTypeDetailsEvent requestCalculusTypeDetails(RequestCalculusTypeDetailsEvent event) throws Exception {
		CalculusTypeDetails calculusTypeDetails=null;
		if (event.getId()!=null){
			CalculusType calculusType = calculusTypeRepository.findByCode(event.getId());
			if (calculusType!=null)
				calculusTypeDetails = calculusType.toCalculusTypeDetails();
		}
		else{
					CalculusType calculusType = calculusTypeRepository.findByDescription(event.getDescription());
					if (calculusType!=null)
						calculusTypeDetails = calculusType.toCalculusTypeDetails();
				}
		return new CalculusTypeDetailsEvent(calculusTypeDetails);
		
	}

	@Override
	public CalculusTypeDetailsEvent modifyCalculusType(CalculusTypeModificationEvent event) throws Exception {
		CalculusType calculusType = CalculusType.fromCalculusTypeDetails(event.getCalculusTypeDetails());
		calculusType = calculusTypeRepository.save(calculusType);
		return new CalculusTypeDetailsEvent(calculusType.toCalculusTypeDetails());
	}
	
	@Override
	public CalculusTypePageEvent requestAll() throws Exception {
		List<CalculusType> all = calculusTypeRepository.findAll();
		List<CalculusTypeDetails> list = new ArrayList<CalculusTypeDetails>();
		for (CalculusType calculusType : all) {
			list.add(calculusType.toCalculusTypeDetails());
		}
		return new CalculusTypePageEvent(list);
	}
	
	@Override
	public CalculusTypePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<CalculusType> codes = calculusTypeCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<CalculusTypeDetails> list = new ArrayList<CalculusTypeDetails>();
		for (CalculusType calculusType : codes) {
			list.add(calculusType.toCalculusTypeDetails());
		}
		return new CalculusTypePageEvent(list);
	}

	@Override
	public CalculusTypePageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<CalculusTypeDetails> list = new ArrayList<CalculusTypeDetails>();
		List<CalculusType> descriptions = calculusTypeCustomRepo.findByContainingDescription(description);
		for (CalculusType calculusType : descriptions) {
			list.add(calculusType.toCalculusTypeDetails());
		}
		return new CalculusTypePageEvent(list);
	}

}