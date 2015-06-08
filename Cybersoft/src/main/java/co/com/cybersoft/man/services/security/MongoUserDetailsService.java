package co.com.cybersoft.man.services.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.com.cybersoft.purchase.tables.persistence.domain.User;
import co.com.cybersoft.util.CyberUtils;

public class MongoUserDetailsService implements UserDetailsService{
	
	private MongoOperations mongo;

	public MongoUserDetailsService(MongoOperations mongo){
		this.mongo=mongo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
			try {
				User user = mongo.findOne(new Query(Criteria.where("user").is(userName)), User.class);
				org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUser(),user.getPassword(),true,true,true,true,getAuthorities("ROLE_"+user.getRole().toUpperCase()));
				
				//Session id creation for the user
				CyberUtils.userSessions.put(userName, UUID.randomUUID().toString());
				SessionAction logonAction = new SessionAction(userName,CyberUtils.userSessions.get(userName),"logon",new Date(),null,new Long(1));
				logonAction.setEntityId(null);
				new Thread(new SessionLogger(logonAction,mongo)).start();
				
				return userDetail;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public List<GrantedAuthority> getAuthorities(String role){
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

}
