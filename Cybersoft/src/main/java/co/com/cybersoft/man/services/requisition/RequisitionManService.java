package co.com.cybersoft.man.services.requisition;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.docs.web.domain.requisitionDistribution.RequisitionDistributionInfo;

public interface RequisitionManService {
	void checkAndSendVerificationMessage(String requestingUser, RequisitionInfo requisition) throws Exception;
	RequisitionInfo checkRequisition(RequisitionInfo requisitionInfo) throws Exception;
	void checkDistributionCompletion(RequisitionDistributionInfo requisitionDistributionInfo) throws Exception;
}
