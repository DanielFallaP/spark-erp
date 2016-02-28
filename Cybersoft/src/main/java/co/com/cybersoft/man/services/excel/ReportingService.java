package co.com.cybersoft.man.services.excel;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

public interface ReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ExchangeRateFilterInfo filter) throws Exception;
	
	String docToExcel(String className, String bodyClassName, Locale locale, Long id) throws Exception;
	
	File docToExcelFile(String className, String bodyClassName, Locale locale, Long id) throws Exception;
	
	void cleanupExcelDirectory() throws Exception;
}
