package co.com.cybersoft.maintenance.tables.core.services.costingType;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeFilterInfo;

public interface CostingTypeReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CostingTypeFilterInfo filter) throws Exception;
	
}