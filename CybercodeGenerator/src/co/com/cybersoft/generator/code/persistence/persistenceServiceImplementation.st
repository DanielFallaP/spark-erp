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
import co.com.cybersoft.persistence.repository.$tableName$.$entityName$Repository;
import co.com.cybersoft.persistence.repository.$tableName$.$entityName$CustomRepo;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $entityName$PersistenceServiceImpl implements $entityName$PersistenceService{

	private final $entityName$Repository $tableName$Repository;
	
	private final $entityName$CustomRepo $tableName$CustomRepo;
	
	public $entityName$PersistenceServiceImpl(final $entityName$Repository $tableName$Repository, final $entityName$CustomRepo $tableName$CustomRepo) {
		this.$tableName$Repository=$tableName$Repository;
		this.$tableName$CustomRepo=$tableName$CustomRepo;
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
		$entityName$Details $tableName$Details=null;
		if (event.getId()!=null){
			$entityName$ $tableName$ = $tableName$Repository.findOne(event.getId());
			if ($tableName$!=null)
				$tableName$Details = $tableName$.to$entityName$Details();
		}
		$queryByField$
		return new $entityName$DetailsEvent($tableName$Details);
		
	}

	@Override
	public $entityName$DetailsEvent modify$entityName$($entityName$ModificationEvent event) throws Exception {
		$entityName$ $tableName$ = $entityName$.from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent($tableName$.to$entityName$Details());
	}
	
	@Override
		public $entityName$DetailsEvent getOnlyRecord() throws Exception {
			List<$entityName$> all = $tableName$Repository.findAll();
			$entityName$Details single = new $entityName$Details();
			for ($entityName$ $tableName$ : all) {
				single=$tableName$.to$entityName$Details();
				break;
			}
			return new $entityName$DetailsEvent(single);
		}
	
	$requestAll$
	
	$autocompleteRequest$
	
}
