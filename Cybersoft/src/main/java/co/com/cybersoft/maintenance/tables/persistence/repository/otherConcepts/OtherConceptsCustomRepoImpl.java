package co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class OtherConceptsCustomRepoImpl implements OtherConceptsCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<OtherConcepts> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM OtherConcepts p WHERE p.active = true");
		return query.getResultList();
	}public List<OtherConcepts> findAllActiveByNameOtherconcepts(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM OtherConcepts p WHERE p.active = true");
		return query.getResultList();
	}public List<OtherConcepts> findAllActiveByUnitMeasure(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM OtherConcepts p WHERE p.active = true");
		return query.getResultList();
	}public List<OtherConcepts> findAllActiveByTypeWork(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM OtherConcepts p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM OtherConcepts p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<OtherConcepts> findAll(Pageable pageable, OtherConceptsFilterInfo filt) throws Exception{
		filt.getOtherConceptsFilterList().add(filt);
		List<OtherConceptsFilterInfo> filters = filt.getOtherConceptsFilterList();
		String queryString="SELECT p FROM OtherConcepts p WHERE (((";
		int index=0;
		List<OtherConceptsFilterInfo> unionFilters= new ArrayList<OtherConceptsFilterInfo>();
		List<OtherConceptsFilterInfo> substractFilters= new ArrayList<OtherConceptsFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (OtherConceptsFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (OtherConceptsFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from OtherConcepts p WHERE (":"");
		for (OtherConceptsFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("unitmeasure"))
				 	queryString+= " ORDER BY p.unitMeasure.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typework"))
				 	queryString+= " ORDER BY p.typeWork.typeWork " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (OtherConceptsFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<OtherConcepts> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getOtherConceptsFilterList().remove(filt.getOtherConceptsFilterList().size()-1);
		return new PageImpl<OtherConcepts>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(OtherConceptsFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getNameOtherconcepts()!=null && !filter.getNameOtherconcepts().equals(""))queryString+=" AND LOWER(p.nameOtherconcepts) LIKE LOWER('"+filter.getNameOtherconcepts()+"')";
		if (filter.getUnitValue()!=null && !filter.getUnitValue().equals(""))queryString+=" AND p.unitValue "+filter.getUnitValue();
		if (filter.getUnitMeasure()!=null && !filter.getUnitMeasure().equals(""))queryString+=" AND LOWER(p.unitMeasure.nameUnit) LIKE LOWER('"+filter.getUnitMeasure()+"')";
		if (filter.getTypeWork()!=null && !filter.getTypeWork().equals(""))queryString+=" AND LOWER(p.typeWork.typeWork) LIKE LOWER('"+filter.getTypeWork()+"')";
		if (filter.getInformative()!=null && !filter.getInformative().equals(""))queryString+=" AND p.informative "+filter.getInformative();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(OtherConceptsFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM OtherConcepts p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("unitmeasure"))
				 	queryString+= " ORDER BY p.unitMeasure.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typework"))
				 	queryString+= " ORDER BY p.typeWork.typeWork " +next.getDirection().toString();
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
	
	public List<OtherConcepts> findAllNoPage(Pageable pageable,
			OtherConceptsFilterInfo filt) throws Exception {
		filt.getOtherConceptsFilterList().add(filt);
		List<OtherConceptsFilterInfo> filters = filt.getOtherConceptsFilterList();
		String queryString="SELECT p FROM OtherConcepts p WHERE (((";
		int index=0;
		List<OtherConceptsFilterInfo> unionFilters= new ArrayList<OtherConceptsFilterInfo>();
		List<OtherConceptsFilterInfo> substractFilters= new ArrayList<OtherConceptsFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (OtherConceptsFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (OtherConceptsFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from OtherConcepts p WHERE (":"");
		for (OtherConceptsFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("unitmeasure"))
				 	queryString+= " ORDER BY p.unitMeasure.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("typework"))
				 	queryString+= " ORDER BY p.typeWork.typeWork " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (OtherConceptsFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getOtherConceptsFilterList().remove(filt.getOtherConceptsFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}