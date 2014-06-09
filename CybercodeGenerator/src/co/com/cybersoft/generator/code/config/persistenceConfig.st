package co.com.cybersoft.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javax.sql.DataSource;

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

@Configuration
@EnableMongoRepositories(basePackages="co.com.cybersoft.persistence.repository",
includeFilters=@ComponentScan.Filter(value={$repos$},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
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
	
	$repoFields$
	
	$beans$

}
