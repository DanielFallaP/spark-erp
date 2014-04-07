package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import co.com.cybersoft.core.domain.$coreDomainClass$;

@Document(collection="$variableName$")
public class $entityName$ {

	@Id
	private String id;
	
	$fieldDeclaration$
	private Date dateOfCreation;
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	$gettersAndSetters$
	
	public static Item from$coreDomainClass$($coreDomainClass$ details){
		$entityName$ $variableName$ = new $entityName$();
		BeanUtils.copyProperties(details, $variableName$);
		return $variableName$;
	}
	
	public $coreDomainClass$ to$coreDomainClass$(){
		$coreDomainClass$ details = new $coreDomainClass$();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}
