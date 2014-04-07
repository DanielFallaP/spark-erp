package co.com.cybersoft.web.domain.measurementUnit;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MeasurementUnitInfo implements Serializable{

private String code;


private String name;


private String description;


public String getCode() {
		return code;	}	

public void setCode(String code) {		
this.code = code;	}
public String getName() {
		return name;	}	

public void setName(String name) {		
this.name = name;	}
public String getDescription() {
		return description;	}	

public void setDescription(String description) {		
this.description = description;	}

}