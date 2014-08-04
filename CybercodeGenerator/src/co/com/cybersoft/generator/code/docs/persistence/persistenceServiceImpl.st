package co.com.cybersoft.docs.persistence.services.$docName$;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.Request$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$Event;
import co.com.cybersoft.docs.events.$docName$.$upperDocName$PageEvent;
import co.com.cybersoft.docs.events.$docName$.Save$upperDocName$Event;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$Body;
import co.com.cybersoft.docs.persistence.repository.$docName$.$upperDocName$CustomRepo;
import co.com.cybersoft.docs.persistence.repository.$docName$.$upperDocName$Repository;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$Info;

$imports$

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $upperDocName$PersistenceServiceImpl implements $upperDocName$PersistenceService{

	private final $upperDocName$Repository $docName$Repository;
	
	private final $upperDocName$CustomRepo $docName$CustomRepo;
	
	$autocompleteRepos$
	$docReference$
	
	public $upperDocName$PersistenceServiceImpl(final $upperDocName$Repository $docName$Repository, final $upperDocName$CustomRepo $docName$CustomRepo) {
		this.$docName$Repository=$docName$Repository;
		this.$docName$CustomRepo=$docName$CustomRepo;
	}
	
	private $upperDocName$ updateBody($upperDocName$ $docName$, $upperDocName$Body modified){
		
		List<$upperDocName$Body> bodyList = $docName$.get$upperDocName$BodyEntityList();
		for ($upperDocName$Body $docName$Item : bodyList) {
			if ($docName$Item.getId().equals(modified.getId())){
				BeanUtils.copyProperties(modified, $docName$Item);
			}
		}
		return $docName$;
	}

	private $upperDocName$ deleteFromBody($upperDocName$ $docName$, List<String> toDelete) {
		List<$upperDocName$Body> entityList = $docName$.get$upperDocName$BodyEntityList();
		List<$upperDocName$Body> copy = new ArrayList<>(entityList);
		for ($upperDocName$Body $docName$Item : copy) {
			if (toDelete.contains($docName$Item.getId())){
				entityList.remove($docName$Item);
			}
		}
		return $docName$;
	}
	
	@Override
	public $upperDocName$Event save$upperDocName$(Save$upperDocName$Event event) throws Exception {
		$checkHeaderReferences$
		
		$upperDocName$ $docName$=new $upperDocName$().from$upperDocName$Info(event.get$upperDocName$Info());
		$docName$.setDateOfModification(new Date());
		$docName$.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		$docReferenceFields$
		
		if ($docName$.getId()==null){
			$docName$.setDateOfCreation(new Date());
			$docName$.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		
		$docName$ = $docName$CustomRepo.save($docName$);
		return new $upperDocName$Event(new $upperDocName$Info().to$upperDocName$Info($docName$));
	}

	@Override
	public $upperDocName$Event create$upperDocName$BodyRecord(Save$upperDocName$Event event)	throws Exception {
		$checkAdditionReferences$
		
		$upperDocName$ $docName$ = $docName$Repository.findByNumericId(event.get$upperDocName$Info().getNumericId());
		$upperDocName$Body $docName$Item = new $upperDocName$Body();
		BeanUtils.copyProperties(event.get$upperDocName$Info().getCurrent$upperDocName$BodyInfo(), $docName$Item);
		UUID id = UUID.randomUUID();
		$docName$Item.setId(id.toString());
		if ($docName$.get$upperDocName$BodyEntityList()==null)
			$docName$.set$upperDocName$BodyEntityList(new ArrayList<$upperDocName$Body>());
		$docName$.get$upperDocName$BodyEntityList().add($docName$Item);

		$docName$.setDateOfModification(new Date());
		$docName$.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$docName$ = $docName$CustomRepo.save($docName$);
		return new $upperDocName$Event(new $upperDocName$Info().to$upperDocName$Info($docName$));
	}
	
	@Override
	public $upperDocName$Event update$upperDocName$Body(Save$upperDocName$Event event) throws Exception {
		$checkModificationReferences$

		$upperDocName$ $docName$ = $docName$Repository.findByNumericId(event.get$upperDocName$Info().getNumericId());
		$upperDocName$Body modified=new $upperDocName$Body();
		BeanUtils.copyProperties(event.get$upperDocName$Info().get$upperDocName$BodyModificationInfo(), modified);
		$docName$=updateBody($docName$, modified);
	
		$docName$.setDateOfModification(new Date());
		$docName$.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		$docName$=$docName$CustomRepo.save($docName$);
		return new $upperDocName$Event(new $upperDocName$Info().to$upperDocName$Info($docName$));
	}
	
	@Override
	public $upperDocName$Event delete$upperDocName$BodyRecords(Save$upperDocName$Event event, List<String> toDelete) throws Exception {
		$upperDocName$ $docName$ = $docName$Repository.findByNumericId(event.get$upperDocName$Info().getNumericId());
		$docName$ = deleteFromBody($docName$, toDelete);
		
		$docName$.setDateOfModification(new Date());
		$docName$.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		return new $upperDocName$Event(new $upperDocName$Info().to$upperDocName$Info($docName$CustomRepo.save($docName$)));
	}

	@Override
	public $upperDocName$PageEvent request$upperDocName$Page(Request$upperDocName$PageEvent event) throws Exception {
		Page<$upperDocName$> $docName$s = $docName$Repository.findAll(event.getPageable());
		return new $upperDocName$PageEvent($docName$s);
	}

	@Override
	public $upperDocName$Event request$upperDocName$Details(Request$upperDocName$Event event) throws Exception {
		$upperDocName$Info $docName$Info=null;
		if (event.getId()!=null){
			$upperDocName$ $docName$ = $docName$Repository.findOne(event.getId());
			if ($docName$!=null)
				$docName$Info = new $upperDocName$Info().to$upperDocName$Info($docName$);
		}
		
		if (event.getNumericId()!=null){
			$upperDocName$ $docName$ = $docName$Repository.findByNumericId(event.getNumericId());if ($docName$!=null)
				$docName$Info = new $upperDocName$Info().to$upperDocName$Info($docName$);
		}
		
		return new $upperDocName$Event($docName$Info);
		
	}
	
	
	@Override
	public $upperDocName$PageEvent requestByContainingNumericId(String numericId)
			throws Exception {
		ArrayList<$upperDocName$Info> list = new ArrayList<$upperDocName$Info>();
		List<$upperDocName$> $docName$List = $docName$CustomRepo.findByContainingNumericId(numericId);
		for ($upperDocName$ $docName$ : $docName$List) {
			list.add(new $upperDocName$Info().to$upperDocName$Info($docName$));
		}
		return new $upperDocName$PageEvent(list);
	}
	

}