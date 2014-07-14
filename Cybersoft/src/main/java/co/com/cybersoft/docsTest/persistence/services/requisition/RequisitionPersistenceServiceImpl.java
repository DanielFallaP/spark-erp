package co.com.cybersoft.docsTest.persistence.services.requisition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.docsTest.persistence.domain.Requisition;
import co.com.cybersoft.docsTest.persistence.domain.RequisitionBody;
import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionCustomRepo;
import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionRepository;
import co.com.cybersoft.docsTest.web.domain.requisition.RequisitionInfo;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionPersistenceServiceImpl implements RequisitionPersistenceService{

	private final RequisitionRepository requisitionRepository;
	
	private final RequisitionCustomRepo requisitionCustomRepo;
	
	public RequisitionPersistenceServiceImpl(final RequisitionRepository requisitionRepository, final RequisitionCustomRepo requisitionCustomRepo) {
		this.requisitionRepository=requisitionRepository;
		this.requisitionCustomRepo=requisitionCustomRepo;
	}
	
	private Requisition updateBody(Requisition requisition, RequisitionBody modified){
		List<RequisitionBody> bodyList = requisition.getRequisitionBodyEntityList();
		for (RequisitionBody requisitionItem : bodyList) {
			if (requisitionItem.getId().equals(modified.getId())){
				BeanUtils.copyProperties(modified, requisitionItem);
			}
		}
		return requisition;
	}

	private Requisition deleteFromBody(Requisition requisition, List<String> toDelete) {
		List<RequisitionBody> entityList = requisition.getRequisitionBodyEntityList();
		List<RequisitionBody> copy = new ArrayList<>(entityList);
		for (RequisitionBody requisitionItem : copy) {
			if (toDelete.contains(requisitionItem.getId())){
				entityList.remove(requisitionItem);
			}
		}
		return requisition;
	}
	
	@Override
	public RequisitionEvent saveRequisition(SaveRequisitionEvent event) throws Exception {
		Requisition requisition=new Requisition().fromRequisitionInfo(event.getRequisitionInfo());
		requisition.setDateOfModification(new Date());
		requisition.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		if (requisition.getId()==null){
			requisition.setDateOfCreation(new Date());
			requisition.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		
		requisition = requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}

	@Override
	public RequisitionEvent createRequisitionBodyRecord(SaveRequisitionEvent event)	throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		RequisitionBody requisitionItem = new RequisitionBody();
		BeanUtils.copyProperties(event.getRequisitionInfo().getCurrentRequisitionBodyInfo(), requisitionItem);
		UUID id = UUID.randomUUID();
		requisitionItem.setId(id.toString());
		if (requisition.getRequisitionBodyEntityList()==null)
			requisition.setRequisitionBodyEntityList(new ArrayList<RequisitionBody>());
		requisition.getRequisitionBodyEntityList().add(requisitionItem);

		requisition.setDateOfModification(new Date());
		requisition.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		requisition = requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}
	
	@Override
	public RequisitionEvent updateRequisitionBody(SaveRequisitionEvent event) throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		RequisitionBody modified=new RequisitionBody();
		BeanUtils.copyProperties(event.getRequisitionInfo().getRequisitionBodyModificationInfo(), modified);
		requisition=updateBody(requisition, modified);
	
		requisition.setDateOfModification(new Date());
		requisition.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		requisition=requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}
	
	@Override
	public RequisitionEvent deleteRequisitionBodyRecords(SaveRequisitionEvent event, List<String> toDelete) throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		requisition = deleteFromBody(requisition, toDelete);
		
		requisition.setDateOfModification(new Date());
		requisition.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisitionCustomRepo.save(requisition)));
	}

	@Override
	public RequisitionPageEvent requestRequisitionPage(RequestRequisitionPageEvent event) throws Exception {
		Page<Requisition> requisitions = requisitionRepository.findAll(event.getPageable());
		return new RequisitionPageEvent(requisitions);
	}

	@Override
	public RequisitionEvent requestRequisitionDetails(RequestRequisitionEvent event) throws Exception {
		RequisitionInfo requisitionInfo=null;
		if (event.getId()!=null){
			Requisition requisition = requisitionRepository.findOne(event.getId());
			if (requisition!=null)
				requisitionInfo = new RequisitionInfo().toRequisitionInfo(requisition);
		}
		
		if (event.getNumericId()!=null){
			Requisition requisition = requisitionRepository.findByNumericId(event.getNumericId());if (requisition!=null)
				requisitionInfo = new RequisitionInfo().toRequisitionInfo(requisition);
		}
		
		return new RequisitionEvent(requisitionInfo);
		
	}

}