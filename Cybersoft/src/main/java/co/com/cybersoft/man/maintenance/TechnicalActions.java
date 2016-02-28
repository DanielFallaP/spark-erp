package co.com.cybersoft.man.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

public class TechnicalActions{
	
	@Autowired
	private ExchangeRateRepository repo;
	
	@Autowired
	private ExchangeRateService exchangeRate;
	
	public void transactionA(ExchangeRateFilterInfo filter){
		System.out.println("transactionA");
	}
	
	public void transactionC(ExchangeRateFilterInfo filter){
		System.out.println("transaction C");
	}
	
	public void transactionB(ExchangeRateFilterInfo filter){
		System.out.println("transactionA");
		PageRequest pageRequest = new PageRequest(0, 100, Direction.DESC,"id");
		RequestExchangeRatePageEvent pageEvent=new RequestExchangeRatePageEvent(pageRequest,filter);			

		try {
			ExchangeRatePageEvent requestExchangeRateFilter = exchangeRate.requestExchangeRateFilter(pageEvent);
			requestExchangeRateFilter.getAllList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
