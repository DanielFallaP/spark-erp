package co.com.cybersoft.maintenance.tables.core.services.storeType;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeFilterInfo;

public interface StoreTypeReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, StoreTypeFilterInfo filter) throws Exception;
	
}