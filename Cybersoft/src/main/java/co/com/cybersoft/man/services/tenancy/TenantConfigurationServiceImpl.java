package co.com.cybersoft.man.services.tenancy;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.tables.persistence.domain.BusinessRules;
import co.com.cybersoft.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesRepository;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;

import com.mongodb.DBCollection;

public class TenantConfigurationServiceImpl implements TenantConfigurationService{

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private BusinessRulesRepository businessRulesRepository;
	
	@Autowired
	private CustomerTenancyRepository customerTenancyRepository;
	
	@Override
	public void updateTenantConfig() throws Exception{
		updateSingletonTableBusinessRules("businessRuless", BusinessRules.class);
		updateSingletonTableCustomerTenancy("customerTenancy", CustomerTenancy.class);
	}
	
	private void updateSingletonTableCustomerTenancy(String tableName, Class<?> clazz) throws Exception{
		DBCollection collection = mongo.getCollection(tableName);
		if (collection.getCount()==0){
			String fileName=tableName+".json";
			InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (stream!=null){
				ObjectMapper mapper = new ObjectMapper();
				CustomerTenancy customerTenancy = (CustomerTenancy) mapper.readValue(new InputStreamReader(stream, "UTF8"), clazz);
				customerTenancyRepository.save(customerTenancy);
			}
		}
	}
	
	private void updateSingletonTableBusinessRules(String tableName, Class<?> clazz) throws Exception{
		DBCollection collection = mongo.getCollection(tableName);
		if (collection.getCount()==0){
			String fileName=tableName+".json";
			InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (stream!=null){
				ObjectMapper mapper = new ObjectMapper();
				BusinessRules businessRules = (BusinessRules) mapper.readValue(new InputStreamReader(stream, "UTF8"), clazz);
				businessRulesRepository.save(businessRules);
			}
		}
	}

}
