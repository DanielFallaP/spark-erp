package co.com.cybersoft.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.util.CyberUtils;

public class SQLUserDetailsService implements UserDetailsService{

//	private MongoOperations mongo;
//
	public SQLUserDetailsService(LocalContainerEntityManagerFactoryBean emFactory){
		this.emFactory=emFactory;
	}
	
	private LocalContainerEntityManagerFactoryBean emFactory;
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
			try {
				String queryString="SELECT p FROM Users p WHERE p.login='"+userName+"'";
				Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
				List resultList = query.getResultList();

				if (!resultList.isEmpty()){
					
					Users user = (Users) resultList.get(0);
					org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),true,true,true,true,getAuthorities("ROLE_"+user.getRole().toUpperCase(),user.getCompany()));
					
					//Session id creation for the user
					CyberUtils.userSessions.put(userName, UUID.randomUUID().toString());
//					SessionAction logonAction = new SessionAction(userName,CyberUtils.userSessions.get(userName),"logon",new Date(),null,new Long(1));
//					logonAction.setEntityId(null);
//					
//					new Thread(new SessionLogger(logonAction,mongo)).start();
					
					return userDetail;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public List<GrantedAuthority> getAuthorities(String role, Company company){
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SparkGrantedAuthority(role, company));
		return authorities;
	}

}
