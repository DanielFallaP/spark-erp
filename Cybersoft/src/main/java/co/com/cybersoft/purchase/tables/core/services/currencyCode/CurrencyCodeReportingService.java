package co.com.cybersoft.purchase.tables.core.services.currencyCode;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;

public interface CurrencyCodeReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CurrencyCodeFilterInfo filter) throws Exception;
	
}