package co.com.cybersoft.persistence.services.$tableName$;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.$entityName$Details;
import co.com.cybersoft.events.$tableName$.Create$entityName$Event;
import co.com.cybersoft.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.events.$tableName$.$entityName$ModificationEvent;
import co.com.cybersoft.events.$tableName$.Request$entityName$DetailsEvent;
import co.com.cybersoft.events.$tableName$.Request$entityName$PageEvent;
import co.com.cybersoft.persistence.domain.$entityName$;
import co.com.cybersoft.persistence.repository.$entityName$Repository;

public class $entityName$PersistenceServiceImpl implements $entityName$PersistenceService{

	private final $entityName$Repository $tableName$Repository;
	
	public $entityName$PersistenceServiceImpl(final $entityName$Repository $tableName$Repository) {
		this.$tableName$Repository=$tableName$Repository;
	}
	
	@Override
	public $entityName$DetailsEvent create$entityName$(Create$entityName$Event event) throws Exception{
		$entityName$ $tableName$ = $entityName$.from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent($tableName$.to$entityName$Details());
	}

	@Override
	public $entityName$PageEvent request$entityName$Page(Request$entityName$PageEvent event) throws Exception {
		Page<$entityName$> $tableName$s = $tableName$Repository.findAll(event.getPageable());
		return new $entityName$PageEvent($tableName$s);
	}

	@Override
	public $entityName$DetailsEvent request$entityName$Details(Request$entityName$DetailsEvent event) throws Exception {
		$entityName$ $tableName$ = $tableName$Repository.findByCode(event.getId());
		$entityName$Details $tableName$Details = $tableName$.to$entityName$Details();
		return new $entityName$DetailsEvent($tableName$Details);
	}

	@Override
	public $entityName$DetailsEvent modify$entityName$($entityName$ModificationEvent event) throws Exception {
		$entityName$ $tableName$ = $entityName$.from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent($tableName$.to$entityName$Details());
	}

}
