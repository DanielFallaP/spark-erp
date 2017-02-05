package co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class PhysicalLocationCustomRepoImpl implements PhysicalLocationCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<PhysicalLocation> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByNamePhysicalLocation(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByDescription(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByDescriptionEnglish(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByDescriptionShort(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByMeasurementUnit(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}public List<PhysicalLocation> findAllActiveByCapacityPhysicalLocation(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM PhysicalLocation p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<PhysicalLocation> findAll(Pageable pageable, PhysicalLocationFilterInfo filt) throws Exception{
		filt.getPhysicalLocationFilterList().add(filt);
		List<PhysicalLocationFilterInfo> filters = filt.getPhysicalLocationFilterList();
		String queryString="SELECT p FROM PhysicalLocation p WHERE (((";
		int index=0;
		List<PhysicalLocationFilterInfo> unionFilters= new ArrayList<PhysicalLocationFilterInfo>();
		List<PhysicalLocationFilterInfo> substractFilters= new ArrayList<PhysicalLocationFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (PhysicalLocationFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (PhysicalLocationFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from PhysicalLocation p WHERE (":"");
		for (PhysicalLocationFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("measurementunit"))
				 	queryString+= " ORDER BY p.measurementUnit.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("capacityphysicallocation"))
				 	queryString+= " ORDER BY p.capacityPhysicalLocation.nameUnit " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (PhysicalLocationFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<PhysicalLocation> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getPhysicalLocationFilterList().remove(filt.getPhysicalLocationFilterList().size()-1);
		return new PageImpl<PhysicalLocation>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(PhysicalLocationFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getNamePhysicalLocation()!=null && !filter.getNamePhysicalLocation().equals(""))queryString+=" AND LOWER(p.namePhysicalLocation) LIKE LOWER('"+filter.getNamePhysicalLocation()+"')";
		if (filter.getDescription()!=null && !filter.getDescription().equals(""))queryString+=" AND LOWER(p.description) LIKE LOWER('"+filter.getDescription()+"')";
		if (filter.getDescriptionEnglish()!=null && !filter.getDescriptionEnglish().equals(""))queryString+=" AND LOWER(p.descriptionEnglish) LIKE LOWER('"+filter.getDescriptionEnglish()+"')";
		if (filter.getDescriptionShort()!=null && !filter.getDescriptionShort().equals(""))queryString+=" AND LOWER(p.descriptionShort) LIKE LOWER('"+filter.getDescriptionShort()+"')";
		if (filter.getPhysicalLocationArea()!=null && !filter.getPhysicalLocationArea().equals(""))queryString+=" AND p.physicalLocationArea "+filter.getPhysicalLocationArea();
		if (filter.getMeasurementUnit()!=null && !filter.getMeasurementUnit().equals(""))queryString+=" AND LOWER(p.measurementUnit.nameUnit) LIKE LOWER('"+filter.getMeasurementUnit()+"')";
		if (filter.getCapacityPhysicalLocation()!=null && !filter.getCapacityPhysicalLocation().equals(""))queryString+=" AND LOWER(p.capacityPhysicalLocation.nameUnit) LIKE LOWER('"+filter.getCapacityPhysicalLocation()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(PhysicalLocationFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM PhysicalLocation p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("measurementunit"))
				 	queryString+= " ORDER BY p.measurementUnit.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("capacityphysicallocation"))
				 	queryString+= " ORDER BY p.capacityPhysicalLocation.nameUnit " +next.getDirection().toString();
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
	
	public List<PhysicalLocation> findAllNoPage(Pageable pageable,
			PhysicalLocationFilterInfo filt) throws Exception {
		filt.getPhysicalLocationFilterList().add(filt);
		List<PhysicalLocationFilterInfo> filters = filt.getPhysicalLocationFilterList();
		String queryString="SELECT p FROM PhysicalLocation p WHERE (((";
		int index=0;
		List<PhysicalLocationFilterInfo> unionFilters= new ArrayList<PhysicalLocationFilterInfo>();
		List<PhysicalLocationFilterInfo> substractFilters= new ArrayList<PhysicalLocationFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (PhysicalLocationFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (PhysicalLocationFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from PhysicalLocation p WHERE (":"");
		for (PhysicalLocationFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("measurementunit"))
				 	queryString+= " ORDER BY p.measurementUnit.nameUnit " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("capacityphysicallocation"))
				 	queryString+= " ORDER BY p.capacityPhysicalLocation.nameUnit " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (PhysicalLocationFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getPhysicalLocationFilterList().remove(filt.getPhysicalLocationFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}