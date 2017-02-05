package co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CostCentersCustomersCustomRepoImpl implements CostCentersCustomersCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<CostCentersCustomers> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByDescription(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByNameCostCenter(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveBySubCostCentersCustomers(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveBySubDescription(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByContact(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByAreaDepartment(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByAddress(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByCity(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByPhone(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByState(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}public List<CostCentersCustomers> findAllActiveByComments(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CostCentersCustomers p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<CostCentersCustomers> findAll(Pageable pageable, CostCentersCustomersFilterInfo filt) throws Exception{
		filt.getCostCentersCustomersFilterList().add(filt);
		List<CostCentersCustomersFilterInfo> filters = filt.getCostCentersCustomersFilterList();
		String queryString="SELECT p FROM CostCentersCustomers p WHERE (((";
		int index=0;
		List<CostCentersCustomersFilterInfo> unionFilters= new ArrayList<CostCentersCustomersFilterInfo>();
		List<CostCentersCustomersFilterInfo> substractFilters= new ArrayList<CostCentersCustomersFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CostCentersCustomersFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CostCentersCustomersFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CostCentersCustomers p WHERE (":"");
		for (CostCentersCustomersFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("state"))
				 	queryString+= " ORDER BY p.state.stateCostCenters " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CostCentersCustomersFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<CostCentersCustomers> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCostCentersCustomersFilterList().remove(filt.getCostCentersCustomersFilterList().size()-1);
		return new PageImpl<CostCentersCustomers>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CostCentersCustomersFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getDescription()!=null && !filter.getDescription().equals(""))queryString+=" AND LOWER(p.description) LIKE LOWER('"+filter.getDescription()+"')";
		if (filter.getNameCostCenter()!=null && !filter.getNameCostCenter().equals(""))queryString+=" AND LOWER(p.nameCostCenter) LIKE LOWER('"+filter.getNameCostCenter()+"')";
		if (filter.getSubCostCentersCustomers()!=null && !filter.getSubCostCentersCustomers().equals(""))queryString+=" AND LOWER(p.subCostCentersCustomers) LIKE LOWER('"+filter.getSubCostCentersCustomers()+"')";
		if (filter.getSubDescription()!=null && !filter.getSubDescription().equals(""))queryString+=" AND LOWER(p.subDescription) LIKE LOWER('"+filter.getSubDescription()+"')";
		if (filter.getContact()!=null && !filter.getContact().equals(""))queryString+=" AND LOWER(p.contact) LIKE LOWER('"+filter.getContact()+"')";
		if (filter.getAreaDepartment()!=null && !filter.getAreaDepartment().equals(""))queryString+=" AND LOWER(p.areaDepartment) LIKE LOWER('"+filter.getAreaDepartment()+"')";
		if (filter.getAddress()!=null && !filter.getAddress().equals(""))queryString+=" AND LOWER(p.address) LIKE LOWER('"+filter.getAddress()+"')";
		if (filter.getCity()!=null && !filter.getCity().equals(""))queryString+=" AND LOWER(p.city) LIKE LOWER('"+filter.getCity()+"')";
		if (filter.getPhone()!=null && !filter.getPhone().equals(""))queryString+=" AND LOWER(p.phone) LIKE LOWER('"+filter.getPhone()+"')";
		if (filter.getClassis()!=null && !filter.getClassis().equals(""))queryString+=" AND p.classis "+filter.getClassis();
		if (filter.getState()!=null && !filter.getState().equals(""))queryString+=" AND LOWER(p.state.stateCostCenters) LIKE LOWER('"+filter.getState()+"')";
		if (filter.getComments()!=null && !filter.getComments().equals(""))queryString+=" AND LOWER(p.comments) LIKE LOWER('"+filter.getComments()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CostCentersCustomersFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM CostCentersCustomers p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("state"))
				 	queryString+= " ORDER BY p.state.stateCostCenters " +next.getDirection().toString();
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
	
	public List<CostCentersCustomers> findAllNoPage(Pageable pageable,
			CostCentersCustomersFilterInfo filt) throws Exception {
		filt.getCostCentersCustomersFilterList().add(filt);
		List<CostCentersCustomersFilterInfo> filters = filt.getCostCentersCustomersFilterList();
		String queryString="SELECT p FROM CostCentersCustomers p WHERE (((";
		int index=0;
		List<CostCentersCustomersFilterInfo> unionFilters= new ArrayList<CostCentersCustomersFilterInfo>();
		List<CostCentersCustomersFilterInfo> substractFilters= new ArrayList<CostCentersCustomersFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CostCentersCustomersFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CostCentersCustomersFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CostCentersCustomers p WHERE (":"");
		for (CostCentersCustomersFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("state"))
				 	queryString+= " ORDER BY p.state.stateCostCenters " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CostCentersCustomersFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getCostCentersCustomersFilterList().remove(filt.getCostCentersCustomersFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}