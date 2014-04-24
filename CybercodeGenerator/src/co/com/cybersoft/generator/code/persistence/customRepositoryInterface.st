package co.com.cybersoft.persistence.repository.$tableName$;

import java.util.List;

import co.com.cybersoft.persistence.domain.$entityName$;

public interface $entityName$CustomRepo {

	List<$entityName$> findByStartingCodeNumber(String codePrefix);
	
	List<$entityName$> findByContainingDescription(String descriptionSubstring);
}
