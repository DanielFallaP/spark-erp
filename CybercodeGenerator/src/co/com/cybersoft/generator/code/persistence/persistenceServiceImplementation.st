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
			$entityName$ $tableName$ = $tableName$Repository.findByCode(event.getId());
			if ($tableName$!=null)
				$tableName$Details = $tableName$.to$entityName$Details();
		}
		$description$
		return new $entityName$DetailsEvent($tableName$Details);
		
	}

	@Override
	public $entityName$DetailsEvent modify$entityName$($entityName$ModificationEvent event) throws Exception {
		$entityName$ $tableName$ = $entityName$.from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent($tableName$.to$entityName$Details());
	}
	
	@Override
	public $entityName$PageEvent requestAll() throws Exception {
		List<$entityName$> all = $tableName$$repo$.findAll$active$();
		List<$entityName$Details> list = new ArrayList<$entityName$Details>();
		for ($entityName$ $tableName$ : all) {
			list.add($tableName$.to$entityName$Details());
		}
		return new $entityName$PageEvent(list);
	}
	
	@Override
	public $entityName$PageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<$entityName$> codes = $tableName$CustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<$entityName$Details> list = new ArrayList<$entityName$Details>();
		for ($entityName$ $tableName$ : codes) {
			list.add($tableName$.to$entityName$Details());
		}
		return new $entityName$PageEvent(list);
	}

	@Override
	public $entityName$PageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<$entityName$Details> list = new ArrayList<$entityName$Details>();
		List<$entityName$> descriptions = $tableName$CustomRepo.findByContainingDescription(description);
		for ($entityName$ $tableName$ : descriptions) {
			list.add($tableName$.to$entityName$Details());
		}
		return new $entityName$PageEvent(list);
	}

}
