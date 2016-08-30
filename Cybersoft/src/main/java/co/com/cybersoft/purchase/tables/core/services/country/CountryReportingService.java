package co.com.cybersoft.purchase.tables.core.services.country;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;

public interface CountryReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CountryFilterInfo filter) throws Exception;
	
}