package co.com.cybersoft.persistence.repository.$tableName$;

import java.util.List;

import co.com.cybersoft.persistence.domain.$entityName$;

public interface $entityName$CustomRepo {

	List<$entityName$> findByContainingDescription(String descriptionSubstring);

	List<$entityName$> findAllActive();
}
