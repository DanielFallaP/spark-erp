package co.com.cybersoft.maintenance.tables.web.controller.effectFailTechnicalAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.web.domain.effectFailTechnicalAction.EffectFailTechnicalActionFilterInfo;


@Controller
@RequestMapping("/maintenance/effectFailTechnicalAction/export")
public class EffectFailTechnicalActionExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(EffectFailTechnicalActionModificationController.class);

	@Autowired
	private EffectFailTechnicalActionReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting EffectFailTechnicalAction to Excel");
		return reportingService.toExcel(EffectFailTechnicalAction.class.getCanonicalName(), EffectFailTechnicalActionDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(EffectFailTechnicalActionFilterInfo) (request.getSession().getAttribute("effectFailTechnicalActionFilter")!=null?request.getSession().getAttribute("effectFailTechnicalActionFilter"):new EffectFailTechnicalActionFilterInfo()));
	}
	
}