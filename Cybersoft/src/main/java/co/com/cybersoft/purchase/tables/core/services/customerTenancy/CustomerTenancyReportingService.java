package co.com.cybersoft.purchase.tables.core.services.customerTenancy;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.customerTenancy.CustomerTenancyFilterInfo;

public interface CustomerTenancyReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CustomerTenancyFilterInfo filter) throws Exception;
	
}