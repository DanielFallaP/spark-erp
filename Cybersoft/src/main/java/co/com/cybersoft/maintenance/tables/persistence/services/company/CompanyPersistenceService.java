package co.com.cybersoft.maintenance.tables.persistence.services.company;

import co.com.cybersoft.maintenance.tables.events.company.CreateCompanyEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyModificationEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CompanyPersistenceService {

	CompanyDetailsEvent createCompany(CreateCompanyEvent event) throws Exception;

	CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception;

	CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event) throws Exception;
	
	CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception;
	CompanyPageEvent requestCompanyFilterPage(RequestCompanyPageEvent event) throws Exception;
	CompanyPageEvent requestCompanyFilter(RequestCompanyPageEvent event) throws Exception;
	
	CompanyPageEvent requestAllByCodeNit(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByCompanyName(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByCompanyLicense(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByComment(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByUserLoginCompany(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByUserSendHistory(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByStateShippingHistory(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByStateCompany(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByCodeOfWIN(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByInventoryConditionPermanent(EmbeddedField... fields) throws Exception;
	CompanyPageEvent requestAllByPermanentInventoryWorkOrder(EmbeddedField... fields) throws Exception;

	
	
	CompanyDetailsEvent getOnlyRecord() throws Exception;
	
}