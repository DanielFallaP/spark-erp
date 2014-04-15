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
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;
import co.com.cybersoft.persistence.repository.ArticuloRepository;
import co.com.cybersoft.persistence.services.articulo.ArticuloPersistenceService;
import co.com.cybersoft.persistence.services.articulo.ArticuloPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.GrupoRepository;
import co.com.cybersoft.persistence.services.grupo.GrupoPersistenceService;
import co.com.cybersoft.persistence.services.grupo.GrupoPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.UnidadMedidaRepository;
import co.com.cybersoft.persistence.services.unidadMedida.UnidadMedidaPersistenceService;
import co.com.cybersoft.persistence.services.unidadMedida.UnidadMedidaPersistenceServiceImpl;



import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories(basePackages="co.com.cybersoft.persistence.repository",
includeFilters=@ComponentScan.Filter(value={ItemRepository.class, MeasurementUnitRepository.class,ArticuloRepository.class,GrupoRepository.class,UnidadMedidaRepository.class},type=FilterType.ASSIGNABLE_TYPE))
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
	private ArticuloRepository articuloRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;


		
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
	public ArticuloPersistenceService articuloPersistenceService(){
		return new ArticuloPersistenceServiceImpl(articuloRepository);
	}

	@Bean 
	public GrupoPersistenceService grupoPersistenceService(){
		return new GrupoPersistenceServiceImpl(grupoRepository);
	}

	@Bean 
	public UnidadMedidaPersistenceService unidadMedidaPersistenceService(){
		return new UnidadMedidaPersistenceServiceImpl(unidadMedidaRepository);
	}


	
	 @Bean
     public PlatformTransactionManager txManager() {
         return new DataSourceTransactionManager(dataSource());
     }
}