package co.com.cybersoft.persistence.services.bill;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.events.bill.CreateBillEvent;
import co.com.cybersoft.events.bill.BillDetailsEvent;
import co.com.cybersoft.events.bill.BillPageEvent;
import co.com.cybersoft.events.bill.BillModificationEvent;
import co.com.cybersoft.events.bill.RequestBillDetailsEvent;
import co.com.cybersoft.events.bill.RequestBillPageEvent;
import co.com.cybersoft.persistence.domain.Bill;
import co.com.cybersoft.persistence.repository.bill.BillRepository;
import co.com.cybersoft.persistence.repository.bill.BillCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillPersistenceServiceImpl implements BillPersistenceService{

	private final BillRepository billRepository;
	
	private final BillCustomRepo billCustomRepo;
	
	public BillPersistenceServiceImpl(final BillRepository billRepository, final BillCustomRepo billCustomRepo) {
		this.billRepository=billRepository;
		this.billCustomRepo=billCustomRepo;
	}
	
	@Override
	public BillDetailsEvent createBill(CreateBillEvent event) throws Exception{
		Bill bill = Bill.fromBillDetails(event.getBillDetails());
		bill = billRepository.save(bill);
		return new BillDetailsEvent(bill.toBillDetails());
	}

	@Override
	public BillPageEvent requestBillPage(RequestBillPageEvent event) throws Exception {
		Page<Bill> bills = billRepository.findAll(event.getPageable());
		return new BillPageEvent(bills);
	}

	@Override
	public BillDetailsEvent requestBillDetails(RequestBillDetailsEvent event) throws Exception {
		BillDetails billDetails=null;
		if (event.getId()!=null){
			Bill bill = billRepository.findByCode(event.getId());
			if (bill!=null)
				billDetails = bill.toBillDetails();
		}
		else{
					Bill bill = billRepository.findByDescription(event.getDescription());
					if (bill!=null)
						billDetails = bill.toBillDetails();
				}
		return new BillDetailsEvent(billDetails);
		
	}

	@Override
	public BillDetailsEvent modifyBill(BillModificationEvent event) throws Exception {
		Bill bill = Bill.fromBillDetails(event.getBillDetails());
		bill = billRepository.save(bill);
		return new BillDetailsEvent(bill.toBillDetails());
	}
	
	@Override
	public BillPageEvent requestAll() throws Exception {
		List<Bill> all = billRepository.findAll();
		List<BillDetails> list = new ArrayList<BillDetails>();
		for (Bill bill : all) {
			list.add(bill.toBillDetails());
		}
		return new BillPageEvent(list);
	}
	
	@Override
	public BillPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Bill> codes = billCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<BillDetails> list = new ArrayList<BillDetails>();
		for (Bill bill : codes) {
			list.add(bill.toBillDetails());
		}
		return new BillPageEvent(list);
	}

	@Override
	public BillPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<BillDetails> list = new ArrayList<BillDetails>();
		List<Bill> descriptions = billCustomRepo.findByContainingDescription(description);
		for (Bill bill : descriptions) {
			list.add(bill.toBillDetails());
		}
		return new BillPageEvent(list);
	}

}