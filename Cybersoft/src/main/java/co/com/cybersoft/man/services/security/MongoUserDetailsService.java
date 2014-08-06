package co.com.cybersoft.man.services.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.com.cybersoft.tables.persistence.domain.User;

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
