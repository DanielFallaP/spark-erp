package co.com.cybersoft.config;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import co.com.cybersoft.man.services.security.MongoUserDetailsService;
import co.com.cybersoft.util.CyberUtils;

import com.mongodb.Mongo;

@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(new MongoUserDetailsService(new MongoTemplate(new Mongo(getDBAddress()),CyberUtils.dataBaseName)));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/configuration/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
			.antMatchers("/settings/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
			.antMatchers("/docs/**").access("hasRole('ROLE_DOCUMENT MANAGER') or hasRole('ROLE_SUPER')")
			.antMatchers("/resources/js/jquery-1.11.0.min.js","/resources/css/bootstrap-responsive.min.css", "/resources/css/bootstrap.min.css", "/resources/img/spark_large.png").permitAll()
            .anyRequest().authenticated()
            .and()
       .formLogin()
            .loginPage("/login").permitAll().and().logout().permitAll()
            .and()
       .sessionManagement()
            .maximumSessions(1)
            .expiredUrl("/login");
		
		//FIXME
		http.csrf().disable();
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
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
}
