package co.com.cybersoft.purchase.tables.persistence.repository.region;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class RegionCustomRepoImpl implements RegionCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;
	
	@Override
		public List<Region> findByContainingRegion(
			String continentSubstring) {
			Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Region p WHERE LOWER(p.region) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
			query.setParameter("substring", continentSubstring);
			return query.setMaxResults(10).getResultList();
	}
	
	@Override
	public List<Region> findAllActiveByContinent(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Region p WHERE p.active = true");
		return query.getResultList();
	}
	
	@Override
	public List<Region> findAllActiveByRegion(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Region p WHERE p.active = true");
		return query.getResultList();
	}
	
	@Override
	public Page<Region> findAll(Pageable pageable, RegionFilterInfo filter) throws Exception{
		Query query = buildCriteriaQuery(filter, pageable);
		Long count=(long) query.getResultList().size();
		List<Region> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<Region>(list, pageable, count);
	}

	private Query buildCriteriaQuery(RegionFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Region p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getContinent()!=null && !filter.getContinent().equals(""))queryString+=" AND LOWER(p.continent.continent) LIKE LOWER('"+filter.getContinent()+"')";
		if (filter.getRegion()!=null && !filter.getRegion().equals(""))queryString+=" AND LOWER(p.region) LIKE LOWER('"+filter.getRegion()+"')";

		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		while (pageable.getSort().iterator().hasNext()){
			Order next = pageable.getSort().iterator().next();
			queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
			break;
		}
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
			query.setParameter("dateOfModification", df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
			query.setParameter("dateOfCreation", df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
		
		return query;
	}
}