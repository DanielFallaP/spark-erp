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
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class ExchangeRateCustomRepoImpl implements ExchangeRateCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	@Override
	public List<ExchangeRate> findAllActiveByLocalCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ExchangeRate p WHERE p.active = true");
		return query.getResultList();
	}@Override
		public List<ExchangeRate> findByCodeName(String code) throws Exception {
			return null;
		}@Override
	public List<ExchangeRate> findAllActiveByForeignCurrency(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM ExchangeRate p WHERE p.active = true");
		return query.getResultList();
	}

	@Override
	public Page<ExchangeRate> findAll(Pageable pageable, ExchangeRateFilterInfo filter) throws Exception{
		Query query = buildCriteriaQuery(filter, pageable);
		Long count=(long) query.getResultList().size();
		List<ExchangeRate> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		return new PageImpl<ExchangeRate>(list, pageable, count);
	}
	@Override
	public List<ExchangeRate> findAllNoPage(Pageable pageable,
			ExchangeRateFilterInfo filter) throws Exception {
		Query query = buildCriteriaQuery(filter, null);
		return query.getResultList();
	}	
	
	public List<ExchangeRate> findAll(ExchangeRateFilterInfo filter) throws Exception{
		Query query = buildCriteriaQuery(filter, null);
		return query.getResultList();
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
}