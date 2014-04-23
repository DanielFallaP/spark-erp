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
import co.com.cybersoft.persistence.repository.BillRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillPersistenceServiceImpl implements BillPersistenceService{

	private final BillRepository billRepository;
	
	public BillPersistenceServiceImpl(final BillRepository billRepository) {
		this.billRepository=billRepository;
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
		Bill bill = billRepository.findByCode(event.getId());
		BillDetails billDetails = bill.toBillDetails();
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

}