package co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;

public interface CostCentersCustomersReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CostCentersCustomersFilterInfo filter) throws Exception;
	
}