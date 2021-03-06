package co.com.cybersoft.man.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import co.com.cybersoft.config.SparkGrantedAuthority;
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;

public class SparkAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	
	 private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	    public void onAuthenticationSuccess(HttpServletRequest request, 
	      HttpServletResponse response, Authentication authentication) throws IOException {
	    	 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		        for (GrantedAuthority grantedAuthority : authorities) {
		            if (grantedAuthority instanceof SparkGrantedAuthority){
		            	Users user = ((SparkGrantedAuthority)grantedAuthority).getUser();
		            	UsersDetails details=new UsersDetails();
		            	details = details.toUsersDetails(user);
		            	request.getSession().setAttribute("_loggedInUser", details);
		            }
		        }
	        handle(request, response, authentication);
	        clearAuthenticationAttributes(request);
	    }
	 
	    protected void handle(HttpServletRequest request, 
	      HttpServletResponse response, Authentication authentication) throws IOException {
	        String targetUrl = determineTargetUrl(authentication);
	 
	        if (response.isCommitted()) {
	            return;
	        }
	 
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }
	 
	    /** Builds the target URL according to the logic defined in the main class. */
	    protected String determineTargetUrl(Authentication authentication) {
	        boolean isUser = false;
	        boolean isAdmin = false;
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        for (GrantedAuthority grantedAuthority : authorities) {
	            if (grantedAuthority.getAuthority().equals("ROLE_DOCUMENT MANAGER")) {
	                isUser = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_USER")) {
	                isAdmin = true;
	                break;
	            }
	        }
	 
	        if (isUser) {
	            return "/";
	        } else if (isAdmin) {
	            return "/";
	        } else {
	            throw new IllegalStateException();
	        }
	    }
	 
	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return;
	        }
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
}
