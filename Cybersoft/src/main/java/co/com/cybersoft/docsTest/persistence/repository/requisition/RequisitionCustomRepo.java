package co.com.cybersoft.docsTest.persistence.repository.requisition;

import co.com.cybersoft.docsTest.persistence.domain.Requisition;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionCustomRepo {

	Requisition save(Requisition requisition) throws Exception;

}