package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.ArticleService;
import co.com.cybersoft.core.services.ArticleServiceImpl;
import co.com.cybersoft.persistence.services.ArticlePersistenceService;

@Configuration
public class CoreConfig {

	@Bean 
	public ArticleService articleService(ArticlePersistenceService articlePersistenceService){
		return new ArticleServiceImpl(articlePersistenceService);
	}
}
