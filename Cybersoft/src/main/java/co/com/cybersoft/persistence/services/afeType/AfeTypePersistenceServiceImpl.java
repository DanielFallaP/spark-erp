package co.com.cybersoft.persistence.services.afeType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.events.afeType.CreateAfeTypeEvent;
import co.com.cybersoft.events.afeType.AfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.events.afeType.AfeTypeModificationEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypePageEvent;
import co.com.cybersoft.persistence.domain.AfeType;
import co.com.cybersoft.persistence.repository.AfeTypeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeTypePersistenceServiceImpl implements AfeTypePersistenceService{

	private final AfeTypeRepository afeTypeRepository;
	
	public AfeTypePersistenceServiceImpl(final AfeTypeRepository afeTypeRepository) {
		this.afeTypeRepository=afeTypeRepository;
	}
	
	@Override
	public AfeTypeDetailsEvent createAfeType(CreateAfeTypeEvent event) throws Exception{
		AfeType afeType = AfeType.fromAfeTypeDetails(event.getAfeTypeDetails());
		afeType = afeTypeRepository.save(afeType);
		return new AfeTypeDetailsEvent(afeType.toAfeTypeDetails());
	}

	@Override
	public AfeTypePageEvent requestAfeTypePage(RequestAfeTypePageEvent event) throws Exception {
		Page<AfeType> afeTypes = afeTypeRepository.findAll(event.getPageable());
		return new AfeTypePageEvent(afeTypes);
	}

	@Override
	public AfeTypeDetailsEvent requestAfeTypeDetails(RequestAfeTypeDetailsEvent event) throws Exception {
		AfeType afeType = afeTypeRepository.findByCode(event.getId());
		AfeTypeDetails afeTypeDetails = afeType.toAfeTypeDetails();
		return new AfeTypeDetailsEvent(afeTypeDetails);
	}

	@Override
	public AfeTypeDetailsEvent modifyAfeType(AfeTypeModificationEvent event) throws Exception {
		AfeType afeType = AfeType.fromAfeTypeDetails(event.getAfeTypeDetails());
		afeType = afeTypeRepository.save(afeType);
		return new AfeTypeDetailsEvent(afeType.toAfeTypeDetails());
	}
	
	@Override
	public AfeTypePageEvent requestAll() throws Exception {
		List<AfeType> all = afeTypeRepository.findAll();
		List<AfeTypeDetails> list = new ArrayList<AfeTypeDetails>();
		for (AfeType afeType : all) {
			list.add(afeType.toAfeTypeDetails());
		}
		return new AfeTypePageEvent(list);
	}

}