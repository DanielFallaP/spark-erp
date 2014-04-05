package co.com.cybersoft.web.domain.items;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $domainClassName$ implements Serializable{

$bodyDomainClass$
}