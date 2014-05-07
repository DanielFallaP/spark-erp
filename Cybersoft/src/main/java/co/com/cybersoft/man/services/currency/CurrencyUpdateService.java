package co.com.cybersoft.man.services.currency;

import java.util.Date;

public interface CurrencyUpdateService {

	void updateTodayRates() throws Exception;
	
	void updatePeriodRates(Date from, Date to) throws Exception;
}
