package co.com.cybersoft.config;


import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import co.com.cybersoft.persistence.repository.continent.ContinentCustomRepo;
import co.com.cybersoft.persistence.repository.continent.ContinentCustomRepoImpl;
import co.com.cybersoft.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.persistence.repository.country.CountryCustomRepo;
import co.com.cybersoft.persistence.repository.country.CountryCustomRepoImpl;
import co.com.cybersoft.persistence.repository.country.CountryRepository;
import co.com.cybersoft.persistence.repository.currency.CurrencyCustomRepo;
import co.com.cybersoft.persistence.repository.currency.CurrencyCustomRepoImpl;
import co.com.cybersoft.persistence.repository.currency.CurrencyRepository;
import co.com.cybersoft.persistence.repository.customerTenancy.CustomerTenancyCustomRepo;
import co.com.cybersoft.persistence.repository.customerTenancy.CustomerTenancyCustomRepoImpl;
import co.com.cybersoft.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.persistence.repository.exchangeRate.ExchangeRateCustomRepo;
import co.com.cybersoft.persistence.repository.exchangeRate.ExchangeRateCustomRepoImpl;
import co.com.cybersoft.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.persistence.repository.language.LanguageCustomRepo;
import co.com.cybersoft.persistence.repository.language.LanguageCustomRepoImpl;
import co.com.cybersoft.persistence.repository.language.LanguageRepository;
import co.com.cybersoft.persistence.repository.populatedPlace.PopulatedPlaceCustomRepo;
import co.com.cybersoft.persistence.repository.populatedPlace.PopulatedPlaceCustomRepoImpl;
import co.com.cybersoft.persistence.repository.populatedPlace.PopulatedPlaceRepository;
import co.com.cybersoft.persistence.repository.region.RegionCustomRepo;
import co.com.cybersoft.persistence.repository.region.RegionCustomRepoImpl;
import co.com.cybersoft.persistence.repository.region.RegionRepository;
import co.com.cybersoft.persistence.repository.state.StateCustomRepo;
import co.com.cybersoft.persistence.repository.state.StateCustomRepoImpl;
import co.com.cybersoft.persistence.repository.state.StateRepository;
import co.com.cybersoft.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.persistence.services.continent.ContinentPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.country.CountryPersistenceService;
import co.com.cybersoft.persistence.services.country.CountryPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.currency.CurrencyPersistenceService;
import co.com.cybersoft.persistence.services.currency.CurrencyPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.customerTenancy.CustomerTenancyPersistenceService;
import co.com.cybersoft.persistence.services.customerTenancy.CustomerTenancyPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.exchangeRate.ExchangeRatePersistenceService;
import co.com.cybersoft.persistence.services.exchangeRate.ExchangeRatePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.language.LanguagePersistenceService;
import co.com.cybersoft.persistence.services.language.LanguagePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.populatedPlace.PopulatedPlacePersistenceService;
import co.com.cybersoft.persistence.services.populatedPlace.PopulatedPlacePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.region.RegionPersistenceService;
import co.com.cybersoft.persistence.services.region.RegionPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.state.StatePersistenceService;
import co.com.cybersoft.persistence.services.state.StatePersistenceServiceImpl;

import com.mongodb.Mongo;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableMongoRepositories(basePackages="co.com.cybersoft.persistence.repository",
includeFilters=@ComponentScan.Filter(value={LanguageRepository.class,CurrencyRepository.class,ContinentRepository.class,RegionRepository.class,CountryRepository.class,StateRepository.class,PopulatedPlaceRepository.class,ExchangeRateRepository.class,CustomerTenancyRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
	}
	
	@Bean
	public  Mongo mongo() throws UnknownHostException{
//		return new Mongo("54.86.51.49");
		return new Mongo("localhost");
	}
	
	@Autowired
	private LanguageRepository languageRepository;

	@Bean
	public LanguageCustomRepo languageCustomRepo(){
		return new LanguageCustomRepoImpl();
	}

	@Autowired
	private CurrencyRepository currencyRepository;

	@Bean
	public CurrencyCustomRepo currencyCustomRepo(){
		return new CurrencyCustomRepoImpl();
	}

	@Autowired
	private ContinentRepository continentRepository;

	@Bean
	public ContinentCustomRepo continentCustomRepo(){
		return new ContinentCustomRepoImpl();
	}

	@Autowired
	private RegionRepository regionRepository;

	@Bean
	public RegionCustomRepo regionCustomRepo(){
		return new RegionCustomRepoImpl();
	}

	@Autowired
	private CountryRepository countryRepository;

	@Bean
	public CountryCustomRepo countryCustomRepo(){
		return new CountryCustomRepoImpl();
	}

	@Autowired
	private StateRepository stateRepository;

	@Bean
	public StateCustomRepo stateCustomRepo(){
		return new StateCustomRepoImpl();
	}

	@Autowired
	private PopulatedPlaceRepository populatedPlaceRepository;

	@Bean
	public PopulatedPlaceCustomRepo populatedPlaceCustomRepo(){
		return new PopulatedPlaceCustomRepoImpl();
	}

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Bean
	public ExchangeRateCustomRepo exchangeRateCustomRepo(){
		return new ExchangeRateCustomRepoImpl();
	}

	@Autowired
	private CustomerTenancyRepository customerTenancyRepository;

	@Bean
	public CustomerTenancyCustomRepo customerTenancyCustomRepo(){
		return new CustomerTenancyCustomRepoImpl();
	}


	
//	@Bean
//    public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUsername("daniel");
//		dataSource.setPassword("daniel");
//		dataSource.setUrl("jdbc:mysql://localhost/cybersoft");
//		return dataSource;
//    }
//
//	@Bean
//    JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        return jdbcTemplate;
//    }
	
	@Bean 
	public LanguagePersistenceService languagePersistenceService(){
		return new LanguagePersistenceServiceImpl(languageRepository, languageCustomRepo());
	}

	@Bean 
	public CurrencyPersistenceService currencyPersistenceService(){
		return new CurrencyPersistenceServiceImpl(currencyRepository, currencyCustomRepo());
	}

	@Bean 
	public ContinentPersistenceService continentPersistenceService(){
		return new ContinentPersistenceServiceImpl(continentRepository, continentCustomRepo());
	}

	@Bean 
	public RegionPersistenceService regionPersistenceService(){
		return new RegionPersistenceServiceImpl(regionRepository, regionCustomRepo());
	}

	@Bean 
	public CountryPersistenceService countryPersistenceService(){
		return new CountryPersistenceServiceImpl(countryRepository, countryCustomRepo());
	}

	@Bean 
	public StatePersistenceService statePersistenceService(){
		return new StatePersistenceServiceImpl(stateRepository, stateCustomRepo());
	}

	@Bean 
	public PopulatedPlacePersistenceService populatedPlacePersistenceService(){
		return new PopulatedPlacePersistenceServiceImpl(populatedPlaceRepository, populatedPlaceCustomRepo());
	}

	@Bean 
	public ExchangeRatePersistenceService exchangeRatePersistenceService(){
		return new ExchangeRatePersistenceServiceImpl(exchangeRateRepository, exchangeRateCustomRepo());
	}

	@Bean 
	public CustomerTenancyPersistenceService customerTenancyPersistenceService(){
		return new CustomerTenancyPersistenceServiceImpl(customerTenancyRepository, customerTenancyCustomRepo());
	}


	
//	 @Bean
//     public PlatformTransactionManager txManager() {
//         return new DataSourceTransactionManager(dataSource());
//     }
}