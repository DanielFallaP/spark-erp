package co.com.cybersoft.maintenance.tables.core.services.contract;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;

public interface ContractReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ContractFilterInfo filter) throws Exception;
	
}