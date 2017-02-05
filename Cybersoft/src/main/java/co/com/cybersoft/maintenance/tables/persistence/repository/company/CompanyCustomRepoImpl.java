package co.com.cybersoft.maintenance.tables.persistence.repository.company;

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
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CompanyCustomRepoImpl implements CompanyCustomRepo {

	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	
	
	public List<Company> findAllActiveByCodeNit(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByCompanyName(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByCompanyLicense(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByComment(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByUserLoginCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByUserSendHistory(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByStateShippingHistory(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByStateCompany(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByCodeOfWIN(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByInventoryConditionPermanent(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}public List<Company> findAllActiveByPermanentInventoryWorkOrder(EmbeddedField... fields) throws Exception {
		Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return query.getResultList();
	}
	
	public Long getTotalCount() throws Exception{
		Query query2 = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM Company p WHERE p.active = true");
		return (long) query2.getResultList().size();
	}

	public Page<Company> findAll(Pageable pageable, CompanyFilterInfo filt) throws Exception{
		filt.getCompanyFilterList().add(filt);
		List<CompanyFilterInfo> filters = filt.getCompanyFilterList();
		String queryString="SELECT p FROM Company p WHERE (((";
		int index=0;
		List<CompanyFilterInfo> unionFilters= new ArrayList<CompanyFilterInfo>();
		List<CompanyFilterInfo> substractFilters= new ArrayList<CompanyFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CompanyFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CompanyFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Company p WHERE (":"");
		for (CompanyFilterInfo filter : substractFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, index2, CyberUtils.filterSubstract);
			index++;
			if (index==substractFilters.size())
				queryString+=")";
		}

		queryString+=")";
		
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CompanyFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getHomeRangesOfDatesOfTheLastGeneration()!=null && !filter.getHomeRangesOfDatesOfTheLastGeneration().equals(""))
			 query.setParameter("homeRangesOfDatesOfTheLastGeneration"+index, df.parse(filter.getHomeRangesOfDatesOfTheLastGeneration().replace(CyberUtils.getOperator(filter.getHomeRangesOfDatesOfTheLastGeneration()), "")));
			if (filter.getEndDateRangesOfTheLastGeneration()!=null && !filter.getEndDateRangesOfTheLastGeneration().equals(""))
			 query.setParameter("endDateRangesOfTheLastGeneration"+index, df.parse(filter.getEndDateRangesOfTheLastGeneration().replace(CyberUtils.getOperator(filter.getEndDateRangesOfTheLastGeneration()), "")));
			if (filter.getStartDateCompanyProcess()!=null && !filter.getStartDateCompanyProcess().equals(""))
			 query.setParameter("startDateCompanyProcess"+index, df.parse(filter.getStartDateCompanyProcess().replace(CyberUtils.getOperator(filter.getStartDateCompanyProcess()), "")));
			if (filter.getEndDateProcessCompany()!=null && !filter.getEndDateProcessCompany().equals(""))
			 query.setParameter("endDateProcessCompany"+index, df.parse(filter.getEndDateProcessCompany().replace(CyberUtils.getOperator(filter.getEndDateProcessCompany()), "")));
			if (filter.getStartDateSendingHistory()!=null && !filter.getStartDateSendingHistory().equals(""))
			 query.setParameter("startDateSendingHistory"+index, df.parse(filter.getStartDateSendingHistory().replace(CyberUtils.getOperator(filter.getStartDateSendingHistory()), "")));
			if (filter.getEndingDatelSendingHistory()!=null && !filter.getEndingDatelSendingHistory().equals(""))
			 query.setParameter("endingDatelSendingHistory"+index, df.parse(filter.getEndingDatelSendingHistory().replace(CyberUtils.getOperator(filter.getEndingDatelSendingHistory()), "")));


			index++;
		}
		

		Long count=(long) query.getResultList().size();
		List<Company> list = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		filt.getCompanyFilterList().remove(filt.getCompanyFilterList().size()-1);
		return new PageImpl<Company>(list, pageable, count);
	}
	
	private String buildCriteriaQuery(CompanyFilterInfo filter, Pageable pageable, int index, int offset, String nature)throws Exception{
		String queryString="";

		if (index!=0)
				queryString=" OR (";
		else {
			queryString="";
		}
		queryString+="1=1 ";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (filter.getCode()!=null && !filter.getCode().equals(""))queryString+=" AND p.code "+filter.getCode();
		if (filter.getCodeNit()!=null && !filter.getCodeNit().equals(""))queryString+=" AND LOWER(p.codeNit) LIKE LOWER('"+filter.getCodeNit()+"')";
		if (filter.getCompanyName()!=null && !filter.getCompanyName().equals(""))queryString+=" AND LOWER(p.companyName) LIKE LOWER('"+filter.getCompanyName()+"')";
		if (filter.getCompanyLicense()!=null && !filter.getCompanyLicense().equals(""))queryString+=" AND LOWER(p.companyLicense) LIKE LOWER('"+filter.getCompanyLicense()+"')";
		if (filter.getComment()!=null && !filter.getComment().equals(""))queryString+=" AND LOWER(p.comment) LIKE LOWER('"+filter.getComment()+"')";
		if (filter.getHomeRangesOfDatesOfTheLastGeneration()!=null && !filter.getHomeRangesOfDatesOfTheLastGeneration().equals(""))queryString+=" AND p.homeRangesOfDatesOfTheLastGeneration "+CyberUtils.getOperator(filter.getHomeRangesOfDatesOfTheLastGeneration())+" :homeRangesOfDatesOfTheLastGeneration"+(index+offset)+" ";
		if (filter.getEndDateRangesOfTheLastGeneration()!=null && !filter.getEndDateRangesOfTheLastGeneration().equals(""))queryString+=" AND p.endDateRangesOfTheLastGeneration "+CyberUtils.getOperator(filter.getEndDateRangesOfTheLastGeneration())+" :endDateRangesOfTheLastGeneration"+(index+offset)+" ";
		if (filter.getUserLoginCompany()!=null && !filter.getUserLoginCompany().equals(""))queryString+=" AND LOWER(p.userLoginCompany) LIKE LOWER('"+filter.getUserLoginCompany()+"')";
		if (filter.getStartDateCompanyProcess()!=null && !filter.getStartDateCompanyProcess().equals(""))queryString+=" AND p.startDateCompanyProcess "+CyberUtils.getOperator(filter.getStartDateCompanyProcess())+" :startDateCompanyProcess"+(index+offset)+" ";
		if (filter.getEndDateProcessCompany()!=null && !filter.getEndDateProcessCompany().equals(""))queryString+=" AND p.endDateProcessCompany "+CyberUtils.getOperator(filter.getEndDateProcessCompany())+" :endDateProcessCompany"+(index+offset)+" ";
		if (filter.getNumberPartialWorkOrderCompany()!=null && !filter.getNumberPartialWorkOrderCompany().equals(""))queryString+=" AND p.numberPartialWorkOrderCompany "+filter.getNumberPartialWorkOrderCompany();
		if (filter.getTotalNumberofWorkOrderintheCompany()!=null && !filter.getTotalNumberofWorkOrderintheCompany().equals(""))queryString+=" AND p.totalNumberofWorkOrderintheCompany "+filter.getTotalNumberofWorkOrderintheCompany();
		if (filter.getUserSendHistory()!=null && !filter.getUserSendHistory().equals(""))queryString+=" AND LOWER(p.userSendHistory) LIKE LOWER('"+filter.getUserSendHistory()+"')";
		if (filter.getStateShippingHistory()!=null && !filter.getStateShippingHistory().equals(""))queryString+=" AND LOWER(p.stateShippingHistory) LIKE LOWER('"+filter.getStateShippingHistory()+"')";
		if (filter.getStartDateSendingHistory()!=null && !filter.getStartDateSendingHistory().equals(""))queryString+=" AND p.startDateSendingHistory "+CyberUtils.getOperator(filter.getStartDateSendingHistory())+" :startDateSendingHistory"+(index+offset)+" ";
		if (filter.getEndingDatelSendingHistory()!=null && !filter.getEndingDatelSendingHistory().equals(""))queryString+=" AND p.endingDatelSendingHistory "+CyberUtils.getOperator(filter.getEndingDatelSendingHistory())+" :endingDatelSendingHistory"+(index+offset)+" ";
		if (filter.getPartialNumberOfSendingHistory()!=null && !filter.getPartialNumberOfSendingHistory().equals(""))queryString+=" AND p.partialNumberOfSendingHistory "+filter.getPartialNumberOfSendingHistory();
		if (filter.getTotalNumberOfSendingHistory()!=null && !filter.getTotalNumberOfSendingHistory().equals(""))queryString+=" AND p.totalNumberOfSendingHistory "+filter.getTotalNumberOfSendingHistory();
		if (filter.getStateCompany()!=null && !filter.getStateCompany().equals(""))queryString+=" AND LOWER(p.stateCompany) LIKE LOWER('"+filter.getStateCompany()+"')";
		if (filter.getCodeOfWIN()!=null && !filter.getCodeOfWIN().equals(""))queryString+=" AND LOWER(p.codeOfWIN) LIKE LOWER('"+filter.getCodeOfWIN()+"')";
		if (filter.getInventoryConditionPermanent()!=null && !filter.getInventoryConditionPermanent().equals(""))queryString+=" AND LOWER(p.inventoryConditionPermanent) LIKE LOWER('"+filter.getInventoryConditionPermanent()+"')";
		if (filter.getPermanentInventoryWorkOrder()!=null && !filter.getPermanentInventoryWorkOrder().equals(""))queryString+=" AND LOWER(p.permanentInventoryWorkOrder) LIKE LOWER('"+filter.getPermanentInventoryWorkOrder()+"')";
		if (filter.getLanguage()!=null && !filter.getLanguage().equals(""))queryString+=" AND p.language "+filter.getLanguage();
		if (filter.getActiveCompany()!=null && !filter.getActiveCompany().equals(""))queryString+=" AND p.activeCompany "+filter.getActiveCompany();

	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation"+(index+offset)+" ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification"+(index+offset);
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		return queryString+")";
	}

	private Query buildCriteriaQuery(CompanyFilterInfo filter, Pageable pageable)throws Exception{
		String queryString="SELECT p FROM Company p WHERE 1=1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))queryString+=" AND p.dateOfCreation "+CyberUtils.getOperator(filter.getDateOfCreation())+" :dateOfCreation ";
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))queryString+=" AND p.dateOfModification "+CyberUtils.getOperator(filter.getDateOfModification())+" :dateOfModification";
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))queryString+=" AND LOWER(p.userName) LIKE LOWER('"+filter.getUserName()+"')";
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))queryString+=" AND LOWER(p.createdBy) LIKE LOWER('"+filter.getCreatedBy()+"')";
	
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
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
	
	public List<Company> findAllNoPage(Pageable pageable,
			CompanyFilterInfo filt) throws Exception {
		filt.getCompanyFilterList().add(filt);
		List<CompanyFilterInfo> filters = filt.getCompanyFilterList();
		String queryString="SELECT p FROM Company p WHERE (((";
		int index=0;
		List<CompanyFilterInfo> unionFilters= new ArrayList<CompanyFilterInfo>();
		List<CompanyFilterInfo> substractFilters= new ArrayList<CompanyFilterInfo>();
		unionFilters.add(filters.get(0));
		
		for (CompanyFilterInfo filter : filters) {
			if (index!=0)
				if (filter.getFffilterNature().equals(CyberUtils.filterSubstract))
					substractFilters.add(filter);
				else
					unionFilters.add(filter);
			index++;
		}
		
		index=0;
		for (CompanyFilterInfo filter : unionFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, 0, CyberUtils.filterUnion);
			index++;
		}
		queryString+=")";
		
		int index2=index;
		index=0;
		queryString+=(substractFilters.size()>0?" AND p.id NOT IN (select p from Company p WHERE (":"");
		for (CompanyFilterInfo filter : substractFilters) {
			queryString+=buildCriteriaQuery(filter, pageable, index, index2, CyberUtils.filterSubstract);
			index++;
			if (index==substractFilters.size())
				queryString+=")";
		}
		
		queryString+=")";
		
		if (pageable!=null){
			while (pageable.getSort().iterator().hasNext()){
				Order next = pageable.getSort().iterator().next();
				queryString+= " ORDER BY p."+next.getProperty() + " " +next.getDirection().toString();
				break;
			}
		}
		
		Query query = emFactory.getObject().createEntityManager().createQuery(queryString);
		index=0;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (CompanyFilterInfo filter : filters) {
			if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))
				query.setParameter("dateOfModification"+index, df.parse(filter.getDateOfModification().replace(CyberUtils.getOperator(filter.getDateOfModification()), "")));
			if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))
				query.setParameter("dateOfCreation"+index, df.parse(filter.getDateOfCreation().replace(CyberUtils.getOperator(filter.getDateOfCreation()), "")));
			if (filter.getHomeRangesOfDatesOfTheLastGeneration()!=null && !filter.getHomeRangesOfDatesOfTheLastGeneration().equals(""))
			 query.setParameter("homeRangesOfDatesOfTheLastGeneration"+index, df.parse(filter.getHomeRangesOfDatesOfTheLastGeneration().replace(CyberUtils.getOperator(filter.getHomeRangesOfDatesOfTheLastGeneration()), "")));
			if (filter.getEndDateRangesOfTheLastGeneration()!=null && !filter.getEndDateRangesOfTheLastGeneration().equals(""))
			 query.setParameter("endDateRangesOfTheLastGeneration"+index, df.parse(filter.getEndDateRangesOfTheLastGeneration().replace(CyberUtils.getOperator(filter.getEndDateRangesOfTheLastGeneration()), "")));
			if (filter.getStartDateCompanyProcess()!=null && !filter.getStartDateCompanyProcess().equals(""))
			 query.setParameter("startDateCompanyProcess"+index, df.parse(filter.getStartDateCompanyProcess().replace(CyberUtils.getOperator(filter.getStartDateCompanyProcess()), "")));
			if (filter.getEndDateProcessCompany()!=null && !filter.getEndDateProcessCompany().equals(""))
			 query.setParameter("endDateProcessCompany"+index, df.parse(filter.getEndDateProcessCompany().replace(CyberUtils.getOperator(filter.getEndDateProcessCompany()), "")));
			if (filter.getStartDateSendingHistory()!=null && !filter.getStartDateSendingHistory().equals(""))
			 query.setParameter("startDateSendingHistory"+index, df.parse(filter.getStartDateSendingHistory().replace(CyberUtils.getOperator(filter.getStartDateSendingHistory()), "")));
			if (filter.getEndingDatelSendingHistory()!=null && !filter.getEndingDatelSendingHistory().equals(""))
			 query.setParameter("endingDatelSendingHistory"+index, df.parse(filter.getEndingDatelSendingHistory().replace(CyberUtils.getOperator(filter.getEndingDatelSendingHistory()), "")));

			index++;
		}
		
		filt.getCompanyFilterList().remove(filt.getCompanyFilterList().size()-1);
		
	
		return query.getResultList();
	}	
}