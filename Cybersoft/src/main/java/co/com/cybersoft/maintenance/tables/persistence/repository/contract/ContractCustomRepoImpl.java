package co.com.cybersoft.maintenance.tables.persistence.repository.contract;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ContractCustomRepoImpl implements ContractCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<Contract> findAllActiveByCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Contract p WHERE p.active = true");
		return query.getResultList();
	}public List<Contract> findAllActiveByDescription(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Contract p WHERE p.active = true");
		return query.getResultList();
	}public List<Contract> findAllActiveByResponsible(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Contract p WHERE p.active = true");
		return query.getResultList();
	}public List<Contract> findAllActiveByCostCenter(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Contract p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Contract p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<Contract> findAll(Pageable pageable, ContractFilterInfo filt) throws Exception{
		filt.getContractFilterList().add(filt);
		List<ContractFilterInfo> filters = filt.getContractFilterList();
		String queryString="SELECT p FROM Contract p WHERE (((";
		int index=0;
		List<ContractFilterInfo> unionFilters= new ArrayList<ContractFilterInfo>();
		List<ContractFilterInfo> substractFilters= new ArrayList<ContractFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ContractFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ContractFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Contract p WHERE (":"");
		for (ContractFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("responsible"))
				 	queryString+= " ORDER BY p.responsible.third.nameThird " +next.getDirection().toString();
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
		for (ContractFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getContractStartDate()!=null && !filter.getContractStartDate().equals(""))
			 query.setParameter("contractStartDate"+index, df.parse(filter.getContractStartDate().replace(CyberUtils.getOperator(filter.getContractStartDate()), "")));
			if (filter.getContractEndDate()!=null && !filter.getContractEndDate().equals(""))
			 query.setParameter("contractEndDate"+index, df.parse(filter.getContractEndDate().replace(CyberUtils.getOperator(filter.getContractEndDate()), "")));


			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<Contract> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getContractFilterList().remove(filt.getContractFilterList().size()-1);
		return new PageImpl<Contract>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(ContractFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
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
		if (filter.getContractStartDate()!=null && !filter.getContractStartDate().equals(""))queryString+=" AND p.contractStartDate "+CyberUtils.getOperator(filter.getContractStartDate())+" :contractStartDate"+(index+offset)+" ";
		if (filter.getContractEndDate()!=null && !filter.getContractEndDate().equals(""))queryString+=" AND p.contractEndDate "+CyberUtils.getOperator(filter.getContractEndDate())+" :contractEndDate"+(index+offset)+" ";
		if (filter.getYearContractValue()!=null && !filter.getYearContractValue().equals(""))queryString+=" AND p.yearContractValue "+filter.getYearContractValue();
		if (filter.getResponsible()!=null && !filter.getResponsible().equals(""))queryString+=" AND LOWER(p.responsible.third.nameThird) LIKE LOWER('"+filter.getResponsible()+"')";
		if (filter.getCostCenter()!=null && !filter.getCostCenter().equals(""))queryString+=" AND LOWER(p.costCenter.nameCostCenter) LIKE LOWER('"+filter.getCostCenter()+"')";

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(ContractFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Contract p WHERE 1=1";
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
				else if (next.getProperty().toLowerCase().equals("responsible"))
				 	queryString+= " ORDER BY p.responsible.third.nameThird " +next.getDirection().toString();
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
	
	public List<Contract> findAllNoPage(Pageable pageable,
			ContractFilterInfo filt) throws Exception {
		filt.getContractFilterList().add(filt);
		List<ContractFilterInfo> filters = filt.getContractFilterList();
		String queryString="SELECT p FROM Contract p WHERE (((";
		int index=0;
		List<ContractFilterInfo> unionFilters= new ArrayList<ContractFilterInfo>();
		List<ContractFilterInfo> substractFilters= new ArrayList<ContractFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (ContractFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (ContractFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Contract p WHERE (":"");
		for (ContractFilterInfo filter : substractFilters) {
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
				else if (next.getProperty().toLowerCase().equals("responsible"))
				 	queryString+= " ORDER BY p.responsible.third.nameThird " +next.getDirection().toString();
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
		for (ContractFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getContractStartDate()!=null && !filter.getContractStartDate().equals(""))
			 query.setParameter("contractStartDate"+index, df.parse(filter.getContractStartDate().replace(CyberUtils.getOperator(filter.getContractStartDate()), "")));
			if (filter.getContractEndDate()!=null && !filter.getContractEndDate().equals(""))
			 query.setParameter("contractEndDate"+index, df.parse(filter.getContractEndDate().replace(CyberUtils.getOperator(filter.getContractEndDate()), "")));

			index++;
		}
		
		filt.getContractFilterList().remove(filt.getContractFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}