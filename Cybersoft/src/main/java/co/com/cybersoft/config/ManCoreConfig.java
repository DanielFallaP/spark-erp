package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.man.services.currency.CurrencyUpdateService;
import co.com.cybersoft.man.services.currency.OpenExchangeUpdateService;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.man.services.excel.ReportingServiceImpl;

@Configuration
public class ManCoreConfig {
	
	@Bean 
	public ReportingService reportingService(){
		return new ReportingServiceImpl();
	}
	
	@Bean
	public CurrencyUpdateService currencyUpdateService() throws Exception{
		return new OpenExchangeUpdateService();
	}
	
}
