package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.man.services.ReportingService;
import co.com.cybersoft.man.services.ReportingServiceImpl;

@Configuration
public class ManCoreConfig {
	
	@Bean 
	public ReportingService reportingService(){
		return new ReportingServiceImpl();
	}
	
}
