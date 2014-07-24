package co.com.cybersoft.man.services.exchangeRate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateRepository;

public class ExchangeRateManServiceImpl implements ExchangeRateManService{

	@Autowired
	private CustomerTenancyRepository customerTenancyRepository;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepo;
	
	@Autowired
	private MongoOperations mongo;
	
	
	@Override
	public Double getTodayLocalToForeignExchangeRate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		List<CustomerTenancy> tenancyList = customerTenancyRepository.findAll();
		if (!tenancyList.isEmpty()){
			//Check if the rate for today already exists
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			ExchangeRate exchangeR = exchangeRateRepo.findByDate(cal.getTime());
			return exchangeR.getExchangeRate();
		}
		
		return null;
	}

}
