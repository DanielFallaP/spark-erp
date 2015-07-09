package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ManCoreConfig {
	
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
