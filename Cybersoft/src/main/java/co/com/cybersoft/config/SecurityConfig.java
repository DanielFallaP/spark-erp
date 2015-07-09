package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/**").hasRole("ADMIN").antMatchers("/resources/js/jquery-1.11.0.min.js","/resources/css/bootstrap-responsive.min.css", "/resources/css/bootstrap.min.css", "/resources/img/spark_large.png").permitAll().anyRequest().authenticated()
        .and()
   .formLogin()
        .loginPage("/login").permitAll().and().logout().permitAll()
        .and()
   .sessionManagement()
        .maximumSessions(1)
        .expiredUrl("/login");;
//		http.authorizeUrls().antMatchers("/welcome").hasRole("USER").anyRequest().anonymous();
//		http.authorizeUrls().antMatchers("/configuration").hasRole("USER").anyRequest().anonymous();
      //FIXME
      http.csrf().disable();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
