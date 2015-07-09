package co.com.cybersoft.purchase.tables.persistence.repository.continent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;




import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class ContinentCustomRepoImpl implements ContinentCustomRepo {
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;
	
	@Override
		public List<Continent> findByContainingContinent(
				String continentSubstring) {
				Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Continent p WHERE LOWER(p.continent) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
				query.setParameter("substring", continentSubstring);
				return query.setMaxResults(10).getResultList();
		}
	
	@Override
	public List<Continent> findAllActiveByContinent(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Continent p WHERE p.active = true");
		return query.getResultList();
	}

	@Override
	public Page<Continent> findAll(Pageable pageable, ContinentFilterInfo filter) throws Exception{
		Query query = buildCriteriaQuery(filter, pageable);
		Long count=(long) query.getResultList().size();
		List<Continent> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<Continent>(list, pageable, count);
	}

	private Query buildCriteriaQuery(ContinentFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Continent p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getContinent()!=null && !filter.getContinent().equals(""))queryString+=" AND LOWER(p.continent) LIKE LOWER('"+filter.getContinent()+"')";

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