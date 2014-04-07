package co.com.cybersoft.web.domain.afe;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AfeInfo implements Serializable{

private String code;


private String shortDescription;


public String getCode() {
		return code;	}	

public void setCode(String code) {		
this.code = code;	}
public String getShortDescription() {
		return shortDescription;	}	

public void setShortDescription(String shortDescription) {		
this.shortDescription = shortDescription;	}

}