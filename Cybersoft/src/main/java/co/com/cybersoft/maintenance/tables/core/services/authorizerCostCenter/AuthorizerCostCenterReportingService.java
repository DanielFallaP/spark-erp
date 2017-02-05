package co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterFilterInfo;

public interface AuthorizerCostCenterReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, AuthorizerCostCenterFilterInfo filter) throws Exception;
	
}