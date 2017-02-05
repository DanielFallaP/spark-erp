package co.com.cybersoft.maintenance.tables.persistence.repository.causeClose;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CauseCloseCustomRepoImpl implements CauseCloseCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<CauseClose> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CauseClose p WHERE p.active = true");
		return query.getResultList();
	}public List<CauseClose> findAllActiveByNameCauseClose(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CauseClose p WHERE p.active = true");
		return query.getResultList();
	}public List<CauseClose> findAllActiveByTypeCauseClose(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CauseClose p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CauseClose p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<CauseClose> findAll(Pageable pageable, CauseCloseFilterInfo filt) throws Exception{
		filt.getCauseCloseFilterList().add(filt);
		List<CauseCloseFilterInfo> filters = filt.getCauseCloseFilterList();
		String queryString="SELECT p FROM CauseClose p WHERE (((";
		int index=0;
		List<CauseCloseFilterInfo> unionFilters= new ArrayList<CauseCloseFilterInfo>();
		List<CauseCloseFilterInfo> substractFilters= new ArrayList<CauseCloseFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CauseCloseFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CauseCloseFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CauseClose p WHERE (":"");
		for (CauseCloseFilterInfo filter : substractFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, index2, CyberUtils.filterSubstract);
			index++;
			if (index==substractFilters.size())
				queryString+=")";
		}

		queryString+=")";
		
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("company"))
				 	queryString+= " ORDER BY p.company.companyName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typecauseclose"))
				 	queryString+= " ORDER BY p.typeCauseClose.typeCauseClose " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CauseCloseFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<CauseClose> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCauseCloseFilterList().remove(filt.getCauseCloseFilterList().size()-1);
		return new PageImpl<CauseClose>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CauseCloseFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getNameCauseClose()!=null && !filter.getNameCauseClose().equals(""))queryString+=" AND LOWER(p.nameCauseClose) LIKE LOWER('"+filter.getNameCauseClose()+"')";
		if (filter.getTypeCauseClose()!=null && !filter.getTypeCauseClose().equals(""))queryString+=" AND LOWER(p.typeCauseClose.typeCauseClose) LIKE LOWER('"+filter.getTypeCauseClose()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CauseCloseFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM CauseClose p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("company"))
				 	queryString+= " ORDER BY p.company.companyName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typecauseclose"))
				 	queryString+= " ORDER BY p.typeCauseClose.typeCauseClose " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
			query.setParameter("dateOfModification", df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
			query.setParameter("dateOfCreation", df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
		
		return query;
	}
	
	public List<CauseClose> findAllNoPage(Pageable pageable,
			CauseCloseFilterInfo filt) throws Exception {
		filt.getCauseCloseFilterList().add(filt);
		List<CauseCloseFilterInfo> filters = filt.getCauseCloseFilterList();
		String queryString="SELECT p FROM CauseClose p WHERE (((";
		int index=0;
		List<CauseCloseFilterInfo> unionFilters= new ArrayList<CauseCloseFilterInfo>();
		List<CauseCloseFilterInfo> substractFilters= new ArrayList<CauseCloseFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CauseCloseFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CauseCloseFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CauseClose p WHERE (":"");
		for (CauseCloseFilterInfo filter : substractFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, index2, CyberUtils.filterSubstract);
			index++;
			if (index==substractFilters.size())
				queryString+=")";
		}
		
		queryString+=")";
		
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("company"))
				 	queryString+= " ORDER BY p.company.companyName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typecauseclose"))
				 	queryString+= " ORDER BY p.typeCauseClose.typeCauseClose " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CauseCloseFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getCauseCloseFilterList().remove(filt.getCauseCloseFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}