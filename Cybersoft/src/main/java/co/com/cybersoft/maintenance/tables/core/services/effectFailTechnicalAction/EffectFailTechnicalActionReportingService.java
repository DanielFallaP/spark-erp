package co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;

public interface EffectFailTechnicalActionReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, EffectFailTechnicalActionFilterInfo filter) throws Exception;
	
}