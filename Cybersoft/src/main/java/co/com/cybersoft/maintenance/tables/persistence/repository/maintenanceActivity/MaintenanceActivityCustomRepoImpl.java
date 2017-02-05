package co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class MaintenanceActivityCustomRepoImpl implements MaintenanceActivityCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<MaintenanceActivity> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM MaintenanceActivity p WHERE p.active = true");
		return query.getResultList();
	}public List<MaintenanceActivity> findAllActiveByNameMaintenanceActivity(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM MaintenanceActivity p WHERE p.active = true");
		return query.getResultList();
	}public List<MaintenanceActivity> findAllActiveByDurationUnitStandard(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM MaintenanceActivity p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM MaintenanceActivity p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<MaintenanceActivity> findAll(Pageable pageable, MaintenanceActivityFilterInfo filt) throws Exception{
		filt.getMaintenanceActivityFilterList().add(filt);
		List<MaintenanceActivityFilterInfo> filters = filt.getMaintenanceActivityFilterList();
		String queryString="SELECT p FROM MaintenanceActivity p WHERE (((";
		int index=0;
		List<MaintenanceActivityFilterInfo> unionFilters= new ArrayList<MaintenanceActivityFilterInfo>();
		List<MaintenanceActivityFilterInfo> substractFilters= new ArrayList<MaintenanceActivityFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (MaintenanceActivityFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (MaintenanceActivityFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from MaintenanceActivity p WHERE (":"");
		for (MaintenanceActivityFilterInfo filter : substractFilters) {
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
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (MaintenanceActivityFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<MaintenanceActivity> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getMaintenanceActivityFilterList().remove(filt.getMaintenanceActivityFilterList().size()-1);
		return new PageImpl<MaintenanceActivity>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(MaintenanceActivityFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getNameMaintenanceActivity()!=null && !filter.getNameMaintenanceActivity().equals(""))queryString+=" AND LOWER(p.nameMaintenanceActivity) LIKE LOWER('"+filter.getNameMaintenanceActivity()+"')";
		if (filter.getStandardCost()!=null && !filter.getStandardCost().equals(""))queryString+=" AND p.standardCost "+filter.getStandardCost();
		if (filter.getStandarDuration()!=null && !filter.getStandarDuration().equals(""))queryString+=" AND p.standarDuration "+filter.getStandarDuration();
		if (filter.getDurationUnitStandard()!=null && !filter.getDurationUnitStandard().equals(""))queryString+=" AND LOWER(p.durationUnitStandard) LIKE LOWER('"+filter.getDurationUnitStandard()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(MaintenanceActivityFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM MaintenanceActivity p WHERE 1=1";
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
	
	public List<MaintenanceActivity> findAllNoPage(Pageable pageable,
			MaintenanceActivityFilterInfo filt) throws Exception {
		filt.getMaintenanceActivityFilterList().add(filt);
		List<MaintenanceActivityFilterInfo> filters = filt.getMaintenanceActivityFilterList();
		String queryString="SELECT p FROM MaintenanceActivity p WHERE (((";
		int index=0;
		List<MaintenanceActivityFilterInfo> unionFilters= new ArrayList<MaintenanceActivityFilterInfo>();
		List<MaintenanceActivityFilterInfo> substractFilters= new ArrayList<MaintenanceActivityFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (MaintenanceActivityFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (MaintenanceActivityFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from MaintenanceActivity p WHERE (":"");
		for (MaintenanceActivityFilterInfo filter : substractFilters) {
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
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (MaintenanceActivityFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getMaintenanceActivityFilterList().remove(filt.getMaintenanceActivityFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}