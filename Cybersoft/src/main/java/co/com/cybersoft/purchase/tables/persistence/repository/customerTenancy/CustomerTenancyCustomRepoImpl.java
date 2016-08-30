package co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy;

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
import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.web.domain.customerTenancy.CustomerTenancyFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CustomerTenancyCustomRepoImpl implements CustomerTenancyCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<CustomerTenancy> findAllActiveBySoftwareTradeMark(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return query.getResultList();
	}public List<CustomerTenancy> findAllActiveBySoftwareVersion(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return query.getResultList();
	}public List<CustomerTenancy> findAllActiveBySoftwareSerialNo(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return query.getResultList();
	}public List<CustomerTenancy> findAllActiveByLocalCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return query.getResultList();
	}public List<CustomerTenancy> findAllActiveByForeignCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CustomerTenancy p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<CustomerTenancy> findAll(Pageable pageable, CustomerTenancyFilterInfo filt) throws Exception{
		filt.getCustomerTenancyFilterList().add(filt);
		List<CustomerTenancyFilterInfo> filters = filt.getCustomerTenancyFilterList();
		String queryString="SELECT p FROM CustomerTenancy p WHERE (((";
		int index=0;
		List<CustomerTenancyFilterInfo> unionFilters= new ArrayList<CustomerTenancyFilterInfo>();
		List<CustomerTenancyFilterInfo> substractFilters= new ArrayList<CustomerTenancyFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CustomerTenancyFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CustomerTenancyFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CustomerTenancy p WHERE (":"");
		for (CustomerTenancyFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("localcurrency"))
				 	queryString+= " ORDER BY p.localCurrency.code.currency " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("foreigncurrency"))
				 	queryString+= " ORDER BY p.foreignCurrency.code.currency " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CustomerTenancyFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<CustomerTenancy> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCustomerTenancyFilterList().remove(filt.getCustomerTenancyFilterList().size()-1);
		return new PageImpl<CustomerTenancy>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CustomerTenancyFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getSoftwareTradeMark()!=null && !filter.getSoftwareTradeMark().equals(""))queryString+=" AND LOWER(p.softwareTradeMark) LIKE LOWER('"+filter.getSoftwareTradeMark()+"')";
		if (filter.getSoftwareVersion()!=null && !filter.getSoftwareVersion().equals(""))queryString+=" AND LOWER(p.softwareVersion) LIKE LOWER('"+filter.getSoftwareVersion()+"')";
		if (filter.getSoftwareSerialNo()!=null && !filter.getSoftwareSerialNo().equals(""))queryString+=" AND LOWER(p.softwareSerialNo) LIKE LOWER('"+filter.getSoftwareSerialNo()+"')";
		if (filter.getLocalCurrency()!=null && !filter.getLocalCurrency().equals(""))queryString+=" AND LOWER(p.localCurrency.code.currency) LIKE LOWER('"+filter.getLocalCurrency()+"')";
		if (filter.getForeignCurrency()!=null && !filter.getForeignCurrency().equals(""))queryString+=" AND LOWER(p.foreignCurrency.code.currency) LIKE LOWER('"+filter.getForeignCurrency()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CustomerTenancyFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM CustomerTenancy p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("localcurrency"))
				 	queryString+= " ORDER BY p.localCurrency.code.currency " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("foreigncurrency"))
				 	queryString+= " ORDER BY p.foreignCurrency.code.currency " +next.getDirection().toString();
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
	
	public List<CustomerTenancy> findAllNoPage(Pageable pageable,
			CustomerTenancyFilterInfo filt) throws Exception {
		filt.getCustomerTenancyFilterList().add(filt);
		List<CustomerTenancyFilterInfo> filters = filt.getCustomerTenancyFilterList();
		String queryString="SELECT p FROM CustomerTenancy p WHERE (((";
		int index=0;
		List<CustomerTenancyFilterInfo> unionFilters= new ArrayList<CustomerTenancyFilterInfo>();
		List<CustomerTenancyFilterInfo> substractFilters= new ArrayList<CustomerTenancyFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CustomerTenancyFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CustomerTenancyFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CustomerTenancy p WHERE (":"");
		for (CustomerTenancyFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("localcurrency"))
				 	queryString+= " ORDER BY p.localCurrency.code.currency " +next.getDirection().toString();
				else if (next.getProperty().toLowerCase().equals("foreigncurrency"))
				 	queryString+= " ORDER BY p.foreignCurrency.code.currency " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CustomerTenancyFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getCustomerTenancyFilterList().remove(filt.getCustomerTenancyFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}