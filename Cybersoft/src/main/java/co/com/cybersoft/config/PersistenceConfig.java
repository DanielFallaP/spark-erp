package co.com.cybersoft.config;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionRepository;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceServiceImpl;


@Configuration
@EnableJpaRepositories(basePackages={"co.com.cybersoft.purchase.tables.persistence.repository"},
includeFilters=@ComponentScan.Filter(value={ContinentRepository.class, RegionRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "co.com.cybersoft" });
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		
		return em;
	} 
//	@Bean
//	  public EntityManagerFactory entityManagerFactory() {
//
//	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	    vendorAdapter.setGenerateDdl(true);
//
//	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//	    factory.setJpaVendorAdapter(vendorAdapter);
//	    factory.setPackagesToScan("co.com.cybersoft");
//	    factory.setDataSource(dataSource());
//	    factory.afterPropertiesSet();
//
//	    return factory.getObject();
//	  }
	
	@Bean
    public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("SYSTEM");
		dataSource.setPassword("8thisbu6");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		return dataSource;
    }

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
	private String getDBAddress(){
		InputStream stream = getClass().getClassLoader().getResourceAsStream("db.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			DBConfig dbConfig = (DBConfig) mapper.readValue(new InputStreamReader(stream, "UTF8"), DBConfig.class);
			return dbConfig.getAddress();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

	
	Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", "update");
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	      return properties;
   }
	
	@Bean
    public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}