package co.com.cybersoft.purchase.tables.core.services.continent;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;

public interface ContinentReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ContinentFilterInfo filter) throws Exception;
	
}