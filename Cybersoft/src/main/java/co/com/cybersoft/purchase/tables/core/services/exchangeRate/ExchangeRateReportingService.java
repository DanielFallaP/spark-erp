package co.com.cybersoft.purchase.tables.core.services.exchangeRate;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

public interface ExchangeRateReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ExchangeRateFilterInfo filter) throws Exception;
	
}