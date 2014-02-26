package co.com.cybersoft.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import co.com.cybersoft.persistence.repository.ArticleRepository;
import co.com.cybersoft.persistence.repository.ArticleSQLRepository;
import co.com.cybersoft.persistence.services.ArticlePersistenceService;
import co.com.cybersoft.persistence.services.ArticlePersistenceServiceImpl;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	@Bean
    public DataSource dataSource() {
		// FIXME Cambiar SimpleDriverDataSource para producción
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUsername("daniel");
		dataSource.setPassword("daniel");
		dataSource.setUrl("jdbc:mysql://localhost/cybersoft");
		return dataSource;
    }

	@Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

	@Bean
	public ArticleRepository articleRepository(){
		return new ArticleSQLRepository();
	}
	
	@Bean
	public ArticlePersistenceService articlePersistenceService(){
		return new ArticlePersistenceServiceImpl(articleRepository());
	}
	
	 @Bean
     public PlatformTransactionManager txManager() {
         return new DataSourceTransactionManager(dataSource());
     }
}
