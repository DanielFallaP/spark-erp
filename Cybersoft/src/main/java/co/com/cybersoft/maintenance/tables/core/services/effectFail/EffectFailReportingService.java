package co.com.cybersoft.maintenance.tables.core.services.effectFail;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailFilterInfo;

public interface EffectFailReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, EffectFailFilterInfo filter) throws Exception;
	
}