package co.com.cybersoft.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;



public class SparkGrantedAuthority implements GrantedAuthority{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5634035129804693325L;
	private final String role;
	private final Users user;

    public SparkGrantedAuthority(String role, Users user) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
        this.user = user;
    }

    public String getAuthority() {
        return role;
    }
    

    
    
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SparkGrantedAuthority) {
            return role.equals(((SparkGrantedAuthority) obj).role);
        }

        return false;
    }

    public Users getUser() {
		return user;
	}

	public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }

}
