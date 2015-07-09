package co.com.cybersoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.core.services.region.RegionServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionRepository;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceServiceImpl;



@Configuration
public class CoreConfig {

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private RegionRepository regionRepository;
	
	@Bean
	public ContinentCustomRepo continentCustomRepo(){
		return new ContinentCustomRepoImpl();
	}
	
	@Bean 
	public ContinentPersistenceService continentPersistenceService(){
		return new ContinentPersistenceServiceImpl(continentRepository, continentCustomRepo());
	}
	
	@Bean
	public ContinentService continentService(ContinentPersistenceService continentPersistenceService){
		return new ContinentServiceImpl(continentPersistenceService);
	}
	
	@Bean
	public RegionCustomRepo regionCustomRepo(){
		return new RegionCustomRepoImpl();
	}
	
	@Bean 
	public RegionPersistenceService regionPersistenceService(){
		return new RegionPersistenceServiceImpl(regionRepository, regionCustomRepo());
	}
	
	@Bean
	public RegionService regionService(RegionPersistenceService regionPersistenceService){
		return new RegionServiceImpl(regionPersistenceService);
	}
}