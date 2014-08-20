package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import co.com.cybersoft.docs.persistence.services.startup.SequenceServiceImpl;
import co.com.cybersoft.man.services.currency.CurrencyUpdateService;
import co.com.cybersoft.man.services.currency.OpenExchangeUpdateService;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.man.services.excel.ReportingServiceImpl;
import co.com.cybersoft.man.services.exchangeRate.ExchangeRateManService;
import co.com.cybersoft.man.services.exchangeRate.ExchangeRateManServiceImpl;
import co.com.cybersoft.man.services.item.ItemManService;
import co.com.cybersoft.man.services.item.ItemManServiceImpl;
import co.com.cybersoft.man.services.quotation.QuotationManService;
import co.com.cybersoft.man.services.quotation.QuotationManServiceImpl;
import co.com.cybersoft.man.services.requisition.RequisitionManService;
import co.com.cybersoft.man.services.requisition.RequisitionManServiceImpl;
import co.com.cybersoft.man.services.sequence.SequenceService;
import co.com.cybersoft.man.services.startup.SparkStartupService;
import co.com.cybersoft.man.services.startup.SparkStartupServiceImpl;
import co.com.cybersoft.man.services.tenancy.TenantConfigurationService;
import co.com.cybersoft.man.services.tenancy.TenantConfigurationServiceImpl;
import co.com.cybersoft.man.services.timer.QuartzTimer;
import co.com.cybersoft.man.services.timer.TimerService;

@Configuration
public class ManCoreConfig {
	
	@Bean 
	public ReportingService reportingService() throws Exception{
		return new ReportingServiceImpl();
	}
	
	@Bean
	public ItemManService itemManService() throws Exception{
		return new ItemManServiceImpl();
	}
	
	@Bean
	public CurrencyUpdateService currencyUpdateService() throws Exception{
		return new OpenExchangeUpdateService();
	}
	
	@Bean
	public SparkStartupService currencyStartupService() throws Exception{
		return new SparkStartupServiceImpl();
	}
	
	@Bean
	public TenantConfigurationService tenantConfigurationService() throws Exception{
		return new TenantConfigurationServiceImpl();
	}
	
	@Bean
	public SequenceService sequenceStartupService() throws Exception{
		return new SequenceServiceImpl();
	}
	
	@Bean
	public TimerService timerService() throws Exception{
		return new QuartzTimer();
	}
	
	@Bean
	public ExchangeRateManService exchangeRateManService() throws Exception{
		return new ExchangeRateManServiceImpl();
	}
	
	@Bean
	public RequisitionManService requisitionManService() throws Exception{
		return new RequisitionManServiceImpl();
	}
	
	@Bean
	public QuotationManService quotationManService() throws Exception{
		return new QuotationManServiceImpl();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource eloadableResourceBundleMessageSource(){
		ReloadableResourceBundleMessageSource eloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		eloadableResourceBundleMessageSource.setBasenames("classpath:messages");
		eloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
		eloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		eloadableResourceBundleMessageSource.setCacheSeconds(0);
		return eloadableResourceBundleMessageSource;
	}

}
