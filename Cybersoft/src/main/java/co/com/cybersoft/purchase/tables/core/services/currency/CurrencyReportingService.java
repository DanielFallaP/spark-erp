package co.com.cybersoft.purchase.tables.core.services.currency;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;

public interface CurrencyReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CurrencyFilterInfo filter) throws Exception;
	
}