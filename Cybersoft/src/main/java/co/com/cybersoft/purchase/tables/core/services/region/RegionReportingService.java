package co.com.cybersoft.purchase.tables.core.services.region;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;

public interface RegionReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, RegionFilterInfo filter) throws Exception;
	
}