package co.com.cybersoft.maintenance.tables.persistence.repository.third;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ThirdCustomRepoImpl implements ThirdCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<Third> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByCostCenter(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByCode(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByNameThird(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByContact(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByAddress(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByCountry(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByPhoneOne(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByPhoneTwo(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByEmail(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByComment(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByTypeRegime(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByExternalAccess(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}public List<Third> findAllActiveByKeyExternalAccess(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Third p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<Third> findAll(Pageable pageable, ThirdFilterInfo filt) throws Exception{
		filt.getThirdFilterList().add(filt);
		List<ThirdFilterInfo> filters = filt.getThirdFilterList();
		String queryString="SELECT p FROM Third p WHERE (((";
		int index=0;
		List<ThirdFilterInfo> unionFilters= new ArrayList<ThirdFilterInfo>();
		List<ThirdFilterInfo> substractFilters= new ArrayList<ThirdFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ThirdFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ThirdFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Third p WHERE (":"");
		for (ThirdFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("costcenter"))
				 	queryString+= " ORDER BY p.costCenter.nameCostCenter " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (ThirdFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getDateEntry()!=null && !filter.getDateEntry().equals(""))
			 query.setParameter("dateEntry"+index, df.parse(filter.getDateEntry().replace(CyberUtils.getOperator(filter.getDateEntry()), "")));
			if (filter.getDateRetirement()!=null && !filter.getDateRetirement().equals(""))
			 query.setParameter("dateRetirement"+index, df.parse(filter.getDateRetirement().replace(CyberUtils.getOperator(filter.getDateRetirement()), "")));


			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<Third> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getThirdFilterList().remove(filt.getThirdFilterList().size()-1);
		return new PageImpl<Third>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(ThirdFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCompany()!=null && !filter.getCompany().equals(""))queryString+=" AND LOWER(p.company.companyName) LIKE LOWER('"+filter.getCompany()+"')";
		if (filter.getCostCenter()!=null && !filter.getCostCenter().equals(""))queryString+=" AND LOWER(p.costCenter.nameCostCenter) LIKE LOWER('"+filter.getCostCenter()+"')";
		if (filter.getCode()!=null && !filter.getCode().equals(""))queryString+=" AND LOWER(p.code) LIKE LOWER('"+filter.getCode()+"')";
		if (filter.getNameThird()!=null && !filter.getNameThird().equals(""))queryString+=" AND LOWER(p.nameThird) LIKE LOWER('"+filter.getNameThird()+"')";
		if (filter.getTypeThird()!=null && !filter.getTypeThird().equals(""))queryString+=" AND p.typeThird "+filter.getTypeThird();
		if (filter.getContact()!=null && !filter.getContact().equals(""))queryString+=" AND LOWER(p.contact) LIKE LOWER('"+filter.getContact()+"')";
		if (filter.getAddress()!=null && !filter.getAddress().equals(""))queryString+=" AND LOWER(p.address) LIKE LOWER('"+filter.getAddress()+"')";
		if (filter.getCountry()!=null && !filter.getCountry().equals(""))queryString+=" AND LOWER(p.country) LIKE LOWER('"+filter.getCountry()+"')";
		if (filter.getPhoneOne()!=null && !filter.getPhoneOne().equals(""))queryString+=" AND LOWER(p.phoneOne) LIKE LOWER('"+filter.getPhoneOne()+"')";
		if (filter.getPhoneTwo()!=null && !filter.getPhoneTwo().equals(""))queryString+=" AND LOWER(p.phoneTwo) LIKE LOWER('"+filter.getPhoneTwo()+"')";
		if (filter.getEmail()!=null && !filter.getEmail().equals(""))queryString+=" AND LOWER(p.email) LIKE LOWER('"+filter.getEmail()+"')";
		if (filter.getDateEntry()!=null && !filter.getDateEntry().equals(""))queryString+=" AND p.dateEntry "+CyberUtils.getOperator(filter.getDateEntry())+" :dateEntry"+(index+offset)+" ";
		if (filter.getDateRetirement()!=null && !filter.getDateRetirement().equals(""))queryString+=" AND p.dateRetirement "+CyberUtils.getOperator(filter.getDateRetirement())+" :dateRetirement"+(index+offset)+" ";
		if (filter.getComment()!=null && !filter.getComment().equals(""))queryString+=" AND LOWER(p.comment) LIKE LOWER('"+filter.getComment()+"')";
		if (filter.getBigContributor()!=null && !filter.getBigContributor().equals(""))queryString+=" AND p.bigContributor "+filter.getBigContributor();
		if (filter.getAutorretenedor()!=null && !filter.getAutorretenedor().equals(""))queryString+=" AND p.autorretenedor "+filter.getAutorretenedor();
		if (filter.getTypeRegime()!=null && !filter.getTypeRegime().equals(""))queryString+=" AND LOWER(p.typeRegime) LIKE LOWER('"+filter.getTypeRegime()+"')";
		if (filter.getExternalAccess()!=null && !filter.getExternalAccess().equals(""))queryString+=" AND LOWER(p.externalAccess) LIKE LOWER('"+filter.getExternalAccess()+"')";
		if (filter.getKeyExternalAccess()!=null && !filter.getKeyExternalAccess().equals(""))queryString+=" AND LOWER(p.keyExternalAccess) LIKE LOWER('"+filter.getKeyExternalAccess()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(ThirdFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Third p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("costcenter"))
				 	queryString+= " ORDER BY p.costCenter.nameCostCenter " +next.getDirection().toString();
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
	
	public List<Third> findAllNoPage(Pageable pageable,
			ThirdFilterInfo filt) throws Exception {
		filt.getThirdFilterList().add(filt);
		List<ThirdFilterInfo> filters = filt.getThirdFilterList();
		String queryString="SELECT p FROM Third p WHERE (((";
		int index=0;
		List<ThirdFilterInfo> unionFilters= new ArrayList<ThirdFilterInfo>();
		List<ThirdFilterInfo> substractFilters= new ArrayList<ThirdFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ThirdFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ThirdFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Third p WHERE (":"");
		for (ThirdFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("costcenter"))
				 	queryString+= " ORDER BY p.costCenter.nameCostCenter " +next.getDirection().toString();
				else
				 queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (ThirdFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getDateEntry()!=null && !filter.getDateEntry().equals(""))
			 query.setParameter("dateEntry"+index, df.parse(filter.getDateEntry().replace(CyberUtils.getOperator(filter.getDateEntry()), "")));
			if (filter.getDateRetirement()!=null && !filter.getDateRetirement().equals(""))
			 query.setParameter("dateRetirement"+index, df.parse(filter.getDateRetirement().replace(CyberUtils.getOperator(filter.getDateRetirement()), "")));

			index++;
		}
		
		filt.getThirdFilterList().remove(filt.getThirdFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}