package co.com.cybersoft.maintenance.tables.core.services.accountant;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantFilterInfo;

public interface AccountantReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, AccountantFilterInfo filter) throws Exception;
	
}