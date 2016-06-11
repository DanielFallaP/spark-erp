package co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate;

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
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ExchangeRateCustomRepoImpl implements ExchangeRateCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<ExchangeRate> findAllActiveByLocalCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ExchangeRate p WHERE p.active = true");
		return query.getResultList();
	}public List<ExchangeRate> findByCodeName(String code) throws Exception {
			return null;
		}public List<ExchangeRate> findAllActiveByForeignCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ExchangeRate p WHERE p.active = true");
		return query.getResultList();
	}

	public Page<ExchangeRate> findAll(Pageable pageable, ExchangeRateFilterInfo filter) throws Exception{
		filter.getExchangeRateFilterList().add(filter);
		List<ExchangeRateFilterInfo> filters = filter.getExchangeRateFilterList();
		String queryString="SELECT p FROM ExchangeRate p WHERE (";
		int index=0;
		for (ExchangeRateFilterInfo filt : filters) {
			queryString+=buildCriteriaQuery(filt, pageable, index);
			index++;
		}
		
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
		for (ExchangeRateFilterInfo filt : filters) {
			if (filt.getDateOfModification()!=null && !filt.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filt.getDateOfModification().replace(CyberUtils.getOperator(filt.getDateOfModification()), "")));
			if (filt.getDateOfCreation()!=null && !filt.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filt.getDateOfCreation().replace(CyberUtils.getOperator(filt.getDateOfCreation()), "")));
			if (filt.getDate()!=null && !filt.getDate().equals(""))
			 query.setParameter("date"+index, df.parse(filt.getDate().replace(CyberUtils.getOperator(filt.getDate()), "")));
			index++;
		}
		
		Long count=(long) query.getResultList().size();
		List<ExchangeRate> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filter.getExchangeRateFilterList().remove(filter.getExchangeRateFilterList().size()-1);
		return new PageImpl<ExchangeRate>(list, pageable, count);
	}

	private String buildCriteriaQuery(ExchangeRateFilterInfo filter, Pageable pageable, int index)throws Exception{
		String queryString=(index!=0?" OR (":"")+"1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getLocalCurrency()!=null && !filter.getLocalCurrency().equals(""))queryString+=" AND LOWER(p.localCurrency.code.currency) LIKE LOWER('"+filter.getLocalCurrency()+"')";
		if (filter.getForeignCurrency()!=null && !filter.getForeignCurrency().equals(""))queryString+=" AND LOWER(p.foreignCurrency.code.currency) LIKE LOWER('"+filter.getForeignCurrency()+"')";
		if (filter.getDate()!=null && !filter.getDate().equals(""))queryString+=" AND p.date "+CyberUtils.getOperator(filter.getDate())+" :date"+index+" ";
		if (filter.getExchangeRate()!=null && !filter.getExchangeRate().equals(""))queryString+=" AND p.exchangeRate "+filter.getExchangeRate();
		if (filter.getVariation()!=null && !filter.getVariation().equals(""))queryString+=" AND p.variation "+filter.getVariation();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+index+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+index;
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(ExchangeRateFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM ExchangeRate p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getLocalCurrency()!=null && !filter.getLocalCurrency().equals(""))queryString+=" AND LOWER(p.localCurrency.code.currency) LIKE LOWER('"+filter.getLocalCurrency()+"')";
		if (filter.getForeignCurrency()!=null && !filter.getForeignCurrency().equals(""))queryString+=" AND LOWER(p.foreignCurrency.code.currency) LIKE LOWER('"+filter.getForeignCurrency()+"')";
		if (filter.getDate()!=null && !filter.getDate().equals(""))queryString+=" AND p.date "+CyberUtils.getOperator(filter.getDate())+" :date ";
		if (filter.getExchangeRate()!=null && !filter.getExchangeRate().equals(""))queryString+=" AND p.exchangeRate "+filter.getExchangeRate();
		if (filter.getVariation()!=null && !filter.getVariation().equals(""))queryString+=" AND p.variation "+filter.getVariation();

	
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
		if (filter.getDate()!=null && !filter.getDate().equals(""))
		 query.setParameter("date", df.parse(filter.getDate().replace(CyberUtils.getOperator(filter.getDate()), "")));

		
		return query;
	}
	
	public List<ExchangeRate> findAllNoPage(Pageable pageable,
			ExchangeRateFilterInfo filter) throws Exception {
		filter.getExchangeRateFilterList().add(filter);
		List<ExchangeRateFilterInfo> filters = filter.getExchangeRateFilterList();
		String queryString="SELECT p FROM ExchangeRate p WHERE (";
		int index=0;
		for (ExchangeRateFilterInfo filt : filters) {
			queryString+=buildCriteriaQuery(filt, pageable, index);
			index++;
		}
		
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
		for (ExchangeRateFilterInfo filt : filters) {
			if (filt.getDateOfModification()!=null && !filt.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filt.getDateOfModification().replace(CyberUtils.getOperator(filt.getDateOfModification()), "")));
			if (filt.getDateOfCreation()!=null && !filt.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filt.getDateOfCreation().replace(CyberUtils.getOperator(filt.getDateOfCreation()), "")));
			if (filt.getDate()!=null && !filt.getDate().equals(""))
			 query.setParameter("date"+index, df.parse(filt.getDate().replace(CyberUtils.getOperator(filt.getDate()), "")));
			index++;
		}
		
		filter.getExchangeRateFilterList().remove(filter.getExchangeRateFilterList().size()-1);

		return query.getResultList();
	}	
}