package co.com.cybersoft.docs.persistence.services.requisition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import co.com.cybersoft.docs.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docs.events.requisition.RequestRequisitionPageEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionModificationEvent;
import co.com.cybersoft.docs.events.requisition.RequisitionPageEvent;
import co.com.cybersoft.docs.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.docs.persistence.domain.Requisition;
import co.com.cybersoft.docs.persistence.domain.RequisitionItem;
import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionCustomRepo;
import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionRepository;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionItemInfo;
import co.com.cybersoft.util.EmbeddedField;

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
	
	private Requisition updateBody(Requisition requisition, RequisitionItem modified){
		List<RequisitionItem> bodyList = requisition.getRequisitionItemEntityList();
		for (RequisitionItem requisitionItem : bodyList) {
			if (requisitionItem.getId().equals(modified.getId())){
				BeanUtils.copyProperties(modified, requisitionItem);
			}
		}
		return requisition;
	}

	private Requisition deleteFromBody(Requisition requisition, List<String> toDelete) {
		List<RequisitionItem> entityList = requisition.getRequisitionItemEntityList();
		List<RequisitionItem> copy = new ArrayList<>(entityList);
		for (RequisitionItem requisitionItem : copy) {
			if (toDelete.contains(requisitionItem.getId())){
				entityList.remove(requisitionItem);
			}
		}
		return requisition;
	}
	
	@Override
	public RequisitionEvent saveRequisition(SaveRequisitionEvent event) throws Exception {
		Requisition requisition=new Requisition().fromRequisitionInfo(event.getRequisitionInfo());
		requisition = requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}

	@Override
	public RequisitionEvent saveRequisitionBody(SaveRequisitionEvent event)	throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		RequisitionItem requisitionItem = new RequisitionItem();
		BeanUtils.copyProperties(event.getRequisitionInfo().getCurrentRequisitionItemInfo(), requisitionItem);
		UUID id = UUID.randomUUID();
		requisitionItem.setId(id.toString());
		if (requisition.getRequisitionItemEntityList()==null)
			requisition.setRequisitionItemEntityList(new ArrayList<RequisitionItem>());
		requisition.getRequisitionItemEntityList().add(requisitionItem);
		requisition = requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}
	
	@Override
	public RequisitionEvent updateRequisitionBody(SaveRequisitionEvent event) throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		RequisitionItem modified=new RequisitionItem();
		BeanUtils.copyProperties(event.getRequisitionInfo().getRequisitionItemModificationInfo(), modified);
		requisition=updateBody(requisition, modified);
		requisition=requisitionCustomRepo.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}
	
	@Override
	public RequisitionEvent deleteRequisition(SaveRequisitionEvent event, List<String> toDelete) throws Exception {
		Requisition requisition = requisitionRepository.findByNumericId(event.getRequisitionInfo().getNumericId());
		requisition = deleteFromBody(requisition, toDelete);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisitionCustomRepo.save(requisition)));
	}


	@Override
	public RequisitionEvent createRequisition(SaveRequisitionEvent event) throws Exception{
		Requisition requisition = new Requisition().fromRequisitionInfo(event.getRequisitionInfo());
		requisition = requisitionRepository.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
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
		return new RequisitionEvent(requisitionInfo);
		
	}

	@Override
	public RequisitionEvent modifyRequisition(RequisitionModificationEvent event) throws Exception {
		Requisition requisition = new Requisition().fromRequisitionInfo(event.getRequisition());
		requisition = requisitionRepository.save(requisition);
		return new RequisitionEvent(new RequisitionInfo().toRequisitionInfo(requisition));
	}
	
	@Override
		public RequisitionPageEvent requestAllByPriority(EmbeddedField... fields) throws Exception {
			List<Requisition> all = requisitionCustomRepo.findAllActiveByPriority(fields);
			List<RequisitionInfo> list = new ArrayList<RequisitionInfo>();
			for (Requisition requisition : all) {
				list.add(new RequisitionInfo().toRequisitionInfo(requisition));
			}
			return new RequisitionPageEvent(list);
		}@Override
		public RequisitionPageEvent requestAllByRequestingDepartment(EmbeddedField... fields) throws Exception {
			List<Requisition> all = requisitionCustomRepo.findAllActiveByRequestingDepartment(fields);
			List<RequisitionInfo> list = new ArrayList<RequisitionInfo>();
			for (Requisition requisition : all) {
				list.add(new RequisitionInfo().toRequisitionInfo(requisition));
			}
			return new RequisitionPageEvent(list);
		}@Override
		public RequisitionPageEvent requestAllByExpenseType(EmbeddedField... fields) throws Exception {
			List<Requisition> all = requisitionCustomRepo.findAllActiveByExpenseType(fields);
			List<RequisitionInfo> list = new ArrayList<RequisitionInfo>();
			for (Requisition requisition : all) {
				list.add(new RequisitionInfo().toRequisitionInfo(requisition));
			}
			return new RequisitionPageEvent(list);
		}@Override
		public RequisitionPageEvent requestAllByTransportationType(EmbeddedField... fields) throws Exception {
			List<Requisition> all = requisitionCustomRepo.findAllActiveByTransportationType(fields);
			List<RequisitionInfo> list = new ArrayList<RequisitionInfo>();
			for (Requisition requisition : all) {
				list.add(new RequisitionInfo().toRequisitionInfo(requisition));
			}
			return new RequisitionPageEvent(list);
		}@Override
		public RequisitionPageEvent requestAllByReceivingWarehouse(EmbeddedField... fields) throws Exception {
			List<Requisition> all = requisitionCustomRepo.findAllActiveByReceivingWarehouse(fields);
			List<RequisitionInfo> list = new ArrayList<RequisitionInfo>();
			for (Requisition requisition : all) {
				list.add(new RequisitionInfo().toRequisitionInfo(requisition));
			}
			return new RequisitionPageEvent(list);
		}



}