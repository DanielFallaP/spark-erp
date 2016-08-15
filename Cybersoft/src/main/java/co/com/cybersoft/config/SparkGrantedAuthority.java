package co.com.cybersoft.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import co.com.cybersoft.maintenance.tables.persistence.domain.Company;



public class SparkGrantedAuthority implements GrantedAuthority{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5634035129804693325L;
	private final String role;
	private final Company company;

    public SparkGrantedAuthority(String role, Company company) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
        this.company = company;
    }

    public String getAuthority() {
        return role;
    }
    
    public Company getCompany() {
		return company;
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

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }

}
