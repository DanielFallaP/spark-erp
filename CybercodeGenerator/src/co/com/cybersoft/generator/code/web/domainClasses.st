package co.com.cybersoft.web.domain.$tableName$;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Cybersoft 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $domainClassName$ implements Serializable{

	$bodyDomainClass$
}