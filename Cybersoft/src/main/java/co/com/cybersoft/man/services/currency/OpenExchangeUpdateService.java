package co.com.cybersoft.man.services.currency;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;

public class OpenExchangeUpdateService implements CurrencyUpdateService{

	
	@Autowired
	private CustomerTenancyRepository tenancyRepo;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepo;
	
	@Override
	public void updateTodayRates() throws Exception{
		updateRatesForDay(new Date());
	}
	
	@Override
	public void updatePeriodRates(Date from, Date to) throws Exception{
		GregorianCalendar init = new GregorianCalendar();
		init.setTime(from);
		init.set(Calendar.HOUR_OF_DAY, 0);
		init.set(Calendar.MINUTE, 0);
		init.set(Calendar.SECOND, 0);
		init.set(Calendar.MILLISECOND, 0);
		
		GregorianCalendar finale = new GregorianCalendar();
		finale.setTime(to);
		finale.set(Calendar.HOUR_OF_DAY, 0);
		finale.set(Calendar.MINUTE, 0);
		finale.set(Calendar.SECOND, 0);
		finale.set(Calendar.MILLISECOND, 0);
		
		
		
		int days = Days.daysBetween(new DateTime(new Date(init.getTimeInMillis())), new DateTime(new Date(finale.getTimeInMillis()))).getDays();
		if (days>30)
			days=30;
		
		//Update the rates for the past 30 days or less
		for (int i = 0; i < days; i++) {
			init.add(Calendar.DATE, 1);
			updateRatesForDay(init.getTime());
		}
	}
	
	private void updateRatesForDay(Date date) throws Exception{
		new Thread(new UpdateTodaysRate(date, tenancyRepo, exchangeRateRepo)).run();
	}

}
