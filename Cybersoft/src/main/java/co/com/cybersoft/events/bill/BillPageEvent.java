package co.com.cybersoft.events.bill;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Bill;
import co.com.cybersoft.core.domain.BillDetails;
import co.com.cybersoft.persistence.domain.Bill;
import java.util.List;

/**
 * Event class for Bill
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BillPageEvent {
	private Page<Bill> billPage;
	
	private List<BillDetails> billList;



	
	public BillPageEvent(List<BillDetails>  billList){
			this.billList= billList;
	}



	
	public List<BillDetails> getBillList() {
	return billList;
	}



	
	public BillPageEvent(Page<Bill>  billPage){
		this.billPage= billPage;
	}

	public Page<Bill> getBillPage() {
		return billPage;
	}
	
}