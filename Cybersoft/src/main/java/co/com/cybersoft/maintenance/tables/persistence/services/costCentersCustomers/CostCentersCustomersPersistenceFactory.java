package co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers;

import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCentersCustomersPersistenceFactory {

	CostCentersCustomersRepository costCentersCustomersRepository;
	
	public CostCentersCustomersPersistenceFactory(CostCentersCustomersRepository costCentersCustomersRepository){
		this.costCentersCustomersRepository=costCentersCustomersRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers getCostCentersCustomersByField(String field, String value){
		if (field.equals("description"))
					return costCentersCustomersRepository.findByDescription(value);if (field.equals("nameCostCenter"))
					return costCentersCustomersRepository.findByNameCostCenter(value);if (field.equals("subCostCentersCustomers"))
					return costCentersCustomersRepository.findBySubCostCentersCustomers(value);if (field.equals("subDescription"))
					return costCentersCustomersRepository.findBySubDescription(value);if (field.equals("contact"))
					return costCentersCustomersRepository.findByContact(value);if (field.equals("areaDepartment"))
					return costCentersCustomersRepository.findByAreaDepartment(value);if (field.equals("address"))
					return costCentersCustomersRepository.findByAddress(value);if (field.equals("city"))
					return costCentersCustomersRepository.findByCity(value);if (field.equals("phone"))
					return costCentersCustomersRepository.findByPhone(value);if (field.equals("comments"))
					return costCentersCustomersRepository.findByComments(value);		
		return null;
	}
}