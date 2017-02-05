package co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ConceptKardexCustomRepoImpl implements ConceptKardexCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<ConceptKardex> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ConceptKardex p WHERE p.active = true");
		return query.getResultList();
	}public List<ConceptKardex> findAllActiveByStore(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ConceptKardex p WHERE p.active = true");
		return query.getResultList();
	}public List<ConceptKardex> findAllActiveByTypeConceptKardex(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ConceptKardex p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ConceptKardex p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<ConceptKardex> findAll(Pageable pageable, ConceptKardexFilterInfo filt) throws Exception{
		filt.getConceptKardexFilterList().add(filt);
		List<ConceptKardexFilterInfo> filters = filt.getConceptKardexFilterList();
		String queryString="SELECT p FROM ConceptKardex p WHERE (((";
		int index=0;
		List<ConceptKardexFilterInfo> unionFilters= new ArrayList<ConceptKardexFilterInfo>();
		List<ConceptKardexFilterInfo> substractFilters= new ArrayList<ConceptKardexFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ConceptKardexFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ConceptKardexFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from ConceptKardex p WHERE (":"");
		for (ConceptKardexFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("store"))
				 	queryString+= " ORDER BY p.store.storeName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typeconceptkardex"))
				 	queryString+= " ORDER BY p.typeConceptKardex.typeConceptKardex " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (ConceptKardexFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<ConceptKardex> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getConceptKardexFilterList().remove(filt.getConceptKardexFilterList().size()-1);
		return new PageImpl<ConceptKardex>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(ConceptKardexFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getStore()!=null && !filter.getStore().equals(""))queryString+=" AND LOWER(p.store.storeName) LIKE LOWER('"+filter.getStore()+"')";
		if (filter.getNumberConceptKardex()!=null && !filter.getNumberConceptKardex().equals(""))queryString+=" AND p.numberConceptKardex "+filter.getNumberConceptKardex();
		if (filter.getNameConceptKardex()!=null && !filter.getNameConceptKardex().equals(""))queryString+=" AND p.nameConceptKardex "+filter.getNameConceptKardex();
		if (filter.getTypeConceptKardex()!=null && !filter.getTypeConceptKardex().equals(""))queryString+=" AND LOWER(p.typeConceptKardex.typeConceptKardex) LIKE LOWER('"+filter.getTypeConceptKardex()+"')";
		if (filter.getWorkOrderConceptKardex()!=null && !filter.getWorkOrderConceptKardex().equals(""))queryString+=" AND p.workOrderConceptKardex "+filter.getWorkOrderConceptKardex();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(ConceptKardexFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM ConceptKardex p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("store"))
				 	queryString+= " ORDER BY p.store.storeName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typeconceptkardex"))
				 	queryString+= " ORDER BY p.typeConceptKardex.typeConceptKardex " +next.getDirection().toString();
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
	
	public List<ConceptKardex> findAllNoPage(Pageable pageable,
			ConceptKardexFilterInfo filt) throws Exception {
		filt.getConceptKardexFilterList().add(filt);
		List<ConceptKardexFilterInfo> filters = filt.getConceptKardexFilterList();
		String queryString="SELECT p FROM ConceptKardex p WHERE (((";
		int index=0;
		List<ConceptKardexFilterInfo> unionFilters= new ArrayList<ConceptKardexFilterInfo>();
		List<ConceptKardexFilterInfo> substractFilters= new ArrayList<ConceptKardexFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ConceptKardexFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ConceptKardexFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from ConceptKardex p WHERE (":"");
		for (ConceptKardexFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("store"))
				 	queryString+= " ORDER BY p.store.storeName " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typeconceptkardex"))
				 	queryString+= " ORDER BY p.typeConceptKardex.typeConceptKardex " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (ConceptKardexFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getConceptKardexFilterList().remove(filt.getConceptKardexFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}