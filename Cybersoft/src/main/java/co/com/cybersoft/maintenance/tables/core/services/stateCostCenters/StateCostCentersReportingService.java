package co.com.cybersoft.maintenance.tables.core.services.stateCostCenters;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersFilterInfo;

public interface StateCostCentersReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, StateCostCentersFilterInfo filter) throws Exception;
	
}