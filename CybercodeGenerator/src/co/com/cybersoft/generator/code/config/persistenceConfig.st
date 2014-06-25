package co.com.cybersoft.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.sql.DataSource;

import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionCustomRepo;
import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionCustomRepoImpl;
import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionRepository;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docs.persistence.services.requisition.RequisitionPersistenceServiceImpl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

$imports$

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


@Configuration
@EnableMongoRepositories(basePackages={"co.com.cybersoft.persistence.repository","co.com.cybersoft.docs.persistence.repository"},
includeFilters=@ComponentScan.Filter(value={RequisitionRepository.class,$repos$},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
	}
	
	@Bean
	public DB DB() throws UnknownHostException{
		//Mongo setup. For replica set configurations, it is necessary to supply
		//the seed members to auto-discover the primary instance
		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress(getDBAddress(),27017)));
		return mongoClient.getDB("cybersoft");
	}
	
	@Bean
	public  Mongo mongo() throws UnknownHostException{
		return new Mongo(getDBAddress());
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
	
	@Autowired
	private RequisitionRepository requisitionRepository;
	
	@Bean
	public RequisitionCustomRepo requisitionCustomRepo(){
		return new RequisitionCustomRepoImpl();
	}
	
	$repoFields$
	
	$beans$
	
	@Bean 
	public RequisitionPersistenceService requisitionPersistenceService(){
		return new RequisitionPersistenceServiceImpl(requisitionRepository, requisitionCustomRepo());
	}

}
