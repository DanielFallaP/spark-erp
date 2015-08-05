package co.com.cybersoft.$module$.tables.persistence.repository.$tableName$;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.Query;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.springframework.data.domain.Sort.Order;
import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$FilterInfo;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class $entityName$CustomRepoImpl implements $entityName$CustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	$startupSingletonRecord$
	
	$byContainingFields$
	
	$findAllActive$

	@Override
	public Page<$entityName$> findAll(Pageable pageable, $entityName$FilterInfo filter) throws Exception{
		Query query = buildCriteriaQuery(filter, pageable);
		Long count=(long) query.getResultList().size();
		List<$entityName$> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<$entityName$>(list, pageable, count);
	}

	private Query buildCriteriaQuery($entityName$FilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM $entityName$ p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		$fieldCriteria$
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		while (pageable.getSort().iterator().hasNext()){
			Order next = pageable.getSort().iterator().next();
			$orderBy$
			break;
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
			query.setParameter("dateOfModification", df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
			query.setParameter("dateOfCreation", df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
		$dateFieldCriteria$
		
		return query;
	}	
}

