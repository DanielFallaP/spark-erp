package co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class EffectFailTechnicalActionCustomRepoImpl implements EffectFailTechnicalActionCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<EffectFailTechnicalAction> findAllActiveByEffectFail(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM EffectFailTechnicalAction p WHERE p.active = true");
		return query.getResultList();
	}public List<EffectFailTechnicalAction> findAllActiveByTechnicalAction(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM EffectFailTechnicalAction p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM EffectFailTechnicalAction p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<EffectFailTechnicalAction> findAll(Pageable pageable, EffectFailTechnicalActionFilterInfo filt) throws Exception{
		filt.getEffectFailTechnicalActionFilterList().add(filt);
		List<EffectFailTechnicalActionFilterInfo> filters = filt.getEffectFailTechnicalActionFilterList();
		String queryString="SELECT p FROM EffectFailTechnicalAction p WHERE (((";
		int index=0;
		List<EffectFailTechnicalActionFilterInfo> unionFilters= new ArrayList<EffectFailTechnicalActionFilterInfo>();
		List<EffectFailTechnicalActionFilterInfo> substractFilters= new ArrayList<EffectFailTechnicalActionFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (EffectFailTechnicalActionFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (EffectFailTechnicalActionFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from EffectFailTechnicalAction p WHERE (":"");
		for (EffectFailTechnicalActionFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("effectfail"))
				 	queryString+= " ORDER BY p.effectFail.effectFailName " +next.getDirection().toString();
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
		for (EffectFailTechnicalActionFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<EffectFailTechnicalAction> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getEffectFailTechnicalActionFilterList().remove(filt.getEffectFailTechnicalActionFilterList().size()-1);
		return new PageImpl<EffectFailTechnicalAction>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(EffectFailTechnicalActionFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getEffectFail()!=null && !filter.getEffectFail().equals(""))queryString+=" AND LOWER(p.effectFail.effectFailName) LIKE LOWER('"+filter.getEffectFail()+"')";
		if (filter.getTechnicalAction()!=null && !filter.getTechnicalAction().equals(""))queryString+=" AND LOWER(p.technicalAction.technicalActionName) LIKE LOWER('"+filter.getTechnicalAction()+"')";
		if (filter.getOrderEffectTechnicalActionFails()!=null && !filter.getOrderEffectTechnicalActionFails().equals(""))queryString+=" AND p.orderEffectTechnicalActionFails "+filter.getOrderEffectTechnicalActionFails();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(EffectFailTechnicalActionFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM EffectFailTechnicalAction p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("effectfail"))
				 	queryString+= " ORDER BY p.effectFail.effectFailName " +next.getDirection().toString();
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
	
	public List<EffectFailTechnicalAction> findAllNoPage(Pageable pageable,
			EffectFailTechnicalActionFilterInfo filt) throws Exception {
		filt.getEffectFailTechnicalActionFilterList().add(filt);
		List<EffectFailTechnicalActionFilterInfo> filters = filt.getEffectFailTechnicalActionFilterList();
		String queryString="SELECT p FROM EffectFailTechnicalAction p WHERE (((";
		int index=0;
		List<EffectFailTechnicalActionFilterInfo> unionFilters= new ArrayList<EffectFailTechnicalActionFilterInfo>();
		List<EffectFailTechnicalActionFilterInfo> substractFilters= new ArrayList<EffectFailTechnicalActionFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (EffectFailTechnicalActionFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (EffectFailTechnicalActionFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from EffectFailTechnicalAction p WHERE (":"");
		for (EffectFailTechnicalActionFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("effectfail"))
				 	queryString+= " ORDER BY p.effectFail.effectFailName " +next.getDirection().toString();
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
		for (EffectFailTechnicalActionFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getEffectFailTechnicalActionFilterList().remove(filt.getEffectFailTechnicalActionFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}