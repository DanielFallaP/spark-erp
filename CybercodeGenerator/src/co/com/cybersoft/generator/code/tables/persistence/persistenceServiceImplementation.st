package co.com.cybersoft.tables.persistence.services.$tableName$;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.tables.core.domain.$entityName$Details;
import co.com.cybersoft.tables.events.$tableName$.Create$entityName$Event;
import co.com.cybersoft.tables.events.$tableName$.$entityName$DetailsEvent;
import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.tables.events.$tableName$.$entityName$ModificationEvent;
import co.com.cybersoft.tables.events.$tableName$.Request$entityName$DetailsEvent;
import co.com.cybersoft.tables.events.$tableName$.Request$entityName$PageEvent;
import co.com.cybersoft.tables.persistence.domain.$entityName$;
import co.com.cybersoft.tables.persistence.repository.$tableName$.$entityName$Repository;
import co.com.cybersoft.tables.persistence.repository.$tableName$.$entityName$CustomRepo;
$imports$

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
	
	$autocompleteRepos$
	
	public $entityName$PersistenceServiceImpl(final $entityName$Repository $tableName$Repository, final $entityName$CustomRepo $tableName$CustomRepo) {
		this.$tableName$Repository=$tableName$Repository;
		this.$tableName$CustomRepo=$tableName$CustomRepo;
	}
	
	@Override
	public $entityName$DetailsEvent create$entityName$(Create$entityName$Event event) throws Exception{
		$checkReferences$
		$entityName$ $tableName$ = new $entityName$().from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent(new $entityName$Details().to$entityName$Details($tableName$));
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
				$tableName$Details = new $entityName$Details().to$entityName$Details($tableName$);
		}
		$queryByField$
		return new $entityName$DetailsEvent($tableName$Details);
		
	}

	@Override
	public $entityName$DetailsEvent modify$entityName$($entityName$ModificationEvent event) throws Exception {
		$checkReferences$
		$entityName$ $tableName$ = new $entityName$().from$entityName$Details(event.get$entityName$Details());
		$tableName$ = $tableName$Repository.save($tableName$);
		return new $entityName$DetailsEvent(new $entityName$Details().to$entityName$Details($tableName$));
	}
	
	@Override
		public $entityName$DetailsEvent getOnlyRecord() throws Exception {
			List<$entityName$> all = $tableName$Repository.findAll();
			$entityName$Details single = new $entityName$Details();
			for ($entityName$ $tableName$ : all) {
				single=new $entityName$Details().to$entityName$Details($tableName$);
				break;
			}
			return new $entityName$DetailsEvent(single);
		}
	
	$requestAll$
	
	$autocompleteRequest$

	@Override
	public $entityName$PageEvent request$entityName$FilterPage(Request$entityName$PageEvent event) throws Exception {
		Page<$entityName$> page = $tableName$CustomRepo.findAll(event.getPageable(), event.getFilter());
		return new $entityName$PageEvent(page);
	}	
}
