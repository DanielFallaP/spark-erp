package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import co.com.cybersoft.man.services.currency.CurrencyUpdateService;
import co.com.cybersoft.man.services.currency.OpenExchangeUpdateService;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.man.services.excel.ReportingServiceImpl;
import co.com.cybersoft.man.services.startup.CurrencyStartupService;
import co.com.cybersoft.man.services.startup.CurrencyStartupServiceImpl;
import co.com.cybersoft.man.services.timer.QuartzTimer;
import co.com.cybersoft.man.services.timer.TimerService;

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
	
	@Bean
	public CurrencyStartupService currencyStartupService() throws Exception{
		return new CurrencyStartupServiceImpl();
	}
	
	@Bean
	public TimerService timerService() throws Exception{
		return new QuartzTimer();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource eloadableResourceBundleMessageSource(){
		ReloadableResourceBundleMessageSource eloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		eloadableResourceBundleMessageSource.setBasenames("classpath:messages");
		eloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		eloadableResourceBundleMessageSource.setDefaultEncoding("ISO-8859-1");
		eloadableResourceBundleMessageSource.setCacheSeconds(0);
		return eloadableResourceBundleMessageSource;
	}

}
