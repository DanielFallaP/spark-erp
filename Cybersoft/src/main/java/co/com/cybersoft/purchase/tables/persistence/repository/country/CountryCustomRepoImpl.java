package co.com.cybersoft.purchase.tables.persistence.repository.country;

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
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CountryCustomRepoImpl implements CountryCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	public List<Country> findByContainingCountry(
				String countrySubstring) {
				Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE LOWER(p.country) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
				query.setParameter("substring", countrySubstring);
				return query.setMaxResults(10).getResultList();
		}public List<Country> findByContainingLocalName(
				String localNameSubstring) {
				Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE LOWER(p.localName) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
				query.setParameter("substring", localNameSubstring);
				return query.setMaxResults(10).getResultList();
		}public List<Country> findByContainingFrenchName(
				String frenchNameSubstring) {
				Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE LOWER(p.frenchName) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
				query.setParameter("substring", frenchNameSubstring);
				return query.setMaxResults(10).getResultList();
		}
	
	public List<Country> findAllActiveByRegion(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE p.active = true");
		return query.getResultList();
	}public List<Country> findAllActiveByCountry(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE p.active = true");
		return query.getResultList();
	}public List<Country> findAllActiveByLocalName(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE p.active = true");
		return query.getResultList();
	}public List<Country> findAllActiveByFrenchName(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Country p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<Country> findAll(Pageable pageable, CountryFilterInfo filt) throws Exception{
		filt.getCountryFilterList().add(filt);
		List<CountryFilterInfo> filters = filt.getCountryFilterList();
		String queryString="SELECT p FROM Country p WHERE (((";
		int index=0;
		List<CountryFilterInfo> unionFilters= new ArrayList<CountryFilterInfo>();
		List<CountryFilterInfo> substractFilters= new ArrayList<CountryFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CountryFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CountryFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Country p WHERE (":"");
		for (CountryFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("region"))
				 	queryString+= " ORDER BY p.region.region " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CountryFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<Country> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCountryFilterList().remove(filt.getCountryFilterList().size()-1);
		return new PageImpl<Country>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CountryFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getRegion()!=null && !filter.getRegion().equals(""))queryString+=" AND LOWER(p.region.region) LIKE LOWER('"+filter.getRegion()+"')";
		if (filter.getCountry()!=null && !filter.getCountry().equals(""))queryString+=" AND LOWER(p.country) LIKE LOWER('"+filter.getCountry()+"')";
		if (filter.getLocalName()!=null && !filter.getLocalName().equals(""))queryString+=" AND LOWER(p.localName) LIKE LOWER('"+filter.getLocalName()+"')";
		if (filter.getFrenchName()!=null && !filter.getFrenchName().equals(""))queryString+=" AND LOWER(p.frenchName) LIKE LOWER('"+filter.getFrenchName()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CountryFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Country p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("region"))
				 	queryString+= " ORDER BY p.region.region " +next.getDirection().toString();
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
	
	public List<Country> findAllNoPage(Pageable pageable,
			CountryFilterInfo filt) throws Exception {
		filt.getCountryFilterList().add(filt);
		List<CountryFilterInfo> filters = filt.getCountryFilterList();
		String queryString="SELECT p FROM Country p WHERE (((";
		int index=0;
		List<CountryFilterInfo> unionFilters= new ArrayList<CountryFilterInfo>();
		List<CountryFilterInfo> substractFilters= new ArrayList<CountryFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CountryFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CountryFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Country p WHERE (":"");
		for (CountryFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("region"))
				 	queryString+= " ORDER BY p.region.region " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CountryFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getCountryFilterList().remove(filt.getCountryFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}