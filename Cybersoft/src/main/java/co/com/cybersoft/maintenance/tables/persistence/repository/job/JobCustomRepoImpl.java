package co.com.cybersoft.maintenance.tables.persistence.repository.job;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class JobCustomRepoImpl implements JobCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<Job> findAllActiveByResponsibleCenter(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Job p WHERE p.active = true");
		return query.getResultList();
	}public List<Job> findAllActiveByNameJob(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Job p WHERE p.active = true");
		return query.getResultList();
	}public List<Job> findAllActiveByTypeWork(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Job p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Job p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<Job> findAll(Pageable pageable, JobFilterInfo filt) throws Exception{
		filt.getJobFilterList().add(filt);
		List<JobFilterInfo> filters = filt.getJobFilterList();
		String queryString="SELECT p FROM Job p WHERE (((";
		int index=0;
		List<JobFilterInfo> unionFilters= new ArrayList<JobFilterInfo>();
		List<JobFilterInfo> substractFilters= new ArrayList<JobFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (JobFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (JobFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Job p WHERE (":"");
		for (JobFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("responsiblecenter"))
				 	queryString+= " ORDER BY p.responsibleCenter.nameResponsibleCenter " +next.getDirection().toString();
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
		for (JobFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<Job> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getJobFilterList().remove(filt.getJobFilterList().size()-1);
		return new PageImpl<Job>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(JobFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getResponsibleCenter()!=null && !filter.getResponsibleCenter().equals(""))queryString+=" AND LOWER(p.responsibleCenter.nameResponsibleCenter) LIKE LOWER('"+filter.getResponsibleCenter()+"')";
		if (filter.getNameJob()!=null && !filter.getNameJob().equals(""))queryString+=" AND LOWER(p.nameJob) LIKE LOWER('"+filter.getNameJob()+"')";
		if (filter.getValueTime1()!=null && !filter.getValueTime1().equals(""))queryString+=" AND p.valueTime1 "+filter.getValueTime1();
		if (filter.getValueTime2()!=null && !filter.getValueTime2().equals(""))queryString+=" AND p.valueTime2 "+filter.getValueTime2();
		if (filter.getValueTime3()!=null && !filter.getValueTime3().equals(""))queryString+=" AND p.valueTime3 "+filter.getValueTime3();
		if (filter.getTypeWork()!=null && !filter.getTypeWork().equals(""))queryString+=" AND LOWER(p.typeWork.typeWork) LIKE LOWER('"+filter.getTypeWork()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(JobFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Job p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("responsiblecenter"))
				 	queryString+= " ORDER BY p.responsibleCenter.nameResponsibleCenter " +next.getDirection().toString();
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
	
	public List<Job> findAllNoPage(Pageable pageable,
			JobFilterInfo filt) throws Exception {
		filt.getJobFilterList().add(filt);
		List<JobFilterInfo> filters = filt.getJobFilterList();
		String queryString="SELECT p FROM Job p WHERE (((";
		int index=0;
		List<JobFilterInfo> unionFilters= new ArrayList<JobFilterInfo>();
		List<JobFilterInfo> substractFilters= new ArrayList<JobFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (JobFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (JobFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Job p WHERE (":"");
		for (JobFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("responsiblecenter"))
				 	queryString+= " ORDER BY p.responsibleCenter.nameResponsibleCenter " +next.getDirection().toString();
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
		for (JobFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getJobFilterList().remove(filt.getJobFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}