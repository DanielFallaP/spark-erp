package co.com.cybersoft.maintenance.tables.core.services.company;

import co.com.cybersoft.maintenance.tables.events.company.CreateCompanyEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyModificationEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.company.CompanyPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyServiceImpl implements CompanyService{

	private final CompanyPersistenceService companyPersistenceService;
	
	public CompanyServiceImpl(final CompanyPersistenceService companyPersistenceService){
		this.companyPersistenceService=companyPersistenceService;
	}
	
	/**
	 */
	public CompanyDetailsEvent createCompany(CreateCompanyEvent event ) throws Exception{
		return companyPersistenceService.createCompany(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception{
		return companyPersistenceService.requestCompanyPage(event);
	}

	public CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event ) throws Exception{
		return companyPersistenceService.requestCompanyDetails(event);
	}

	public CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception {
		return companyPersistenceService.modifyCompany(event);
	}
	
	public CompanyDetailsEvent requestOnlyRecord() throws Exception {
		return companyPersistenceService.getOnlyRecord();
	}
	
	public CompanyPageEvent requestCompanyFilterPage(RequestCompanyPageEvent event) throws Exception {
		return companyPersistenceService.requestCompanyFilterPage(event);
	}
	
	public CompanyPageEvent requestCompanyFilter(RequestCompanyPageEvent event) throws Exception {
		return companyPersistenceService.requestCompanyFilter(event);
	}
	

	public CompanyPageEvent requestAllByCodeNit(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByCodeNit(fields);
	}public CompanyPageEvent requestAllByCompanyName(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByCompanyName(fields);
	}public CompanyPageEvent requestAllByCompanyLicense(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByCompanyLicense(fields);
	}public CompanyPageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByComment(fields);
	}public CompanyPageEvent requestAllByUserLoginCompany(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByUserLoginCompany(fields);
	}public CompanyPageEvent requestAllByUserSendHistory(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByUserSendHistory(fields);
	}public CompanyPageEvent requestAllByStateShippingHistory(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByStateShippingHistory(fields);
	}public CompanyPageEvent requestAllByStateCompany(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByStateCompany(fields);
	}public CompanyPageEvent requestAllByCodeOfWIN(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByCodeOfWIN(fields);
	}public CompanyPageEvent requestAllByInventoryConditionPermanent(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByInventoryConditionPermanent(fields);
	}public CompanyPageEvent requestAllByPermanentInventoryWorkOrder(EmbeddedField... fields) throws Exception {
		return companyPersistenceService.requestAllByPermanentInventoryWorkOrder(fields);
	}
	
	
	
}