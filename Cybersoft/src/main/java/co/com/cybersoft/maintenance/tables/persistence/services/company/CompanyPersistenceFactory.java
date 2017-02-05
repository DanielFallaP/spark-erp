package co.com.cybersoft.maintenance.tables.persistence.services.company;

import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyPersistenceFactory {

	CompanyRepository companyRepository;
	
	public CompanyPersistenceFactory(CompanyRepository companyRepository){
		this.companyRepository=companyRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Company getCompanyByField(String field, String value){
		if (field.equals("codeNit"))
					return companyRepository.findByCodeNit(value);if (field.equals("companyName"))
					return companyRepository.findByCompanyName(value);if (field.equals("companyLicense"))
					return companyRepository.findByCompanyLicense(value);if (field.equals("comment"))
					return companyRepository.findByComment(value);if (field.equals("userLoginCompany"))
					return companyRepository.findByUserLoginCompany(value);if (field.equals("userSendHistory"))
					return companyRepository.findByUserSendHistory(value);if (field.equals("stateShippingHistory"))
					return companyRepository.findByStateShippingHistory(value);if (field.equals("stateCompany"))
					return companyRepository.findByStateCompany(value);if (field.equals("codeOfWIN"))
					return companyRepository.findByCodeOfWIN(value);if (field.equals("inventoryConditionPermanent"))
					return companyRepository.findByInventoryConditionPermanent(value);if (field.equals("permanentInventoryWorkOrder"))
					return companyRepository.findByPermanentInventoryWorkOrder(value);		
		return null;
	}
}