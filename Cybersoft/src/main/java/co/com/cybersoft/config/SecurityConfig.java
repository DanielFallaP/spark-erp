package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("raul").password("raul").roles("USER");
		auth.inMemoryAuthentication().withUser("daniel").password("daniel").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
//		LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthentication = auth.ldapAuthentication();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
        .authorizeUrls()
            .anyRequest().hasRole("USER")
            .and()
       .formLogin()
            .loginPage("/login").permitAll().and().logout().permitAll()
            .and()
       .sessionManagement()
            .maximumSessions(1)
            .expiredUrl("/login");

	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
