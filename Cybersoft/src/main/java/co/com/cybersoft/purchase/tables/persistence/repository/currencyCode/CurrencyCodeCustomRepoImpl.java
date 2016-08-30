package co.com.cybersoft.purchase.tables.persistence.repository.currencyCode;

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
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CurrencyCodeCustomRepoImpl implements CurrencyCodeCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	public List<CurrencyCode> findByContainingCurrencyName(
				String currencyNameSubstring) {
				Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE LOWER(p.currencyName) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
				query.setParameter("substring", currencyNameSubstring);
				return query.setMaxResults(10).getResultList();
		}
	
	public List<CurrencyCode> findAllActiveByCurrencyName(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveByCountry(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveByCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveBySymbol(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveByHex1(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveByHex2(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}public List<CurrencyCode> findAllActiveByHex3(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM CurrencyCode p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<CurrencyCode> findAll(Pageable pageable, CurrencyCodeFilterInfo filt) throws Exception{
		filt.getCurrencyCodeFilterList().add(filt);
		List<CurrencyCodeFilterInfo> filters = filt.getCurrencyCodeFilterList();
		String queryString="SELECT p FROM CurrencyCode p WHERE (((";
		int index=0;
		List<CurrencyCodeFilterInfo> unionFilters= new ArrayList<CurrencyCodeFilterInfo>();
		List<CurrencyCodeFilterInfo> substractFilters= new ArrayList<CurrencyCodeFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CurrencyCodeFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CurrencyCodeFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CurrencyCode p WHERE (":"");
		for (CurrencyCodeFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("country"))
				 	queryString+= " ORDER BY p.country.country " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CurrencyCodeFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));

			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<CurrencyCode> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCurrencyCodeFilterList().remove(filt.getCurrencyCodeFilterList().size()-1);
		return new PageImpl<CurrencyCode>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CurrencyCodeFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCurrencyName()!=null && !filter.getCurrencyName().equals(""))queryString+=" AND LOWER(p.currencyName) LIKE LOWER('"+filter.getCurrencyName()+"')";
		if (filter.getCountry()!=null && !filter.getCountry().equals(""))queryString+=" AND LOWER(p.country.country) LIKE LOWER('"+filter.getCountry()+"')";
		if (filter.getCurrency()!=null && !filter.getCurrency().equals(""))queryString+=" AND LOWER(p.currency) LIKE LOWER('"+filter.getCurrency()+"')";
		if (filter.getSymbol()!=null && !filter.getSymbol().equals(""))queryString+=" AND LOWER(p.symbol) LIKE LOWER('"+filter.getSymbol()+"')";
		if (filter.getDec1()!=null && !filter.getDec1().equals(""))queryString+=" AND p.dec1 "+filter.getDec1();
		if (filter.getDec2()!=null && !filter.getDec2().equals(""))queryString+=" AND p.dec2 "+filter.getDec2();
		if (filter.getDec3()!=null && !filter.getDec3().equals(""))queryString+=" AND p.dec3 "+filter.getDec3();
		if (filter.getHex1()!=null && !filter.getHex1().equals(""))queryString+=" AND LOWER(p.hex1) LIKE LOWER('"+filter.getHex1()+"')";
		if (filter.getHex2()!=null && !filter.getHex2().equals(""))queryString+=" AND LOWER(p.hex2) LIKE LOWER('"+filter.getHex2()+"')";
		if (filter.getHex3()!=null && !filter.getHex3().equals(""))queryString+=" AND LOWER(p.hex3) LIKE LOWER('"+filter.getHex3()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CurrencyCodeFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM CurrencyCode p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				if (1==0){}
				else if (next.getProperty().toLowerCase().equals("country"))
				 	queryString+= " ORDER BY p.country.country " +next.getDirection().toString();
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
	
	public List<CurrencyCode> findAllNoPage(Pageable pageable,
			CurrencyCodeFilterInfo filt) throws Exception {
		filt.getCurrencyCodeFilterList().add(filt);
		List<CurrencyCodeFilterInfo> filters = filt.getCurrencyCodeFilterList();
		String queryString="SELECT p FROM CurrencyCode p WHERE (((";
		int index=0;
		List<CurrencyCodeFilterInfo> unionFilters= new ArrayList<CurrencyCodeFilterInfo>();
		List<CurrencyCodeFilterInfo> substractFilters= new ArrayList<CurrencyCodeFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CurrencyCodeFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CurrencyCodeFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from CurrencyCode p WHERE (":"");
		for (CurrencyCodeFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("country"))
				 	queryString+= " ORDER BY p.country.country " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CurrencyCodeFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			index++;
		}
		
		filt.getCurrencyCodeFilterList().remove(filt.getCurrencyCodeFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}