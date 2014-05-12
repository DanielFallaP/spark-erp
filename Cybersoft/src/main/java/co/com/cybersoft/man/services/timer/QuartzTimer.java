package co.com.cybersoft.man.services.timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.man.services.currency.CurrencyUpdateService;

public class QuartzTimer implements TimerService{
	
	@Autowired
	CurrencyUpdateService currencyUpdateService;

	@PostConstruct
	@Override
	public void executeJobDaily() {
		
	}

}
