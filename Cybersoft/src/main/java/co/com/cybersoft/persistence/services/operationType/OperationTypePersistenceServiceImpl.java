package co.com.cybersoft.persistence.services.operationType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.events.operationType.CreateOperationTypeEvent;
import co.com.cybersoft.events.operationType.OperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.OperationTypePageEvent;
import co.com.cybersoft.events.operationType.OperationTypeModificationEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypeDetailsEvent;
import co.com.cybersoft.events.operationType.RequestOperationTypePageEvent;
import co.com.cybersoft.persistence.domain.OperationType;
import co.com.cybersoft.persistence.repository.OperationTypeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationTypePersistenceServiceImpl implements OperationTypePersistenceService{

	private final OperationTypeRepository operationTypeRepository;
	
	public OperationTypePersistenceServiceImpl(final OperationTypeRepository operationTypeRepository) {
		this.operationTypeRepository=operationTypeRepository;
	}
	
	@Override
	public OperationTypeDetailsEvent createOperationType(CreateOperationTypeEvent event) throws Exception{
		OperationType operationType = OperationType.fromOperationTypeDetails(event.getOperationTypeDetails());
		operationType = operationTypeRepository.save(operationType);
		return new OperationTypeDetailsEvent(operationType.toOperationTypeDetails());
	}

	@Override
	public OperationTypePageEvent requestOperationTypePage(RequestOperationTypePageEvent event) throws Exception {
		Page<OperationType> operationTypes = operationTypeRepository.findAll(event.getPageable());
		return new OperationTypePageEvent(operationTypes);
	}

	@Override
	public OperationTypeDetailsEvent requestOperationTypeDetails(RequestOperationTypeDetailsEvent event) throws Exception {
		OperationType operationType = operationTypeRepository.findByCode(event.getId());
		OperationTypeDetails operationTypeDetails = operationType.toOperationTypeDetails();
		return new OperationTypeDetailsEvent(operationTypeDetails);
	}

	@Override
	public OperationTypeDetailsEvent modifyOperationType(OperationTypeModificationEvent event) throws Exception {
		OperationType operationType = OperationType.fromOperationTypeDetails(event.getOperationTypeDetails());
		operationType = operationTypeRepository.save(operationType);
		return new OperationTypeDetailsEvent(operationType.toOperationTypeDetails());
	}
	
	@Override
	public OperationTypePageEvent requestAll() throws Exception {
		List<OperationType> all = operationTypeRepository.findAll();
		List<OperationTypeDetails> list = new ArrayList<OperationTypeDetails>();
		for (OperationType operationType : all) {
			list.add(operationType.toOperationTypeDetails());
		}
		return new OperationTypePageEvent(list);
	}

}