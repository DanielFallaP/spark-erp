package co.com.cybersoft.config;


import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import co.com.cybersoft.persistence.repository.items.ItemRepository;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemPersistenceServiceImpl;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.AfeRepository;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;
import co.com.cybersoft.persistence.services.afe.AfePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.PaymentTypeRepository;
import co.com.cybersoft.persistence.services.paymentType.PaymentTypePersistenceService;
import co.com.cybersoft.persistence.services.paymentType.PaymentTypePersistenceServiceImpl;



import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories(basePackages="co.com.cybersoft.persistence.repository",
includeFilters=@ComponentScan.Filter(value={ItemRepository.class,MeasurementUnitRepository.class,AfeRepository.class,PaymentTypeRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
	}
	
	@Bean
	public  Mongo mongo() throws UnknownHostException{
		return new Mongo("localhost");
	}
	
	/**
	 * Item repository
	 */
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;

	@Autowired
	private AfeRepository afeRepository;

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;


		
	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
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
	public ItemPersistenceService itemPersistenceService(){
		return new ItemPersistenceServiceImpl(itemRepository);
	}
	
	@Bean 
	public MeasurementUnitPersistenceService measurementUnitPersistenceService(){
		return new MeasurementUnitPersistenceServiceImpl(measurementUnitRepository);
	}

	@Bean 
	public AfePersistenceService afePersistenceService(){
		return new AfePersistenceServiceImpl(afeRepository);
	}

	@Bean 
	public PaymentTypePersistenceService paymentTypePersistenceService(){
		return new PaymentTypePersistenceServiceImpl(paymentTypeRepository);
	}


	
	 @Bean
     public PlatformTransactionManager txManager() {
         return new DataSourceTransactionManager(dataSource());
     }
}