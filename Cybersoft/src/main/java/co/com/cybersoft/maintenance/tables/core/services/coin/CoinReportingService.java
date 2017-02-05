package co.com.cybersoft.maintenance.tables.core.services.coin;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinFilterInfo;

public interface CoinReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CoinFilterInfo filter) throws Exception;
	
}