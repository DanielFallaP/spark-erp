package co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionCustomRepoImpl implements FailureCauseTechnicalactionCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<FailureCauseTechnicalaction> findAllActiveByFailureCause(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM FailureCauseTechnicalaction p WHERE p.active = true");
		return query.getResultList();
	}public List<FailureCauseTechnicalaction> findAllActiveByTechnicalAction(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM FailureCauseTechnicalaction p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM FailureCauseTechnicalaction p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<FailureCauseTechnicalaction> findAll(Pageable pageable, FailureCauseTechnicalactionFilterInfo filt) throws Exception{
		filt.getFailureCauseTechnicalactionFilterList().add(filt);
		List<FailureCauseTechnicalactionFilterInfo> filters = filt.getFailureCauseTechnicalactionFilterList();
		String queryString="SELECT p FROM FailureCauseTechnicalaction p WHERE (((";
		int index=0;
		List<FailureCauseTechnicalactionFilterInfo> unionFilters= new ArrayList<FailureCauseTechnicalactionFilterInfo>();
		List<FailureCauseTechnicalactionFilterInfo> substractFilters= new ArrayList<FailureCauseTechnicalactionFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (FailureCauseTechnicalactionFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (FailureCauseTechnicalactionFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from FailureCauseTechnicalaction p WHERE (":"");
		for (FailureCauseTechnicalactionFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("failurecause"))
				 	queryString+= " ORDER BY p.failureCause.nameFailureCause " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("technicalaction"))
				 	queryString+= " ORDER BY p.technicalAction.technicalActionName " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (FailureCauseTechnicalactionFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<FailureCauseTechnicalaction> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getFailureCauseTechnicalactionFilterList().remove(filt.getFailureCauseTechnicalactionFilterList().size()-1);
		return new PageImpl<FailureCauseTechnicalaction>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(FailureCauseTechnicalactionFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getFailureCause()!=null && !filter.getFailureCause().equals(""))queryString+=" AND LOWER(p.failureCause.nameFailureCause) LIKE LOWER('"+filter.getFailureCause()+"')";
		if (filter.getTechnicalAction()!=null && !filter.getTechnicalAction().equals(""))queryString+=" AND LOWER(p.technicalAction.technicalActionName) LIKE LOWER('"+filter.getTechnicalAction()+"')";
		if (filter.getOrderFailureCauseTechnicalactions()!=null && !filter.getOrderFailureCauseTechnicalactions().equals(""))queryString+=" AND p.orderFailureCauseTechnicalactions "+filter.getOrderFailureCauseTechnicalactions();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(FailureCauseTechnicalactionFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM FailureCauseTechnicalaction p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("failurecause"))
				 	queryString+= " ORDER BY p.failureCause.nameFailureCause " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("technicalaction"))
				 	queryString+= " ORDER BY p.technicalAction.technicalActionName " +next.getDirection().toString();
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
	
	public List<FailureCauseTechnicalaction> findAllNoPage(Pageable pageable,
			FailureCauseTechnicalactionFilterInfo filt) throws Exception {
		filt.getFailureCauseTechnicalactionFilterList().add(filt);
		List<FailureCauseTechnicalactionFilterInfo> filters = filt.getFailureCauseTechnicalactionFilterList();
		String queryString="SELECT p FROM FailureCauseTechnicalaction p WHERE (((";
		int index=0;
		List<FailureCauseTechnicalactionFilterInfo> unionFilters= new ArrayList<FailureCauseTechnicalactionFilterInfo>();
		List<FailureCauseTechnicalactionFilterInfo> substractFilters= new ArrayList<FailureCauseTechnicalactionFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (FailureCauseTechnicalactionFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (FailureCauseTechnicalactionFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from FailureCauseTechnicalaction p WHERE (":"");
		for (FailureCauseTechnicalactionFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("failurecause"))
				 	queryString+= " ORDER BY p.failureCause.nameFailureCause " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("technicalaction"))
				 	queryString+= " ORDER BY p.technicalAction.technicalActionName " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (FailureCauseTechnicalactionFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getFailureCauseTechnicalactionFilterList().remove(filt.getFailureCauseTechnicalactionFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}